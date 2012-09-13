package com.avaje.ebean.validation.factory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.avaje.ebean.validation.Length;


/**
 * Creates a String length validator.
 */
public final class LengthValidatorFactory implements ValidatorFactory {

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
			String msg = "You can only specify @Length on String types";
			throw new RuntimeException(msg);
		}
		Length length = (Length) annotation;
		return create(length.min(), length.max());
	}

	/**
	 * Create or get a LengthValidator single threaded.
	 */
	public synchronized static Validator create(int min, int max) {

		String key = min + ":" + max;
		Validator validator = cache.get(key);
		if (validator == null) {
			validator = new LengthValidator(min, max);
			cache.put(key, validator);
		}
		return validator;
	}

	/**
	 * The actual length validator.
	 */
	public static final class LengthValidator implements Validator {

		private final int min;
		private final int max;
		private final String key;
		private final Object[] attributes;
		
		private LengthValidator(int min, int max) {
			this.min = min;
			this.max = max;
			this.key = determineKey(min, max);
			this.attributes = determineAttributes(min, max);
		}

		private String determineKey(int min, int max) {
			if (min == 0 && max > 0){
				return "length.max";
			} else if (min > 0 && max == 0) {
				return "length.min";
			} else {
				return "length.minmax";
			}
		}
		
		private Object[] determineAttributes(int min, int max) {
			if (min == 0 && max > 0){
				return new Object[]{Integer.valueOf(max)};
			} else if (min > 0 && max == 0) {
				return new Object[]{Integer.valueOf(min)};
			} else {
				return new Object[]{Integer.valueOf(min), Integer.valueOf(max)};
			}
		}
		
		/**
		 * Returns the min and/or max attributes.
		 */
		public Object[] getAttributes() {
			return attributes;
		}

		public String getKey() {
			return key;
		}

		public boolean isValid(Object value) {
			if (value == null) {
				return true;
			}
			String s = (String) value;
			int len = s.length();
			return len >= min && len <= max;
		}
	}
}
