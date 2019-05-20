package com.blubber.homework.hw3.zork;

import java.util.Scanner;

public class Zork {

    private Scanner scanner;
    private String userInput;

    public Zork(){
        scanner = new Scanner(System.in);
        userInput = "";
    }

    public void run(){
        while(true){
            userInput = scanner.nextLine();
            if (userInput.compareTo("quit") == 0){
                System.out.println("Quitting game...");
                return;
            }
            System.out.println(userInput);
        }
    }

}
