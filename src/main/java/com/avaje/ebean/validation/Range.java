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

import com.avaje.ebean.validation.factory.RangeValidatorFactory;

/**
 * Validate the numeric range of a property.
 * <p>
 * You can specify a min, max or both. If you specify only a max then this
 * effectively becomes a max only range test.
 * </p>
 */
@ValidatorMeta(factory = RangeValidatorFactory.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

  /**
   * The minimum value. Defaults to Long.MIN_VALUE.
   * <p>
   * If left at Long.MIN_VALUE then this really becomes a Max only validation
   * test.
   * </p>
   */
  long min() default Long.MIN_VALUE;

  /**
   * The maximum value. Defaults to Long.MAX_VALUE.
   * <p>
   * If left at Long.MAX_VALUE then this really becomes a Min only validation
   * test.
   * </p>
   */
  long max() default Long.MAX_VALUE;
}
