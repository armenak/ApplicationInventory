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
