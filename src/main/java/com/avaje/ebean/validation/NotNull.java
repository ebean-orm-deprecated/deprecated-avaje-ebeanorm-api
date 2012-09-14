package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.NotNullValidatorFactory;

/**
 * Validate property as not null.
 */
@ValidatorMeta(factory = NotNullValidatorFactory.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

}
