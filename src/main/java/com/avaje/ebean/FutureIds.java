/**
 * Copyright (C) 2009 Authors
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
package com.avaje.ebean;

import java.util.List;
import java.util.concurrent.Future;

/**
 * FutureIds represents the result of a background query execution for the
 * Id's.
 * <p>
 * It extends the java.util.concurrent.Future with the ability to get the Id's
 * while the query is still executing in the background.
 * </p>
 * 
 * @author rbygrave
 */
public interface FutureIds<T> extends Future<List<Object>> {

	/**
	 * Returns the original query used to fetch the Id's.
	 */
	public Query<T> getQuery();
	
	/**
	 * Return the list of Id's which could be partially populated. 
	 * <p>
	 * That is the query getting the id's could still be running and
	 * adding id's to this list.
	 * </p>
	 * <p>
	 * To get the list of Id's ensuring the query has finished use
	 * the {@link Future#get()} method instead of this one.
	 * </p>
	 */
	public List<Object> getPartialIds();
}
