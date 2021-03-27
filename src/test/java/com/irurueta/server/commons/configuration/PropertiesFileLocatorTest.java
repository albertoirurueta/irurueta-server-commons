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

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.*;

public class PropertiesFileLocatorTest {

    @Test
    public void testConstructor() {

        PropertiesFileLocator<PropertiesFileLocatorTest> locator =
                new PropertiesFileLocator<>("file");
        assertNotNull(locator);

        locator = new PropertiesFileLocator<>(this);
        assertNotNull(locator);

        locator = new PropertiesFileLocator<>(null, this);
        assertNotNull(locator);

        locator = new PropertiesFileLocator<>(this, "file");
        assertNotNull(locator);

        locator = new PropertiesFileLocator<>(null, this, "file");
        assertNotNull(locator);
    }

    @Test
    public void testLocate() throws IOException {
        PropertiesFileLocator<PropertiesFileLocatorTest> locator =
                new PropertiesFileLocator<>(this);

        //default properties is not found
        Properties props = locator.locate();
        assertTrue(props.isEmpty());

        locator = new PropertiesFileLocator<>(this, "test.properties");
        props = locator.locate();
        assertFalse(props.isEmpty());
        assertTrue(props.containsKey("key"));

        locator = new PropertiesFileLocator<>(new CustomServletContext(), this, "test.properties");
        props = locator.locate();
        assertFalse(props.isEmpty());
        assertTrue(props.containsKey("key"));
    }

    private static class CustomServletContext implements ServletContext {

        public String getContextPath() {
            return "context";
        }

        public ServletContext getContext(String s) {
            return null;
        }

        public int getMajorVersion() {
            return 0;
        }

        public int getMinorVersion() {
            return 0;
        }

        public String getMimeType(String s) {
            return null;
        }

        public Set<?> getResourcePaths(String s) {
            return null;
        }

        public URL getResource(String s) {
            return null;
        }

        public InputStream getResourceAsStream(String s) {
            return null;
        }

        public RequestDispatcher getRequestDispatcher(String s) {
            return null;
        }

        public RequestDispatcher getNamedDispatcher(String s) {
            return null;
        }

        public Servlet getServlet(String s) {
            return null;
        }

        public Enumeration<?> getServlets() {
            return null;
        }

        public Enumeration<?> getServletNames() {
            return null;
        }

        public void log(String s) {

        }

        public void log(Exception e, String s) {

        }

        public void log(String s, Throwable throwable) {

        }

        public String getRealPath(String s) {
            return null;
        }

        public String getServerInfo() {
            return null;
        }

        public String getInitParameter(String s) {
            return null;
        }

        public Enumeration<?> getInitParameterNames() {
            return null;
        }

        public Object getAttribute(String s) {
            return null;
        }

        public Enumeration<?> getAttributeNames() {
            return null;
        }

        public void setAttribute(String s, Object o) {

        }

        public void removeAttribute(String s) {

        }

        public String getServletContextName() {
            return null;
        }
    }
}