package com.jordan.tunnelgame;

import java.util.ArrayList;

public class LevelPack {

    public ArrayList<Level> levels = new ArrayList<Level>();
    public int currentLevel;
    public int packID;

    public LevelPack(int packID)
    {
        this.packID = packID;

        for (int k = 1; k <= 10; k++)
        {
            levels.add(new Level(packID, k));
        }
    }

    public void startOn(int startLevel)
    {
        currentLevel = startLevel;
    }

    public Level getLevel(int point)
    {
        return levels.get(point - 1);
    }

    public Level nextLevel()
    {
        if (currentLevel == 25) return null;
        getLevel(currentLevel).load();
        return getLevel(currentLevel++);
    }

    public Level thisLevel()
    {
        currentLevel--;
        return nextLevel();
    }

}
