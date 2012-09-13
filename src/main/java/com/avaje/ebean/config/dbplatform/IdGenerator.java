/**
 * Copyright (C) 2006  Robin Bygrave
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
package com.avaje.ebean.config.dbplatform;

import com.avaje.ebean.Transaction;


/**
 * Generates unique id's for objects. This occurs prior to the actual insert.
 * <p>
 * Note that many databases have sequences or auto increment features. These
 * can be used rather than an IdGenerator and are different in that they 
 * occur during an insert. IdGenerator is used to generate an id <em>BEFORE</em>
 * the actual insert.
 * </p>
 */
public interface IdGenerator {

    /**
     * The name of the default UUID generator.
     */
    public static final String AUTO_UUID = "auto.uuid";
    
    /**
     * Return the name of the IdGenerator. For sequences this is the sequence name.
     */
    public String getName();
    
    /**
     * Return true if this is a DB sequence.
     */
    public boolean isDbSequence();
    
    /**
     * return the next unique identity value.
     * <p>
     * Note the transaction passed in can be null.
     * </p>
     */
    public Object nextId(Transaction transaction);

    /**
     * Is called prior to inserting OneToMany's as an indication
     * that a number of beans are likely to need id's shortly.
     * <p>
     * Can be used as a performance optimisation to prefetch a number of Id's.
     * Especially when the allocateSize is very large.
     * </p>
     */
    public void preAllocateIds(int allocateSize);

}
