/**
 * @file
 * This file contains unit tests for
 * com.irurueta.server.commons.CommonsException
 * 
 * @author Alberto Irurueta (alberto@irurueta.com)
 * @date June 9, 2013
 */
package com.irurueta.server.commons;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommonsExceptionTest {
    
    public CommonsExceptionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testConstructor(){
        CommonsException ex;
        assertNotNull(ex = new CommonsException());
        
        ex = null;
        assertNotNull(ex = new CommonsException("message"));
        
        ex = null;
        assertNotNull(ex = new CommonsException(new Exception()));
        
        ex = null;
        assertNotNull(ex = new CommonsException("message", 
                new Exception()));        
    }    
}