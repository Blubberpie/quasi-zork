package com.blubber.homework.hw3.zork.entities;

import com.blubber.homework.hw3.zork.items.Consumable;
import com.blubber.homework.hw3.zork.items.Weapon;

import java.util.HashSet;
import java.util.Set;

public class Player extends Entity{

//    private Set<Consumable> consumables;
    private Set<Weapon> weapons;

    public Player(){
        super();
//        consumables = new HashSet<>();
        weapons = new HashSet<>();
        setDamage(10.0);
        setMaximumHealth(100.0);
        setHealth(100.0);
    }

//    public void attack(){
//
//    }

//    public Set<Consumable> getConsumables() { return consumables; }
    public Set<Weapon> getWeapons() { return weapons; }
    public void addWeapon(Weapon weapon){ weapons.add(weapon); }
    public void removeWeapon(Weapon weapon){ weapons.remove(weapon); }

}
