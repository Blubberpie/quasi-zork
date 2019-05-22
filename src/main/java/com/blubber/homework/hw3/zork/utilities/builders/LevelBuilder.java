package com.blubber.homework.hw3.zork.utilities.builders;

import com.blubber.homework.hw3.zork.levels.Level;
import com.blubber.homework.hw3.zork.levels.Room;

import java.util.Set;

public class LevelBuilder{

    private Level level = new Level();

    public LevelBuilder setRooms(Set<Room> rooms){
        level.setRooms(rooms);
        return this;
    }

    public LevelBuilder setStartRoom(Room startRoom){
        level.setStartRoom(startRoom);
        return this;
    }

    public LevelBuilder initLevel(){
        level.initLevel();
        return this;
    }

    public LevelBuilder clearLevel(){
        level.clearLevel();
        return this;
    }

    public LevelBuilder setTotalMonsters(int totalMonsters){
        level.setTotalMonsters(totalMonsters);
        return this;
    }

    public Level getLevel(){ return level; }
}
