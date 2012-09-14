package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.AssertTrueValidatorFactory;


/**
 * Validation that can be placed on a method to assert it is true.
 * <p>
 * The method needs to follow the bean specification and start with "is".
 * </p>
 */
@ValidatorMeta(factory=AssertTrueValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AssertFalse {

}
