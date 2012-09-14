package com.avaje.ebean.bean;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * Bean that is aware of EntityBeanIntercept.
 * <p>
 * This interface and implementation of these methods is added to Entity Beans
 * via instrumentation. These methods have a funny _ebean_ prefix to avoid any
 * clash with normal methods these beans would have. These methods are not for
 * general application consumption.
 * </p>
 */
public interface EntityBean extends Serializable {

  /**
   * Return the enhancement marker value.
   * <p>
   * This is the class name of the enhanced class and used to check that all
   * entity classes are enhanced (specifically not just a super class).
   * </p>
   */
  public String _ebean_getMarker();

  /**
   * Create and return a new entity bean instance.
   */
  public Object _ebean_newInstance();

  /**
   * Add a PropertyChangeListener to this bean.
   */
  public void addPropertyChangeListener(PropertyChangeListener listener);

  /**
   * Remove a PropertyChangeListener from this bean.
   */
  public void removePropertyChangeListener(PropertyChangeListener listener);

  /**
   * Generated method that sets the loaded state on all the embedded beans on
   * this entity bean by using EntityBeanIntercept.setEmbeddedLoaded(Object o);
   */
  public void _ebean_setEmbeddedLoaded();

  /**
   * Return true if any embedded beans are new or dirty.
   */
  public boolean _ebean_isEmbeddedNewOrDirty();

  /**
   * Return the intercept for this object.
   */
  public EntityBeanIntercept _ebean_getIntercept();

  /**
   * Similar to _ebean_getIntercept() except it checks to see if the intercept
   * field is null and will create it if required.
   * <p>
   * This is really only required when transientInternalFields=true as an
   * enhancement option. In this case the intercept field is transient and will
   * be null after a bean has been deserialised.
   * </p>
   * <p>
   * This transientInternalFields=true option was to support some serialization
   * frameworks that can't take into account our ebean fields.
   * </p>
   */
  public EntityBeanIntercept _ebean_intercept();

  /**
   * Create a copy of this entity bean.
   * <p>
   * This occurs when a bean is changed. The copy represents the bean as it was
   * initially (oldValues) before any changes where made. This is used for
   * optimistic concurrency control.
   * </p>
   */
  public Object _ebean_createCopy();

  /**
   * Return the fields in their index order.
   */
  public String[] _ebean_getFieldNames();

  /**
   * Set the value of a field of an entity bean of this type.
   * <p>
   * Note that using this method bypasses any interception that otherwise occurs
   * on entity beans. That means lazy loading and oldValues creation.
   * </p>
   * 
   * @param fieldIndex
   *          the index of the field
   * @param entityBean
   *          the entityBean of this type to modify
   * @param value
   *          the value to set
   */
  public void _ebean_setField(int fieldIndex, Object entityBean, Object value);

  /**
   * Set the field value with interception.
   */
  public void _ebean_setFieldIntercept(int fieldIndex, Object entityBean, Object value);

  /**
   * Return the value of a field from an entity bean of this type.
   * <p>
   * Note that using this method bypasses any interception that otherwise occurs
   * on entity beans. That means lazy loading.
   * </p>
   * 
   * @param fieldIndex
   *          the index of the field
   * @param entityBean
   *          the entityBean to get the value from
   */
  public Object _ebean_getField(int fieldIndex, Object entityBean);

  /**
   * Return the field value with interception.
   */
  public Object _ebean_getFieldIntercept(int fieldIndex, Object entityBean);

}
