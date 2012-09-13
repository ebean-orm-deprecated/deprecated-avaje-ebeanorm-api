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

import java.util.ArrayList;
import java.util.List;

/**
 * JSON Array element.
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
public class JsonElementArray implements JsonElement {

    private final List<JsonElement> values = new ArrayList<JsonElement>();

    public List<JsonElement> getValues() {
        return values;
    }
    
    public void add(JsonElement value){
        values.add(value);
    }
    
    public String toString() {
        return values.toString();
    }

    public boolean isPrimitive() {
        return false;
    }

    public String toPrimitiveString() {
        return null;
    }

    private String[] split(String exp) {
    	int pos = exp.indexOf('.');
    	if (pos == -1){
    		return new String[]{exp, null};
    	}
		String exp0 = exp.substring(0, pos);
		String exp1 = exp.substring(pos+1);
		return new String[]{exp0, exp1};
    }
    
    public Object eval(String exp){
    	String[] e = split(exp);
    	return eval(e[0], e[1]);    
    }
    
    public int evalInt(String exp) {
    	String[] e = split(exp);
    	return evalInt(e[0], e[1]);    
    }

    public String evalString(String exp) {
    	String[] e = split(exp);
    	return evalString(e[0], e[1]);    
    }

    public boolean evalBoolean(String exp) {
	    // TODO Auto-generated method stub
	    return false;
    }

	private Object eval(String exp0, String exp1) {
    	if ("size".equals(exp0)){
    		return values.size();
    	}
    	if ("isEmpty".equals(exp0)){
    		return values.isEmpty();
    	}
    	int idx = Integer.parseInt(exp0);
    	JsonElement element = values.get(idx);
    	return element.eval(exp1);
    }

	private int evalInt(String exp0, String exp1) {
    	if ("size".equals(exp0)){
    		return values.size();
    	}
    	if ("isEmpty".equals(exp0)){
    		return values.isEmpty() ? 1 : 0;
    	}
    	int idx = Integer.parseInt(exp0);
    	JsonElement element = values.get(idx);
    	return element.evalInt(exp1);
    }

	private String evalString(String exp0, String exp1) {
    	if ("size".equals(exp0)){
    		return String.valueOf(values.size());
    	}
    	if ("isEmpty".equals(exp0)){
    		return String.valueOf(values.isEmpty());
    	}
    	int idx = Integer.parseInt(exp0);
    	JsonElement element = values.get(idx);
    	return element.evalString(exp1);
    }

    public String getString() {
    	return toString();
    }
    
}
