/*
 * 
 * Copyright 2015, Matt Eaton, and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */
package com.strider.appinv.agent.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

/**
 * @author Matt Eaton
 */
public final class ApplicationProperties {
    
    public static final String PROP_RESOURCE_NAME = "resource.name";
    public static final String PROP_RESOURCE_TYPE = "resource.type";
    public static final String PROP_RESOURCE_PATH = "resource.path";
    public static final String PROP_SCAN_OUTPUT_PATH = "scan.output.path";
    public static final String PROP_VERSION_REGEX = "version.regex";


    private static Logger log = getLogger(ApplicationProperties.class);

    /**
     * Load properties from classpath
     *
     * @param fileName
     * @return Properties
     */
    public static Properties loadPropertiesFromClassPath(String fileName) {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = ApplicationProperties.class.getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                log.warn("Unable to find " + fileName);
                return null;
            }
            // load a properties file from class path, inside static method
            properties.load(input);
        } catch (IOException ex) {
            log.error(ex.toString());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
        }

        return properties;
    }

    /**
     * Load properties from the path/file specified
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Properties loadProperties(String fileName) throws IOException {

        Properties properties = new Properties();
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
            properties.load(in);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException ex) {
                    log.error(ex.toString(), ex);
                }
            }
        }
        return properties;
    }
}