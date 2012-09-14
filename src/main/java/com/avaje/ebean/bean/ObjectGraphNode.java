package com.avaje.ebean.bean;

import java.io.Serializable;

/**
 * Identifies a unique node of an object graph.
 * <p>
 * It represents a location relative to the root of an object graph and specific
 * to a query and call stack hash.
 * </p>
 */
public final class ObjectGraphNode implements Serializable {

  private static final long serialVersionUID = 2087081778650228996L;

  /**
   * Identifies the origin.
   */
  private final ObjectGraphOrigin originQueryPoint;

  /**
   * The path relative to the root.
   */
  private final String path;

  /**
   * Create at a sub level.
   */
  public ObjectGraphNode(ObjectGraphNode parent, String path) {
    this.originQueryPoint = parent.getOriginQueryPoint();
    this.path = parent.getChildPath(path);
  }

  /**
   * Create an the root level.
   */
  public ObjectGraphNode(ObjectGraphOrigin originQueryPoint, String path) {
    this.originQueryPoint = originQueryPoint;
    this.path = path;
  }

  /**
   * Return the origin query point.
   */
  public ObjectGraphOrigin getOriginQueryPoint() {
    return originQueryPoint;
  }

  private String getChildPath(String childPath) {
    if (path == null) {
      return childPath;
    } else if (childPath == null) {
      return path;
    } else {
      return path + "." + childPath;
    }
  }

  /**
   * Return the path relative to the root.
   */
  public String getPath() {
    return path;
  }

  public String toString() {
    return "origin:" + originQueryPoint + " " + ":" + path + ":" + path;
  }
}
