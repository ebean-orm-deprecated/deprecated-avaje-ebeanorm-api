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
 * For a timestamp property that is set to the datetime when the entity is
 * created/inserted.
 * <p>
 * An alternative to using this annotation would be to use insertable=false,
 * updateable=false with @Column and have the DB insert the current time
 * (default value on the DB column is SYSTIME etc).
 * </p>
 * <p>
 * The downside to this approach is that the inserted entity does not have the
 * timestamp value after the insert has occurred. You need to fetch the entity
 * back to get the inserted timestamp if you want to used it.
 * </p>
 * 
 * <pre class="code">
 * &#064;Column(insertable = false, updateable = false)
 * Timestamp cretimestamp;
 * </pre>
 */
@Target( { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatedTimestamp {


};
