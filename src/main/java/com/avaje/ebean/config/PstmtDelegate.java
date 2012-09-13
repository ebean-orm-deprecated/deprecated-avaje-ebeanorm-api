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
package com.avaje.ebean.config;

import java.sql.PreparedStatement;

/**
 * Unwrap the PreparedStatement to get the specific underlying implementation.
 * <p>
 * This is used to handle specific JDBC driver issues. Typically this means
 * getting the OraclePreparedStatement to handle Oracle specific issues etc.
 * </p>
 * 
 * @author rbygrave
 */
public interface PstmtDelegate {

	/**
	 * Unwrap the PreparedStatement to get the specific underlying
	 * implementation.
	 * 
	 * @param pstmt
	 *            the PreparedStatement coming out of the connection pool
	 * @return the underlying PreparedStatement
	 */
	public PreparedStatement unwrap(PreparedStatement pstmt);
}
