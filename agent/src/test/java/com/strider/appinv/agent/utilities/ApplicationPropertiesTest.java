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

import junit.framework.TestCase;

import java.util.Properties;

/**
 * Unit tests for ApplicationProperties
 */
public class ApplicationPropertiesTest extends TestCase {
    Properties props = null;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        props = ApplicationProperties.loadPropertiesFromClassPath("scanner.properties");
    }

    /**
     * Test load property file
     * @author Matthew Eaton
     * @throws Exception
     */
    public void testLoad() throws Exception {

        assertNotNull(props);
        assertEquals("hrcatalog.war", props.getProperty(ApplicationProperties.PROP_RESOURCE_NAME));
        assertEquals("war", props.getProperty(ApplicationProperties.PROP_RESOURCE_TYPE));
        assertEquals("/opt/tomcat/webapps", props.getProperty(ApplicationProperties.PROP_RESOURCE_PATH));
        assertEquals("/home/scanner/output", props.getProperty(ApplicationProperties.PROP_SCAN_OUTPUT_PATH));
        assertEquals("([A-Za-z0-9-]*)-([0-9]\\d*)\\.(\\d+)\\.(\\d+)\\.(GA)\\.jar", 
                props.getProperty(ApplicationProperties.PROP_VERSION_REGEX));

    }
}
