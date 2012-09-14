package com.avaje.ebean.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks validation annotations.
 * <p>
 * Each validation annotation uses this to 'mark' it as a validation annotation.
 * Additionally you can specify the factory used to create the actual Validator
 * that is assigned to the bean properties.
 * </p>
 */
@Target( { ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatorMeta {

	/**
	 * Specify the factory used to build the specific Validator.
	 */
	Class<?> factory() default void.class;
}
