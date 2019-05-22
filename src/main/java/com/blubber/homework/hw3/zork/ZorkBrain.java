package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.utilities.enums.Command;
import com.blubber.homework.hw3.zork.utilities.enums.Direction;

public final class ZorkBrain {

    private Traverser traverser = Traverser.getInstance();

    private ZorkBrain(){}

    public Command getCommand(String userInput){
        for (Command myCommand: Command.values()){
            if(userInput.compareTo(myCommand.toString()) == 0){
                return myCommand;
            }
        }
        return null;
    }

    public boolean handleCommand(Command command, String[] args){
        if (command == null){
            System.out.println("Invalid command! Type \'help\' to see the list of valid commands.");
            return false;
        }
        switch(command){
            case INFO:
                traverser.getInfo();
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

//            case USE:
//                if (args.length == 1){
//                    System.out.println("Please specify an item to use!");
//                    return false;
//                }else if(args.length > 2){
//                    System.out.println("Too many arguments!");
//                    return false;
//                }
//                use(args[1]);
//                return false;

            case NORTH:
                traverser.traverseRoom(Direction.NORTH);
                return false;

            case SOUTH:
                traverser.traverseRoom(Direction.SOUTH);
                return false;

            case EAST:
                traverser.traverseRoom(Direction.EAST);
                return false;

            case WEST:
                traverser.traverseRoom(Direction.WEST);
                return false;

            default:
                return false;
        }
    }

    private void take(){
        System.out.println("in take");
    }

    private void drop(String item){
        System.out.println("in drop: " + item);
    }

    private void attackWith(String weapon){
        System.out.println("in attack: "+ weapon);
    }

    private void help(){
        System.out.println("========= LIST OF COMMANDS =========");
        for (Command cmd: Command.values()){
            System.out.println(cmd.toString() + ": " + cmd.getDescription());
        }
        System.out.println("====================================");
    }

    private void quit(){
        // todo: save state maybe?
    }

    private static final ZorkBrain ZORK_COMMAND_HANDLER = new ZorkBrain();

    public static ZorkBrain getInstance(){ return ZORK_COMMAND_HANDLER; }
}
