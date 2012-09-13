package com.avaje.ebean.validation.factory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import com.avaje.ebean.validation.Pattern;


/**
 * Creates a String length validator.
 */
public final class PatternValidatorFactory implements ValidatorFactory {

	/**
	 * Hold a cache because these validators could be used lots of properties
	 * and the LengthValidator is safe for concurrent use.
	 * <p>
	 * This cache has only single threaded access through the synchronised
	 * create() method.
	 * </p>
	 */
	private static final Map<String, Validator> cache = new HashMap<String, Validator>();


	public Validator create(Annotation annotation, Class<?> type) {
		if (!type.equals(String.class)) {
			String msg = "You can only specify @Pattern on String types";
			throw new RuntimeException(msg);
		}
		Pattern pattern = (Pattern) annotation;
		return create(pattern.regex(), pattern.flags());
	}

	/**
	 * Create or get a LengthValidator single threaded.
	 */
	public synchronized static Validator create(String regex, int flags) {

		regex = regex.trim();
		if (regex.length() == 0){
			throw new RuntimeException("Missing regex attribute on @Pattern");
		}
		String key = regex;
		Validator validator = cache.get(key);
		if (validator == null) {
			validator = new PatternValidator(regex, flags);
			cache.put(key, validator);
		}
		return validator;
	}

	/**
	 * The actual length validator.
	 */
	private static final class PatternValidator implements Validator {

		private final java.util.regex.Pattern pattern;
		
		private final Object[] attributes;
		
		private PatternValidator(String regex, int flags) {
			pattern = java.util.regex.Pattern.compile(regex,flags);
			attributes = new Object[]{regex, flags};
		}
		
		/**
		 * Returns the min and/or max attributes.
		 */
		public Object[] getAttributes() {
			return attributes;
		}

		public String getKey() {
			return "pattern";
		}

		public boolean isValid(Object value) {
			if (value == null) {
				return true;
			}
			String string = (String) value;
			Matcher m = pattern.matcher( string );
			return m.matches();
		}
	}
}
