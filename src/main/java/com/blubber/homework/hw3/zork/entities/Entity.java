package com.blubber.homework.hw3.zork.entities;

import com.blubber.homework.hw3.zork.utilities.enums.StatusEffect;

public abstract class Entity {

    private String name;

    private double health;
    private double maximumHealth;
    private boolean alive;

    private double damage;

//    private StatusEffect activeStatusEffect;

    public Entity(){
        alive = true;
    }

    public abstract void attack();

    public void decrementHealth(double decrementValue){
        health -= decrementValue;
        if (health <= 0.0) kill();
    }
    public void incrementHealth(double incrementValue){
        this.health += incrementValue;
        if (health >= maximumHealth){
            health = maximumHealth;
        }
    }

    public boolean isAlive(){ return alive;}
    public double getMaximumHealth(){ return maximumHealth; }
    public String getName() { return name; }
    public double getHealth(){ return health; }
    public double getDamage() { return damage; }

    private void kill(){ alive = false;}
    public void setName(String name) { this.name = name; }
    public void setDamage(double damage) { this.damage = damage; }

    public void setMaximumHealth(double maximumHealth) { this.maximumHealth = maximumHealth; }

    public void setHealth(double health) { this.health = health; }
}
