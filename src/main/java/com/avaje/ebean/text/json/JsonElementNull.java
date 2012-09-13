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
package com.avaje.ebean.text.json;

/**
 * JSON null element.
 * <p>
 * You will only use the JsonElements when you register a JsonReadBeanVisitor.
 * The JSON elements that are not mapped to a bean property are made available
 * to the JsonReadBeanVisitor.
 * </p>
 * 
 * @see JsonReadBeanVisitor
 * 
 * @author rbygrave
 */
public class JsonElementNull implements JsonElement {

    public static final JsonElementNull NULL = new JsonElementNull();
    
    private JsonElementNull() {
    }

    public String getValue() {
        return "null";
    }
    
    public String toString() {
        return "json null";
    }

    public boolean isPrimitive() {
        return true;
    }

    public String toPrimitiveString() {
        return null;
    }

    public Object eval(String exp){
    	if (exp != null){
    		throw new IllegalArgumentException("expression ["+exp+"] not allowed on null");
    	}
    	return null;
    }

    public int evalInt(String exp) {
	    return 0;
    }

    public String evalString(String exp) {
	    return null;
    }

    public boolean evalBoolean(String exp) {
	    return false;
    }
    
}
