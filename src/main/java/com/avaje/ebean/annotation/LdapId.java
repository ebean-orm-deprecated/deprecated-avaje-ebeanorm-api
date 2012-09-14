package com.avaje.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to define the ID property.
 * <p>
 * You can use this or alternatively the JPA Id annotation.
 * </p>
 * 
 * @author rbygrave
 */
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LdapId {
    
};
