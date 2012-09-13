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
package com.avaje.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify cache tuning for a specific entity type.
 * <p>
 * If this is not specified then the system default settings are used.
 * </p>
 */
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheTuning {

	/**
	 * The maximum size for the cache.
	 * <p>
	 * This defaults to 0 which means unlimited.
	 * </p>
	 */
	int maxSize() default 0;

	/**
	 * The maximum time (in seconds) that a cache entry is allowed to stay in
	 * the cache when it has not been accessed.
	 * <p>
	 * This defaults to 0 which means unlimited.
	 * </p>
	 */
	int maxIdleSecs() default 0;

	/**
	 * The maximum time (in seconds) a cache entry is allowed to stay in the
	 * cache.
	 * <p>
	 * This is not generally required as the cache entries are automatically
	 * evicted when related data changes are committed.
	 * </p>
	 * <p>
	 * This defaults to 0 which means unlimited.
	 * </p>
	 */
	int maxSecsToLive() default 0;
};
