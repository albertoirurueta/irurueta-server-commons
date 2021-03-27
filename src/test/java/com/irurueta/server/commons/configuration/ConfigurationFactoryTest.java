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

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class ConfigurationFactoryTest {

    @Test
    public void testGetInstance() {
        final ConfigurationFactory factory1 = ConfigurationFactory.getInstance();
        final ConfigurationFactory factory2 = ConfigurationFactory.getInstance();

        assertSame(factory1, factory2);
    }

    @Test
    public void testConfigure() throws ConfigurationException {
        final ConfigurationFactory factory = ConfigurationFactory.getInstance();

        final Properties props = new Properties();
        assertNull(factory.configure(props));

        assertSame(factory.getProperties(), props);
    }

    @Test
    public void testRegisterAndUnregister() throws ConfigurationException {
        final ConfigurationFactory factory = ConfigurationFactory.getInstance();

        final CustomConfigurationFactory otherFactory = new CustomConfigurationFactory();
        assertTrue(factory.register(otherFactory));

        // check
        assertTrue(factory.getRegisteredFactories().contains(otherFactory));

        // configure
        assertNull(factory.configure());

        // unregister
        assertTrue(factory.unregister(otherFactory));

        // check
        assertFalse(factory.getRegisteredFactories().contains(otherFactory));
    }

    @Test
    public void testReset() throws ConfigurationException {
        final ConfigurationFactory factory = ConfigurationFactory.getInstance();

        factory.reset();

        assertNull(factory.getConfiguration());
    }

    @Test
    public void testReconfigure() throws ConfigurationException {
        final ConfigurationFactory factory = ConfigurationFactory.getInstance();

        assertNull(factory.reconfigure());
        assertNull(factory.reconfigure(new Properties()));
    }

    private static class CustomConfigurationFactory extends
            BaseConfigurationFactory<Configuration> {

        @Override
        public Configuration configure(Properties properties) {
            return null;
        }
    }
}
