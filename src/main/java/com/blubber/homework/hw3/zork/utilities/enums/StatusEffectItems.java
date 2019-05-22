package com.blubber.homework.hw3.zork.utilities.enums;

public enum StatusEffectItems {
    CHLOROFORM("Chloroform", StatusEffect.SLEEPING);

    private String name;
    private StatusEffect statusEffect;

    StatusEffectItems(String name, StatusEffect statusEffect){
        this.name = name;
        this.statusEffect = statusEffect;
    }

    @Override
    public String toString() { return name; }
    public StatusEffect getStatusEffect() { return statusEffect; }
}
