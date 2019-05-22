package com.blubber.homework.hw3.zork.levels;

import java.util.Set;

public class Level {
    private Set<Room> rooms;
    private Room startRoom;
    private boolean levelCleared;

    private Level nextLevel;

    private int totalMonsters;
    private int monstersDefeated = 0;

    public void setLevelStatus() {
        if(getTotalMonsters() == getMonstersDefeated()){
            this.clearLevel();
        }
    }

    public Room getStartRoom() { return startRoom; }
    public Set<Room> getRooms() { return rooms; }
    public boolean isCleared() { return levelCleared; }
    public int getTotalMonsters() { return totalMonsters; }
    public int getMonstersDefeated() { return monstersDefeated; }

    public void setRooms(Set<Room> rooms){ this.rooms = rooms; }
    public void setStartRoom(Room startRoom){ this.startRoom = startRoom; }
    public void clearLevel(){ levelCleared = true; }
    public void initLevel(){ levelCleared = false; }
    public void setNextLevel(Level nextLevel) { this.nextLevel = nextLevel;}
    public void setTotalMonsters(int totalMonsters) { this.totalMonsters = totalMonsters; }
}