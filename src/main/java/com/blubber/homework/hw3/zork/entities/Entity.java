package com.blubber.homework.hw3.zork.entities;

import com.blubber.homework.hw3.zork.utilities.enums.StatusEffect;

public abstract class Entity {
    private double health;
    private double maximumHealth;
    private boolean alive;

    private double damage;

    private StatusEffect activeStatusEffect;

    public double getHealth(){ return health; }
    private void kill(){ alive = false;}

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

}
