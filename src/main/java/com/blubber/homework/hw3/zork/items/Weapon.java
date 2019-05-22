package com.blubber.homework.hw3.zork.items;

public class Weapon extends Item{

    private double damage;

    public Weapon(){}

    public Weapon(String name){
        super(name);
    }

    public void setDamage(double damage) { this.damage = damage; }

    public double getDamage() { return damage; }
}
