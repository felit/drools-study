package com.felit.drools.chapter07.model;

import java.io.Serializable;

/**
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -2078248745772001403L;
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
