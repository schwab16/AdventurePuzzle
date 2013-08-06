package com.jordan.tunnelgame;

import java.util.ArrayList;

public class LevelPack {

    public ArrayList<Level> levels = new ArrayList<Level>();
    public int currentLevel;
    public int packID;
    public String saved;

    public LevelPack(int packID)
    {
        this.packID = packID;

        for (int k = 1; k <= 25; k++)
            levels.add(new Level(packID, k));

        saved = read(packID);
    }

    public static String read(int packID)
    {
        String saved = Assets.readFromMemory(C.packFileName + "" + packID);

        if (saved.equals("")) {
            Assets.writeToMemory(C.packFileName + "" + packID, "paku00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00l00pak");
            saved = Assets.readFromMemory(C.packFileName + "" + packID);
        }

        return saved;
    }

    public void setSave(int lvl, int type, char mem)
    {
        int k = lvl*3 + type;
        saved = saved.substring(0,k) + mem + saved.substring(k+1);
        Assets.writeToMemory(C.packFileName + "" + packID, saved);
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
        if (currentLevel == 26) return null;
        getLevel(currentLevel).load();
        return getLevel(currentLevel++);
    }

    public Level thisLevel()
    {
        currentLevel--;
        return nextLevel();
    }

}
