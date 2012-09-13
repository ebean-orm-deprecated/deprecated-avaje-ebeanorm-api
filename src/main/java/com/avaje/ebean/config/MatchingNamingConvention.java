/**
 * Copyright (C) 2009  Authors
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
package com.avaje.ebean.config;


/**
 * The JPA naming convention where column names match property names 
 * and table names match entity names.
 *
 * <p>
 * The JPA specification states that the in the case of no annotations the
 * name of the class will be take as the table name and the name of a property
 * will be taken as the name of the column.
 * </p>
 * @author emcgreal
 */
public class MatchingNamingConvention extends AbstractNamingConvention {

	/**
	 * Create with a sequence format of "{table}_seq".
	 */
	public MatchingNamingConvention() {
		super();
	}

	/**
	 * Instantiates with a specific format for DB sequences.
	 *
	 * @param sequenceFormat the sequence format
	 */
	public MatchingNamingConvention(String sequenceFormat) {
		super(sequenceFormat);
	}


	public String getColumnFromProperty(Class<?> beanClass, String propertyName){
		return propertyName;
	}


	public TableName getTableNameByConvention(Class<?> beanClass) {

		return new TableName(getCatalog(), getSchema(), beanClass.getSimpleName());
	}

	
	public String getPropertyFromColumn(Class<?> beanClass, String dbColumnName) {
		return dbColumnName;
	}
}
