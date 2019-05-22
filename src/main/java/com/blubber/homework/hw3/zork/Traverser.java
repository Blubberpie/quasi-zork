package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.entities.Mob;
import com.blubber.homework.hw3.zork.entities.Player;
import com.blubber.homework.hw3.zork.items.Weapon;
import com.blubber.homework.hw3.zork.levels.BattleRoom;
import com.blubber.homework.hw3.zork.levels.Level;
import com.blubber.homework.hw3.zork.levels.LootRoom;
import com.blubber.homework.hw3.zork.levels.Room;
import com.blubber.homework.hw3.zork.utilities.enums.Direction;
import com.blubber.homework.hw3.zork.utilities.initializers.ZorkInitializer;

import java.util.Map;

public final class Traverser {

    private Level currentLevel;
    private Room currentRoom;
    private Player player;

    private Traverser(){
        currentLevel = ZorkInitializer.getInstance().initialize();
        currentRoom = currentLevel.getStartRoom();
        currentRoom.setVisited();
        player = new Player();
    }

    public void traverseRoom(Direction direction){
        Map<Direction, Room> connectedRooms = currentRoom.getConnectedRooms();
        if (connectedRooms.containsKey(direction)) {
            Room nxt = connectedRooms.get(direction);
            currentRoom.setInactive();
            currentRoom = nxt;
            currentRoom.setActive();
            currentRoom.setVisited();
            System.out.println("Entered room to the " + direction.toString().toLowerCase() + ".");
        }else{
            System.out.println("There is no door to the " + direction.toString().toLowerCase() + ".");
        }
    }

    /**
     * Prints out:
     * - Player stats (HP, damage, etc.)
     * - Room information (Room name, monster/item stats, neighbors)
     */
    public void getInfo(){
        System.out.println();
        System.out.println("======= Player Stats =======");
        System.out.println("HP: " + player.getHealth() + "/" + player.getMaximumHealth());
        System.out.println("Damage: " + player.getDamage() + "\n");

        System.out.println("======= Inventory =======");
        if (player.getWeapons().size() == 0){
            System.out.println("(empty)");
        }
        else{
            for (Weapon weapon: player.getWeapons()){
                System.out.println("Weapon: " + weapon.getName());
            }
        }
        System.out.println();

        System.out.println("======= Room Information =======");
        System.out.println("Current room: " + currentRoom.getName());
        if (BattleRoom.class.isAssignableFrom(currentRoom.getClass())){
            Mob mob = ((BattleRoom) currentRoom).getMob();
            System.out.println("Enemy: " + mob.getName());
            System.out.println("Current enemy HP: " + mob.getHealth() + "/" + mob.getMaximumHealth());
            System.out.println("Current enemy's damage: " + mob.getDamage());
        }else if(LootRoom.class.isAssignableFrom(currentRoom.getClass())){
            System.out.print("Weapon: ");
            if (((LootRoom) currentRoom).getItem() == null){
                System.out.println("None");
            }else{
                System.out.println(((LootRoom) currentRoom).getItem().getName());
            }
        }
        System.out.println();

        System.out.println("======= Neighboring Rooms =======");
        for (Map.Entry<Direction, Room> entry : currentRoom.getConnectedRooms().entrySet()){
            System.out.println(entry.getKey().toString()
                    + ": "
                    + entry.getValue().getName()
                    + " (" + entry.getValue().isVisitedString() + ")"
                    );
        }
        System.out.println();
    }

    private static final Traverser TRAVERSER = new Traverser();
    public static Traverser getInstance() { return TRAVERSER; }

}
