package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.FutureValidatorFactory;


/**
 * Validate a Date, Timestamp or Calendar property as having to be in the future.
 */
@ValidatorMeta(factory = FutureValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Future {

}
