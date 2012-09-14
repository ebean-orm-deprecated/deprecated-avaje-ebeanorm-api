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
