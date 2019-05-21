package com.blubber.homework.hw3.zork.utilities.enums;

public enum Command {
    INFO(new String[]{"info", "Display information about player and current room."}),
    TAKE(new String[]{"take", "Pick up the item in the current room."}),
    DROP(new String[]{"drop", "Drop a specified item."}),
    ATTACK_WITH(new String[]{"attack with", "Attack a mob with a specified weapon."}),
    HELP(new String[]{"help", "Display this message."}),
    QUIT(new String[]{"quit", "Quit the game."});

    private String[] title;

    Command(String[] title) {this.title = title;}

    @Override
    public String toString() { return title[0]; }

    public String getDescription() { return title[1]; }

}
