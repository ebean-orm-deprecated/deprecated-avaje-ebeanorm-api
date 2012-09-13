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
package com.avaje.ebean.bean;

/**
 * Used to specify a listener to be notified when a BeanCollection is first
 * used.
 * <p>
 * To use this you can set a BeanCollectionTouched onto a BeanCollection before
 * it has been used. When the BeanCollection is first used by the client code
 * then the BeanCollectionTouched is notified. It can only be notified once.
 * </p>
 * 
 * @author rbygrave
 */
public interface BeanCollectionTouched {

	/**
	 * Notify the listener that the bean collection has been used.
	 */
	public void notifyTouched(BeanCollection<?> c);
}
