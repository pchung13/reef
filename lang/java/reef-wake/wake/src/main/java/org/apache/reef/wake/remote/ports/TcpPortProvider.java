/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.reef.wake.remote.ports;

import org.apache.reef.tang.Configuration;
import org.apache.reef.tang.annotations.DefaultImplementation;

import java.util.Iterator;

/**
 * Provides an iterator that returns port numbers.
*/
@DefaultImplementation(RangeTcpPortProvider.class)
public interface TcpPortProvider extends Iterable<Integer> {
  /**
   * Returns an iterator over a set of tcp ports
   *
   * @return an Iterator.
   */
  @Override
  Iterator<Integer> iterator();

  /**
   * returns a configuration for the class that implements TcpPortProvider so that class can be instantiated
   * somewhere else
   *
   * @return Configuration.
   */
  Configuration getConfiguration();
}
