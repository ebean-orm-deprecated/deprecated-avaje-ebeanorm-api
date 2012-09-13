package com.avaje.ebean.validation.factory;

/**
 * Executes validation on a bean.
 * <p>
 * This can be assigned to a bean property or the bean itself.
 * </p>
 */
public interface Validator {

	/**
	 * Return a String key used to identify the validator.
	 * <p>
	 * This is used to identify the type of validation error and look up a
	 * localised error message.
	 * </p>
	 */
	public String getKey();

	/**
	 * Return attribute values of the validator for use in building a error
	 * message (such as the actual min and max for Length). This should return
	 * an empty array if there are no attributes (such as for NotNull).
	 */
	public Object[] getAttributes();

	/**
	 * Return true if the value is valid.
	 * 
	 * @param value
	 *            the property value being tested.
	 */
	public boolean isValid(Object value);
}
