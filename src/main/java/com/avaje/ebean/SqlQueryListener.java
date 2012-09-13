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

/**
 * Provides a mechanism for processing a SqlQuery one SqlRow at a time.
 * <p>
 * This is useful when the query will return a large number of results and you
 * want to process the beans one at a time rather than have all of the beans in
 * memory at once.
 * </p>
 * <pre class="code">
 * SqlQueryListener listener = ...;
 *    
 * SqlQuery query  = Ebean.createSqlQuery(&quot;my.large.query&quot;);
 *    
 * // set the listener that will process each row one at a time
 * query.setListener(listener);
 *    
 * // execute the query. Note that the returned
 * // list will be empty ... so don't bother assigning it...
 * query.findList();
 * </pre>
 */
public interface SqlQueryListener {

	/**
	 * Process the bean that has just been read.
	 * <p>
	 * Note this bean will not be added to the List Set or Map.
	 * </p>
	 */
	public void process(SqlRow bean);
}
