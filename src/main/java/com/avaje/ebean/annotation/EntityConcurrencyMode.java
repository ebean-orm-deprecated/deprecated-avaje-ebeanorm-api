package com.avaje.ebean.annotation;

import com.avaje.ebeaninternal.server.core.ConcurrencyMode;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify explicit ConcurrencyMode for entity bean.
 */
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityConcurrencyMode
{
	/**
   * The ConcurrencyMode value.
   */
    ConcurrencyMode value();
}
