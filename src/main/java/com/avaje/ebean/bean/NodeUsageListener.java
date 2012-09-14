package com.avaje.ebean.bean;

/**
 * Collects the profile information.
 */
public interface NodeUsageListener {

	/**
	 * Collect node usage "profiling" information.
	 * <p>
	 * This is the properties that are used for a given bean in the object
	 * graph. This information is used by autoFetch to tune queries.
	 * </p>
	 */
	public void collectNodeUsage(NodeUsageCollector collector);
}
