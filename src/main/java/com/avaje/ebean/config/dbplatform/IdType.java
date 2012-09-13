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
package com.avaje.ebean.config.dbplatform;

/**
 * The types of Identity generation that can be defined.
 */
public enum IdType {

	/**
	 * Use a Database Identity (autoincrement) to generate the identity.
	 */
	IDENTITY,
	
	/**
	 * Use a Database sequence to generate the identity.
	 * <p>
	 * Note: Some databases support getGeneratedKeys with sequences 
	 * and this then does not involve an extra statement to return 
	 * the id.
	 * </p>
	 */
	SEQUENCE,
	
	/**
	 * Use an IdGenerator to generate the identity (prior to insert).
	 * <p>
	 * Note: There is a IdGenerator for UUID's and it is automatically 
	 * assigned to id properties of type UUID.
	 * </p>
	 */
	GENERATOR;

}
