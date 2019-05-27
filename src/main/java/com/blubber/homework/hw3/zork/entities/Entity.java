package com.blubber.homework.hw3.zork.entities;

import com.blubber.homework.hw3.zork.utilities.MessagePrinter;

public abstract class Entity {

    private String name;

    private double health;
    private double maximumHealth;
    private boolean alive;

    private double baseDamage;
    private double buffedDamage;

//    private StatusEffect activeStatusEffect;

    public Entity(){
        alive = true;
    }

    public void attackDefault(Entity victim){
        victim.decrementHealth(this.getBuffedDamage());
        MessagePrinter.mpEntityInflictsDamage(this.getName(), victim.getName(), this.getBuffedDamage());
    }

    public abstract boolean attack(Entity victim);

    public void decrementHealth(double decrementValue){
        health -= decrementValue;
        if (health <= 0.0) {
            killSelf();
            setHealth(0.0);
        }
    }
    public void incrementHealth(double incrementValue){
        this.health += incrementValue;
        if (health >= maximumHealth){
            health = maximumHealth;
        }
    }

    public void incrementBuffedDamage(double incrementValue){
        this.buffedDamage += incrementValue;
    }

    public void decrementBuffedDamage(double decrementValue){
        this.buffedDamage -= decrementValue;
    }

    public boolean isAlive(){ return alive;}
    public double getMaximumHealth(){ return maximumHealth; }
    public String getName() { return name; }
    public double getHealth(){ return health; }
    public double getBaseDamage() { return baseDamage; }
    public double getBuffedDamage() { return buffedDamage; }

    private void killSelf(){ alive = false;}
    public void setName(String name) { this.name = name; }
    public void setBaseDamage(double baseDamage) { this.baseDamage = baseDamage; }
    public void setMaximumHealth(double maximumHealth) { this.maximumHealth = maximumHealth; }
    public void setHealth(double health) { this.health = health; }
    public void setBuffedDamage(double buffedDamage) { this.buffedDamage = buffedDamage; }
}
