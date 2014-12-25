package com.felit.drools.chapter03.task.element.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class RuleDomain implements Serializable {

    private static final long serialVersionUID = -9207723333118997361L;
    private String title;

    public RuleDomain(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static List<RuleDomain> getRuleDomains() {
        List<RuleDomain> result = new ArrayList<RuleDomain>();
        result.add(new RuleDomain("test1"));
        result.add(new RuleDomain("test2"));
        return result;
    }

    @Override
    public String toString() {
        return "RuleDomain{" +
                "title='" + title + '\'' +
                '}';
    }
}
