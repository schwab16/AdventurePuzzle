package com.jordan.tunnelgame;

import com.jordan.framework.Graphics;
import com.jordan.framework.Graphics.ImageFormat;
import com.jordan.framework.Image;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assets {

    public static Image splash;
    public static Image packselect, levelselect, failmenu, finishmenu, readymenu, pauseicon, pausemenu;
    public static Image background, backgroundmoon, backgroundnight, backgroundsunset;
    public static Button buttonretry, buttonresume, buttonnextlevel, buttonmainmenu;

    public static Image iChaser, iOrb, iTile;
    public static Image iBasicTile, iRockTile, iStoneTile, iFireTile, iIceTile, iMetalTile, iSnowTile, iWarpTile, iFinishTile;

    //public static Sound click;
    //public static Music theme;

    public static void loadSplash(Graphics g) {
        splash = g.newImage("splash.png", ImageFormat.RGB565);
    }

    public static void load(Graphics g) {

        background = g.newImage("background.png", ImageFormat.RGB565);
        backgroundmoon = g.newImage("backgroundmoon.png", ImageFormat.RGB565);
        backgroundnight = g.newImage("backgroundnight.png", ImageFormat.RGB565);
        backgroundsunset = g.newImage("backgroundsunset.png", ImageFormat.RGB565);

        buttonretry = new Button(g.newImage("buttonretry.png", ImageFormat.RGB565),g.newImage("pushedbuttonretry.png", ImageFormat.RGB565));
        buttonmainmenu = new Button(g.newImage("buttonmainmenu.png", ImageFormat.RGB565),g.newImage("pushedbuttonmainmenu.png", ImageFormat.RGB565));
        buttonnextlevel = new Button(g.newImage("buttonnextlevel.png", ImageFormat.RGB565),g.newImage("pushedbuttonnextlevel.png", ImageFormat.RGB565));
        buttonresume = new Button(g.newImage("buttonresume.png", ImageFormat.RGB565),g.newImage("pushedbuttonresume.png", ImageFormat.RGB565));

        failmenu = g.newImage("failmenu.png", ImageFormat.RGB565);
        finishmenu = g.newImage("finishmenu.png", ImageFormat.RGB565);
        packselect = g.newImage("packselect.png", ImageFormat.RGB565);
        levelselect = g.newImage("levelselect.png", ImageFormat.RGB565);
        readymenu = g.newImage("readymenu.png", ImageFormat.RGB565);
        pauseicon = g.newImage("pauseicon.png", ImageFormat.RGB565);
        pausemenu = g.newImage("pausemenu.png", ImageFormat.RGB565);

        iChaser = g.newImage("chaser.png", ImageFormat.RGB565);
        iOrb = g.newImage("orb.png", ImageFormat.RGB565);
        iTile = g.newImage("emptytile.png", ImageFormat.RGB565);
        iBasicTile = g.newImage("basictile.png", ImageFormat.RGB565);
        iRockTile = g.newImage("midtile.png", ImageFormat.RGB565);
        iStoneTile = g.newImage("stonetile.png", ImageFormat.RGB565);
        iFireTile = g.newImage("firetile.png", ImageFormat.RGB565);
        iIceTile = g.newImage("icetile.png", ImageFormat.RGB565);
        iMetalTile = g.newImage("metaltile.png", ImageFormat.RGB565);
        iSnowTile = g.newImage("snowtile.png", ImageFormat.RGB565);
        iWarpTile = g.newImage("warp.png", ImageFormat.RGB565);
        iFinishTile = g.newImage("finish.png", ImageFormat.RGB565);

    }

    public static Image backgroundByString(String bg)
    {
        if (bg.equals("")) return Assets.background;
        else if (bg.equals("moon")) return Assets.backgroundmoon;
        else if (bg.equals("night")) return Assets.backgroundnight;
        else if (bg.equals("sunset")) return Assets.backgroundsunset;

        else return Assets.background;
    }

    public static double distance(Coord a, Coord b)
    {
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    public static boolean inBounds(Coord a, Coord b, Coord c) {
        return a.x>b.x && a.x<c.x && a.y>b.y && a.y < c.y;
    }

    public static File fileLocation;
    public static void writeToMemory(String fileName, String text)
    {//needed to instantiate file directory in sample game activity
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileLocation+File.separator+fileName)));
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {}
    }
    public static String readFromMemory(String fileName)
    {
        String res = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileLocation+File.separator+fileName)));
            String read;
            StringBuilder builder = new StringBuilder("");
            while((read = bufferedReader.readLine()) != null){
                builder.append(read);
            }
            res = builder.toString();
            bufferedReader.close();
        } catch (IOException e) {
            writeToMemory(fileName, "");
        }
        return res;
    }

     /*pack ids:
    0: test

    name#background#level

    TILE CHAR IDS
    '  ' : empty space
    'f!' : finish tile

    'b+' : basic tile
    'b-' : rock tile
    'b/' : stone tile
    'b^' : snow tile
    'b&' : metal tile

    'd*' : fire tile

    's_' : ice tile

    'w0' - 'w9' : warp tile (their id is as they appear, they target the # representing them )

    CHASER/ORB IDS 'a-j' 'A-J'
    'Ca' - 'Cj' : default chaser
    'Oa' - 'Oj; : default orb

    TO REMEMBER WHEN ADDING:
    1) add picture in assets folder
    2) load picture in assets class
    3) define char here
    4) make it recognised by the level class switch statement
    5) add a class that extends tile, implements methods (jordan only)
    6) getImage needs to return the correct image
    7) add functionality (in the class, in GameRunner) (jordan only)
    8) add it to a level

    HOW TO ADD A NEW BACKGROUND
    1) put it in assets folder, name it backgroundwhatever.png
    2) make it an instance variable here
    3) load it in the load method
    4) add an if statement in the backgroundByString method
    5) add it to a level after the first hashtaga
    */

    public static String getLevelString(int packID, int levelNum) {

        switch (packID * 100 + levelNum)
        {
            case 1: return "test1#moon#" +
                    "  w0                            " +
                    "                w2      Ob      " +
                    "            Oa    f!            " +
                    "                  b+      Cb    " +
                    "            b+b+b+b+d*b+b+b+    " +
                    "b+  w1                          " +
                    "  b+                            " +
                    "b+b+b+          Ca              " +
                    "b+b+b+b+b+            w0  b+b+d*" +
                    "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 2: return "test2#sunset#" +
                    "  w0                            " +
                    "                w2      Ob      " +
                    "            Oa    f!            " +
                    "                  b+      Cb    " +
                    "            b+b+b+b+d*b+b+b+    " +
                    "b+  w1                          " +
                    "  b+                            " +
                    "  b+b+          Ca              " +
                    "b+b+b+b+b+            w0  b+b+d*" +
                    "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 3: return "test3#night#" +
                    "  w0                            " +
                    "                w2      Ob      " +
                    "            Oa    f!            " +
                    "                  b+      Cb    " +
                    "            b+b+b+b+d*b+b+b+    " +
                    "b+  w1                          " +
                    "  b+                            " +
                    "  b+b+          Ca              " +
                    "  b+b+b+b+            w0  b+b+d*" +
                    "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 4: return "test4##" +
                    "  w0                            " +
                    "                w2      Ob      " +
                    "            Oa    f!            " +
                    "                  b+      Cb    " +
                    "            b+b+b+b+d*b+b+b+    " +
                    "b+  w1                          " +
                    "  b+                            " +
                    "  b+b+          Ca              " +
                    "  b+b+b+b+            w0  b+b+d*" +
                    "  b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
            case 5:
                return " ";
            case 6:
                return " ";
            case 7:
                return " ";
            case 8:
                return " ";
            case 9:
                return " ";
            case 10:
                return " ";

        }

        return "";
    }


}