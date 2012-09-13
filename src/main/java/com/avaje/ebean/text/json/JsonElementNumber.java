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
 * JSON number element.
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
public class JsonElementNumber implements JsonElement {

    private final String value;
    
    public JsonElementNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public String toString() {
        return value;
    }

    public boolean isPrimitive() {
        return true;
    }

    public String toPrimitiveString() {
        return value;
    }

    public Object eval(String exp){
    	if (exp != null){
    		throw new IllegalArgumentException("expression ["+exp+"] not allowed on number");
    	}
    	return Double.parseDouble(value);
    }

    public int evalInt(String exp) {
    	if (exp != null){
    		throw new IllegalArgumentException("expression ["+exp+"] not allowed on number");
    	}
    	return Integer.parseInt(value);
    }

    public String evalString(String exp) {
    	if (exp != null){
    		throw new IllegalArgumentException("expression ["+exp+"] not allowed on number");
    	}
    	return value;
    }

    public boolean evalBoolean(String exp) {
    	if (exp != null){
    		throw new IllegalArgumentException("expression ["+exp+"] not allowed on number");
    	}
    	return Boolean.parseBoolean(value);
    }

    

}
