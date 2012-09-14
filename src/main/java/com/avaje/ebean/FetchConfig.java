package com.avaje.ebean;

import java.io.Serializable;

/**
 * Defines the configuration options for a "query fetch" or a
 * "lazy loading fetch". This gives you the ability to use multiple smaller
 * queries to populate an object graph as opposed to a single large query.
 * <p>
 * The primary goal is to provide efficient ways of loading complex object
 * graphs avoiding SQL Cartesian product and issues around populating object
 * graphs that have multiple *ToMany relationships.
 * </p>
 * <p>
 * It also provides the ability to control the lazy loading queries (batch size,
 * selected properties and fetches) to avoid N+1 queries etc.
 * <p>
 * There can also be cases loading across a single OneToMany where 2 SQL queries
 * using Ebean FetchConfig.query() can be more efficient than one SQL query.
 * When the "One" side is wide (lots of columns) and the cardinality difference
 * is high (a lot of "Many" beans per "One" bean) then this can be more
 * efficient loaded as 2 SQL queries.
 * </p>
 * 
 * <pre class="code">
 * // Normal fetch join results in a single SQL query 
 * List&lt;Order&gt; list = Ebean.find(Order.class).join(&quot;details&quot;).findList();
 * 
 * // Find Orders join details using a single SQL query
 * </pre>
 * <p>
 * Example: Using a "query join" instead of a "fetch join" we instead use 2 SQL
 * queries
 * </p>
 * 
 * <pre class="code">
 * // This will use 2 SQL queries to build this object graph
 * List&lt;Order&gt; list = 
 *     Ebean.find(Order.class)
 *       .fetch(&quot;details&quot;, new FetchConfig().query())
 *       .findList();
 * 
 * // query 1)  find order 
 * // query 2)  find orderDetails where order.id in (?,?...) // first 100 order id's
 * </pre>
 * <p>
 * Example: Using 2 "query joins"
 * </p>
 * 
 * <pre class="code">
 * // This will use 3 SQL queries to build this object graph
 * List&lt;Order&gt; list = 
 *     Ebean.find(Order.class)
 *       .fetch(&quot;details&quot;, new JoinConfig().query())
 *       .fetch(&quot;customer&quot;, new JoinConfig().query(5))
 *       .findList();
 * 
 * // query 1) find order 
 * // query 2) find orderDetails where order.id in (?,?...) // first 100 order id's
 * // query 3) find customer where id in (?,?,?,?,?) // first 5 customers
 * </pre>
 * <p>
 * Example: Using "query joins" and partial objects
 * </p>
 * 
 * <pre class="code">
 * // This will use 3 SQL queries to build this object graph
 * List&lt;Order&gt; list = 
 *     Ebean.find(Order.class)
 *       .select(&quot;status, shipDate&quot;)
 *       .fetch(&quot;details&quot;, &quot;quantity, price&quot;, new FetchConfig().query())
 *       .fetch(&quot;details.product&quot;, &quot;sku, name&quot;)
 *       .fetch(&quot;customer&quot;, &quot;name&quot;, new FetchConfig().query(10))
 *       .fetch(&quot;customer.contacts&quot;)
 *       .fetch(&quot;customer.shippingAddress&quot;)
 *       .findList();
 * 
 * // query 1) find order (status, shipDate)
 * // query 2) find orderDetail (quantity, price) fetch product (sku, name) where order.id in (?,? ...)
 * // query 3) find customer (name) fetch contacts (*) fetch shippingAddress (*) where id in (?,?,?,?,?)
 * 
 * // Note: the fetch of &quot;details.product&quot; is automatically included into the 
 * //       fetch of &quot;details&quot;
 * //
 * // Note: the fetch of &quot;customer.contacts&quot; and &quot;customer.shippingAddress&quot; 
 * //       are automatically included in the fetch of &quot;customer&quot; 
 * </pre>
 * <p>
 * You can use query() and lazy together on a single join. The query is executed
 * immediately and the lazy defines the batch size to use for further lazy
 * loading (if lazy loading is invoked).
 * </p>
 * 
 * <pre class="code">
 * List&lt;Order&gt; list = 
 *     Ebean.find(Order.class)
 *       .fetch(&quot;customer&quot;, new FetchConfig().query(3).lazy(10))
 *       .findList();
 * 
 * // query 1) find order 
 * // query 2) find customer where id in (?,?,?) // first 3 customers 
 * // .. then if lazy loading of customers is invoked 
 * // .. use a batch size of 10 to load the customers
 * 
 * </pre>
 * 
 * <p>
 * Example of controlling the lazy loading query:
 * </p>
 * <p>
 * This gives us the ability to optimise the lazy loading query for a given use
 * case.
 * </p>
 * 
 * <pre class="code">
 * List&lt;Order&gt; list = Ebean.find(Order.class)
 *   .fetch(&quot;customer&quot;,&quot;name&quot;, new FetchConfig().lazy(5))
 *   .fetch(&quot;customer.contacts&quot;,&quotcontactName, phone, email&quot)
 *   .fetch(&quot;customer.shippingAddress&quot;)
 *   .where().eq(&quot;status&quot;,Order.Status.NEW)
 *   .findList();
 * 
 * // query 1) find order where status = Order.Status.NEW
 * //  
 * // .. if lazy loading of customers is invoked 
 * // .. use a batch size of 5 to load the customers 
 *  
 *       find  customer (name) 
 *       fetch contact (contactName, phone, email) 
 *       fetch shippingAddress (*) 
 *       where id in (?,?,?,?,?)
 * 
 * </pre>
 * 
 * @author mario
 * @author rbygrave
 */
public class FetchConfig implements Serializable {

  private static final long serialVersionUID = 1L;

  private int lazyBatchSize = -1;
	
	private int queryBatchSize = -1;
	
	private boolean queryAll;
	
	/**
	 * Construct the fetch configuration object.
	 */
	public FetchConfig() {
	}
	
	/**
	 * Specify that this path should be lazy loaded using 
	 * the default batch load size.
	 */
	public FetchConfig lazy() {
		this.lazyBatchSize = 0;
		return this;
	}

	/**
	 * Specify that this path should be lazy loaded with a specified batch size.
	 * 
	 * @param lazyBatchSize
	 *            the batch size for lazy loading
	 */
	public FetchConfig lazy(int lazyBatchSize) {
		this.lazyBatchSize = lazyBatchSize;
		return this;
	}

	/**
	 * Specify that this path should be loaded as a separate query
	 * (rather than as part of the main query).
	 * <p>
	 * This will use the default batch size for separate query which is 100.
	 * </p>
	 */
	public FetchConfig query() {
		this.queryBatchSize = 0;
        this.queryAll = true;
		return this;
	}

    /**
     * Specify that this path should be loaded as a separate query (rather than
     * as part of the main query).
     * <p>
     * The queryBatchSize is the number of parent id's that this separate query
     * will load per batch.
     * </p>
     * <p>
     * This will load all beans on this path eagerly.
     * </p>
     * 
     * @param queryBatchSize
     *            the batch size used to load beans on this path
     */
	public FetchConfig query(int queryBatchSize) {
		this.queryBatchSize = queryBatchSize;
		this.queryAll = true;
		return this;
	}

    /**
     * Similar to {@link #query(int)} but only fetches the first batch.
     * <p>
     * If there are more parent beans than the batch size then they will not be
     * loaded eagerly but instead use lazy loading.
     * </p>
     * 
     * @param queryBatchSize
     *            the number of parent beans this path is populated for
     */
    public FetchConfig queryFirst(int queryBatchSize) {
        this.queryBatchSize = queryBatchSize;
        this.queryAll = false;
        return this;
    }
	
	/**
	 * Return the batch size for lazy loading.
	 */
	public int getLazyBatchSize() {
		return lazyBatchSize;
	}

	/**
	 * Return the batch size for separate query load.
	 */
	public int getQueryBatchSize() {
		return queryBatchSize;
	}

    /**
     * Return true if the query fetch should fetch 'all' rather than just the
     * 'first' batch.
     */
    public boolean isQueryAll() {
        return queryAll;
    }
	
	
}
