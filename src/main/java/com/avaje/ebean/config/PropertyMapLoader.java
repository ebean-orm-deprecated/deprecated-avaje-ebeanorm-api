package com.avaje.ebean.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

/**
 * Helper used to load the PropertyMap.
 */
final class PropertyMapLoader {

  private static Logger logger = Logger.getLogger(PropertyMapLoader.class.getName());

  private static ServletContext servletContext;

  /**
   * Return the servlet context when in a web environment.
   */
  public static ServletContext getServletContext() {
    return servletContext;
  }

  /**
   * Set the ServletContext for when ebean.properties is in WEB-INF in a web
   * application environment.
   */
  public static void setServletContext(ServletContext servletContext) {
    PropertyMapLoader.servletContext = servletContext;
  }

  /**
   * Load the file returning the property map.
   * 
   * @param p
   *          an existing property map to load into.
   * @param fileName
   *          the name of the properties file to load.
   */
  public static PropertyMap load(PropertyMap p, String fileName) {

    InputStream is = findInputStream(fileName);
    if (is == null) {
      logger.severe(fileName + " not found");
      return p;
    } else {
      return load(p, is);
    }
  }

  /**
   * Load the inputstream returning the property map.
   * 
   * @param p
   *          an existing property map to load into.
   * @param in
   *          the InputStream of the properties file to load.
   */
  private static PropertyMap load(PropertyMap p, InputStream in) {

    Properties props = new Properties();
    try {
      props.load(in);
      in.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (p == null) {
      p = new PropertyMap();
    }

    // put values in initially without any evaluation
    Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<Object, Object> entry = it.next();
      String key = ((String) entry.getKey()).toLowerCase();
      String val = ((String) entry.getValue());
      if (val != null) {
        val = val.trim();
      }
      p.put(key, val);
    }

    p.evaluateProperties();

    String otherProps = p.remove("load.properties");
    if (otherProps == null) {
      otherProps = p.remove("load.properties.override");
    }
    if (otherProps != null) {
      otherProps = otherProps.replace("\\", "/");
      InputStream is = findInputStream(otherProps);
      if (is != null) {
        logger.fine("loading properties from " + otherProps);
        load(p, is);
      } else {
        logger.severe("load.properties " + otherProps + " not found.");
      }
    }

    return p;
  }

  /**
   * Find the input stream given the file name.
   */
  private static InputStream findInputStream(String fileName) {

    if (fileName == null) {
      throw new NullPointerException("fileName is null?");
    }

    if (servletContext == null) {
      logger.fine("No servletContext so not looking in WEB-INF for " + fileName);

    } else {
      // first look in WEB-INF ...
      InputStream in = servletContext.getResourceAsStream("/WEB-INF/" + fileName);
      if (in != null) {
        logger.fine(fileName + " found in WEB-INF");
        return in;
      }
    }

    try {
      File f = new File(fileName);

      if (f.exists()) {
        logger.fine(fileName + " found in file system");
        return new FileInputStream(f);
      } else {
        InputStream in = findInClassPath(fileName);
        if (in != null) {
          logger.fine(fileName + " found in classpath");
        }
        return in;
      }

    } catch (FileNotFoundException ex) {
      // already made the check so this
      // should never be thrown
      throw new RuntimeException(ex);
    }
  }

  private static InputStream findInClassPath(String fileName) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
  }

}
