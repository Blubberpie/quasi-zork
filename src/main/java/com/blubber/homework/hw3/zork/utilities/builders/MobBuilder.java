package com.blubber.homework.hw3.zork.utilities.builders;

import com.blubber.homework.hw3.zork.entities.Mob;
import com.blubber.homework.hw3.zork.utilities.enums.Mobs;

public class MobBuilder {

    private Mob mob = new Mob();

    public MobBuilder setMob(Mobs mobInfo){
        this.mob.setName(mobInfo.toString());
        this.mob.setHitProbability(mobInfo.getHitProbability());
        this.mob.setDamage(mobInfo.getDamage());
        this.mob.setMaximumHealth(mobInfo.getMaxHealth());
        this.mob.setHealth(mobInfo.getMaxHealth());
        return this;
    }

    public Mob getMob() { return mob; }
}
