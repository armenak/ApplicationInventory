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
package com.strider.appinv.agent;

import com.strider.appinv.agent.domain.Application;
import com.strider.appinv.agent.domain.Library;
import com.strider.appinv.agent.utilities.ApplicationProperties;
import com.strider.appinv.agent.utilities.ApplicationUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;

import static org.apache.log4j.Logger.getLogger;

/**
 * Class to scan application resources, initially just web archives
 *
 * @author Matt Eaton
 */
public class AppScanner implements IScanner {
    private static final Logger log = getLogger(AppScanner.class);
    
    private static final String JAR = "jar";

    @Override
    public Application scan(Properties scannerProperties) {

        Application application = new Application();
        application.setName(scannerProperties.getProperty(ApplicationProperties.PROP_RESOURCE_NAME));
        application.setType(scannerProperties.getProperty(ApplicationProperties.PROP_RESOURCE_TYPE));
        log.info("Scanning resource [" + application.getName() + "], type [" + application.getType() + "]");

        JarFile jar = null;
        try {
            jar = new JarFile(new File(scannerProperties.getProperty(ApplicationProperties.PROP_RESOURCE_PATH)
                    + scannerProperties.getProperty(ApplicationProperties.PROP_RESOURCE_NAME)));
        } catch (IOException e) {
            log.error("Unable to open file.", e);
            //e.printStackTrace();
        }
        try {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = (JarEntry) entries.nextElement();
                if (entry.getName().endsWith(JAR)) {
                    application.addLibrary(getLibrary(entry, scannerProperties));
                }
            }
        } finally {
            if (jar != null) {
                try {
                    jar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return application;
    }

    /**
     * Extract library information from JarEntry
     *
     * @param jarEntry Jar file entry in archive
     * @param scannerProperties Scanner properties
     * @return Library
     */
    private Library getLibrary(JarEntry jarEntry, Properties scannerProperties) {
        log.info("Jar [" + jarEntry.getName() + "]");

        int pathEnd = jarEntry.getName().lastIndexOf('/');
        String path = jarEntry.getName().substring(0, pathEnd);
        String fileName = jarEntry.getName().substring(pathEnd + 1);

        Matcher matcher = ApplicationUtils.determineLibraryPattern(fileName, 
                scannerProperties.getProperty(ApplicationProperties.PROP_VERSION_REGEX));
        Library library = new Library();
        library.setFileName(fileName);
        library.setLocation(path);
        library.setType(JAR);

        library.setName(ApplicationUtils.extractLibraryName(matcher));
        library.setVersion(ApplicationUtils.extractVersion(matcher));

        return library;
    }

    @Override
    public void persist(Properties scannerProperties, Application application) throws SecurityException, ScannerException {
        log.info("Saving to " + scannerProperties.getProperty(ApplicationProperties.PROP_SCAN_OUTPUT_PATH)
                + "result.xml");
        ApplicationUtils.save(application, scannerProperties.getProperty(ApplicationProperties.PROP_SCAN_OUTPUT_PATH) +
                "result.xml");
    }

}
