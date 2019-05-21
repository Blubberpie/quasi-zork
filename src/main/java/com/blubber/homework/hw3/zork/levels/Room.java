package com.blubber.homework.hw3.zork.levels;

import com.blubber.homework.hw3.zork.utilities.enums.Direction;

import java.util.Map;

public abstract class Room {

    private boolean active;
    private Map<Direction, Room> connectedRooms; // Room = null if not connected

    public void setActive() { active = true; }
    public void setInactive() { active = false; }

    public Map<Direction, Room> getConnectedRooms(){ return connectedRooms; }
    public boolean isActive() { return active; }
}
