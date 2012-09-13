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
package com.avaje.ebean;

/**
 * Holds two values as the result of a difference comparison.
 */
public class ValuePair {

	final Object value1;
	
	final Object value2;
	
	public ValuePair(Object value1, Object value2){
		this.value1 = value1;
		this.value2 = value2;
	}
	
	/**
	 * Return the first value.
	 */
	public Object getValue1() {
		return value1;
	}

	/**
	 * Return the second value.
	 */
	public Object getValue2() {
		return value2;
	}
	
	public String toString(){
		return value1+","+value2;
	}
}
