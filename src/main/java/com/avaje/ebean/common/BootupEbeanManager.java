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

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;

/**
 * Creates the EbeanServer implementations. This is used by the Ebean singleton
 * to determine the implementation for each server name.
 * <p>
 * Note that on a remote client it is expected that this factory will return
 * EbeanServers that behave as a proxy using http or tcp sockets etc to talk to
 * the EbeanServer on the application server.
 * </p>
 */
public interface BootupEbeanManager {
	
	/**
	 * Create the EbeanServer for a given configuration.
	 * 
	 * @param configuration
	 *            The configuration information for this server.
	 */
	public EbeanServer createServer(ServerConfig configuration);

	/**
	 * Create an EbeanServer just using the name.
	 * <p>
	 * In this case the dataSource parameters etc will be defined on the global
	 * avaje.properties file.
	 * </p>
	 */
	public EbeanServer createServer(String name);

	/**
	 * Shutdown any Ebean wide resources such as clustering.
	 */
	public void shutdown();
}
