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

import java.lang.ref.WeakReference;
import java.util.HashSet;

/**
 * Collects profile information for a bean (or reference/proxy bean) at a
 * given AutoFetchNode.
 * <p>
 * The AutoFetchNode identifies the location of the bean in the object graph.
 * </p>
 * <p>
 * It has to use a weak reference so as to ensure that it does not stop the
 * associated bean from being garbage collected.
 * </p>
 */
public final class NodeUsageCollector {

	/**
	 * The point in the object graph for a specific query and call stack point.
	 */
	private final ObjectGraphNode node;
	
	/**
	 * Weak to allow garbage collection.
	 */
	private final WeakReference<NodeUsageListener> managerRef;

	/**
	 * The properties used at this profile point.
	 */
	private final HashSet<String> used = new HashSet<String>();

	/**
	 * set to true if the bean is modified (setter called)
	 */
	private boolean modified;

	/**
	 * The property that cause a reference to lazy load.
	 */
	private String loadProperty;

	public NodeUsageCollector(ObjectGraphNode node, WeakReference<NodeUsageListener> managerRef) {
		this.node = node;
		// weak to allow garbage collection.
		this.managerRef = managerRef;
	}

	/**
	 * The bean has been modified by a setter method.
	 */
	public void setModified() {
		modified = true;
	}

	/**
	 * Add the name of a property that has been used.
	 */
	public void addUsed(String property) {
		used.add(property);
	}

	/**
	 * The property that invoked a lazy load.
	 */
	public void setLoadProperty(String loadProperty) {
		this.loadProperty = loadProperty;
	}

	/**
	 * Publish the usage info to the manager.
	 */
	private void publishUsageInfo() {
		NodeUsageListener manager = managerRef.get();
		if (manager != null) {
			manager.collectNodeUsage(this);
		}
	}

	/**
	 * publish the collected usage information when garbage collection occurs.
	 */
	@Override
	protected void finalize() throws Throwable {
		publishUsageInfo();
		super.finalize();
	}

	/**
	 * Return the associated node which identifies the location in the object graph of the bean/reference.
	 */
	public ObjectGraphNode getNode() {
		return node;
	}
	
	/**
	 * Return true if no properties where used.
	 */
	public boolean isEmpty() {
		return used.isEmpty();
	}

	/**
	 * Return the set of used properties.
	 */
	public HashSet<String> getUsed() {
		return used;
	}

	/**
	 * Return true if the bean was modified by a setter.
	 */
	public boolean isModified() {
		return modified;
	}

	public String getLoadProperty() {
		return loadProperty;
	}

	public String toString() {
		return node + " read:" + used + " modified:" + modified;
	}
}
