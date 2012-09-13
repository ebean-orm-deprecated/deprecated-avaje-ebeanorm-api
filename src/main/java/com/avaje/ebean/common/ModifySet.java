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
package com.avaje.ebean.common;

import java.util.Set;

import com.avaje.ebean.bean.BeanCollection;

/**
 * Wraps a Set for the purposes of notifying removals and additions to
 * the BeanCollection owner.
 * <p>
 * This is required for persisting ManyToMany objects. Additions and removals
 * become inserts and deletes to the intersection table.
 * </p>
 */
class ModifySet<E> extends ModifyCollection<E> implements Set<E> {


	/**
	 * Create with an Owner that is notified of any additions or deletions.
	 */
	public ModifySet(BeanCollection<E> owner, Set<E> s) {
		super(owner, s);
	}
	
}
