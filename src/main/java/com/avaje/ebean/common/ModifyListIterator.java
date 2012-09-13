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

import java.util.ListIterator;

import com.avaje.ebean.bean.BeanCollection;

/**
 * Wraps a ListIterator for the purposes of notifying removals and additions to
 * the BeanCollection owner.
 * <p>
 * This is required for persisting ManyToMany objects. Additions and removals
 * become inserts and deletes to the intersection table.
 * </p>
 */
class ModifyListIterator<E> implements ListIterator<E> {

	private final BeanCollection<E> owner;

	private final ListIterator<E> it;

	private E last;

	/**
	 * Create with an Owner that is notified of any additions or deletions.
	 */
	ModifyListIterator(BeanCollection<E> owner, ListIterator<E> it) {
		this.owner = owner;
		this.it = it;
	}

	public void add(E bean) {
		owner.modifyAddition(bean);
		last = null;
		it.add(bean);
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public boolean hasPrevious() {
		return it.hasPrevious();
	}

	public E next() {
		last = it.next();
		return last;
	}

	public int nextIndex() {
		return it.nextIndex();
	}

	public E previous() {
		last = it.previous();
		return last;
	}

	public int previousIndex() {
		return it.previousIndex();
	}

	public void remove() {
		owner.modifyRemoval(last);
		last = null;
		it.remove();
	}

	public void set(E o) {
		if (last == null) {
			// in theory this is not allowed
		} else {
			owner.modifyRemoval(last);
			owner.modifyAddition(o);
		}
		it.set(o);
	}

}
