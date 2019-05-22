package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.utilities.enums.Command;

import java.util.Scanner;

public class Zork {

    private Scanner scanner;
    private String userInput;
    private boolean quit;
    private ZorkBrain zorkBrain = ZorkBrain.getInstance();

    public Zork(){
        scanner = new Scanner(System.in);
        userInput = "";
        quit = false;
    }

    public void run(){
        displayWelcomeMessage();
        zorkBrain.handleCommand(Command.HELP, null);
        while(isRunning()){
            if (quit) { break; }
            System.out.print("Player action> ");
            userInput = scanner.nextLine();
            String[] userArgs = userInput.split(" ");
            Command inputCommand = zorkBrain.getCommand(userArgs[0]);
            quit = zorkBrain.handleCommand(inputCommand, userArgs);
        }
    }

    private void displayWelcomeMessage(){
        System.out.println("Welcome to Quasi-Zork!");
        System.out.println("In this totally-not-ripped-off version of Zork,\nyour goal is to escape the dungeon by traversing through 3 levels.");
        System.out.println("Defeat all enemies to clear a level and move on to the next.");
        System.out.println("Entering an unvisited room will boost your HP.");
        System.out.println("Good luck!\n");
        System.out.println("=====================");
        System.out.println("Developed by Zwel Pai");
        System.out.println("=====================\n\n");
    }

    public boolean isRunning(){ return !quit; }
}
