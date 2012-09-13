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
package com.avaje.ebean.text.json;

import java.util.Map;

/**
 * Provides for some custom handling of json content as it is read.
 * <p>
 * This visit method is called after all the known properties of the bean have
 * been processed. Any JSON elements that could not be mapped to known bean
 * properties are available in the unmapped Map.
 * </p>
 * 
 * @author rbygrave
 * 
 * @param <T>
 *            The type of entity bean
 */
public interface JsonReadBeanVisitor<T> {

    /**
     * Visit the bean that has just been processed.
     * <p>
     * This provides a method of customising the bean and processing any custom
     * JSON content.
     * </p>
     * 
     * @param bean
     *            the bean being processed
     * @param unmapped
     *            Map of any JSON elements that didn't map to known bean
     *            properties
     */
    public void visit(T bean, Map<String, JsonElement> unmapped);

}
