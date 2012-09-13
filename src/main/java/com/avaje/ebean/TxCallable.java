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
package com.avaje.ebean;

/**
 * Execute a TxCallable in a Transaction scope.
 * <p>
 * Use this with the {@link Ebean#execute(TxCallable)} method.
 * </p>
 * <p>
 * Note that this is basically the same as TxRunnable except that it returns an
 * Object (and you specify the return type via generics).
 * </p>
 * <p>
 * See also {@link TxRunnable}.
 * </p>
 * 
 * <pre class="code">
 * Ebean.execute(new TxCallable&lt;String&gt;() {
 * 	public String call() {
 * 		User u1 = Ebean.find(User.class, 1);
 * 		User u2 = Ebean.find(User.class, 2);
 * 
 * 		u1.setName(&quot;u1 mod&quot;);
 * 		u2.setName(&quot;u2 mod&quot;);
 * 
 * 		Ebean.save(u1);
 * 		Ebean.save(u2);
 * 
 * 		return u1.getEmail();
 * 	}
 * });
 * </pre>
 * @see TxRunnable
 */
public interface TxCallable<T> {

	/**
	 * Execute the method within a transaction scope returning the result.
	 * <p>
	 * If you do not want to return a result you should look to use TxRunnable
	 * instead.
	 * </p>
	 */
	public T call();
}
