package com.blubber.homework.hw3.zork.utilities.enums;

public enum DamageItems {
    BOMB("Bomb", 70.0);

    private String name;
    private double damageValue;

    DamageItems(String name, double damageValue){
        this.name = name;
        this.damageValue = damageValue;
    }

    @Override
    public String toString() { return name; }
    public double getDamageValue() { return damageValue; }
}
