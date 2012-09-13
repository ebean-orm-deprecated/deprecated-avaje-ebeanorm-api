/**
 * Copyright (C) 2006  Robin Bygrave
 * 
 * This file is part of Ebean.
 * 
 * Ebean is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *  
 * Ebean is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Ebean; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA  
 */
package com.avaje.ebean.bean;

/**
 * This is ONLY used for <b>subclassed</b> entity beans.
 * <p>
 * This is NOT USED for entity beans that are enhanced via a javaagent or ant
 * task etc. This is only used when the entity beans are created as a
 * subclass of the original class.
 * </p>
 * <p>
 * Allows the developer to control whether beans and collections are serialized
 * to plain 'vanilla' classes or left in byte code generated subclasses.
 * <p>
 * Vanilla beans are beans that have plain ordinary classes as opposed to specially
 * generated classes that Ebean creates. Ebean creates classes (using ASM) to support
 * lazy loading (reference beans) and concurrency checking etc.
 * </p>
 * <p>
 * SerializeControl gives you the ability to control whether an object graph is 
 * serialized to plain 'vanilla' objects or in the special byte code generated form.
 * There are pros and cons for both approaches depending on whether you want to 
 * support "FULL" concurrency checking and lazy loading when the object graph is
 * deserialized. 
 * </p>
 * <p>
 * Note that BeanMap, BeanList and BeanSet are not byte code generated. They are 
 * ordinary classes. However you may wish to have these serialized to the 
 * underlying List Set and Map implementations for the benefit that they can be
 * deserialised in a JVM without <em>ANY</em> ebean code at all.
 * </p>
 */
public class SerializeControl {

    private static final String BEANS = "com.avaje.ebean.vanillabeans";

    private static final String COLLECTIONS = "com.avaje.ebean.vanillacollections";

    private static Boolean getDefault(String key, Boolean dflt) {
        String val = System.getProperty(key);
        if (val != null) {
            return val.equalsIgnoreCase("true");
        }
        return dflt;
    }

    private static ThreadLocal<Boolean> vanillaBeans = new ThreadLocal<Boolean>() {
        protected synchronized Boolean initialValue() {
            return getDefault(BEANS, Boolean.TRUE);
        }
    };

    private static ThreadLocal<Boolean> vanillaCollections = new ThreadLocal<Boolean>() {
        protected synchronized Boolean initialValue() {
            return getDefault(COLLECTIONS, Boolean.TRUE);
        }
    };

    /**
     * Set the JVM wide default for Beans.
     */
    public static void setDefaultForBeans(boolean vanillaOn) {
        Boolean b = Boolean.valueOf(vanillaOn);
        System.setProperty(BEANS, b.toString());
    }

    /**
     * Set the JVM wide default for Collections.
     */
    public static void setDefaultForCollections(boolean vanillaOn) {
        Boolean b = Boolean.valueOf(vanillaOn);
        System.setProperty(COLLECTIONS, b.toString());
    }

    /**
     * Reset the mode for beans and collections back to the JVM wide default
     * setting.
     */
    public static void resetToDefault() {
        Boolean beans = getDefault(BEANS, Boolean.FALSE);
        setVanillaBeans(beans);

        Boolean coll = getDefault(COLLECTIONS, Boolean.FALSE);
        setVanillaCollections(coll);
    }

    /**
     * Set the mode for both Beans and Collections.
     */
    public static void setVanilla(boolean vanillaOn) {
        if (vanillaOn) {
            vanillaBeans.set(Boolean.TRUE);
            vanillaCollections.set(Boolean.TRUE);
        } else {
            vanillaBeans.set(Boolean.FALSE);
            vanillaCollections.set(Boolean.FALSE);
        }
    }

    /**
     * Return true if beans are serialized to Vanilla as opposed
     * to byte code generated subclasses.
     */
    public static boolean isVanillaBeans() {
        return (Boolean) vanillaBeans.get();
    }

    /**
     * Set whether beans should be serialized to Vanilla as opposed
     * to byte code generated subclasses.
     */
    public static void setVanillaBeans(boolean vanillaOn) {
        vanillaBeans.set(vanillaOn);
    }

    /**
     * Return true if collections are serialized to be plain Lists Sets or Maps
     * as opposed to BeanList, BeanMap or BeanSet.
     */
    public static boolean isVanillaCollections() {
        return (Boolean) vanillaCollections.get();
    }

    /**
     * Set whether collections should be serialized to Vanilla Lists Sets or
     * Maps (instead of BeanList, BeanMap or BeanSet).
     */
    public static void setVanillaCollections(boolean vanillaOn) {
        vanillaCollections.set(vanillaOn);
    }
}
