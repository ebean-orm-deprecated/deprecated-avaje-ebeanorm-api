/**
 * Copyright (C) 2009  Robin Bygrave
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
 * Enables you to specify a value to use to persist for an enum value.
 * 
 * <pre class="code">
 *     public enum Status {
 *         &#064;EnumValue(&quot;N&quot;)
 *         NEW,
 *         
 *         &#064;EnumValue(&quot;A&quot;)
 *         ACTIVE,
 *         
 *         &#064;EnumValue(&quot;I&quot;)
 *         INACTIVE,
 *     }
 * 
 * </pre>
 * <p>
 * This is an alternative to using the JPA standard approach or Ebean's
 * {@link EnumMapping} annotation.
 * </p>
 * <p>
 * Note that if all the EnumValue values are parsable as Integers then Ebean
 * will persist and fetch them as integers - otherwise they will be persisted
 * and fetched as strings.
 * </p>
 */
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValue {

    /**
     * Specify the value to persist for a specific enum value.
     * <p>
     * If all the values are parsable as Integers then Ebean will persist and
     * fetch them as integers rather than strings.
     * </p>
     */
    String value();
};
