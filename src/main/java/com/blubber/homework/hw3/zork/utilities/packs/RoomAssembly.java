package com.blubber.homework.hw3.zork.utilities.packs;

import com.blubber.homework.hw3.zork.levels.BattleRoom;
import com.blubber.homework.hw3.zork.levels.LootRoom;
import com.blubber.homework.hw3.zork.levels.Room;
import com.blubber.homework.hw3.zork.utilities.builders.MobBuilder;
import com.blubber.homework.hw3.zork.utilities.builders.RoomBuilder;
import com.blubber.homework.hw3.zork.utilities.builders.WeaponBuilder;
import com.blubber.homework.hw3.zork.utilities.enums.Mobs;
import com.blubber.homework.hw3.zork.utilities.enums.Weapons;

public class RoomAssembly {

    static Room createWeaponLootRoom(Weapons weaponInfo){
        return new RoomBuilder(LootRoom.class)
                .initItem(
                        new WeaponBuilder()
                                .setInfo(weaponInfo)
                                .getWeapon())
                .setRoomName("Weapon Room")
                .getRoom();
    }

    static Room createBattleRoom(Mobs mobInfo){
        return new RoomBuilder(BattleRoom.class)
                .initMob(
                        new MobBuilder()
                                .setMob(mobInfo)
                                .getMob())
                .setRoomName("Battle Room")
                .getRoom();
    }
}
