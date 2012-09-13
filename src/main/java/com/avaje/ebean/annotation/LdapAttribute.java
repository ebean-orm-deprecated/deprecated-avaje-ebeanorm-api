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
 * Used to define the mapping of a bean property to an Ldap attribute.
 * <p>
 * Similar to the JPA Column annotation with LDAP specific features.
 * </p>
 * 
 * @author rbygrave
 */
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LdapAttribute {

    /**
     * Specify the Attribute name this property maps to.
     */
    String name() default "";

    /**
     * Specify a LdapAttributeAdpater which can be used for this specific
     * property to convert between the logical bean property value and the LDAP
     * Attribute value.
     */
    Class<?> adapter() default void.class;
    
    /**
     * Set this to false so the property is not included in an insert.
     */
    boolean insertable() default true;

    /**
     * Set this to false so the property is not included in an update.
     */
    boolean updatable() default true;
    
};
