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
 * Postgres pgp_sym_encrypt pgp_sym_decrypt based encryption support.
 * 
 * @author rbygrave
 */
public class PostgresDbEncrypt extends AbstractDbEncrypt {

    
    public PostgresDbEncrypt() {
        this.varcharEncryptFunction = new PgVarcharFunction();
        this.dateEncryptFunction = new PgDateFunction();
    }
        
    private static class PgVarcharFunction implements DbEncryptFunction {
        
        public String getDecryptSql(String columnWithTableAlias) {
            return "pgp_sym_decrypt(" + columnWithTableAlias + ",?)";
        }

        public String getEncryptBindSql() {
            return "pgp_sym_encrypt(?,?)";
        }
    }

    private static class PgDateFunction implements DbEncryptFunction {
        
        public String getDecryptSql(String columnWithTableAlias) {
            return "to_date(pgp_sym_decrypt(" + columnWithTableAlias + ",?),'YYYYMMDD')";
        }

        public String getEncryptBindSql() {
            return "pgp_sym_encrypt(to_char(?::date,'YYYYMMDD'),?)";
        }
    }
}
