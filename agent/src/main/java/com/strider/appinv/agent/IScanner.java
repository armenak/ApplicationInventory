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

import java.util.Properties;

/**
 * Defines contract for all scanners
 * @author meaton
 */
public interface IScanner {
    /**
     * Scans an application or resource.
     * @param scannerProperties Scanner properties file
     */
    Application scan(Properties scannerProperties);

    /**
     * Persists an application or resource.
     * @param scannerProperties Scanner properties file
     * @param application Scanned application to persist
     */
    void persist(Properties scannerProperties, Application application) throws ScannerException;
}
