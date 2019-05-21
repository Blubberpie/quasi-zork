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

    public static boolean handleCommand(Command command, String[] args){
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
                if (args.length == 1){
                    System.out.println("Please specify an item to drop!");
                    return false;
                }else if(args.length > 2){
                    System.out.println("Too many arguments!");
                    return false;
                }
                drop(args[1]);
                return false;
            case ATTACK:
                if (args.length == 1){
                    System.out.println("Usage: attack with [weapon name].");
                    return false;
                }else if(args.length == 2){
                    if (args[1].compareTo("with") == 0) {
                        System.out.println("Please specify a weapon to attack with!");
                    }else{
                        System.out.println("Usage: attack with [weapon name].");
                    }
                    return false;
                }else if(args.length > 3){
                    System.out.println("Too many arguments!");
                    return false;
                }
                attackWith(args[2]);
                return false;
            case HELP:
                help();
                return false;
            case QUIT:
                System.out.println("Quitting...");
                quit();
                return true;
            default:
                return false;
        }
    }

    private static void info(){
        System.out.println("in info");
    }

    private static void take(){
        System.out.println("in take");
    }

    private static void drop(String item){
        System.out.println("in drop: " + item);
    }

    private static void attackWith(String weapon){
        System.out.println("in attack: "+ weapon);
    }

    private static void help(){
        System.out.println("========= LIST OF COMMANDS =========");
        for (Command cmd: Command.values()){
            System.out.println(cmd.toString() + ": " + cmd.getDescription());
        }
        System.out.println("====================================");
    }

    private static void quit(){
        // todo: save state maybe?
    }
}
