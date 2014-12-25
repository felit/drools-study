package com.felit.drools.chapter03.task.element.model;

import java.io.Serializable;

/**
 */
public class RuleDomain  implements Serializable{

    private static final long serialVersionUID = -9207723333118997361L;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
