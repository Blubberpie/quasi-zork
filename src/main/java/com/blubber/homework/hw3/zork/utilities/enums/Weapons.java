package com.blubber.homework.hw3.zork.utilities.enums;

public enum Weapons {
    SWORD("sword", 5.0),
    BOW("bow", 6.0),
    HAMMER("hammer", 7.0),
    SPEAR("spear", 8.0),
    AXE("axe", 9.0);

    private String name;
    private Double damage;

    Weapons(String name, Double damage){
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String toString() { return name; }

    public double getDamage() { return damage; }
}
