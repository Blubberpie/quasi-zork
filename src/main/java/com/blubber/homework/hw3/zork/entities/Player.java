package com.blubber.homework.hw3.zork.entities;

import com.blubber.homework.hw3.zork.items.Weapon;

import java.util.HashSet;
import java.util.Set;

public class Player extends Entity{

//    private Set<Consumable> consumables;
    private Set<Weapon> weapons;

    public Player(String name){
        super();
//        consumables = new HashSet<>();
        weapons = new HashSet<>();
        setBaseDamage(20.0);
        setBuffedDamage(this.getBaseDamage());
        setMaximumHealth(100.0);
        setHealth(this.getMaximumHealth());
        this.setName(name); // Since this game is single-player only, we're safe
    }

    public boolean attack(Entity victim){
        attackDefault(victim);
        return (!victim.isAlive()); // Return true if dead
    }

//    public Set<Consumable> getConsumables() { return consumables; }
    public Set<Weapon> getWeapons() { return weapons; }
    public void addWeapon(Weapon weapon){ weapons.add(weapon); }
    public void removeWeapon(Weapon weapon){ weapons.remove(weapon); }

}
