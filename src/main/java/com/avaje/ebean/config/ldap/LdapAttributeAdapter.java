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
