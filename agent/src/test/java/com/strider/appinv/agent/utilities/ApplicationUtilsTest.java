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
