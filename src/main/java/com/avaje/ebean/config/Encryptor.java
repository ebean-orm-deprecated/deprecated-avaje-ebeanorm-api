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

/**
 * Used for Java side encryption of properties when DB encryption is not used.
 * <p>
 * By default this is used on non-varchar types such as Blobs.
 * </p>
 *  
 * @author rbygrave
 *
 */
public interface Encryptor {

    /**
     * Encrypt the data using the key.
     */
    public byte[] encrypt(byte[] data, EncryptKey key);

    /**
     * Decrypt the data using the key.
     */
    public byte[] decrypt(byte[] data, EncryptKey key);
    
    /**
     * Encrypt the formatted string value using a key.
     */
    public byte[] encryptString(String formattedValue, EncryptKey key);

    /**
     * Decrypt the data returning a formatted string value using a key.
     */
    public String decryptString(byte[] data, EncryptKey key);

}
