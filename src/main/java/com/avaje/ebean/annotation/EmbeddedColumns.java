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
 * Specify property name to db column mapping for Embedded beans.
 * <p>
 * This is designed to be easier to use than the AttributeOverride annotation in
 * standard JPA.
 * </p>
 */
@Target( { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmbeddedColumns {

	/**
	 * A list of property names mapped to DB columns.
	 * <p>
	 * For example <code>currency=IN_CURR, amount=IN_AMOUNT</code>
	 * </p>
	 * <p>
	 * Where currency and amount are properties and IN_CURR and IN_AMOUNT are
	 * the respective DB columns these properties will be mapped to.
	 * </p>
	 */
	String columns() default "";

};
