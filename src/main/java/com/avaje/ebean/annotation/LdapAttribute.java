package com.avaje.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to define the mapping of a bean property to an Ldap attribute.
 * <p>
 * Similar to the JPA Column annotation with LDAP specific features.
 * </p>
 * 
 * @author rbygrave
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LdapAttribute {

  /**
   * Specify the Attribute name this property maps to.
   */
  String name() default "";

  /**
   * Specify a LdapAttributeAdpater which can be used for this specific property
   * to convert between the logical bean property value and the LDAP Attribute
   * value.
   */
  Class<?> adapter() default void.class;

  /**
   * Set this to false so the property is not included in an insert.
   */
  boolean insertable() default true;

  /**
   * Set this to false so the property is not included in an update.
   */
  boolean updatable() default true;

};
