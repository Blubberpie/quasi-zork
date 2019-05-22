package com.blubber.homework.hw3.zork.items;

public abstract class Item {

    private String name;

    public Item(){}

    public Item(String name){
        setName(name);
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
