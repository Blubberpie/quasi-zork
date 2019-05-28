package com.blubber.homework.hw3.zork.utilities.packs;

import com.blubber.homework.hw3.zork.levels.Level;
import com.blubber.homework.hw3.zork.levels.Room;
import com.blubber.homework.hw3.zork.utilities.builders.LevelBuilder;
import com.blubber.homework.hw3.zork.utilities.enums.Direction;
import com.blubber.homework.hw3.zork.utilities.enums.Mobs;
import com.blubber.homework.hw3.zork.utilities.enums.Weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LevelTwo {

    Level createLevelTwo(){
        List<Room> rooms = new ArrayList<>(9);
        rooms.add(RoomAssembly.createWeaponLootRoom(Weapons.SWORD));
        rooms.add(RoomAssembly.createBattleRoom(Mobs.STALFOS));
        rooms.add(RoomAssembly.createBattleRoom(Mobs.STALLORD));
        rooms.add(RoomAssembly.createBattleRoom(Mobs.LIZALFOS));
        rooms.add(RoomAssembly.createBattleRoom(Mobs.STALFOS));
        rooms.add(RoomAssembly.createBattleRoom(Mobs.REDEAD));
        rooms.add(RoomAssembly.createWeaponLootRoom(Weapons.BOW));
        rooms.add(RoomAssembly.createWeaponLootRoom(Weapons.HAMMER));
        rooms.add(RoomAssembly.createBattleRoom(Mobs.LIKE_LIKE));

        rooms.get(0).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.WEST, rooms.get(1));
                put(Direction.NORTH, rooms.get(2));
                put(Direction.EAST, rooms.get(3));}});

        rooms.get(1).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.WEST, rooms.get(4));
                put(Direction.EAST, rooms.get(0));}});

        rooms.get(2).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, rooms.get(0));}});

        rooms.get(3).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.WEST, rooms.get(0));
                put(Direction.NORTH, rooms.get(5));}});

        rooms.get(4).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, rooms.get(6));
                put(Direction.NORTH, rooms.get(8));
                put(Direction.EAST, rooms.get(1));}});

        rooms.get(5).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, rooms.get(3));
                put(Direction.NORTH, rooms.get(7));}});

        rooms.get(6).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.NORTH, rooms.get(4));}});

        rooms.get(7).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, rooms.get(5));}});

        rooms.get(8).setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, rooms.get(4));}});

        return new LevelBuilder().setRooms(new HashSet<Room>(){{ addAll(rooms); }})
                .setName("Level 2")
                .setStartRoom(rooms.get(0))
                .setTotalMonsters(6)
                .initLevel()
                .getLevel();
    }

    private static final LevelTwo LEVEL_TWO = new LevelTwo();
    public static LevelTwo getInstance(){ return LEVEL_TWO; }
}
