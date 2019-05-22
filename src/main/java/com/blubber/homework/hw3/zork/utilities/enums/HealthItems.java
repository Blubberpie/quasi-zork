package com.blubber.homework.hw3.zork.utilities.enums;

public enum HealthItems {
    HEALTH_POTION("Health Potion", 50.0);

    private String name;
    private double healValue;

    HealthItems(String name, double healValue){
        this.name = name;
        this.healValue = healValue;
    }

    @Override
    public String toString() { return name; }
    public double getHealValue() { return healValue; }
}