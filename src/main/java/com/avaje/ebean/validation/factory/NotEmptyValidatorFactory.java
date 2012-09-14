package com.avaje.ebean.validation.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates NotEmpty validators depending on the type.
 * <p>
 * Only valid for Strings, Lists, Sets, Collections and Maps.
 * </p>
 */
public class NotEmptyValidatorFactory implements ValidatorFactory {

  static final Logger logger = Logger.getLogger(NotEmptyValidatorFactory.class.getName());

  static final Validator STRING = new StringNotEmptyValidator();

  static final Validator ARRAY = new ArrayValidator();
  static final Validator LIST = new ListNotEmptyValidator();
  static final Validator SET = new SetNotEmptyValidator();
  static final Validator MAP = new MapNotEmptyValidator();
  static final Validator COLLECTION = new CollectionNotEmptyValidator();

  public Validator create(Annotation annotation, Class<?> type) {

    if (type.equals(String.class)) {
      return STRING;
    }
    if (type.isArray()) {
      return ARRAY;
    }
    if (List.class.isAssignableFrom(type)) {
      return LIST;
    }
    if (Set.class.isAssignableFrom(type)) {
      return SET;
    }
    if (Collection.class.isAssignableFrom(type)) {
      return COLLECTION;
    }
    if (Map.class.isAssignableFrom(type)) {
      return MAP;
    }

    String msg = "@NotEmpty not assignable to type " + type;
    logger.log(Level.SEVERE, msg);
    return null;
  }

  private static class StringNotEmptyValidator extends NoAttributesValidator {

    public String getKey() {
      return "notempty.string";
    }

    public boolean isValid(Object value) {
      if (value == null) {
        return false;
      }
      String s = (String) value;
      return s.length() > 0;
    }
  }

  private static class MapNotEmptyValidator extends NoAttributesValidator {

    public String getKey() {
      return "notempty.map";
    }

    public boolean isValid(Object value) {
      if (value == null) {
        return false;
      }
      Map<?, ?> map = (Map<?, ?>) value;
      return map.size() > 0;
    }
  }

  private static class ArrayValidator extends NoAttributesValidator {

    public String getKey() {
      return "notempty.array";
    }

    public boolean isValid(Object value) {
      if (value == null) {
        return false;
      }
      return Array.getLength(value) > 0;
    }
  }

  /**
   * Enables a set specific error message.
   */
  private static class SetNotEmptyValidator extends CollectionNotEmptyValidator {

    public String getKey() {
      return "notempty.set";
    }
  }

  /**
   * Enables a list specific error message.
   */
  private static class ListNotEmptyValidator extends CollectionNotEmptyValidator {

    public String getKey() {
      return "notempty.list";
    }
  }

  private static class CollectionNotEmptyValidator extends NoAttributesValidator {

    public String getKey() {
      return "notempty.collection";
    }

    public boolean isValid(Object value) {
      if (value == null) {
        return false;
      }
      Collection<?> c = (Collection<?>) value;
      return c.size() > 0;
    }
  }
}
