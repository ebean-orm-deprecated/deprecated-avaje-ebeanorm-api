/**
 * Copyright (C) 2009 Authors
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
package com.avaje.ebean.config;

/**
 * API from creating and getting property values from an Immutable Compound
 * Value Object.
 * 
 * <p>
 * A Compound Value object should contain multiple properties that are stored
 * separately. If you only have a single scalar value you should instead look to
 * use {@link ScalarTypeConverter}.
 * </p>
 * <p>
 * For each property in the compound type you need to implement the
 * {@link CompoundTypeProperty} interface. These must be returned from
 * {@link #getProperties()} in the same order that the properties appear in the
 * constructor.
 * </p>
 * <p>
 * If your compound type is mutable then you should look to use the JPA Embedded
 * annotation instead of implementing this interface.
 * </p>
 * <p>
 * When using classpath search Ebean will detect and automatically register any
 * implementations of this interface (along with detecting the entity classes
 * etc).
 * </p>
 * 
 * @author rbygrave
 * 
 * @param <V>
 *            The type of the Value Object
 * 
 * @see ScalarTypeConverter
 */
public interface CompoundType<V> {

    /**
     * Create an instance of the compound type given its property values.
     */
    public V create(Object[] propertyValues);

    /**
     * Return the properties in the order they appear in the constructor.
     */
    public CompoundTypeProperty<V, ?>[] getProperties();
}
