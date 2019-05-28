package com.blubber.homework.hw3.zork.levels;

import com.blubber.homework.hw3.zork.utilities.enums.Direction;

import java.util.Map;

public abstract class Room {

    private String name;
    private boolean active;
    private boolean visited;
    private Map<Direction, Room> connectedRooms; // Room = null if not connected

    public String isVisitedString() {
        if (isVisited()){
            return "visited";
        }else{
            return "unvisited";
        }
    }

    public abstract boolean isClear();
    public abstract void clearRoom();

    public void setActive() { active = true; }
    public void setInactive() { active = false; }
    public void setConnectedRooms(Map<Direction, Room> connectedRooms){
        this.connectedRooms = connectedRooms;
    }
    public void setVisited() { this.visited = true; }
    public void setName(String name) { this.name = name; }

    public Map<Direction, Room> getConnectedRooms(){ return connectedRooms; }
    public boolean isActive() { return active; }
    public String getName() { return name; }
    public boolean isVisited() { return visited; }

}
