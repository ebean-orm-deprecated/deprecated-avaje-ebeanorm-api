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
 * Defines DB encryption support for encrypting and decrypting data using DB
 * encryption features.
 * <p>
 * As an alternative to using DB encryption you can encrypt/decrypt in java via
 * a special ScalarType but this has the limitation that you can't include that
 * property in query where clauses.
 * </p>
 * 
 * @author rbygrave
 */
public interface DbEncrypt {

//    /**
//     * Return the SQL for decrypting a column returning a VARCHAR.
//     */
//    public String getDecryptSql(String columnWithTableAlias);
//
//    /**
//     * Return the DB function with bind variables used to encrypt a VARCHAR
//     * value.
//     */
//    public String getEncryptBindSql();
    
    public DbEncryptFunction getDbEncryptFunction(int jdbcType);

    /**
     * Return the DB type that encrypted Strings are stored in.
     * <p>
     * This is VARCHAR for MySql and VARBINARY for most others.
     * </p>
     */
    public int getEncryptDbType();

    /**
     * Return true if the DB encrypt function binds the data before the key.
     */
    public boolean isBindEncryptDataFirst();
}