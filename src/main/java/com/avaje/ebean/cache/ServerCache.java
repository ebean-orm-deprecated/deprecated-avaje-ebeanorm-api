package com.avaje.ebean.cache;

import com.avaje.ebean.EbeanServer;

/**
 * Represents part of the "L2" server side cache.
 * <p>
 * This is used to cache beans or query results (bean collections).
 * </p>
 * <p>
 * There are 2 ServerCache's for each bean type. One is used as the 'bean cache'
 * which holds beans of a given type. The other is the 'query cache' holding
 * query results for a given type.
 * </p>
 * 
 * @author rbygrave
 */
public interface ServerCache {

  /**
   * Just after a cache is created this init method is called. This is so that a
   * cache implementation can make use of the BackgroundExecutor service to
   * trim/cleanup itself or use the EbeanServer to populate itself.
   * <p>
   * This method is called after the cache is constructed but before the cache
   * is made available for use.
   * </p>
   */
  public void init(EbeanServer ebeanServer);

  /**
   * Return the configuration options for this cache.
   */
  public ServerCacheOptions getOptions();

  /**
   * Update the configuration options for this cache.
   */
  public void setOptions(ServerCacheOptions options);

  /**
   * Return the value given the key.
   */
  public Object get(Object id);

  /**
   * Put the value in the cache with a given id.
   */
  public Object put(Object id, Object value);

  /**
   * Put the value in the cache but only if a matching value is not already in
   * the cache.
   */
  public Object putIfAbsent(Object id, Object value);

  /**
   * Remove a entry from the cache given its id.
   */
  public Object remove(Object id);

  /**
   * Clear all entries from the cache.
   * <p>
   * NOTE: Be careful using this method in that most of the time application
   * code should clear BOTH the bean and query caches at the same time. This can
   * be done via {@link ServerCacheManager#clear(Class)}.
   * </p>
   */
  public void clear();

  /**
   * Return the number of entries in the cache.
   */
  public int size();

  /**
   * Return the hit ratio the cache is currently getting.
   */
  public int getHitRatio();

  /**
   * Return statistics for the cache.
   * 
   * @param reset
   *          if true the statistics are reset.
   */
  public ServerCacheStatistics getStatistics(boolean reset);
}
