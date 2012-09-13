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
package com.avaje.ebean.config.ldap;

/**
 * Used to configure LDAP support.
 * 
 * @author rbygrave
 * 
 */
public class LdapConfig {

    private LdapContextFactory contextFactory;

    private boolean vanillaMode;

    /**
     * Return the LDAP context factory.
     */
    public LdapContextFactory getContextFactory() {
        return contextFactory;
    }

    /**
     * Set the LDAP context factory.
     */
    public void setContextFactory(LdapContextFactory contextFactory) {
        this.contextFactory = contextFactory;
    }

    /**
     * Return true if by default LDAP queries should return 'vanilla' objects.
     */
    public boolean isVanillaMode() {
        return vanillaMode;
    }

    /**
     * Set to true if by default LDAP queries should return 'vanilla' objects.
     */
    public void setVanillaMode(boolean vanillaMode) {
        this.vanillaMode = vanillaMode;
    }

}
