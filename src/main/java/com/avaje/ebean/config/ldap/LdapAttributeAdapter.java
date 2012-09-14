package com.avaje.ebean.config.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;

/**
 * Assigned to a property to convert between the logical bean property value and
 * the LDAP Attribute value.
 * <p>
 * This can be used when the normal LDAP type conversion does not do what you
 * require.
 * </p>
 * 
 * @author rbygrave
 */
public interface LdapAttributeAdapter {

  /**
   * Read the attribute and convert its value to the bean value.
   */
  public Object readAttribute(Attribute attribute) throws NamingException;

  /**
   * Read the bean property value and creating the appropriate attribute.
   */
  public Attribute createAttribute(Object beanPropertyValue);

}
