package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.entities.Mob;
import com.blubber.homework.hw3.zork.entities.Player;
import com.blubber.homework.hw3.zork.items.Item;
import com.blubber.homework.hw3.zork.items.Weapon;
import com.blubber.homework.hw3.zork.levels.BattleRoom;
import com.blubber.homework.hw3.zork.levels.Level;
import com.blubber.homework.hw3.zork.levels.LootRoom;
import com.blubber.homework.hw3.zork.levels.Room;
import com.blubber.homework.hw3.zork.utilities.MessagePrinter;
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
                MessagePrinter.mpPlayerHealedBy(TRAVERSAL_HEAL_VALUE);
            }
            currentRoom.setInactive();
            currentRoom = nxt;
            currentRoom.setActive();
            currentRoom.setVisited();
            MessagePrinter.mpRoomEntered(direction.toString().toLowerCase());
        }else MessagePrinter.mpNoDoorAt(direction.toString().toLowerCase());
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
                MessagePrinter.mpPickedUp(roomItem.getName());
            }else MessagePrinter.mpEmptyItemRoom();
        }else MessagePrinter.mpCannotTakeBattleRoom();
    }

    public void drop(String weaponToDrop){
        for (Weapon weapon: player.getWeapons()){
            if (weapon.getName().compareTo(weaponToDrop) == 0){
                player.removeWeapon(weapon);
                MessagePrinter.mpDropped(weaponToDrop);
                return;
            }
        }
        MessagePrinter.mpWeaponAbsent();
    }

    public boolean roomIsAttackable(){
        if(BattleRoom.class.isAssignableFrom(currentRoom.getClass())){
            if (currentRoom.isClear()){
                MessagePrinter.mpCannotAttackCorpse();
                return false;
            }else return true;
        } else {
            MessagePrinter.mpCannotAttackLootRoom();
            return false;
        }
    }

    public boolean attackWeaponless(){
        if (roomIsAttackable()) return commenceOneBattleTurn();
        return false;
    }

    public boolean attackWith(String weaponToAttackWith){
        if (roomIsAttackable()){
            boolean playerHasWeapon = false;
            double weaponDamage = 0.0;
            for (Weapon weapon : player.getWeapons()) {
                if (weapon.getName().compareTo(weaponToAttackWith) == 0) {
                    weaponDamage = weapon.getDamage();
                    player.incrementBuffedDamage(weaponDamage);
                    playerHasWeapon = true;
                }
            }
            if (!playerHasWeapon) {
                MessagePrinter.mpWeaponAbsent();
                return false;
            }
            boolean playerIsDead = commenceOneBattleTurn();
            // Player damage returns to last known value after attacking
            player.decrementBuffedDamage(weaponDamage);
            return playerIsDead;
        }
        return false;
    }

    private boolean commenceOneBattleTurn(){
        Mob mob = ((BattleRoom) currentRoom).getMob();
        if (player.attack(mob)){
            ((BattleRoom) currentRoom).setDefeated();
            currentLevel.incrementMonstersDefeated();
            MessagePrinter.mpEntityKilledBy(mob.getName(), player.getName());
            // re-instantiation code here
            return false;
        }else{
            if (mob.attack(player)){
                MessagePrinter.mpEntityKilledBy(player.getName(), mob.getName());
                MessagePrinter.mpGameOver();
                return true;
            }
        }
        return false;
    }

    // re-instantiation code todo: uncomment and paste into commenceOneBattleTurn
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

    /**
     * Prints out:
     * - Player stats (HP, damage, etc.)
     * - Room information (Room name, monster/item stats, neighbors)
     */
    public void getInfo(){
        MessagePrinter.mpPlayerStats(player);
        MessagePrinter.mpPlayerInventory(player);
        MessagePrinter.mpRoomInfo(currentRoom);
        MessagePrinter.mpNeighboringRooms(currentRoom);
    }

    private static final Traverser TRAVERSER = new Traverser();
    public static Traverser getInstance() { return TRAVERSER; }

}
