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
 * FutureList represents the result of a background query execution that
 * will return a list of entities.
 * <p>
 * It extends the java.util.concurrent.Future with the ability to cancel the
 * query, check if it is finished and get the resulting list waiting for the
 * query to finish (ie. the standard features of java.util.concurrent.Future).
 * </p>
 * <p>
 * A simple example:
 * </p>
 * <pre class="code">
 *  // create a query to find all orders
 * Query&lt;Order&gt; query = Ebean.find(Order.class);
 * 
 *  // execute the query in a background thread
 *  // immediately returning the futureList
 * FutureList&lt;Order&gt; futureList = query.findFutureList();
 * 
 *  // do something else ... 
 * 
 * if (!futureList.isDone()){
 * 	// we can cancel the query execution. This will cancel
 *	// the underlying query if that is supported by the JDBC
 *	// driver and database
 * 	futureList.cancel(true);
 * }
 * 
 * 
 * if (!futureList.isCancelled()){
 * 	// wait for the query to finish and return the list
 * 	List&lt;Order&gt; list = futureList.get();
 * 	...
 * }
 * 
 * </pre>
 * 
 * @author rbygrave
 */
public interface FutureList<T> extends Future<List<T>> {

	/**
	 * Return the query that is being executed by a background thread.
	 */
	public Query<T> getQuery();
	
}
