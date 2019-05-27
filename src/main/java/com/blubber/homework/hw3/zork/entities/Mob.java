package com.blubber.homework.hw3.zork.entities;

import com.blubber.homework.hw3.zork.items.Weapon;
import com.blubber.homework.hw3.zork.utilities.MessagePrinter;

import java.util.Random;

public class Mob extends Entity {

    private double hitProbability;

    public boolean attack(Entity victim){
        if (new Random().nextDouble() <= this.hitProbability) {
            attackDefault(victim);
            return (!victim.isAlive());
        } else MessagePrinter.mpAttackMissed(this.getName());
        return false;
    }

    public void setHitProbability(double hitProbability) { this.hitProbability = hitProbability; }

    public double getHitProbability() { return hitProbability; }
}
