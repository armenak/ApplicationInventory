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
import java.util.regex.Matcher;

/**
 * Unit tests for ApplicationUtils
 */
public class ApplicationUtilsTest extends TestCase {

    /**
     * Test library version pattern matching
     * @author Matthew Eaton
     * @throws Exception
     */
    public void testRegEx1() throws Exception {
        String regEx1 = "([A-Za-z0-9-]*)-([0-9]\\d*)\\.(\\d+)\\.(\\d+)\\.(GA)\\.jar";
        Matcher matcher = ApplicationUtils.determineLibraryPattern("lib-name-1.2.1.GA.jar", regEx1);
        assertNotNull(matcher);
        assertEquals("lib-name", ApplicationUtils.extractLibraryName(matcher));
        assertEquals("1.2.1.GA", ApplicationUtils.extractVersion(matcher));
    }

    /**
     * Test library version pattern matching
     * @author Matthew Eaton
     * @throws Exception
     */
    public void testRegEx2() throws Exception {
        String regEx1 = "([A-Za-z0-9-]*)-([0-9]\\d*)\\.(\\d+)\\.(\\d+)\\.(RELEASE)\\.jar";
        Matcher matcher = ApplicationUtils.determineLibraryPattern("lib-name-1.2.1.RELEASE.jar", regEx1);
        assertNotNull(matcher);
        assertEquals("lib-name", ApplicationUtils.extractLibraryName(matcher));
        assertEquals("1.2.1.RELEASE", ApplicationUtils.extractVersion(matcher));
    }

    /**
     * Test library version pattern matching
     * @author Matthew Eaton
     * @throws Exception
     */
    public void testRegEx3() throws Exception {
        String regEx1 = "([A-Za-z0-9-]*)-([0-9]\\d*)\\.(\\d+)\\.(\\d+)\\.jar";
        Matcher matcher = ApplicationUtils.determineLibraryPattern("lib-name-1.2.1.jar", regEx1);
        assertNotNull(matcher);
        assertEquals("lib-name", ApplicationUtils.extractLibraryName(matcher));
        assertEquals("1.2.1", ApplicationUtils.extractVersion(matcher));
    }

    /**
     * Test library version pattern matching
     * @author Matthew Eaton
     * @throws Exception
     */
    public void testRegEx4() throws Exception {
        String regEx1 = "([A-Za-z0-9-]*)-([0-9]\\d*)\\.(\\d+)\\.jar";
        Matcher matcher = ApplicationUtils.determineLibraryPattern("lib-name-1.2.jar", regEx1);
        assertNotNull(matcher);
        assertEquals("lib-name", ApplicationUtils.extractLibraryName(matcher));
        assertEquals("1.2", ApplicationUtils.extractVersion(matcher));
    }

    /**
     * Test library version pattern matching
     * @author Matthew Eaton
     * @throws Exception
     */
    public void testRegEx5() throws Exception {
        String regEx1 = "([A-Za-z0-9-]*)-([0-9]\\d*)\\.jar";
        Matcher matcher = ApplicationUtils.determineLibraryPattern("lib-name-1.jar", regEx1);
        assertNotNull(matcher);
        assertEquals("lib-name", ApplicationUtils.extractLibraryName(matcher));
        assertEquals("1", ApplicationUtils.extractVersion(matcher));
    }

    /**
     * Test library version pattern matching
     * @author Matthew Eaton
     * @throws Exception
     */
    public void testRegEx6() throws Exception {
        String regEx1 = "([A-Za-z0-9-]*)([0-9]\\d*)\\.jar";
        Matcher matcher = ApplicationUtils.determineLibraryPattern("lib-name1.jar", regEx1);
        assertNotNull(matcher);
        assertEquals("lib-name", ApplicationUtils.extractLibraryName(matcher));
        assertEquals("1", ApplicationUtils.extractVersion(matcher));
    }

    /**
     * Test library version pattern matching
     * @author Matthew Eaton
     * @throws Exception
     */
    public void testRegEx7() throws Exception {
        String regEx1 = "([A-Za-z0-9-]*)-([0-9]\\d*)\\.(\\d+)\\.(\\d+)\\.([0-9]\\d*)\\.jar";
        Matcher matcher = ApplicationUtils.determineLibraryPattern("lib-name-1.2.3.456.jar", regEx1);
        assertNotNull(matcher);
        assertEquals("lib-name", ApplicationUtils.extractLibraryName(matcher));
        assertEquals("1.2.3.456", ApplicationUtils.extractVersion(matcher));
    }
}
