package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.entities.Mob;
import com.blubber.homework.hw3.zork.entities.Player;
import com.blubber.homework.hw3.zork.items.Item;
import com.blubber.homework.hw3.zork.items.Weapon;
import com.blubber.homework.hw3.zork.levels.BattleRoom;
import com.blubber.homework.hw3.zork.levels.Level;
import com.blubber.homework.hw3.zork.levels.LootRoom;
import com.blubber.homework.hw3.zork.levels.Room;
import com.blubber.homework.hw3.zork.utilities.enums.Direction;
import com.blubber.homework.hw3.zork.utilities.initializers.ZorkInitializer;

import java.util.Map;
import java.util.Random;

public final class Traverser {

    private double TRAVERSAL_HEAL_VALUE = 30.0;
    private Random rand = new Random();

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
            // Heal the player upon entering a new room
            if (!nxt.isVisited()) {
                player.incrementHealth(TRAVERSAL_HEAL_VALUE);
                System.out.println("You healed by " + TRAVERSAL_HEAL_VALUE + " HP!!");
            }
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
     * Picks up a weapon from the room and puts it in the player's
     * inventory.
     */
    public void take(){
        if (LootRoom.class.isAssignableFrom(currentRoom.getClass())){
            Item roomItem = ((LootRoom) currentRoom).pickUpItem();
            if (!(roomItem == null)){
                player.addWeapon((Weapon) roomItem);
                System.out.println("You picked up the " + roomItem.getName() + "!");
            }else{
                System.out.println("There is nothing to take from this room!");
            }
        }else{
            System.out.println("You cannot take anything from a battle room!");
        }
    }

    public void drop(String weaponToDrop){
        for (Weapon weapon: player.getWeapons()){
            if (weapon.getName().compareTo(weaponToDrop) == 0){
                player.removeWeapon(weapon);
                System.out.println("You dropped the " + weaponToDrop + "!");
                return;
            }
        }
        System.out.println("You don't have that weapon! " +
                "\nPlease check that you spelled it correctly.");
    }

    public boolean attackWith(String weaponToAttackWith){
        if(BattleRoom.class.isAssignableFrom(currentRoom.getClass())){
            if (((BattleRoom) currentRoom).isDefeated()){
                System.out.println("Leave its corpse alone, you sick monster!");
                return false;
            }else{
                Mob mob = ((BattleRoom) currentRoom).getMob();
                boolean foundWeapon = false;
                double totalDamage = player.getDamage();
                for (Weapon weapon : player.getWeapons()) {
                    if (weapon.getName().compareTo(weaponToAttackWith) == 0) {
                        totalDamage += weapon.getDamage();
                        foundWeapon = true;
                    }
                }
                if (!foundWeapon) System.out.println(weaponToAttackWith + " is not in your inventory!\n" +
                        "Attacking with fist instead.");
                mob.decrementHealth(totalDamage);
                System.out.println("You inflicted "
                        + totalDamage
                        + " damage!");
                if (!mob.isAlive()) {
                    System.out.println("You've defeated " + mob.getName() + "!");
                    ((BattleRoom) currentRoom).setDefeated();
                    currentLevel.incrementMonstersDefeated();
                    // re-instantiation code todo: move?
//                    if (currentLevel.setLevelStatus()){
//                        System.out.println("Congratulations! You beat " + currentLevel.getName() + "!");
//                        System.out.println("Warping to next level...");
//                        if (currentLevel.getNextLevel() != null) {
//                            currentLevel = currentLevel.getNextLevel();
//                            currentRoom = currentLevel.getStartRoom();
//                            currentRoom.setVisited();
//                        }else{
//                            System.out.println("You've completed the game!");
//                            printYay();
//                        }
//                    }
                    return false;
                }else{
                    if (rand.nextDouble() <= mob.getHitProbability()){
                        player.decrementHealth(mob.getDamage());
                        System.out.println("You took " + mob.getDamage() + " damage!");
                        if (!player.isAlive()){
                            System.out.println(mob.getName() + " has killed you!");
                            printGameOver();
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            }
        }
        else {
            System.out.println("You cannot attack in a loot room!");
            return false;
        }
    }

    private void printYay(){
        System.out.println(" __     __         _ \n" +
                " \\ \\   / /        | |\n" +
                "  \\ \\_/ /_ _ _   _| |\n" +
                "   \\   / _` | | | | |\n" +
                "    | | (_| | |_| |_|\n" +
                "    |_|\\__,_|\\__, (_)\n" +
                "              __/ |  \n" +
                "             |___/   ");
        System.out.println();
    }

    private void printGameOver(){
        System.out.println(" _____                        _____                _\n" +
                "|  __ \\                      |  _  |              | |\n" +
                "| |  \\/ __ _ _ __ ___   ___  | | | |_   _____ _ __| |\n" +
                "| | __ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__| |\n" +
                "| |_\\ \\ (_| | | | | | |  __/ \\ \\_/ /\\ V /  __/ |  |_|\n" +
                " \\____/\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|  (_)");
        System.out.println();
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
