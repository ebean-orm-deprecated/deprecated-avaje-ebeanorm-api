package com.avaje.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark an Entity as a Ldap Domain object.
 * 
 * @author rbygrave
 */
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LdapDomain {

    /**
     * Specify a base DN.
     */
    String baseDn() default "";
    
    /**
     * Specify a comma delimited list of object classes for this type of Ldap object.
     */
    String objectclass() default "";
};
