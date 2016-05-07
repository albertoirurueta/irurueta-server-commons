/**
 * Copyright (C) 2016 Alberto Irurueta Carro (alberto@irurueta.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.irurueta.server.commons.configuration;

import java.util.Properties;

/**
 * Defines the interface of a configuration factory to obtain a given
 * configuration implementation based on provided properties.
 * @param <E> a Configuration subclass.
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseConfigurationFactory<E extends Configuration> {

    /**
     * Reference to configuration to be created.
     */
    protected volatile E mConfiguration;

    /**
     * Configures based on default configuration and returns a Configuration 
     * instance. If a configuration already exists, then it is returned and
     * configuration process is skipped.
     * @return a configuration instance.
     * @throws ConfigurationException if something fails during configuration.
     */
    public E configure() throws ConfigurationException {
        return configure(null);
    }

    /**
     * Configures based on provided properties and returns a Configuration
     * instance. If a configuration already exists, then it is returned and
     * configuration process is skipped.
     * @param properties properties to use for configuration.
     * @return a configuration instance.
     * @throws ConfigurationException if something fails during configuration.
     */
    public abstract E configure(Properties properties)
            throws ConfigurationException;

    /**
     * Resets configuration by removing any existing configuration if already
     * defined.
     * @return returns this instance so that this method can be chained.
     * @throws ConfigurationException if something fails during configuration
     * reset.
     */
    public synchronized BaseConfigurationFactory<E> reset()
            throws ConfigurationException {
        mConfiguration = null;
        return this;
    }

    /**
     * Resets configuration and configures again using default configuration.
     * @return a configuration instance.
     * @throws ConfigurationException if something fails during configuration.
     */
    public final E reconfigure() throws ConfigurationException {
        return reset().configure();
    }

    /**
     * Resets configuration and configures again based on provided properties.
     * @param properties properties to use for configuration.
     * @return a configuration instance.
     * @throws ConfigurationException if something fails during configuration.
     */
    public final E reconfigure(Properties properties) throws ConfigurationException {
        return reset().configure(properties);
    }

    /**
     * Returns current configuration if it is already defined, otherwise null
     * is returned.
     * @return current configuration.
     */
    public final synchronized E getConfiguration() {
        return mConfiguration;
    }
}
