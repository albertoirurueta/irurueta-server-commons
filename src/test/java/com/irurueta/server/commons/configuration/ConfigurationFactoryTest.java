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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class ConfigurationFactoryTest {

    public ConfigurationFactoryTest() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}

    @Test
    public void testGetInstance() {
        ConfigurationFactory factory1 = ConfigurationFactory.getInstance();
        ConfigurationFactory factory2 = ConfigurationFactory.getInstance();

        assertSame(factory1, factory2);
    }

    @Test
    public void testConfigure() throws ConfigurationException {
        ConfigurationFactory factory = ConfigurationFactory.getInstance();

        Properties props = new Properties();
        assertNull(factory.configure(props));

        assertSame(factory.getProperties(), props);
    }

    @Test
    public void testRegisterAndUnregister() throws ConfigurationException {
        ConfigurationFactory factory = ConfigurationFactory.getInstance();

        CustomConfigurationFactory otherFactory = new CustomConfigurationFactory();
        factory.register(otherFactory);

        //check
        assertTrue(factory.getRegisteredFactories().contains(otherFactory));

        //configure
        assertNull(factory.configure());

        //unregister
        factory.unregister(otherFactory);

        //check
        assertFalse(factory.getRegisteredFactories().contains(otherFactory));
    }

    @Test
    public void testReset() throws ConfigurationException {
        ConfigurationFactory factory = ConfigurationFactory.getInstance();

        factory.reset();

        assertNull(factory.getConfiguration());
    }

    @Test
    public void testReconfigure() throws ConfigurationException {
        ConfigurationFactory factory = ConfigurationFactory.getInstance();

        assertNull(factory.reconfigure());
        assertNull(factory.reconfigure(new Properties()));
    }

    private static class CustomConfigurationFactory extends
            BaseConfigurationFactory<Configuration>{

        @Override
        public Configuration configure(Properties properties)
                throws ConfigurationException {
            return null;
        }
    }
}
