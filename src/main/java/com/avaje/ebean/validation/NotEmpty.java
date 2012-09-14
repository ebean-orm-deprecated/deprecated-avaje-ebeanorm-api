package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.NotEmptyValidatorFactory;


/**
 * Validate property as not null and not an empty string.
 * <p>
 * Can also be placed on collections and maps to make sure that they have more
 * than 0 elements.
 * </p>
 */
@ValidatorMeta(factory = NotEmptyValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {

}
