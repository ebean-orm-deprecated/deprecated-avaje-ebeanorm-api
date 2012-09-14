package com.avaje.ebean.config.dbplatform;

/**
 * The types of Identity generation that can be defined.
 */
public enum IdType {

	/**
	 * Use a Database Identity (autoincrement) to generate the identity.
	 */
	IDENTITY,
	
	/**
	 * Use a Database sequence to generate the identity.
	 * <p>
	 * Note: Some databases support getGeneratedKeys with sequences 
	 * and this then does not involve an extra statement to return 
	 * the id.
	 * </p>
	 */
	SEQUENCE,
	
	/**
	 * Use an IdGenerator to generate the identity (prior to insert).
	 * <p>
	 * Note: There is a IdGenerator for UUID's and it is automatically 
	 * assigned to id properties of type UUID.
	 * </p>
	 */
	GENERATOR;

}
