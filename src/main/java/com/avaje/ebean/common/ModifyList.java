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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.avaje.ebean.bean.BeanCollection;

/**
 * Wraps a List for the purposes of notifying removals and additions to
 * the BeanCollection owner.
 * <p>
 * This is required for persisting ManyToMany objects. Additions and removals
 * become inserts and deletes to the intersection table.
 * </p>
 * <p>
 * Note that this is created by a call to subList() on a BeanList.
 * Thats its only purpose really. BeanList holds the actual List. 
 * </p>
 */
class ModifyList<E> extends ModifyCollection<E> implements List<E> {

	/**
	 * The underlying list.
	 */
	private final List<E> list;
	
	/**
	 * Create with an Owner that is notified of any additions or deletions.
	 */
	ModifyList(BeanCollection<E> owner, List<E> list) {
		super(owner, list);
		this.list = list;
	}

	public void add(int index, E element) {
		list.add(index, element);
		owner.modifyAddition(element);
	}

	public boolean addAll(int index, Collection<? extends E> co) {
		if (list.addAll(index, co)) {
			Iterator<? extends E> it = co.iterator();
			while (it.hasNext()) {
				E o = it.next();
				owner.modifyAddition(o);
			}
			return true;
		}
		return false;
	}

	public E get(int index) {
		return list.get(index);
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public ListIterator<E> listIterator() {
		return new ModifyListIterator<E>(owner,list.listIterator());
	}

	public ListIterator<E> listIterator(int index) {
		return new ModifyListIterator<E>(owner,list.listIterator(index));
	}

	public E remove(int index) {
		E o = list.remove(index);
		owner.modifyRemoval(o);
		return o;
	}

	public E set(int index, E element) {
		E o = list.set(index, element);
		owner.modifyAddition(element);
		owner.modifyRemoval(o);
		return o;
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return new ModifyList<E>(owner, list.subList(fromIndex, toIndex));
	}
	
	
}
