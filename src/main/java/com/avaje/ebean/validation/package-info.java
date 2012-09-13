/**
 * Annotations for validating entity beans.
 * <p>
 * We will look to migrate over to the standard
 * java bean validation annotations.
 * </p>
 * 
 * <p>
 * You and put these annotations on entity beans and either get Ebean to
 * automatically validate on Ebean.save() or manually validate using
 * Ebean.validate().
 * </p>
 * <p>
 * It is possibly debatable if validation should be performed by an ORM rather
 * than at a higher level in your application.
 * </p>
 * <p>
 * Even if you choose not to use these validation annotations the validation
 * framework is in place and if you wish Ebean can automatically read the
 * maximum lengths of all varchar columns (and not nullable constraints) from
 * the database meta data. This is useful when using <em>JDBC batching</em>
 * where the error reporting can be rather obscure (as the error could occur on
 * any data in the batch).
 * </p>
 * 
 * <pre class="code">
 *   ## turn on automatic validation for Ebean.save()
 *   ebean.validation=true
 *  
 *   ## turn on automatic creation of not null validation
 *   ## and max length validation for varchar's based on 
 *   ## database meta data (no annotations required)
 *   ebean.validation.autocreate=true
 *   
 *   ## for more specific control... 
 *   ## 
 *   ## ebean.validation.autocreate.notnull=true
 *   ## ebean.validation.autocreate.length=true
 *   ## ebean.validation.autocreate.length.max=4000
 *   ## NB: Only auto create length validation for
 *   ## varchar columns less than 4000 characters
 * </pre>
 */
package com.avaje.ebean.validation;