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
 * MySql aes_encrypt aes_decrypt based encryption support.
 * 
 * @author rbygrave
 */
public class MySqlDbEncrypt extends AbstractDbEncrypt {

    public MySqlDbEncrypt() {
        this.varcharEncryptFunction = new MyVarcharFunction();
        this.dateEncryptFunction = new MyDateFunction();
    }    
    
    private static class MyVarcharFunction implements DbEncryptFunction {

        public String getDecryptSql(String columnWithTableAlias) {
            return "CONVERT(AES_DECRYPT(" + columnWithTableAlias + ",?) USING UTF8)";
        }

        public String getEncryptBindSql() {
            return "AES_ENCRYPT(?,?)";
        }        
    }
    
    private static class MyDateFunction implements DbEncryptFunction {

        public String getDecryptSql(String columnWithTableAlias) {
            return "STR_TO_DATE(AES_DECRYPT(" + columnWithTableAlias + ",?),'%Y%d%m')";
        }

        public String getEncryptBindSql() {
            return "AES_ENCRYPT(DATE_FORMAT(?,'%Y%d%m'),?)";
        }        
    }
}
