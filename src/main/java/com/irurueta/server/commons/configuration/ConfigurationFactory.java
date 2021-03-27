/*
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

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Base factory class to obtain and configure all modules of the application.
 */
public class ConfigurationFactory extends
        BaseConfigurationFactory<Configuration> {

    /**
     * Singleton instance of configuration factory.
     */
    private static ConfigurationFactory mSingleton;

    /**
     * Provided properties containing all configuration properties of the
     * application.
     */
    private Properties mProperties;

    /**
     * Collection of configuration factories registered to be configured
     * when configuring from some Properties.
     */
    private final Set<BaseConfigurationFactory<? extends Configuration>> mRegisteredFactories;

    /**
     * Private constructor of this class.
     */
    private ConfigurationFactory() {
        mRegisteredFactories = new HashSet<>();
    }

    /**
     * Factory method to return or create singleton instance of this class.
     *
     * @return singleton instance of this class.
     */
    public static synchronized ConfigurationFactory getInstance() {
        if (mSingleton == null) {
            mSingleton = new ConfigurationFactory();
        }
        return mSingleton;
    }

    /**
     * Configures.
     *
     * @param properties properties to be read during configuration.
     * @return a configuration instance.
     * @throws ConfigurationException if configuration fails.
     */
    @Override
    public Configuration configure(final Properties properties)
            throws ConfigurationException {
        mProperties = properties;
        // configure all registered configuration factories
        for (final BaseConfigurationFactory<?> factory : mRegisteredFactories) {
            factory.configure(properties);
        }
        return null;
    }

    /**
     * Obtains properties used during configuration.
     *
     * @return properties used during configuration.
     */
    public Properties getProperties() {
        return mProperties;
    }

    /**
     * Registers a configuration factory for configuration from some Properties.
     *
     * @param factory factory to be registered.
     * @return true if factory was registered, false otherwise.
     */
    public boolean register(final BaseConfigurationFactory<? extends Configuration> factory) {
        return factory != null && mRegisteredFactories.add(factory);
    }

    /**
     * Unregisters a configuration factory from configuration from some
     * Properties.
     *
     * @param factory factory to be registered.
     * @return true if factory was unregistered, false otherwise.
     */
    public boolean unregister(final BaseConfigurationFactory<? extends Configuration> factory) {
        return factory != null && mRegisteredFactories.remove(factory);
    }

    /**
     * Returns non modifiable set containing registered factories.
     *
     * @return registered factories.
     */
    public Set<BaseConfigurationFactory<? extends Configuration>> getRegisteredFactories() {
        return Collections.unmodifiableSet(mRegisteredFactories);
    }
}
