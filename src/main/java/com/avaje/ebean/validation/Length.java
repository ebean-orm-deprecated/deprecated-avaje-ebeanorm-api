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
package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.LengthValidatorFactory;


/**
 * Validate the length of a String property.
 * <p>
 * You can only apply this to String properties.
 * </p>
 */
@ValidatorMeta(factory=LengthValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {

	/**
	 * The minimum length of a String. Defaults to 0.
	 * <p>
	 * Checks that the string length is greater than or equal to min.
	 * </p>
	 */
	int min() default 0;

	/**
	 * The maximum length of a String. Defaults to Integer.MAX_VALUE.
	 * <p>
	 * Checks that the string length is less than or equal to max.
	 * </p>
	 */
	int max() default Integer.MAX_VALUE;
}
