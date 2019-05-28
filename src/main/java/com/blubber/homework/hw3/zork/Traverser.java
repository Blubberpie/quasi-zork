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
import com.blubber.homework.hw3.zork.utilities.packs.ZorkLevelSetup;

import java.util.Map;

public final class Traverser {

    private final int TOTAL_LEVELS = 3;
    private int LEVEL_NUM = 1;
    private double TRAVERSAL_HEAL_VALUE = 30.0;

    private Level currentLevel;
    private Room currentRoom;
    private Player player;

    private Traverser(){
        currentLevel = ZorkLevelSetup.getInstance().initialize(LEVEL_NUM);
        if (currentLevel != null) {
            currentRoom = currentLevel.getStartRoom();
            currentRoom.setVisited();
            player = new Player("Player");
        }
    }

//    public void cheat(){
//        for (int i=0; i < currentLevel.getTotalMonsters() - currentLevel.getMonstersDefeated(); i++){
//            currentLevel.incrementMonstersDefeated();
//        }
//
//        checkAndProgressLevel();
//    }

    public void traverseRoom(Direction direction){
        Map<Direction, Room> connectedRooms = currentRoom.getConnectedRooms();
        if (connectedRooms.containsKey(direction)) {
            Room nxt = connectedRooms.get(direction);
            // Heal the player upon entering a new room
            if (!nxt.isVisited()) {
                player.incrementHealth(TRAVERSAL_HEAL_VALUE);
                MessagePrinter.mpPlayerHealedBy(TRAVERSAL_HEAL_VALUE);
            }
            // Perhaps this might be useful in controlling events
            // e.g. a monster in a non-active room may not be damaged,
            // or an item in a non-active room may not be collected
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
            currentRoom.clearRoom();
            currentLevel.incrementMonstersDefeated();
            MessagePrinter.mpEntityKilledBy(mob.getName(), player.getName());
            return checkAndProgressLevel();
        }else{
            if (mob.attack(player)){
                MessagePrinter.mpEntityKilledBy(player.getName(), mob.getName());
                MessagePrinter.mpGameOver();
                return true;
            }
        }
        return false;
    }

    private boolean checkAndProgressLevel(){
        String currentLevelName = currentLevel.getName();
        if (currentLevel.setLevelStatus()){
            if (LEVEL_NUM == TOTAL_LEVELS){
                MessagePrinter.mpYay();
                return true;
            }else {
                LEVEL_NUM++;
                currentLevel = ZorkLevelSetup.getInstance().initialize(LEVEL_NUM);
                if (currentLevel != null) {
                    MessagePrinter.mpLevelBeat(currentLevelName);
                    currentRoom = currentLevel.getStartRoom();
                    currentRoom.setVisited();
                }
            }return false;
        }return false;
    }

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
