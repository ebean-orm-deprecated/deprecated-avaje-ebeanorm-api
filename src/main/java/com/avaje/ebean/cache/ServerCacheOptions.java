/**
 * Copyright (C) 2009 Robin Bygrave
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
package com.avaje.ebean.cache;

import com.avaje.ebean.annotation.CacheTuning;

/**
 * Options for controlling a cache.
 */
public class ServerCacheOptions {

	private int maxSize;
	private int maxIdleSecs;
	private int maxSecsToLive;

	/**
	 * Construct with no set options.
	 */
	public ServerCacheOptions() {
		
	}

	/**
	 * Create from the cacheTuning deployment annotation.
	 */
	public ServerCacheOptions(CacheTuning cacheTuning) {
		this.maxSize = cacheTuning.maxSize();
		this.maxIdleSecs = cacheTuning.maxIdleSecs();
		this.maxSecsToLive = cacheTuning.maxSecsToLive();
	}
	
	/**
	 * Create merging default options with the deployment specified ones.
	 */
	public ServerCacheOptions(ServerCacheOptions d){
		this.maxSize = d.getMaxSize();
		this.maxIdleSecs = d.getMaxIdleSecs();
		this.maxSecsToLive = d.getMaxIdleSecs();
	}
	
	/**
	 * Apply any settings from the default settings that have not
	 * already been specifically set.
	 */
	public void applyDefaults(ServerCacheOptions defaults){
		if (maxSize == 0) {
			maxSize = defaults.getMaxSize();
		}
		if (maxIdleSecs == 0) {
			maxIdleSecs = defaults.getMaxIdleSecs();
		}
		if (maxSecsToLive == 0) {
			maxSecsToLive = defaults.getMaxSecsToLive();
		}
	}
	
	/**
	 * Return a copy of this object.
	 */
	public ServerCacheOptions copy() {
		
		ServerCacheOptions copy = new ServerCacheOptions();
		copy.maxSize = maxSize;
		copy.maxIdleSecs = maxIdleSecs;
		copy.maxSecsToLive = maxSecsToLive;
		
		return copy;
	}
	
	/**
	 * Return the maximum cache size.
	 */
	public int getMaxSize() {
		return maxSize;
	}
	
	/**
	 * Set the maximum cache size.
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	/**
	 * Return the maximum idle time.
	 */
	public int getMaxIdleSecs() {
		return maxIdleSecs;
	}
	
	/**
	 * Set the maximum idle time.
	 */
	public void setMaxIdleSecs(int maxIdleSecs) {
		this.maxIdleSecs = maxIdleSecs;
	}
	
	/**
	 * Return the maximum time to live.
	 */
	public int getMaxSecsToLive() {
		return maxSecsToLive;
	}
	
	/**
	 * Set the maximum time to live.
	 */
	public void setMaxSecsToLive(int maxSecsToLive) {
		this.maxSecsToLive = maxSecsToLive;
	}
	
}
