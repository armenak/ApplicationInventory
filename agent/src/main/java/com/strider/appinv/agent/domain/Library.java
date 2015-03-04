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
package com.strider.appinv.agent.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * JAXB class that defines library
 *
 * @author Matt Eaton
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Library {


    @XmlAttribute(name = "Location")
    private String location;
    
    @XmlAttribute(name = "FileName")
    private String fileName;

    @XmlAttribute(name = "Type")
    private String type;
    
    @XmlAttribute(name = "Name")
    private String name;

    @XmlAttribute(name = "Version")
    private String version;


    /**
     * Getter method for name attribute
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for fileName attribute
     *
     * @return String
     */
    public String getFileName() {
        return this.fileName;
    }
    
    /**
     * Getter method for type attribute
     *
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter method for version attribute
     *
     * @return String
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Getter method for location attribute
     *
     * @return String
     */
    public String getLocation() {
        return this.location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}