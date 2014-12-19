package com.felit.drools.chapter01.model.monitor;

/**
 *
 */
public class Fire {
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Fire{" +
                "room=" + room +
                '}';
    }
}
