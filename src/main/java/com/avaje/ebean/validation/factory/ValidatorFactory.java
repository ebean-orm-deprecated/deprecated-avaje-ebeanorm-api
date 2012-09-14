package com.avaje.ebean.validation.factory;

import java.lang.annotation.Annotation;

/**
 * Creates a Validator for using in Ebean.
 */
public interface ValidatorFactory {

  /**
   * Create the validator given the annotation.
   */
  public Validator create(Annotation annotation, Class<?> type);
}
