package com.avaje.ebean.config.dbplatform;

/**
 * Adds LIMIT OFFSET clauses to a SQL query.
 */
public class LimitOffsetSqlLimiter implements SqlLimiter {

  /**
   * LIMIT keyword.
   */
  private static final String LIMIT = "limit";

  /**
   * OFFSET keyword.
   */
  private static final String OFFSET = "offset";

  public SqlLimitResponse limit(SqlLimitRequest request) {

    StringBuilder sb = new StringBuilder(512);
    sb.append("select ");
    if (request.isDistinct()) {
      sb.append("distinct ");
    }

    sb.append(request.getDbSql());

    int firstRow = request.getFirstRow();
    int maxRows = request.getMaxRows();
    if (maxRows > 0) {
      maxRows = maxRows + 1;
    }

    sb.append(" ").append(NEW_LINE).append(LIMIT).append(" ");
    if (maxRows > 0) {
      sb.append(maxRows);
    }
    if (firstRow > 0) {
      sb.append(" ").append(OFFSET).append(" ");
      sb.append(firstRow);
    }

    String sql = request.getDbPlatform().completeSql(sb.toString(), request.getOrmQuery());

    return new SqlLimitResponse(sql, false);
  }

}
