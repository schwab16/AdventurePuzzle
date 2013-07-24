package com.jordan.tunnelgame;

import android.util.Log;

import com.jordan.framework.Graphics;
import com.jordan.framework.Graphics.ImageFormat;
import com.jordan.framework.Image;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Assets {

    public static Image splash;
    public static Image packselect, levelselect, failmenu, finishmenu, readymenu, pauseicon, pausemenu ,toomanywarps, blockselect, selectedwarp;
    public static Image background, backgroundmoon, backgroundnight, backgroundsunset;
    public static Button buttonretry, buttonresume, buttonnextlevel, buttonmainmenu;

    public static Image iChaser, iOrb, iTile;
    public static Image iBasicTile, iRockTile, iStoneTile, iFireTile, iIceTile, iMetalTile, iSnowTile, iWarpTile, iFinishTile;

    //public static Sound click;
    //public static Music theme;

    public static ArrayList<Image> tiles = new ArrayList<Image>();
    public static ArrayList<String> charCodes = new ArrayList<String>();
    public static ArrayList<String> backgrounds = new ArrayList<String>();
    public static Image editoricons, editorreturn;
    public static boolean loaded = false;

    public static void loadSplash(Graphics g) {
        splash = g.newImage("splash.png", ImageFormat.RGB565);
    }

    public static void load(Graphics g) { if (!loaded) { loaded = true;

        background = g.newImage("background.png", ImageFormat.RGB565); backgrounds.add("");
        backgroundmoon = g.newImage("backgroundmoon.png", ImageFormat.RGB565); backgrounds.add("moon");
        backgroundnight = g.newImage("backgroundnight.png", ImageFormat.RGB565); backgrounds.add("night");
        backgroundsunset = g.newImage("backgroundsunset.png", ImageFormat.RGB565); backgrounds.add("sunset");

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
        editoricons = g.newImage("editoricons.png", ImageFormat.RGB565);
        editorreturn = g.newImage("editorreturn.png", ImageFormat.RGB565);
        toomanywarps = g.newImage("toomanywarps.png", ImageFormat.RGB565);
        selectedwarp = g.newImage("selectedwarp.png", ImageFormat.RGB565);
        blockselect = g.newImage("blockselect.png", ImageFormat.RGB565);

        iTile = g.newImage("emptytile.png", ImageFormat.RGB565); tiles.add(iTile); charCodes.add( "  ");
        iChaser = g.newImage("chaser.png", ImageFormat.RGB565); tiles.add(iChaser); charCodes.add("Ca");
        iOrb = g.newImage("orb.png", ImageFormat.RGB565); tiles.add(iOrb); charCodes.add( "Oa");
        iWarpTile = g.newImage("warp.png", ImageFormat.RGB565); tiles.add(iWarpTile); charCodes.add( "w0");
        iFinishTile = g.newImage("finish.png", ImageFormat.RGB565); tiles.add(iFinishTile); charCodes.add( "f!");
        iBasicTile = g.newImage("basictile.png", ImageFormat.RGB565); tiles.add(iBasicTile); charCodes.add( "b+");
        iRockTile = g.newImage("midtile.png", ImageFormat.RGB565); tiles.add(iRockTile); charCodes.add( "b-");
        iStoneTile = g.newImage("stonetile.png", ImageFormat.RGB565); tiles.add(iStoneTile); charCodes.add( "b/");
        iFireTile = g.newImage("firetile.png", ImageFormat.RGB565); tiles.add(iFireTile); charCodes.add( "d*");
        iIceTile = g.newImage("icetile.png", ImageFormat.RGB565); tiles.add(iIceTile); charCodes.add( "s_");
        iMetalTile = g.newImage("metaltile.png", ImageFormat.RGB565); tiles.add(iMetalTile); charCodes.add( "b&");
        iSnowTile = g.newImage("snowtile.png", ImageFormat.RGB565); tiles.add(iSnowTile); charCodes.add( "b^");

    }}

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
        Log.e("olderorbgame", fileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileLocation,fileName)));
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readFromMemory(String fileName)
    {
        String res = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileLocation,fileName)));
            String read;
            StringBuilder builder = new StringBuilder("");
            while((read = bufferedReader.readLine()) != null){
                builder.append(read);
            }
            res = builder.toString();
            bufferedReader.close();
        } catch (IOException e) {

        }
        return res;
    }

    public static String getLevelString(int packID, int levelNum) {
        return LevelStorage.getLevelString(packID,levelNum);
    }

}