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
 * Used to process a query result one bean at a time via a callback to this
 * visitor.
 * <p>
 * If you wish to stop further processing return false from the accept method.
 * </p>
 * <p>
 * Unlike findList() and findSet() using a QueryResultVisitor does not require
 * all the beans in the query result to be held in memory at once. This makes
 * QueryResultVisitor useful for processing large queries.
 * </p>
 * <pre class="code">
 * 
 * Query&lt;Customer&gt; query = server.find(Customer.class)
 *  .fetch(&quot;contacts&quot;, new FetchConfig().query(2))
 *  .where().gt(&quot;id&quot;, 0)
 *  .orderBy(&quot;id&quot;)
 *  .setMaxRows(2);
 * 
 * query.findVisit(new QueryResultVisitor&lt;Customer&gt;() {
 * 
 *     public boolean accept(Customer customer) {
 *         // do something with customer
 *         System.out.println(&quot;-- visit &quot; + customer);
 *         return true;
 *     }
 * });
 * </pre>
 * 
 * @author rbygrave
 * 
 * @param <T>
 *            the type of entity bean being queried.
 */
public interface QueryResultVisitor<T> {

    /**
     * Process the bean and return true if you want to continue processing more
     * beans. Return false if you want to stop processing further.
     * 
     * @param bean
     *            the entity bean to process
     * @return true to continue processing or false to stop.
     */
    public boolean accept(T bean);
}
