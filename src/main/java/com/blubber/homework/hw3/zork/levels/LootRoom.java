package com.blubber.homework.hw3.zork.levels;

import com.blubber.homework.hw3.zork.items.Item;

public class LootRoom extends Room{

    private Item item;

    private void removeItem(){ item = null; }

    public Item getItem() { return item; }
    public void setItem(Item item){ this.item = item; }

    // todo: implement null checker in usage
    public Item pickUpItem() {
        Item got = item;
        removeItem();
        return got;
    }

    public boolean isClear() {
        return item == null;}
}
