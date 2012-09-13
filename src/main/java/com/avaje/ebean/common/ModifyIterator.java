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

import java.util.Iterator;

import com.avaje.ebean.bean.BeanCollection;

/**
 * Wraps an iterator for the purposes of notifying removals and additions to
 * the BeanCollection owner.
 * <p>
 * This is required for persisting ManyToMany objects. Additions and removals
 * become inserts and deletes to the intersection table.
 * </p>
 */
class ModifyIterator<E> implements Iterator<E> {

	private final BeanCollection<E> owner;

	private final Iterator<E> it;

	private E last;
	
	/**
	 * Create with an Owner and the underlying Iterator this wraps.
	 * <p>
	 * The owner is notified of the removals.
	 * </p>
	 */
	ModifyIterator(BeanCollection<E> owner, Iterator<E> it) {
		this.owner = owner;
		this.it = it;
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public E next() {
		last = it.next();
		return last;
	}

	public void remove() {
		owner.modifyRemoval(last);
		it.remove();
	}

}
