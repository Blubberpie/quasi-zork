package com.blubber.homework.hw3.zork.utilities.enums;

public enum Mobs {
    STALFOS("Stalfos", 20.0, 10.0, 0.4),
    LIZALFOS("Lizalfos", 20.0, 11.0, 0.43),
    REDEAD("Redead", 20.0, 20.0, 0.3),
    LIKE_LIKE("Like-Like", 20.0, 23.0, 0.3),
    STALLORD("Stallord", 20.0, 40.0, 0.24),
    ARMOS("Armos", 20.0, 10.0, 0.4),
    BEAMOS("Beamos", 20.0, 11.0, 0.43),
    SHADOW_BEAST("Shadow Beast", 50.0, 20.0, 0.3),
    KARGAROK("Kargarok",20.0, 23.0, 0.3),
    ZANT("Zant", 20.0, 30.0, 0.24),
    BOKOBLIN("Bokoblin",20.0, 10.0, 0.4),
    KEESE("Keese",20.0, 11.0,0.43),
    DARKNUT("Darknut",20.0, 20.0,0.3),
    PHANTOM_GANON("Phantom Ganon",20.0, 23.0, 0.3),
    GANONDORF("Ganondorf",20.0, 30.0, 0.24);
//    STALFOS("Stalfos", 70.0, 10.0, 0.4),
//    LIZALFOS("Lizalfos", 50.0, 11.0, 0.43),
//    REDEAD("Redead", 100.0, 20.0, 0.3),
//    LIKE_LIKE("Like-Like", 100.0, 23.0, 0.3),
//    STALLORD("Stallord", 150.0, 40.0, 0.24),
//    ARMOS("Armos", 50.0, 10.0, 0.4),
//    BEAMOS("Beamos", 50.0, 11.0, 0.43),
//    SHADOW_BEAST("Shadow Beast", 50.0, 20.0, 0.3),
//    KARGAROK("Kargarok",50.0, 23.0, 0.3),
//    ZANT("Zant", 50.0, 30.0, 0.24),
//    BOKOBLIN("Bokoblin",50.0, 10.0, 0.4),
//    KEESE("Keese",50.0, 11.0,0.43),
//    DARKNUT("Darknut",50.0, 20.0,0.3),
//    PHANTOM_GANON("Phantom Ganon",50.0, 23.0, 0.3),
//    GANONDORF("Ganondorf",50.0, 30.0, 0.24);

    private String name;
    private double maxHealth;
    private double damage;
    private double hitProbability;

    Mobs(String name, double maxHealth, double damage, double hitProbability){
        this.name = name;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.hitProbability = hitProbability;
    }

    @Override
    public String toString(){ return name; }
    public double getMaxHealth() {return maxHealth; }
    public double getDamage(){ return damage; }
    public double getHitProbability() { return hitProbability; }
}
