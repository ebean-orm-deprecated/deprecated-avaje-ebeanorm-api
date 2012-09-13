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
 * Specify that the elements of a OneToMany are private owned.
 * <p>
 * This means that if they are removed from the List/Set/Map they will be
 * deleted when their parent object is saved.
 * </p>
 * <p>
 * This could also be described as deleting orphans - in that beans removed from
 * the List/Set/Map will be deleted automatically when the parent bean is saved.
 * They are considered 'orphans' when they have been removed from the collection
 * in that they are no longer associated/linked to their parent bean.
 * </p>
 */
@Target( { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivateOwned {

    /**
     * Set this to false if you don't want cascade REMOVE on this relationship.
     * <p>
     * That is, by default PrivateOwned implicitly adds a cascade REMOVE to the
     * relationship and if you don't want that you need to set this to false.
     * </p>
     */
    boolean cascadeRemove() default true;

};
