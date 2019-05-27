package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.utilities.MessagePrinter;
import com.blubber.homework.hw3.zork.utilities.enums.Command;
import com.blubber.homework.hw3.zork.utilities.enums.Direction;

public final class ZorkHandler {

    private Traverser traverser = Traverser.getInstance();

    private ZorkHandler(){}

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
            MessagePrinter.mpInvalidCommand();
            return false;
        }
        switch(command){
            case INFO:
                traverser.getInfo();
                return false;

            case TAKE:
                traverser.take();
                return false;

            case DROP:
                drop(args);
                return false;

            case ATTACK:
                return attack(args);

            case HELP:
                MessagePrinter.mpHelp();
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

    private void drop(String[] args){
        if (args.length == 1){
            MessagePrinter.mpSpecifyItem();
            return;
        }else if(args.length > 2){
            MessagePrinter.mpArgumentExcess();
            return;
        }
        traverser.drop(args[1].toLowerCase());
    }

    private boolean attack(String[] args){
        if (args.length == 1) return traverser.attackWeaponless();
        else if(args.length == 2){

            if (args[1].compareTo("with") == 0) MessagePrinter.mpSpecifyWeapon();
            else                                MessagePrinter.mpAttackUsage();

            return false;
        }else if(args.length > 3){
            MessagePrinter.mpArgumentExcess();
            return false;
        }
        return traverser.attackWith(args[2].toLowerCase());
    }

    private void quit(){
        // todo: save state maybe? possibly? perhaps? if likely? ideally?
    }

    private static final ZorkHandler ZORK_COMMAND_HANDLER = new ZorkHandler();

    public static ZorkHandler getInstance(){ return ZORK_COMMAND_HANDLER; }
}
