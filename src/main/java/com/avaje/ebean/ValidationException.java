package com.avaje.ebean;

import java.util.Arrays;

import javax.persistence.PersistenceException;

/**
 * Exception thrown when a validation rule fails when saving a bean.
 */
public class ValidationException extends PersistenceException {

  private static final long serialVersionUID = -6185355529565362494L;

  final InvalidValue invalid;

  public ValidationException(InvalidValue invalid) {
    super("validation failed for: " + invalid.getBeanType());
    this.invalid = invalid;
  }

  /**
   * Return the InvalidValue tree.
   * <p>
   * This is a tree structure as validation can cascade to associated one and
   * associated many beans.
   * </p>
   */
  public InvalidValue getInvalid() {
    return invalid;
  }

  /**
   * Return the actual errors for all beans validated.
   * <p>
   * This flattens the InvalidValue tree just returning the actual errors
   * excluding the InvalidValue 'beans' that just hold errors (as children).
   * </p>
   */
  public InvalidValue[] getErrors() {
    return invalid.getErrors();
  }

  public String toString() {
    return super.toString() + ": " + Arrays.toString(getErrors());
  }
}
