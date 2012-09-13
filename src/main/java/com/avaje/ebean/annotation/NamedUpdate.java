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
package com.avaje.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An Update statement for a particular entity bean type.
 * <p>
 * The update can either be a sql insert,update or delete statement with tables
 * and columns etc or the equivalent statement but with table names and columns
 * expressed as bean types and bean properties.
 * </p>
 */
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NamedUpdate {

	/**
	 * The name of the update.
	 */
	String name();

	/**
	 * The insert, update or delete statement.
	 */
	String update();

	/**
	 * Set this to false if you do not want the cache to be notified. If true
	 * the cache will invalidate appropriate objects from the cache (after a
	 * successful transaction commit).
	 */
	boolean notifyCache() default true;

};
