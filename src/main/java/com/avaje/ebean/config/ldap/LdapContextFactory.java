package com.avaje.ebean.config.ldap;

import javax.naming.directory.DirContext;

/**
 * Factory for creating LDAP DirContext.
 * 
 * @author rbygrave
 */
public interface LdapContextFactory {

    /**
     * Create a LDAP context for searching and persisting to an LDAP data store. 
     */
    public DirContext createContext();
}
