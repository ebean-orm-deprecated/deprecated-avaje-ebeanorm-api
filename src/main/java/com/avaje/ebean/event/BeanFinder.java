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
package com.avaje.ebean.event;

import com.avaje.ebean.bean.BeanCollection;



/**
 * Used to override the finding implementation for a bean.
 * <p>
 * For beans that are not in a JDBC data source you can implement this handle
 * bean finding. For example, read a log file building each entry as a bean and
 * returning that.
 * </p>
 * <p>
 * There are a number of internal BeanFinders in Ebean to return meta
 * data from Ebean at runtime such as query execution statistics etc.
 * See the beans in com.avaje.ebean.meta and finders in com.avaje.ebean.server.meta.
 * </p>
 */
public interface BeanFinder<T> {
    
    /**
     * Find a bean using its id or unique predicate.
     */
    public T find(BeanQueryRequest<T> request);

    /**
     * Return a List, Set or Map for the given find request.
     * <p>
     * Note the returning object is cast to a List Set or Map so you do need to
     * get the return type right.
     * </p>
     */
    public BeanCollection<T> findMany(BeanQueryRequest<T> request);
    
    

}
