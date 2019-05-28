package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.utilities.MessagePrinter;
import com.blubber.homework.hw3.zork.utilities.enums.Command;

import java.util.Scanner;

public class Zork {

    private Scanner scanner;
    private String userInput;
    private boolean quit;
    private ZorkHandler zorkHandler = ZorkHandler.getInstance();

    public Zork(){
        scanner = new Scanner(System.in);
        userInput = "";
        quit = false;
    }

    public void run(){
        MessagePrinter.mpWelcomeMessage();
        zorkHandler.handleCommand(Command.HELP, null);
        while(isRunning()){
            System.out.print("Player action> ");
            userInput = scanner.nextLine();
            String[] userArgs = userInput.split(" ");
            Command inputCommand = zorkHandler.getCommand(userArgs[0]);
            quit = zorkHandler.handleCommand(inputCommand, userArgs);
        }
    }

    public boolean isRunning(){ return !quit; }
}
