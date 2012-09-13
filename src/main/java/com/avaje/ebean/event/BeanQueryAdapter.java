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
package com.avaje.ebean.event;

import com.avaje.ebean.config.ServerConfig;

/**
 * Objects extending this modify queries prior their execution.
 * <p>
 * This can be used to add expressions to a query - for example to enable
 * partitioning based on the user executing the query.
 * </p>
 * <p>
 * A BeanQueryAdapter is either found automatically via class path search or can
 * be added programmatically via {@link ServerConfig#add(BeanQueryAdapter)}.
 * </p>
 * <p>
 * Note that a BeanQueryAdapter should be thread safe (stateless) and if
 * registered automatically via class path search it needs to have a default
 * constructor.
 * </p>
 */
public interface BeanQueryAdapter {

	/**
	 * Return true if this adapter is interested in queries for the given entity
	 * type.
	 */
	public boolean isRegisterFor(Class<?> cls);
	
	/**
	 * Returns an int to to control the order in which BeanQueryAdapter are
	 * executed when there is multiple of them registered for a given entity
	 * type (class).
	 */
	public int getExecutionOrder();
	
	/**
	 * Modify the associated query prior to it being executed.
	 */
	public void preQuery(BeanQueryRequest<?> request);

}
