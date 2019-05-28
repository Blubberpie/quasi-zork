package com.blubber.homework.hw3.zork.utilities.packs;

import com.blubber.homework.hw3.zork.levels.Level;

/**
 * This is a hard-coded initialization of levels and rooms in the game.
 * Do NOT change unless you know what you're doing!!
 */
public final class ZorkLevelSetup {

    private ZorkLevelSetup(){}

    public Level initialize(int levelNum){
        if (levelNum == 1) {
            return LevelOne.getInstance().createLevelOne();
        }else if(levelNum == 2){
            return LevelTwo.getInstance().createLevelTwo();
        }else if(levelNum == 3){
            return LevelThree.getInstance().createLevelThree();
        }else return null;
    }

    private static final ZorkLevelSetup ZORK_INITIALIZER = new ZorkLevelSetup();

    public static ZorkLevelSetup getInstance(){ return ZORK_INITIALIZER; }
}
