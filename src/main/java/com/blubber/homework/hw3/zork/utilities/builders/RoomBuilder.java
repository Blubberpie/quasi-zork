package com.blubber.homework.hw3.zork.utilities.builders;

import com.blubber.homework.hw3.zork.entities.Mob;
import com.blubber.homework.hw3.zork.items.Item;
import com.blubber.homework.hw3.zork.levels.BattleRoom;
import com.blubber.homework.hw3.zork.levels.LootRoom;
import com.blubber.homework.hw3.zork.levels.Room;

import java.lang.reflect.InvocationTargetException;

public class RoomBuilder {

    private Room room;

    public RoomBuilder(Class roomClass){
        try{
            room = (Room) roomClass.getConstructor().newInstance();
        }catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex){
            System.out.println(ex.getMessage());
        }
    }

    public RoomBuilder setRoomName(String name){
        this.room.setName(name);
        return this;
    }

    public RoomBuilder initItem(Item item){
        ((LootRoom) getRoom()).setItem(item);
        return this;
    }

    public RoomBuilder initMob(Mob mob){
        ((BattleRoom) getRoom()).setMob(mob);
        return this;
    }

    public Room getRoom() { return room; }
}
