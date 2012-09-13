/**
 * Copyright (C) 2011  Authors
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

import com.avaje.ebean.config.ServerConfig;

/**
 * Used to configure the server on startup.
 * <p>
 * Provides a simple way to construct and register multiple listeners and
 * adapters that need shared services without using DI.
 * </p>
 * 
 * @author Robin Bygrave
 */
public interface ServerConfigStartup {

	/**
	 * On starting configure the ServerConfig.
	 */
	public void onStart(ServerConfig serverConfig);

}
