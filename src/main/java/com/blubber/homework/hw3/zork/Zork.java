package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.levels.Level;
import com.blubber.homework.hw3.zork.utilities.ZorkCommandHandler;
import com.blubber.homework.hw3.zork.utilities.enums.Command;

import java.util.Scanner;

public class Zork {

    private Scanner scanner;
    private String userInput;
    private boolean quit;
    private Level currentLevel;

    public Zork(){
        scanner = new Scanner(System.in);
        userInput = "";
        quit = false;

    }

    public void run(){
        while(isRunning()){
            if (quit) { break; }
            userInput = scanner.nextLine();
            String[] userArgs = userInput.split(" ");
            Command inputCommand = ZorkCommandHandler.getCommand(userArgs[0]);
            quit = ZorkCommandHandler.handleCommand(inputCommand, userArgs);
        }
    }

    public boolean isRunning(){ return !quit; }

    public void progressLevel(Level nextLevel){ this.currentLevel = nextLevel;}

}
