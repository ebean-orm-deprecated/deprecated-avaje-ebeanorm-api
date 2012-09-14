package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.AssertFalseValidatorFactory;

/**
 * Validation that can be placed on a method to assert it is false.
 * <p>
 * The method needs to follow the bean specification and start with "is".
 * </p>
 */
@ValidatorMeta(factory = AssertFalseValidatorFactory.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AssertTrue {

}
