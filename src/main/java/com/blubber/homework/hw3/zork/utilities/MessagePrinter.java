package com.blubber.homework.hw3.zork.utilities;

import com.blubber.homework.hw3.zork.entities.Mob;
import com.blubber.homework.hw3.zork.entities.Player;
import com.blubber.homework.hw3.zork.items.Weapon;
import com.blubber.homework.hw3.zork.levels.BattleRoom;
import com.blubber.homework.hw3.zork.levels.LootRoom;
import com.blubber.homework.hw3.zork.levels.Room;
import com.blubber.homework.hw3.zork.utilities.enums.Command;
import com.blubber.homework.hw3.zork.utilities.enums.Direction;

import java.util.Map;

public class MessagePrinter {

    public static void mpSpecifyItem(){
        System.out.println("Please specify an item to drop!");
    }

    public static void mpSpecifyWeapon(){
        System.out.println("Please specify a weapon to attack with!");
    }

    public static void mpArgumentExcess(){
        System.out.println("Too many arguments!");
    }

    public static void mpAttackUsage(){
        System.out.println("Usage: attack with [weapon name].");
    }

    public static void mpInvalidCommand(){
        System.out.println("Invalid command! Type \'help\' to see the list of valid commands.");
    }

    public static void mpWelcomeMessage(){
        System.out.println("Welcome to Quasi-Zork!\n" +
                        "In this totally-not-ripped-off version of Zork,\n" +
                        "your goal is to escape the dungeon by traversing through 3 levels.\n" +
                        "Defeat all enemies to clear a level and move on to the next.\n" +
                        "Entering an unvisited room will boost your HP.\n" +
                        "Good luck!\n" +
                        "=====================\n" +
                        "Developed by Zwel Pai\n" +
                        "=====================\n\n");
    }

    public static void mpEmptyItemRoom(){
        System.out.println("There is nothing to take from this room!");
    }

    public static void mpCannotTakeBattleRoom(){
        System.out.println("You cannot take anything from a battle room!");
    }

    public static void mpWeaponAbsent(){
        System.out.println("You don't have that weapon! " +
                "\nPlease check that you spelled it correctly.");
    }

    public static void mpAttackWithFistInstead(String weaponToAttackWith){
        System.out.println(weaponToAttackWith + " is not in your inventory!\n" +
                "Attacking with fist instead.");
    }

    public static void mpCannotAttackCorpse(){
        System.out.println("Leave its corpse alone, you sick monster!");
    }

    public static void mpCannotAttackLootRoom(){
        System.out.println("You cannot attack in a loot room!");
    }

    public static void mpPickedUp(String itemName){
        System.out.println("You picked up the " + itemName + "!");
    }

    public static void mpDropped(String objectToDrop){
        System.out.println("You dropped the " + objectToDrop + "!");
    }

    public static void mpPlayerInflictsDamage(double totalDamage){
        System.out.println("You inflicted "
                + totalDamage
                + " damage!");
    }

    public static void mpMobDefeated(String mobName){
        System.out.println("You've defeated " + mobName + "!");
    }

    public static void mpDamageTaken(double damage){
        System.out.println("You took " + damage + " damage!");
    }

    public static void mpPlayerKilledBy(String mobName){
        System.out.println(mobName + " has killed you!");
    }

    public static void mpPlayerHealedBy(double healValue){
        System.out.println("You healed by " + healValue + " HP!!");
    }

    public static void mpRoomEntered(String direction){
        System.out.println("Entered room to the " + direction + ".");
    }

    public static void mpNoDoorAt(String direction){
        System.out.println("There is no door to the " + direction + ".");
    }

    public static void mpYay(){
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

    public static void mpGameOver(){
        System.out.println(" _____                        _____                _\n" +
                "|  __ \\                      |  _  |              | |\n" +
                "| |  \\/ __ _ _ __ ___   ___  | | | |_   _____ _ __| |\n" +
                "| | __ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__| |\n" +
                "| |_\\ \\ (_| | | | | | |  __/ \\ \\_/ /\\ V /  __/ |  |_|\n" +
                " \\____/\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|  (_)");
        System.out.println();
    }

    public static void mpHelp(){
        System.out.println("========= LIST OF COMMANDS =========");
        for (Command cmd: Command.values()){
            System.out.println(cmd.toString() + ": " + cmd.getDescription());
        }
        System.out.println("====================================");
    }

    // === LESS SIMPLE PRINT MESSAGES === //

    public static void mpPlayerStats(Player player){
        System.out.println("\n======= Player Stats =======\n"+
                "HP: " +
                player.getHealth() + "/" +
                player.getMaximumHealth() + "\n" +
                "Damage: " +
                player.getDamage() + "\n");
    }

    public static void mpPlayerInventory(Player player){
        System.out.println("======= Inventory =======");
        if (player.getWeapons().size() == 0) System.out.println("(empty)");
        else{
            for (Weapon weapon: player.getWeapons()){
                System.out.println("Weapon: " + weapon.getName());
            }
        }
        System.out.println();
    }

    public static void mpRoomInfo(Room currentRoom){
        System.out.println("======= Room Information =======\n" +
                "Current room: " +
                currentRoom.getName());
        if (BattleRoom.class.isAssignableFrom(currentRoom.getClass())){
            Mob mob = ((BattleRoom) currentRoom).getMob();
            System.out.println("Enemy: " +
                            mob.getName() + "\n" +
                    "Current enemy HP: " +
                            mob.getHealth() + "/" +
                            mob.getMaximumHealth() + "\n" +
                    "Current enemy's damage: " +
                            mob.getDamage());
        }else if(LootRoom.class.isAssignableFrom(currentRoom.getClass())){
            System.out.print("Weapon: ");
            if (((LootRoom) currentRoom).getItem() == null){
                System.out.println("None");
            }else{
                System.out.println(((LootRoom) currentRoom).getItem().getName());
            }
        }
        System.out.println();
    }

    public static void mpNeighboringRooms(Room currentRoom){
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


}
