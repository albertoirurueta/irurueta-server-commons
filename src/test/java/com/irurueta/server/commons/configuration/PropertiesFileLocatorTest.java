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

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertiesFileLocatorTest {
    
    public PropertiesFileLocatorTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    @Test
    public void testConstructor(){
        
        PropertiesFileLocator<PropertiesFileLocatorTest> locator =
                new PropertiesFileLocator("file");
        assertNotNull(locator);
        
        locator = new PropertiesFileLocator(this);
        assertNotNull(locator);
        
        locator = new PropertiesFileLocator((ServletContext)null, this);
        assertNotNull(locator);
        
        locator = new PropertiesFileLocator(this, (String)"file");
        assertNotNull(locator);
        
        locator = new PropertiesFileLocator((ServletContext)null, this, "file");
        assertNotNull(locator);
    }
    
    @Test
    public void testLocate() throws IOException{
        PropertiesFileLocator<PropertiesFileLocatorTest> locator =
                new PropertiesFileLocator(this);
        
        //default properties is not found
        Properties props = locator.locate();
        assertTrue(props.isEmpty());
        
        locator = new PropertiesFileLocator(this, "test.properties");
        props = locator.locate();        
        assertFalse(props.isEmpty());
        assertTrue(props.containsKey("key"));
    }
}