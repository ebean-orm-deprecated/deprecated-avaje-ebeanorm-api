package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.EmailValidatorFactory;

/**
 * Validate property as email address.
 * <p>
 * Note uses Les Hazlewood's email validation code with both domain literals and
 * quoted identifiers turned off. Thanks Les!!
 * </p>
 */
@ValidatorMeta(factory = EmailValidatorFactory.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

}
