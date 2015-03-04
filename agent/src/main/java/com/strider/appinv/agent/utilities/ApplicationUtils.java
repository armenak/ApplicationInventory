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

import static javax.xml.bind.JAXBContext.newInstance;
import static org.apache.log4j.Logger.getLogger;

import com.strider.appinv.agent.ScannerException;
import com.strider.appinv.agent.domain.Application;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class providing application utilities
 * 
 * @author Matt Eaton
 */
public class ApplicationUtils {
    private static Logger log = getLogger(ApplicationUtils.class);


    /**
     * Save Application object to file as XML
     * @param application Application object to save
     * @param outputPath Path where xml will be saved to
     * @throws com.strider.appinv.agent.ScannerException
     */
    public static void save(Application application, String outputPath) throws ScannerException {

        log.info("ApplicationUtils.save() path: " + outputPath);

        try {
            JAXBContext jc = newInstance(Application.class);
            Marshaller marshaller = jc.createMarshaller();

            File file = new File(outputPath + application.getName() + ".xml");
            // output pretty printed
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(application, file);
        } catch (JAXBException je) {
            log.error(je.toString());
            throw new ScannerException(je.toString(), je);
        }
    }

    /**
     * Name of library without version info
     * @param matcher Matcher that matches library format
     * @return String Library without version info
     */
    public static String extractLibraryName(Matcher matcher) {
        if (matcher != null) {
            return matcher.group(1);    
        }
        
        return null;  
    }

    /**
     * Version of library
     * @param matcher Matcher that matches library format
     * @return String Version of library
     */
    public static String extractVersion(Matcher matcher) {
        if (matcher != null) {
            StringBuffer version = new StringBuffer(10);
            int group = 2;
            while (group <= matcher.groupCount()) {
                version.append(matcher.group(group));
                group++;
                version.append(group <= matcher.groupCount() ? ".":"");
            }
            return version.toString();
        }

        return null;
    }

    /**
     * Returns true if library names follows standard naming
     * @param library Name of library
     * @param patterns Regex patterns for library name
     * @return true if matches standard
     */
    public static Matcher determineLibraryPattern(String library, String patterns) {
        Pattern pattern = null;
        Matcher matcherCurrent = null;
        int regexElement = 0;
 
        String[] regExPatterns = patterns.split("\\|");
        while (regexElement < regExPatterns.length) {
            pattern = Pattern.compile(regExPatterns[regexElement]);
            matcherCurrent = pattern.matcher(library);
            if (matcherCurrent.matches()) {
                log.info("Matches pattern " + regExPatterns[regexElement]);
                return matcherCurrent;
            }
            regexElement++;
        }

        return null;       
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("([A-Za-z0-9-]*)-([0-9]\\d*)\\.(\\d+)\\.(\\d+)\\.([0-9]\\d*)\\.jar");
        Matcher matcherCurrent = pattern.matcher("ibatis-sqlmap-2.3.4.726.jar");
        System.out.println("Result = " + matcherCurrent.matches());
        System.out.println("Match count = " + matcherCurrent.groupCount());
        int group = 0;
        while (group <= matcherCurrent.groupCount()) {
            System.out.println("Match count = " + matcherCurrent.group(group));
            group++;
        }
    }
}
