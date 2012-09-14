package com.avaje.ebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Invalid value returned from validation rules.
 * <p>
 * An InvalidValue instance could be a simple Invalid Value or a container of
 * "child invalid values".
 * <p>
 * Validation can recurse your entity bean object graph just like save() and as
 * such a InvalidValue is actually a tree structure containing invalid values
 * from each of the properties of a bean (which in turn could contain invalid
 * values etc etc).
 * </p>
 * <p>
 * <b><i>To get the simple list of invalid values use ... </i></b>
 * {@link ValidationException#getErrors()} or {@link InvalidValue#getErrors()}
 * method.
 * </p>
 * <p>
 * Validation rules can fire automatically on Ebean.save() which you can turn on
 * or off. You can also use {@link Ebean#validate(Object)} to validate a bean or
 * {@link Ebean#validate(Object, String, Object)} to validate a specific
 * property.
 * </p>
 * <p>
 * InvalidValue objects can be retrieved via
 * {@link ValidationException#getErrors()} or via
 * {@link InvalidValue#getErrors()}
 * </p>
 * 
 * <pre class="code">
 *  ## turn on automatic validation for Ebean.save()
 * ebean.validation=true
 * 
 *  ## turn on automatic creation of not null validation
 *  ## and max length validation for varchar's based on 
 *  ## database meta data (no annotations required)
 * ebean.validation.autocreate=true
 * 
 * ## for more specific control... 
 * ## 
 * ## ebean.validation.autocreate.notnull=true
 * ## ebean.validation.autocreate.length=true
 * ## ebean.validation.autocreate.length.max=4000
 * ## NB: Only auto create length validation for
 * ## varchar columns less than 4000 characters
 * </pre>
 */
public class InvalidValue implements Serializable {

  private static final long serialVersionUID = 2408556605456324913L;

  private static final Object[] EMPTY = new Object[0];

  private final String beanType;

  private final String propertyName;

  private final String validatorKey;

  private final Object value;

  private final InvalidValue[] children;

  private final Object[] validatorAttributes;

  private String message;

  public InvalidValue(String validatorKey, String beanType, Object bean, InvalidValue[] children) {
    this.validatorKey = validatorKey;
    this.validatorAttributes = EMPTY;
    this.beanType = beanType;
    this.propertyName = null;
    this.value = bean;
    this.children = children;
  }

  public InvalidValue(String validatorKey, Object[] validatorAttributes, String beanType,
      String propertyName, Object value) {
    this.validatorKey = validatorKey;
    this.validatorAttributes = validatorAttributes;
    this.beanType = beanType;
    this.propertyName = propertyName;
    this.value = value;
    this.children = null;
  }

  public String getBeanType() {
    return beanType;
  }

  /**
   * Return the property name.
   */
  public String getPropertyName() {
    return propertyName;
  }

  /**
   * Return the key of the validator that caused this InvalidValue.
   */
  public String getValidatorKey() {
    return validatorKey;
  }

  /**
   * Returns the attributes of the validator. For example, this is the min, max
   * of the range validator.
   * <p>
   * If there are no attributes (like NotNull) then an empty array is returned.
   * </p>
   * <p>
   * These attributes can be used with the propertyName and validatorKey to
   * build a nice error message for the user.
   * </p>
   */
  public Object[] getValidatorAttributes() {
    return validatorAttributes;
  }

  /**
   * Return the value deemed invalid.
   */
  public Object getValue() {
    return value;
  }

  /**
   * Returns children when validation recurses.
   */
  public InvalidValue[] getChildren() {
    return children;
  }

  /**
   * Return a user error message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Set a user error message.
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Returns true if this represents a bean with a list of property errors.
   * <p>
   * Note that this is recursive (can follow associated beans or collections) so
   * some of the children could in fact be other beans with errors themselves.
   * </p>
   */
  public boolean isBean() {
    return !isError();
  }

  /**
   * Returns true if this is a an error on a property.
   */
  public boolean isError() {
    return children == null;
  }

  /**
   * Return a flat list of the errors.
   */
  public InvalidValue[] getErrors() {

    ArrayList<InvalidValue> list = new ArrayList<InvalidValue>();
    buildList(list);

    return toArray(list);
  }

  /**
   * Builds the flat list of errors ignoring any that are bean holders.
   */
  private void buildList(List<InvalidValue> list) {
    if (isError()) {
      list.add(this);
    } else {
      for (int i = 0; i < children.length; i++) {
        children[i].buildList(list);
      }
    }
  }

  public String toString() {

    if (isError()) {
      return "errorKey=" + validatorKey + " type=" + beanType + " property=" + propertyName
          + " value=" + value;
    }
    if (children.length == 1) {
      return children[0].toString();
    }

    // its a holder of a list of errors for a bean
    StringBuilder sb = new StringBuilder(50);
    sb.append("\r\nCHILDREN[").append(children.length).append("]");
    for (int i = 0; i < children.length; i++) {
      sb.append(children[i].toString()).append(", ");
    }
    return sb.toString();
  }

  /**
   * Helper method to convert a list to an array.
   */
  public static InvalidValue[] toArray(List<InvalidValue> list) {
    return list.toArray(new InvalidValue[list.size()]);
  }

  /**
   * Helper method to convert a recursive error into a list.
   */
  public static List<InvalidValue> toList(InvalidValue invalid) {
    ArrayList<InvalidValue> list = new ArrayList<InvalidValue>();
    list.add(invalid);
    return list;
  }

}
