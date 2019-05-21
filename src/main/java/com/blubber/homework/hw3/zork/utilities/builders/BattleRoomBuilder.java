package com.blubber.homework.hw3.zork.utilities.builders;

import com.blubber.homework.hw3.zork.entities.Mob;
import com.blubber.homework.hw3.zork.levels.BattleRoom;

public class BattleRoomBuilder {

    private BattleRoom room = new BattleRoom();

    public BattleRoomBuilder initMob(Mob mob){
        room.setMob(mob);
        return this;
    }
}
