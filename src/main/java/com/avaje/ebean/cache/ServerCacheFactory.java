/**
 * Copyright (C) 2009  Robin Bygrave
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
 * Defines method for constructing caches for beans and queries.
 */
public interface ServerCacheFactory {

	/**
	 * Just after the ServerCacheFactory is constructed this method is called
	 * passing the EbeanServer.
	 * <p>
	 * This is so that a cache implementation can utilise the EbeanServer to
	 * populate itself or use the BackgroundExecutor service to schedule periodic
	 * cache trimming/cleanup.
	 * </p>
	 */
	public void init(EbeanServer ebeanServer);

	/**
	 * Create the cache for the given type with options.
	 */
	public ServerCache createCache(String cacheKey, ServerCacheOptions cacheOptions);

}
