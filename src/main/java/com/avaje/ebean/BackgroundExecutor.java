/**
 * Copyright (C) 2009  Robin Bygrave
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
package com.avaje.ebean;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Background thread pool service for executing of tasks asynchronously.
 * <p>
 * This service is used internally by Ebean for executing background tasks such
 * as the {@link Query#findFutureList()} and also for executing background tasks
 * periodically.
 * </p>
 * <p>
 * This service has been made available so you can use it for your application
 * code if you want. It can be useful for some server caching implementations
 * (background population and trimming of the cache etc).
 * </p>
 * 
 * @author rbygrave
 */
public interface BackgroundExecutor {

	/**
	 * Execute a task in the background.
	 */
	public void execute(Runnable r);

	/**
	 * Execute a task periodically with a fixed delay between each execution.
	 * <p>
	 * For example, execute a runnable every minute.
	 * </p>
	 * <p>
	 * The delay is the time between executions no matter how long the task
	 * took. That is, this method has the same behaviour characteristics as
	 * {@link ScheduledExecutorService#scheduleWithFixedDelay(Runnable, long, long, TimeUnit)}
	 * </p>
	 */
	public void executePeriodically(Runnable r, long delay, TimeUnit unit);
}
