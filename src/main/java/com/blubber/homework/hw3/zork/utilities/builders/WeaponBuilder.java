package com.blubber.homework.hw3.zork.utilities.builders;

import com.blubber.homework.hw3.zork.items.Weapon;
import com.blubber.homework.hw3.zork.utilities.enums.Weapons;

public class WeaponBuilder {

    private Weapon weapon = new Weapon();

    public WeaponBuilder setInfo(Weapons weapon){
        this.weapon.setName(weapon.toString());
        this.weapon.setDamage(weapon.getDamage());
        return this;
    }

    public Weapon getWeapon() { return weapon; }
}
