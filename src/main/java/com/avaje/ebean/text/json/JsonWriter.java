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
 * The JSON Writer made available to JsonWriteBeanVisitor's so that you can
 * append your own JSON content into the output.
 * 
 * @see JsonWriteBeanVisitor
 * @see JsonWriteOptions#setRootPathVisitor(JsonWriteBeanVisitor)
 * @see JsonWriteOptions#setPathVisitor(String, JsonWriteBeanVisitor)
 * 
 * @author rbygrave
 */
public interface JsonWriter {

    /**
     * Use this to append some custom content into the JSON output.
     * 
     * @param key
     *            the json key
     * 
     * @param rawJsonValue
     *            raw json value
     */
    public void appendRawValue(String key, String rawJsonValue);

    public void appendQuoteEscapeValue(String key, String rawJsonValue);

}
