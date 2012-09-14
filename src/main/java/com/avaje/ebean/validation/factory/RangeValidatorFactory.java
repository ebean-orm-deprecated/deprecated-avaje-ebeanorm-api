package com.avaje.ebean.validation.factory;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.avaje.ebean.validation.Range;

/**
 * Creates a String length validator.
 */
public final class RangeValidatorFactory implements ValidatorFactory {

  /**
   * Hold a cache because these validators could be used lots of properties and
   * the LengthValidator is safe for concurrent use.
   * <p>
   * This cache has only single threaded access through the synchronised
   * create() method.
   * </p>
   */
  private static final Map<String, Validator> cache = new HashMap<String, Validator>();

  public Validator create(Annotation annotation, Class<?> type) {
    Range range = (Range) annotation;
    return create(range.min(), range.max(), type);
  }

  public synchronized Validator create(long min, long max, Class<?> type) {

    String key = type + ":" + min + ":" + max;
    Validator validator = cache.get(key);
    if (validator != null) {
      return validator;
    }
    if (type.equals(String.class)) {
      validator = new StringValidator(min, max);

    } else if (useDouble(type)) {
      validator = new DoubleValidator(min, max);

    } else if (useLong(type)) {
      validator = new LongValidator(min, max);

    } else {
      String msg = "@Range annotation not assignable to type " + type;
      throw new RuntimeException(msg);
    }

    cache.put(key, validator);
    return validator;
  }

  /**
   * Return true if use long precision for range test.
   */
  private static boolean useLong(Class<?> type) {
    if (type.equals(int.class) || type.equals(long.class) || type.equals(short.class)) {
      return true;
    }
    // bit of a catch all ... so must be tested
    // after useDouble...
    if (Number.class.isAssignableFrom(type)) {
      return true;
    }
    return false;
  }

  /**
   * Return true if use double precision for range test.
   */
  private static boolean useDouble(Class<?> type) {
    if (type.equals(float.class) || type.equals(double.class)) {
      return true;
    }
    if (type.equals(BigDecimal.class)) {
      return true;
    }
    if (Double.class.isAssignableFrom(type)) {
      return true;
    }
    if (Float.class.isAssignableFrom(type)) {
      return true;
    }
    return false;
  }

  /**
   * The Double validator.
   */
  private static class DoubleValidator implements Validator {

    final long min;
    final long max;
    final String key;
    final Object[] attributes;

    private DoubleValidator(long min, long max) {
      this.min = min;
      this.max = max;
      this.key = determineKey(min, max);
      this.attributes = determineAttributes(min, max);
    }

    private String determineKey(long min, long max) {
      if (min > Long.MIN_VALUE && max < Long.MAX_VALUE) {
        return "range.minmax";
      } else if (min > Long.MIN_VALUE) {
        return "range.min";
      } else {
        return "range.max";
      }
    }

    private Object[] determineAttributes(long min, long max) {
      if (min > Long.MIN_VALUE && max < Long.MAX_VALUE) {
        return new Object[] { min, max };
      } else if (min > Long.MIN_VALUE) {
        return new Object[] { min };
      } else {
        return new Object[] { max };
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
      Number n = (Number) value;
      double dv = n.doubleValue();
      return dv >= min && dv <= max;
    }

    public String toString() {
      return getClass().getName() + "key:" + key + " min:" + min + " max:" + max;
    }
  }

  /**
   * Uses long precision for testing.
   */
  private static class LongValidator extends DoubleValidator {

    private LongValidator(long min, long max) {
      super(min, max);
    }

    @Override
    public boolean isValid(Object value) {
      if (value == null) {
        return true;
      }
      Number n = (Number) value;
      long lv = n.longValue();
      return lv >= min && lv <= max;
    }
  }

  /**
   * Uses BigDecimal to convert to double prior to testing.
   */
  private static class StringValidator extends DoubleValidator {

    // final BigDecimal bdMin;
    // final BigDecimal bdMax;

    private StringValidator(long min, long max) {
      super(min, max);
      // bdMin = BigDecimal.valueOf(min);
      // bdMax = BigDecimal.valueOf(max);
    }

    @Override
    public boolean isValid(Object value) {
      if (value == null) {
        return true;
      }

      BigDecimal bd = new BigDecimal((String) value);
      double dv = bd.doubleValue();
      return dv >= min && dv <= max;
    }
  }
}
