package com.avaje.ebean;

/**
 * Administrative control over transaction logging at runtime.
 * <p>
 * Enables an administrator to change the amount of transaction logging that
 * occurs while the application is running.
 * </p>
 */
public interface AdminLogging {

  /**
   * Set the overall transaction logging level.
   */
  public void setLogLevel(LogLevel defaultLogLevel);

  /**
   * Return the overall transaction logging level.
   */
  public LogLevel getLogLevel();

  /**
   * Returns true if generated sql is logged to the console.
   */
  public boolean isDebugGeneratedSql();

  /**
   * Set to true to Log generated sql to the console.
   */
  public void setDebugGeneratedSql(boolean debugSql);

  /**
   * Return true if lazy loading should be debugged.
   */
  public boolean isDebugLazyLoad();

  /**
   * Set the debugging on lazy loading.
   */
  public void setDebugLazyLoad(boolean debugLazyLoad);

}