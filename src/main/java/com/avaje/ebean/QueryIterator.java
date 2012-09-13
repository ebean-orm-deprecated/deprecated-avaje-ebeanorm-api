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

import java.util.Iterator;

/**
 * Used to provide iteration over query results.
 * <p>
 * This can be used when you want to process a very large number of results and
 * means that you don't have to hold all the results in memory at once (unlike
 * findList(), findSet() etc where all the beans are held in the List or Set
 * etc).
 * </p>
 * 
 * <pre class="code">
 * 
 * Query&lt;Customer&gt; query = server.find(Customer.class)
 *  .fetch(&quot;contacts&quot;, new FetchConfig().query(2))
 *  .where().gt(&quot;id&quot;, 0)
 *  .orderBy(&quot;id&quot;)
 *  .setMaxRows(2);
 * 
 * QueryIterator&lt;Customer&gt; it = query.findIterate();
 * try {
 *     while (it.hasNext()) {
 *         Customer customer = it.next();
 *         // do something with customer... 
 *     }
 * } finally {
 *     // close the associated resources
 *     it.close();
 * }
 * </pre>
 * 
 * @author rbygrave
 * 
 * @param <T>
 *            the type of entity bean in the iteration
 */
public interface QueryIterator<T> extends Iterator<T>, java.io.Closeable {

    /**
     * Returns <tt>true</tt> if the iteration has more elements.
     */
    public boolean hasNext();

    /**
     * Returns the next element in the iteration.
     */
    public T next();

    /**
     * Remove is not allowed.
     */
    public void remove();

    /**
     * Close the underlying resources held by this iterator.
     */
    public void close();
}
