package com.blubber.homework.hw3.zork;

import com.blubber.homework.hw3.zork.levels.BattleRoom;
import com.blubber.homework.hw3.zork.levels.Level;
import com.blubber.homework.hw3.zork.levels.Room;
import com.blubber.homework.hw3.zork.utilities.LevelBuilder;

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
            userInput = scanner.nextLine();
            if (userInput.compareTo("quit") == 0){
                System.out.println("Quitting game...");
                quitGame();
            };
            if (isRunning()){
                System.out.println(userInput);
            }
        }
    }

    public boolean isRunning(){ return !quit; }
    public void quitGame(){ this.quit = true; }

    public void progressLevel(Level nextLevel){ this.currentLevel = nextLevel;}

}
