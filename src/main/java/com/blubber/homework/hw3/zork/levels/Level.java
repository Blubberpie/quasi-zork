package com.blubber.homework.hw3.zork.levels;

import java.util.Set;

public class Level {

    private String name;

    private Set<Room> rooms;
    private Room startRoom;
    private boolean levelCleared;


    private int totalMonsters;
    private int monstersDefeated = 0;

    public boolean setLevelStatus() {
        if(getTotalMonsters() <= getMonstersDefeated()){
            this.clearLevel();
            return true;
        }
        return false;
    }

    //    private Level nextLevel;
    //    public Level getNextLevel() { return nextLevel; }
    //    public void setNextLevel(Level nextLevel) { this.nextLevel = nextLevel;}

    public void setTotalMonsters(int totalMonsters) { this.totalMonsters = totalMonsters; }
    public Room getStartRoom() { return startRoom; }
    public Set<Room> getRooms() { return rooms; }
    public boolean isCleared() { return levelCleared; }
    public int getTotalMonsters() { return totalMonsters; }
    public int getMonstersDefeated() { return monstersDefeated; }

    public String getName() { return name; }
    public void setRooms(Set<Room> rooms){ this.rooms = rooms; }
    public void setStartRoom(Room startRoom){ this.startRoom = startRoom; }
    public void clearLevel(){ levelCleared = true; }
    public void initLevel(){ levelCleared = false; }
    public void setName(String name) { this.name = name; }

    public void incrementMonstersDefeated(){
        monstersDefeated++;
        setLevelStatus();
    }
}