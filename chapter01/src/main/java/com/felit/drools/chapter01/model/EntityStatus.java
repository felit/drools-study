package com.felit.drools.chapter01.model;

/**
 *
 */
public class EntityStatus {
    public static enum Status{
        AVAILABLE,DISABLE
    }
    private String title;
    private Status status;
    private int count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        if (this.count > 10) {
            this.status = Status.DISABLE;
        }
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "EntityStatus{" +
                "title='" + title + '\'' +
                ", status=" + status +
                ", count=" + count +
                '}';
    }
}
