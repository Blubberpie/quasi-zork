package com.blubber.homework.hw3.zork.utilities;

import com.blubber.homework.hw3.zork.utilities.enums.Command;

import java.util.Collections;

public class ZorkCommandHandler {

    public static Command getCommand(String userInput){
        for (Command myCommand: Command.values()){
            if(userInput.compareTo(myCommand.toString()) == 0){
                return myCommand;
            }
        }
        return null;
    }

    public static boolean handleCommand(Command command){
        if (command == null){
            System.out.println("Invalid command!");
            command = Command.HELP;
        }
        switch(command){
            case INFO:
                info();
                return false;
            case TAKE:
                take();
                return false;
            case DROP:
                drop();
                return false;
            case ATTACK_WITH:
                attackWith();
                return false;
            case HELP:
                help();
                return false;
            case QUIT:
                System.out.println("Quitting...");
                return true;
            default:
                return false;
        }
    }

    private static void info(){

    }

    private static void take(){

    }

    private static void drop(){

    }

    private static void attackWith(){

    }

    private static void help(){
        System.out.println("========= LIST OF COMMANDS =========");
        for (Command cmd: Command.values()){
            System.out.println(cmd.toString() + ": " + cmd.getDescription());
        }
        System.out.println("====================================");
    }
}
