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

import java.sql.Connection;

/**
 * The Transaction Isolation levels.
 * <p>
 * These match those of java.sql.Connection with the addition of DEFAULT which
 * implies the configured default of the DataSource.
 * </p>
 * <p>
 * This can be used with TxScope to define transactional scopes to execute
 * method within.
 * </p>
 * 
 * @see TxScope
 */
public enum TxIsolation {

	/**
	 * Read Committed Isolation level. This is typically the default for most
	 * configurations.
	 */
	READ_COMMITED(Connection.TRANSACTION_READ_COMMITTED),

	/**
	 * Read uncommitted Isolation level.
	 */
	READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),

	/**
	 * Repeatable Read Isolation level.
	 */
	REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),

	/**
	 * Serializable Isolation level.
	 */
	SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE),

	/**
	 * No Isolation level.
	 */
	NONE(Connection.TRANSACTION_NONE),

	/**
	 * The default isolation level. This typically means the default that the
	 * DataSource is using or configured to use.
	 */
	DEFAULT(-1);

	final int level;

	private TxIsolation(int level) {
		this.level = level;
	}

	/**
	 * Return the level as per java.sql.Connection.
	 * <p>
	 * Note that -1 denotes the default isolation level.
	 * </p>
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Return the TxIsolation given the java.sql.Connection isolation level.
	 * <p>
	 * Note that -1 denotes the default isolation level.
	 * </p>
	 */
	public static TxIsolation fromLevel(int connectionIsolationLevel) {

		switch (connectionIsolationLevel) {
		case Connection.TRANSACTION_READ_UNCOMMITTED:
			return TxIsolation.READ_UNCOMMITTED;

		case Connection.TRANSACTION_READ_COMMITTED:
			return TxIsolation.READ_COMMITED;

		case Connection.TRANSACTION_REPEATABLE_READ:
			return TxIsolation.REPEATABLE_READ;

		case Connection.TRANSACTION_SERIALIZABLE:
			return TxIsolation.SERIALIZABLE;

		case Connection.TRANSACTION_NONE:
			return TxIsolation.NONE;

		case -1:
			return TxIsolation.DEFAULT;

		default:
			throw new RuntimeException("Unknown isolation level " + connectionIsolationLevel);
		}

	}
}
