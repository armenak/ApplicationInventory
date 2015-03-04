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

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

import javax.xml.bind.annotation.*;

/**
 * JAXB class that defines application
 *
 * @author Matt Eaton
 */

@XmlRootElement(name="Application")
@XmlAccessorType(XmlAccessType.FIELD)
public class Application {

    @XmlAttribute(name = "Name")
    private String name;

    @XmlAttribute(name = "Type")
    private String type;

    @XmlElementWrapper(name = "Libraries")
    @XmlElement(name = "Library")
    private List<Library> libraries;


    /**
     * Getter method for name attribute
     *
     * @return String
     */
    public String getName() {
        return this.name;
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
     * Getter method for libraries attribute
     *
     * @return List<Library>
     */
    public List<Library> getLibraries() {
        if (this.libraries != null) {
            return unmodifiableList(this.libraries);
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addLibrary(Library library) {
        if (this.libraries == null) {
            this.libraries = new ArrayList<Library>();
        }
        this.libraries.add(library);
    }

}