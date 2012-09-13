/**
 * Copyright (C) 2006  Authors
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
 * Query by Example expression.
 * <p>
 * Pass in an example entity and for each non-null scalar properties an
 * expression is added.
 * </p>
 * <p>
 * By Default this case sensitive, will ignore numeric zero values and
 * will use a Like for string values (you must put in your own wildcards).
 * </p>
 * <p>
 * To get control over the options you can create an ExampleExpression
 * and set those options such as case insensitive etc.
 * </p>
 * <pre class="code"> 
 *  // create an example bean and set the properties
 *  // with the query parameters you want
 * Customer example = new Customer();
 * example.setName("Rob%"); 
 * example.setNotes("%something%");
 * 
 * List&lt;Customer&gt; list = 
 * 		Ebean.find(Customer.class)
 * 		.where()
 * 		// pass the bean into the where() clause
 * 		.exampleLike(example)
 * 		// you can add other expressions to the same query
 * 		.gt("id", 2)
 * 		.findList();
 *  
 * </pre>
 * 
 * Similarly you can create an ExampleExpression
 * <pre>
 * Customer example = new Customer();
 * example.setName("Rob%"); 
 * example.setNotes("%something%");
 * 
 *  // create a ExampleExpression with more control 
 * ExampleExpression qbe = new ExampleExpression(example, true, LikeType.EQUAL_TO)
 * 		.includeZeros();
 *		
 * List&lt;Customer&gt; list = 
 * 		Ebean.find(Customer.class)
 * 		.where()
 * 		.add(qbe)
 * 		.findList();
 * </pre>
 * @author Rob Bygrave
 */
public interface ExampleExpression extends Expression {

	/**
	 * By calling this method zero value properties are going to be included in
	 * the expression.
	 * <p>
	 * By default numeric zero values are excluded as they can result from primitive
	 * int and long types.
	 * </p>
	 */
	public ExampleExpression includeZeros();

	/**
	 * Set case insensitive to true.
	 */
	public ExampleExpression caseInsensitive();

	/**
	 * Use startsWith expression for string properties.
	 */
	public ExampleExpression useStartsWith();

	/**
	 * Use contains expression for string properties.
	 */
	public ExampleExpression useContains();

	/**
	 * Use endsWith expression for string properties.
	 */
	public ExampleExpression useEndsWith();

	/**
	 * Use equal to expression for string properties.
	 */
	public ExampleExpression useEqualTo();

}