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
package com.avaje.ebean.bean;

/**
 * Holds entity beans by there type and id.
 * <p>
 * This is used to ensure only one instance for a given entity type and id is
 * used to build object graphs from queries and lazy loading.
 * </p>
 */
public interface PersistenceContext {

	/**
	 * Put the entity bean into the PersistanceContext.
	 */
	public void put(Object id, Object bean);

	/**
	 * Put the entity bean into the PersistanceContext if one is not already
	 * present (for this id).
	 * <p>
	 * Returns an existing entity bean (if one is already there) and otherwise
	 * returns null.
	 * </p>
	 */
	public Object putIfAbsent(Object id, Object bean);

	/**
	 * Return an object given its type and unique id.
	 */
	public Object get(Class<?> beanType, Object uid);

	/**
	 * Clear all the references.
	 */
	public void clear();

	/**
	 * Clear all the references for a given type of entity bean.
	 */
	public void clear(Class<?> beanType);

	/**
	 * Clear the reference to a specific entity bean.
	 */
	public void clear(Class<?> beanType, Object uid);
	
    /**
     * Return the number of beans of the given type in the persistence context.
     */
    public int size(Class<?> beanType);

}
