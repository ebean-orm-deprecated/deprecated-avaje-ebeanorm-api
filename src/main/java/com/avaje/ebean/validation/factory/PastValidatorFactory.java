package com.avaje.ebean.validation.factory;

import java.lang.annotation.Annotation;
import java.util.Calendar;
import java.util.Date;

/**
 * Creates an appropriate Past validator.
 */
public class PastValidatorFactory implements ValidatorFactory {

	Validator DATE = new DateValidator();
	Validator CALENDAR = new CalendarValidator();

	public Validator create(Annotation annotation, Class<?> type) {
		if (Date.class.isAssignableFrom(type)) {
			return DATE;
		}
		if (Calendar.class.isAssignableFrom(type)) {
			return CALENDAR;
		}
		String msg = "Can not use @Past on type " + type;
		throw new RuntimeException(msg);
	}

	private static class DateValidator extends NoAttributesValidator {

		public String getKey() {
			return "past";
		}

		public boolean isValid(Object value) {
			if (value == null) {
				return true;
			}

			Date date = (Date) value;
			return date.before(new Date());
		}
	}

	private static class CalendarValidator extends NoAttributesValidator {

		public String getKey() {
			return "past";
		}

		public boolean isValid(Object value) {
			if (value == null) {
				return true;
			}

			Calendar cal = (Calendar) value;
			return cal.before(Calendar.getInstance());
		}
	}
}
