package com.blubber.homework.hw3.zork.levels;

import com.blubber.homework.hw3.zork.entities.Mob;

public class BattleRoom extends Room {

    private Mob mob;
    private boolean defeated = false;

    public void setMob(Mob mob){ this.mob = mob; }
    public void clearRoom(){ defeated = true; }

    public Mob getMob() { return mob; }

    public boolean isClear() {
        return defeated;
    }
}
