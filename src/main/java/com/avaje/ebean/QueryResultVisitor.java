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
