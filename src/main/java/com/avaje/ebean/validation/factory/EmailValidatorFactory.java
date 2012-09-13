package com.avaje.ebean.validation.factory;

import java.lang.annotation.Annotation;


/**
 * Creates a Email validator.
 * <p>
 * Note uses Les Hazlewood's validation code with both domain literals and
 * quoted identifiers turned off.
 * </p>
 */
public class EmailValidatorFactory implements ValidatorFactory {

	public static final Validator EMAIL = new EmailValidator();

	public Validator create(Annotation annotation, Class<?> type) {
		if (!type.equals(String.class)) {
			throw new RuntimeException("Can only apply this annotation to String types, not "
					+ type);
		}
		return EMAIL;
	}

	public static class EmailValidator extends NoAttributesValidator {

		private final EmailValidation emailValidation;

		EmailValidator() {
			// no domain or literals allowed...
			emailValidation = EmailValidation.create(false, false);
		}

		public String getKey() {
			return "email";
		}

		public boolean isValid(Object value) {
			if (value == null) {
				return true;
			}

			return emailValidation.isValid((String) value);
		}
	}
}
