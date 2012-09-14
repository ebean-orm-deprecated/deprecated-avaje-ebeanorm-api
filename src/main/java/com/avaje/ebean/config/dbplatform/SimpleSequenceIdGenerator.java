package com.avaje.ebean.config.dbplatform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import com.avaje.ebean.Transaction;

/**
 * A very simple Database sequence based IdGenerator.
 * <p>
 * One which batch requests sequence Id's would be better for performance.
 * </p>
 */
public class SimpleSequenceIdGenerator implements IdGenerator {

  private static final Logger logger = Logger.getLogger(SimpleSequenceIdGenerator.class.getName());

  private final String sql;

  private final DataSource dataSource;

  private final String seqName;

  /**
   * Construct given a dataSource and sql to return the next sequence value.
   */
  public SimpleSequenceIdGenerator(DataSource dataSource, String sql, String seqName) {
    this.dataSource = dataSource;
    this.sql = sql;
    this.seqName = seqName;
  }

  public String getName() {
    return seqName;
  }

  public boolean isDbSequence() {
    return true;
  }

  public void preAllocateIds(int batchSize) {
    // just ignore this
  }

  public Object nextId(Transaction t) {

    boolean useTxnConnection = t != null;

    Connection c = null;
    PreparedStatement pstmt = null;
    ResultSet rset = null;
    try {
      c = useTxnConnection ? t.getConnection() : dataSource.getConnection();
      pstmt = c.prepareStatement(sql);
      rset = pstmt.executeQuery();
      if (rset.next()) {
        int val = rset.getInt(1);
        return Integer.valueOf(val);
      } else {
        String m = "Always expecting 1 row from " + sql;
        throw new PersistenceException(m);
      }
    } catch (SQLException e) {
      throw new PersistenceException("Error getting sequence nextval", e);

    } finally {
      if (useTxnConnection) {
        closeResources(rset, pstmt, null);
      } else {
        closeResources(rset, pstmt, c);
      }
    }
  }

  private void closeResources(ResultSet rset, PreparedStatement pstmt, Connection c) {
    try {
      if (rset != null) {
        rset.close();
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error closing ResultSet", e);
    }
    try {
      if (pstmt != null) {
        pstmt.close();
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error closing PreparedStatement", e);
    }
    try {
      if (c != null) {
        c.close();
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, "Error closing Connection", e);
    }
  }

}
