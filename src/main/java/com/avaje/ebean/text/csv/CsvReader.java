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
package com.avaje.ebean.text.csv;

import java.io.Reader;
import java.util.Locale;

import com.avaje.ebean.text.StringParser;

/**
 * Reads CSV data turning it into object graphs that you can be saved (inserted)
 * or processed yourself.
 * 
 * <p>
 * This first example doesn't use a {@link CsvCallback} and this means it will
 * automatically create a transaction, save the customers and commit the
 * transaction when successful.
 * </p>
 * 
 * <pre class="code">
 * try {
 * 	File f = new File(&quot;src/test/resources/test1.csv&quot;);
 * 
 * 	FileReader reader = new FileReader(f);
 * 
 * 	CsvReader&lt;Customer&gt; csvReader = Ebean.createCsvReader(Customer.class);
 * 
 * 	csvReader.setPersistBatchSize(20);
 * 
 * 	csvReader.addProperty(&quot;status&quot;);
 * 	// ignore the next property
 * 	csvReader.addIgnore();
 * 	csvReader.addProperty(&quot;name&quot;);
 * 	csvReader.addDateTime(&quot;anniversary&quot;, &quot;dd-MMM-yyyy&quot;);
 * 	csvReader.addProperty(&quot;billingAddress.line1&quot;);
 * 	csvReader.addProperty(&quot;billingAddress.city&quot;);
 * 	csvReader.addReference(&quot;billingAddress.country.code&quot;);
 * 
 * 	csvReader.process(reader);
 * 
 * } catch (Exception e) {
 * 	throw new RuntimeException(e);
 * }
 * </pre>
 * 
 * @author rbygrave
 * 
 * @param <T>
 *            the entity bean type
 */
public interface CsvReader<T> {

	/**
	 * Explicitly set the default Locale.
	 */
	public void setDefaultLocale(Locale defaultLocale);

	/**
	 * Set the default format to use for Time types.
	 */
	public void setDefaultTimeFormat(String defaultTimeFormat);

	/**
	 * Set the default format to use for Date types.
	 */
	public void setDefaultDateFormat(String defaultDateFormat);

	/**
	 * Set the default format to use for Timestamp types.
	 */
	public void setDefaultTimestampFormat(String defaultTimestampFormat);

	/**
	 * Set the batch size for using JDBC statement batching.
	 * <p>
	 * By default this is set to 20 and setting this to 1 will disable the use
	 * of JDBC statement batching.
	 * </p>
	 */
	public void setPersistBatchSize(int persistBatchSize);

	/**
	 * Set to true if there is a header row that should be ignored.
	 * <p>
	 * If addPropertiesFromHeader is true then all the properties are added
	 * using the default time,date and timestamp formats.
	 * <p>
	 * If you have a mix of dateTime formats you can not use this method and
	 * must add the properties yourself.
	 * </p>
	 */
	public void setHasHeader(boolean hasHeader, boolean addPropertiesFromHeader);

	/**
	 * Same as setHasHeader(true,true);
	 * <p>
	 * This will use a header to define all the properties to load using the
	 * default formats for time, date and datetime types.
	 * </p>
	 */
	public void setAddPropertiesFromHeader();

	/**
	 * Same as setHasHeader(true, false);
	 * <p>
	 * This indicates that there is a header but that it should be ignored.
	 * </p>
	 */
	public void setIgnoreHeader();

	/**
	 * Set the frequency with which a INFO message will be logged showing the
	 * progress of the processing. You might set this to 1000 or 10000 etc.
	 * <p>
	 * If this is not set then no INFO messages will be logged.
	 * </p>
	 */
	public void setLogInfoFrequency(int logInfoFrequency);

	/**
	 * Ignore the next column of data.
	 */
	public void addIgnore();

	/**
	 * Define the property which will be loaded from the next column of data.
	 * <p>
	 * This takes into account the data type of the property and handles the
	 * String to object conversion automatically.
	 * </p>
	 */
	public void addProperty(String propertyName);

	/**
	 * Define the next property to be a reference. This effectively means it
	 * represents a foreign key. For example, with an Address object a Country
	 * Code could be a reference.
	 */
	public void addReference(String propertyName);

	/**
	 * Define the next property and use a custom StringParser to convert the
	 * string content into the appropriate type for the property.
	 */
	public void addProperty(String propertyName, StringParser parser);

	/**
	 * Add a property with a custom Date/Time/Timestamp format using the default
	 * Locale. This will convert the string into the appropriate java type for
	 * the given property (Date, Calendar, SQL Date, Time, Timestamp, JODA etc).
	 */
	public void addDateTime(String propertyName, String dateTimeFormat);

	/**
	 * Add a property with a custom Date/Time/Timestamp format. This will
	 * convert the string into the appropriate java type for the given property
	 * (Date, Calendar, SQL Date, Time, Timestamp, JODA etc).
	 */
	public void addDateTime(String propertyName, String dateTimeFormat, Locale locale);

	/**
	 * Automatically create a transaction if required to process all the CSV
	 * content from the reader.
	 * <p>
	 * This will check for a current transaction. If there is no current
	 * transaction then one is started and will commit (or rollback) at the end
	 * of processing. This will also set the persistBatchSize on the
	 * transaction.
	 * </p>
	 */
	public void process(Reader reader) throws Exception;

	/**
	 * Process the CSV content passing the bean to the CsvCallback after each
	 * row.
	 * <p>
	 * This provides you with the ability to modify and process the bean.
	 * </p>
	 * <p>
	 * When using a CsvCallback the reader WILL NOT create a transaction or save
	 * the bean(s) for you. If you want to insert the processed beans you must
	 * create your own transaction and save the bean(s) yourself.
	 * </p>
	 */
	public void process(Reader reader, CsvCallback<T> callback) throws Exception;

}