/**
 * @file
 * This file contains unit tests for
 * com.irurueta.server.commons.BuildInfo
 * 
 * @author Alberto Irurueta (alberto@irurueta.com)
 * @date December 12, 2015
 */
package com.irurueta.server.commons;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuildInfoTest {
    
    public BuildInfoTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    @Test
    public void testGetInstance(){
        BuildInfo info1 = BuildInfo.getInstance();
        BuildInfo info2 = BuildInfo.getInstance();
        
        assertSame(info1, info2);
    }
    
    @Test
    public void testGetters(){
        BuildInfo info = BuildInfo.getInstance();
        
        info.getBuildNumber();
        info.getCommit();
        info.getBranch();
        
        assertNotNull(info.getBuildTimestamp());
        assertNotNull(info.getGroupId());
        assertNotNull(info.getArtifactId());
        assertNotNull(info.getVersion());
    }
}
