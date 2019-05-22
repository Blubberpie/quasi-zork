package com.blubber.homework.hw3.zork.utilities.initializers;

import com.blubber.homework.hw3.zork.levels.BattleRoom;
import com.blubber.homework.hw3.zork.levels.Level;
import com.blubber.homework.hw3.zork.levels.LootRoom;
import com.blubber.homework.hw3.zork.levels.Room;
import com.blubber.homework.hw3.zork.utilities.builders.*;
import com.blubber.homework.hw3.zork.utilities.enums.Direction;
import com.blubber.homework.hw3.zork.utilities.enums.Mobs;
import com.blubber.homework.hw3.zork.utilities.enums.Weapons;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This is a hard-coded initialization of levels and rooms in the game.
 * Do NOT change unless you know what you're doing!!
 */
public final class ZorkInitializer {

    private Level level1, level2, level3;

    private ZorkInitializer(){}

    public Level initialize(){
        level1 = createLevelOne();
        level2 = createLevelTwo();
        level3 = createLevelThree();

//        level1.setNextLevel(level2);
//        level2.setNextLevel(level3);

        return level1;
    }

    private Level createLevelOne(){
        Room startRoom,r1,r2,r3,r4,r5,r6,r7,r8;
        startRoom = createWeaponLootRoom(Weapons.SWORD);
        r1 = createBattleRoom(Mobs.STALFOS);
        r2 = createBattleRoom(Mobs.STALLORD);
        r3 = createBattleRoom(Mobs.LIZALFOS);
        r4 = createBattleRoom(Mobs.STALFOS);
        r5 = createBattleRoom(Mobs.REDEAD);
        r6 = createWeaponLootRoom(Weapons.BOW);
        r7 = createWeaponLootRoom(Weapons.HAMMER);
        r8 = createBattleRoom(Mobs.LIKE_LIKE);

        startRoom.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.WEST, r1);
            put(Direction.NORTH, r2);
            put(Direction.EAST, r3);}});

        r1.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.WEST, r4);
            put(Direction.EAST, startRoom);}});

        r2.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, startRoom);}});

        r3.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.WEST, startRoom);
            put(Direction.NORTH, r5);}});

        r4.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, r6);
            put(Direction.NORTH, r8);
            put(Direction.EAST, r1);}});

        r5.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, r3);
            put(Direction.NORTH, r7);}});

        r6.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.NORTH, r4);}});

        r7.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, r5);}});

        r8.setConnectedRooms(new HashMap<Direction, Room>(){
            {put(Direction.SOUTH, r4);}});

        return new LevelBuilder().setRooms(new HashSet<Room>(){{
            add(startRoom); add(r1); add(r2); add(r3);
            add(r4); add(r5); add(r6); add(r7); add(r8);
        }}).setName("Level 1").setStartRoom(startRoom).setTotalMonsters(6).initLevel().getLevel();
    }

    private Room createWeaponLootRoom(Weapons weaponInfo){
        return new RoomBuilder(LootRoom.class)
                .initItem(
                        new WeaponBuilder()
                                .setInfo(weaponInfo)
                                .getWeapon())
                .setRoomName("Weapon Room")
                .getRoom();
    }

    private Room createBattleRoom(Mobs mobInfo){
        return new RoomBuilder(BattleRoom.class)
                .initMob(
                        new MobBuilder()
                                .setMob(mobInfo)
                                .getMob())
                .setRoomName("Battle Room")
                .getRoom();
    }

    private Level createLevelTwo(){
        return null;
    }

    private Level createLevelThree(){
        return null;
    }

    private static final ZorkInitializer ZORK_INITIALIZER = new ZorkInitializer();

    public static ZorkInitializer getInstance(){ return ZORK_INITIALIZER; }
}
