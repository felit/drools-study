package com.felit.drools.chapter01.model;

import java.util.Date;

/**
 *
 */
public class Application {
    private Date dateApplied;
    private boolean valid;

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
