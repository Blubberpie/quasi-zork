package com.blubber.homework.hw3.zork.levels;

import com.blubber.homework.hw3.zork.utilities.Direction;

import java.util.Map;

public abstract class Room {

    private Map<Direction, Room> connectedRooms; // Room = null if not connected

    public Map<Direction, Room> getConnectedRooms(){ return connectedRooms; }

}
