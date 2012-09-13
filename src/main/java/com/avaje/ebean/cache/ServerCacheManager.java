/**
 * Copyright (C) 2009 Robin Bygrave
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
package com.avaje.ebean.cache;

import com.avaje.ebean.EbeanServer;

/**
 * The cache service for server side caching of beans and query results.
 */
public interface ServerCacheManager {

	/**
	 * This method is called just after the construction of the
	 * ServerCacheManager.
	 * <p>
	 * The EbeanServer is provided so that cache implementations can make use of
	 * EbeanServer and BackgroundExecutor for automatically populating and
	 * background trimming of the cache.
	 * </p>
	 */
	public void init(EbeanServer server);

	public void setCaching(Class<?> beanType, boolean useCache);
	
	/**
	 * Return true if there is an active bean cache for this type of bean.
	 */
	public boolean isBeanCaching(Class<?> beanType);

	/**
	 * Return the cache for mapping natural keys to id values.
	 */
	public ServerCache getNaturalKeyCache(Class<?> beanType);
	
	/**
	 * Return the cache for beans of a particular type.
	 */
	public ServerCache getBeanCache(Class<?> beanType);

	public ServerCache getCollectionIdsCache(Class<?> beanType, String propertyName);

	/**
	 * Return the cache for query results of a particular type of bean.
	 */
	public ServerCache getQueryCache(Class<?> beanType);

	/**
	 * This clears both the bean and query cache for a given type.
	 */
	public void clear(Class<?> beanType);
	
	/**
	 * Clear all the caches.
	 */
	public void clearAll();

}
