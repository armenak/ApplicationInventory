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

import static com.strider.appinv.agent.utilities.ApplicationProperties.loadProperties;

import java.io.IOException;
import java.util.Properties;

import com.strider.appinv.agent.domain.Application;
import com.strider.appinv.agent.utilities.ApplicationUtils;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import static org.apache.log4j.Logger.getLogger;

/**
 * Entry point to Application Scanner.
 */
public class Scanner {

    private static final Logger log = getLogger(Scanner.class);

    public static void main(String[] args) throws ParseException, ScannerException {

        if (args.length == 0) {
            log.info("To display usage info please type");
            log.info("    java -jar ai.jar com.strider.appinv.agent.Scanner --help");
            return;
        }

        final Options options = createOptions();
        final CommandLine line = getCommandLine(options, args);
        if (line.hasOption("help")) {
            help(options);
            return;
        }

        if (line.hasOption("debug")) {
            LogManager.getRootLogger().setLevel(Level.DEBUG);
        } else {
            LogManager.getRootLogger().setLevel(Level.INFO);
        }

        String scannerPropertyFile = "scanner.properties";
        if (line.hasOption("config")) {
            scannerPropertyFile = line.getOptionValue("config");
        }

        Properties scannerProperties = null;
        try {
            scannerProperties = loadProperties(scannerPropertyFile);
        } catch (IOException ioe) {
            throw new ScannerException("ERROR: Unable to load " + scannerPropertyFile, ioe);
        }
        if (scannerProperties == null) {
            throw new ScannerException("ERROR: Scanner property file is not defined.");
        }

        IScanner scanner = new AppScanner();
        Application application = null;
        if (line.hasOption("scan")) {
            scanner = new AppScanner();
            application = scanner.scan(scannerProperties);
        }
        if (line.hasOption("persist") && application != null) {
            ApplicationUtils.save(application, scannerProperties.getProperty("scan.output.path"));
        }
    }

    /**
     * Parses command line arguments
     *
     * @param options
     * @param args
     * @return CommandLine
     * @throws ParseException
     */
    private static CommandLine getCommandLine(final Options options, final String[] args)
            throws ParseException {
        final CommandLineParser parser = new GnuParser();
        CommandLine line = null;

        try {
            line = parser.parse(options, args);
        } catch (ParseException e) {
            help(options);
        }

        return line;
    }

    /**
     * Creates options for the command line
     *
     * @return Options
     */
    @SuppressWarnings("static-access")
    private static Options createOptions() {
        final Options options = new Options();
        options.addOption("h", "help", false, "Display help");
        options.addOption("s", "scan", false, "Scan resource");
        options.addOption("p", "persist", false, "Persist scan results");
        options.addOption("c", "config", true, "Defines scanner configuration property file");
        options.addOption("d", "debug", false, "Enable debug output");
        return options;
    }

    /**
     * Displays help
     *
     * @param options Command line options
     */
    private static void help(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Scanner", options);
    }
}