package com.blubber.homework.hw3.zork.utilities;

public enum Command {
    INFO("info"), TAKE("take"), DROP("drop"), ATTACK_WITH("attack with"), HELP("help"), QUIT("quit");

    private String title;

    Command(String title) {this.title = title;}

    @Override
    public String toString() { return title; }

}
