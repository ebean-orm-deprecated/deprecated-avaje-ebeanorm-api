/**
 * Copyright (C) 2006  Robin Bygrave
 * 
 * This file is part of Ebean.
 * 
 * Ebean is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *  
 * Ebean is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Ebean; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA  
 */
package com.avaje.ebean.config.dbplatform;

import com.avaje.ebean.BackgroundExecutor;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Oracle10 and greater specific platform.
 */
public class Oracle10Platform extends DatabasePlatform {

  public Oracle10Platform() {
    super();
    this.name = "oracle";
    this.dbEncrypt = new Oracle10DbEncrypt();

    this.sqlLimiter = new RownumSqlLimiter();

    // Not using getGeneratedKeys as instead we will
    // batch load sequences which enables JDBC batch execution
    dbIdentity.setSupportsGetGeneratedKeys(false);
    dbIdentity.setIdType(IdType.SEQUENCE);
    dbIdentity.setSupportsSequence(true);

    this.treatEmptyStringsAsNull = true;

    this.openQuote = "\"";
    this.closeQuote = "\"";

    booleanDbType = Types.INTEGER;
    dbTypeMap.put(Types.BOOLEAN, new DbType("number(1) default 0"));

    dbTypeMap.put(Types.INTEGER, new DbType("number", 10));
    dbTypeMap.put(Types.BIGINT, new DbType("number", 19));
    dbTypeMap.put(Types.REAL, new DbType("number", 19, 4));
    dbTypeMap.put(Types.DOUBLE, new DbType("number", 19, 4));
    dbTypeMap.put(Types.SMALLINT, new DbType("number", 5));
    dbTypeMap.put(Types.TINYINT, new DbType("number", 3));
    dbTypeMap.put(Types.DECIMAL, new DbType("number", 38));

    dbTypeMap.put(Types.VARCHAR, new DbType("varchar2", 255));

    dbTypeMap.put(Types.LONGVARBINARY, new DbType("blob"));
    dbTypeMap.put(Types.LONGVARCHAR, new DbType("clob"));
    dbTypeMap.put(Types.VARBINARY, new DbType("raw", 255));
    dbTypeMap.put(Types.BINARY, new DbType("raw", 255));

    dbTypeMap.put(Types.TIME, new DbType("timestamp"));

    dbDdlSyntax.setDropTableCascade("cascade constraints purge");
    dbDdlSyntax.setIdentity(null);
    dbDdlSyntax.setMaxConstraintNameLength(30);
  }

  @Override
  public IdGenerator createSequenceIdGenerator(BackgroundExecutor be, DataSource ds, String seqName, int batchSize) {

    return new OracleSequenceIdGenerator(be, ds, seqName, batchSize);
  }

  @Override
  protected String withForUpdate(String sql) {
    return sql + " for update";
  }
}
