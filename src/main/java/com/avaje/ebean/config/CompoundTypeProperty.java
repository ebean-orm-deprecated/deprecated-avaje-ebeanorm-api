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
 * Represents a Property of a Compound Value Object.
 * <p>
 * For each property in a {@link CompoundType} you need an implementation of
 * this CompoundTypeProperty interface.
 * 
 * </p>
 * 
 * @author rbygrave
 * 
 * @param <V>
 *            The type of the Compound value object
 * @param <P>
 *            The type of the property
 * 
 * @see CompoundType
 * @see ScalarTypeConverter
 */
public interface CompoundTypeProperty<V, P> {

    /**
     * The name of this property.
     */
    public String getName();

    /**
     * Return the property value from the containing compound value object.
     * 
     * @param valueObject
     *            the compound value object
     * @return the property value.
     */
    public P getValue(V valueObject);

    /**
     * This should <b>ONLY</b> be used when the persistence type is different
     * from the logical type returned. It most cases just return 0 and Ebean
     * will persist the logical type.
     * <p>
     * Typically this should be used when the logical type is long but the
     * persistence type is java.sql.Timestamp. In this case return
     * java.sql.Types.TIMESTAMP (rather than 0).
     * </p>
     * 
     * @return Return the java.sql.Type that you want to use to persist this
     *         property or 0 and Ebean will use the logical type.
     */
    public int getDbType();
}
