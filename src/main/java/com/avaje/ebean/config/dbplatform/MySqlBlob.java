/**
 * Copyright (C) 2009 Authors
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
 * Support for blob, mediumblob or longblob selection
 * based on the deployment length. 
 * <p>
 * If no deployment length is defined longblob is used.
 * </p>
 */
public class MySqlBlob extends DbType {

	private static final int POWER_2_16 = 65536;
	private static final int POWER_2_24 = 16777216;
	
	
	public MySqlBlob() {
		super("blob");
	}

	@Override
	public String renderType(int deployLength, int deployScale) {
		
		if (deployLength >= POWER_2_24){
			return "longblob";
		}
		if (deployLength >= POWER_2_16){
			return "mediumblob";
		}
		if (deployLength < 1){
			// length not explicitly defined
			return "longblob";
		}
		return "blob";
	}
	
	
}
