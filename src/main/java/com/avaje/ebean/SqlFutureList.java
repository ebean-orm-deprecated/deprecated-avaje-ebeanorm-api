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
 * The SqlFutureList represents the result of a background SQL query execution.
 * 
 * <p>
 * It extends the java.util.concurrent.Future.
 * </p>
 *
 * <pre class="code">
 *  // create a query
 * String sql = ... ;
 * SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
 * 
 *  // execute the query in a background thread
 * SqlFutureList sqlFuture = sqlQuery.findFutureList();
 * 
 *  // do something else ... we will sleep
 * Thread.sleep(3000);
 * System.out.println("end of sleep");
 * 
 * if (!futureList.isDone()){
 * 	// we can cancel the query execution
 * 	futureList.cancel(true);
 * }
 * 
 * System.out.println("and... done:"+futureList.isDone());
 * 
 * if (!futureList.isCancelled()){
 * 	// wait for the query to finish and return the list
 * 	List&lt;SqlRow&gt; list = futureList.get();
 * 	System.out.println("list:"+list);
 * }
 * 
 * </pre>
 * 
 * @author rob
 *
 */
public interface SqlFutureList extends Future<List<SqlRow>> {

	public SqlQuery getQuery();
	
}
