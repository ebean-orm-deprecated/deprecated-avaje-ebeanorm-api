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

import java.sql.Types;

/**
 * Base type for DB platform specific Encryption.
 * <p>
 * DB specific classes that extend this need to set their specific encryption
 * functions for varchar, date and timestamp. If they are left null then that is
 * treated as though that data type can not be encrypted in the DB and will
 * instead use java client encryption.
 * </p>
 * 
 * @author rbygrave
 */
public abstract class AbstractDbEncrypt implements DbEncrypt {

    /**
     * The encryption function for all String types (VARCHAR, CLOB, LONGVARCHAR,
     * CHAR).
     */
    protected DbEncryptFunction varcharEncryptFunction;

    /**
     * The encryption function for all Date types (java.sql.Date, Joda Date
     * types).
     */
    protected DbEncryptFunction dateEncryptFunction;

    /**
     * The encryption function for all Timestamp types (java.sql.Timestamp,
     * java.util.Date, java.util.Calendar, Joda DateTime types etc).
     */
    protected DbEncryptFunction timestampEncryptFunction;

    /**
     * Return the DB encryption function for the given JDBC type.
     * <p>
     * Null is returned if DB encryption of the type is not supported.
     * </p>
     */
    public DbEncryptFunction getDbEncryptFunction(int jdbcType) {
        switch (jdbcType) {
        case Types.VARCHAR:
            return varcharEncryptFunction;
        case Types.CLOB:
            return varcharEncryptFunction;
        case Types.CHAR:
            return varcharEncryptFunction;
        case Types.LONGVARCHAR:
            return varcharEncryptFunction;

        case Types.DATE:
            return dateEncryptFunction;

        case Types.TIMESTAMP:
            return timestampEncryptFunction;

        default:
            return null;
        }
    }

    /**
     * Return the DB stored type for encrypted properties.
     */
    public int getEncryptDbType() {
        return Types.VARBINARY;
    }

    /**
     * Generally encrypt function binding the data before the key (except h2).
     */
    public boolean isBindEncryptDataFirst() {
        return true;
    }
}
