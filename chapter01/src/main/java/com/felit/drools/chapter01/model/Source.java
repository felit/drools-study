package com.felit.drools.chapter01.model;

/**
 * 需求来源
 */
public class Source {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                '}';
    }
}

