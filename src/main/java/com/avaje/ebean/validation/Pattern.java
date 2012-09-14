package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avaje.ebean.validation.factory.PatternValidatorFactory;


/**
 * Validate using a Regular expression the pattern of a String property.
 * <p>
 * You can only apply this to String properties.
 * </p>
 */
@ValidatorMeta(factory=PatternValidatorFactory.class)
@Target( { ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pattern {

	/**
	 * The regular expression.
	 */
	String regex() default "";

	/**
	 * Regular expression flags.
	 */
	int flags() default 0;
}
