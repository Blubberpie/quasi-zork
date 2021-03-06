package com.blubber.homework.hw3.zork.utilities.enums;

public enum Command {
    INFO("info", "Display information about player and current room."),
    TAKE("take", "Pick up the item in the current room."),
    DROP("drop", "(Usage: drop [item name]) Drop a specified item."),
    ATTACK("attack", "(Usage: attack with [weapon name]) Attack a mob with a specified weapon.\n" +
                                            "\t\tSimply typing \'attack\' will inflict your player's base damage on the enemy."),
//    USE("use", "(Usage: use [item name]) Use an item."),
//    RULES("rules", "Get a detailed description of how the entire game works."),
//    CHEAT("cheat", "yes"),
    HELP("help", "Display this message."),
    QUIT("quit", "Quit the game."),
    NORTH("north", "Enter the room to the north."),
    SOUTH("south", "Enter the room to the south."),
    EAST("east", "Enter the room to the east."),
    WEST("west", "Enter the room to the west.");

    private String command;
    private String description;

    Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    @Override
    public String toString() { return command; }

    public String getDescription() { return description; }

}
