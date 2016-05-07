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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 * This class searches for a properties file in the root of the classpath of
 * the deployed war or at the server user home directory.
 * @param <T> type of object to be used to locate a resource.
 */
public class PropertiesFileLocator<T> {

    /**
     * Default name to search for if none is provided.
     */
    public static final String DEFAULT_FILE_NAME = "configuration.properties";

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(
            PropertiesFileLocator.class.getName());

    /**
     * Reference to servlet context where application is running.
     */
    private ServletContext mContext;

    /**
     * Reference to an object where resources can be looked in its library jar
     * or war file.
     */
    //source object. This is used to search a resource on
    //a given jar o war where the class belongs to
    private T mSource;

    /**
     * Properties file name containing configuration.
     */
    private String mFileName;

    /**
     * Constructor.
     * @param fileName properties file name containing configuration.
     */
    public PropertiesFileLocator(String fileName) {
        mContext = null;
        mSource = null;
        mFileName = fileName;
    }

    /**
     * Constructor.
     * @param source reference to an object where resources can be loaded in its
     * library jar or war file.
     */
    public PropertiesFileLocator(T source) {
        mContext = null;
        mSource = source;
        mFileName = DEFAULT_FILE_NAME;
    }

    /**
     * Constructor.
     * @param context reference to servlet context where application is running.
     * @param source reference to an object where resources can be loaded in its
     * library jar or war file.
     */
    public PropertiesFileLocator(ServletContext context, T source) {
        mContext = context;
        mSource = source;
        mFileName = DEFAULT_FILE_NAME;
    }

    /**
     * Constructor.
     * @param source reference to an object where resources can be loaded in its
     * library jar or war file.
     * @param fileName properties file name containing configuration.
     */
    public PropertiesFileLocator(T source, String fileName) {
        mContext = null;
        mSource = source;
        mFileName = fileName;
    }

    /**
     * Constructor.
     * @param context reference to servlet context where application is running.
     * @param source reference to an object where resources can be loaded in its
     * library jar or war file.
     * @param fileName properties file name containing configuration.
     */
    public PropertiesFileLocator(ServletContext context, T source, String fileName) {
        mContext = context;
        mSource = source;
        mFileName = fileName;
    }

    /**
     * Locates properties configuration based on provided parameters.
     * First properties are searched based on applicaiton context name, then
     * on user home and finally within a war or jar file.
     * @return properties configuration.
     * @throws IOException if an I/O error occurs.
     */
    public Properties locate() throws IOException {
        //properties file was found
        Properties props = new Properties();
        InputStream stream = locateResource();
        if (stream != null) {
            props.load(stream);
        }
        return props;
    }

    /**
     * Internal method to search for configuration file and return its
     * corresponding input stream.
     * @return input stream corresponding to configuration file.
     * @throws IOException if an I/O error occurs.
     */
    private InputStream locateResource() throws IOException {
        //get server user home location
        String home = System.getProperty("user.home");
        //for windows replace \ with /
        home = home.replaceAll("\\\\", "/");

        LOGGER.log(Level.INFO, "User home folder: {0}", home);

        if (mContext != null) {
            //Attempt to retrieve first properties files in home path with
            //same name as application context
            String contextPath = mContext.getContextPath();
            String path;
            if (!contextPath.isEmpty()) {
                path = "~" + contextPath + ".properties";
                path = path.replaceAll("~", home);

                File f = new File(path);
                if (f.exists()) {
                    LOGGER.log(Level.INFO, "Properties file: {0}", path);
                    return new FileInputStream(f);
                }
            }
        }

        //if not found, then attempt to find file at user home
        if (mFileName != null) {
            File f = new File(home, mFileName);
            if (f.exists()) {
                LOGGER.log(Level.INFO, "Properties file: {0}", f.getPath());
                return new FileInputStream(f);
            }
        }

        //if no file is found, then attempt to search it within war file
        InputStream stream = null;
        if (mSource != null) {
            stream = mSource.getClass().getResourceAsStream(mFileName);

            if (stream == null) {
                //no properties file was found
                LOGGER.log(Level.INFO, "Properties file not found.");
            }
        }
        return stream;
    }
}
