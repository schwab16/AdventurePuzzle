package com.jordan.tunnelgame;

import android.graphics.Color;
import android.graphics.Paint;

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

public class Assets {

    public static Image pauseicon, selectedwarp, returnicon, star, finishstar, finishmedal, locked, levelstar, levelmedal;
    public static Image background , menu, pack;
    public static Button buttonreturn, buttonretry, buttonresume, buttonnextlevel, buttonlevelselect, buttonplay, buttonleveleditor, buttonabout, buttonsave, buttonquit, buttonhelp;
    public static ButtonSet returnButtons, pauseButtons, finishButtons, failButtons, mainButtons, saveButtons, helpButtons;

    public static Image iChaser, iOrb, iTile;
    public static Image iBasicTile, iCloudTile, iRockTile, iStoneTile, iFireTile, iIceTile, iMetalTile, iSnowTile, iWarpTile, iFinishTile;

    public static Paint paint1, paint2;

    //public static Sound click;
    //public static Music theme;

    public static ArrayList<Image> tiles = new ArrayList<Image>();
    public static ArrayList<String> charCodes = new ArrayList<String>();
    public static ArrayList<String> backgrounds = new ArrayList<String>();
    public static String currentBG = "del", currentMenu = "nada";
    public static boolean loaded = false;

    public static Graphics g;
    public static File fileLocation;

    public static void loadSplash(Graphics h) {
        g = h;
        menuByString("splash");
    }

    public static void load() { if (!loaded) { loaded = true;

        backgrounds.add("");
        backgrounds.add("moon");
        backgrounds.add("night");
        backgrounds.add("sunset");
        backgrounds.add("grassland");

        //buttons
        Button.setButtons(g.newImage("button.png", ImageFormat.RGB565),g.newImage("buttonpushed.png", ImageFormat.RGB565));
        buttonretry = new Button(g.newImage("textretry.png", ImageFormat.RGB565));
        buttonlevelselect = new Button(g.newImage("textlevelselect.png", ImageFormat.RGB565));
        buttonnextlevel = new Button(g.newImage("textnextlevel.png", ImageFormat.RGB565));
        buttonresume = new Button(g.newImage("textresume.png", ImageFormat.RGB565));
        buttonplay = new Button(g.newImage("textplay.png", ImageFormat.RGB565));
        buttonleveleditor = new Button(g.newImage("textleveleditor.png", ImageFormat.RGB565));
        buttonabout = new Button(g.newImage("textabout.png", ImageFormat.RGB565));
        buttonreturn = new Button(g.newImage("textreturn.png", ImageFormat.RGB565));
        buttonsave = new Button(g.newImage("textsave.png", ImageFormat.RGB565));
        buttonquit = new Button(g.newImage("textquit.png", ImageFormat.RGB565));
        buttonhelp = new Button(g.newImage("texthelp.png", ImageFormat.RGB565));
        ArrayList<Button> buttonsPause = new ArrayList<Button>(); buttonsPause.add(buttonresume); buttonsPause.add(buttonretry); buttonsPause.add(buttonlevelselect);
        ArrayList<Button> buttonsFail = new ArrayList<Button>(); buttonsFail.add(buttonretry); buttonsFail.add(buttonlevelselect);
        ArrayList<Button> buttonsFinish = new ArrayList<Button>(); buttonsFinish.add(buttonnextlevel); buttonsFinish.add(buttonretry); buttonsFinish.add(buttonlevelselect);
        ArrayList<Button> buttonsMain = new ArrayList<Button>(); buttonsMain.add(buttonplay); buttonsMain.add(buttonleveleditor); buttonsMain.add(buttonabout);
        ArrayList<Button> buttonsReturn = new ArrayList<Button>(); buttonsReturn.add(buttonreturn);
        ArrayList<Button> buttonsSave = new ArrayList<Button>(); buttonsSave.add(buttonsave); buttonsSave.add(buttonquit); buttonsSave.add(buttonreturn);
        ArrayList<Button> buttonsHelp = new ArrayList<Button>(); buttonsHelp.add(buttonhelp);
        pauseButtons = new ButtonSet(buttonsPause, C.buttonNormal3);
        failButtons = new ButtonSet(buttonsFail, C.buttonNormal2);
        finishButtons = new ButtonSet(buttonsFinish, C.buttonNormal3);
        mainButtons = new ButtonSet(buttonsMain, C.buttonNormal3);
        returnButtons = new ButtonSet(buttonsReturn, C.buttonNormal1);
        saveButtons = new ButtonSet(buttonsSave, C.buttonNormal3);
        helpButtons = new ButtonSet(buttonsHelp, C.buttonHelp);

        //icons
        pauseicon = g.newImage("pauseicon.png", ImageFormat.RGB565);//small
        selectedwarp = g.newImage("selectedwarp.png", ImageFormat.RGB565);//small
        returnicon = g.newImage("returnicon.png", ImageFormat.RGB565);//small
        star = g.newImage("star.png", ImageFormat.RGB565);
        finishstar = g.newImage("finishstar.png", ImageFormat.RGB565);
        finishmedal = g.newImage("finishmedal.png", ImageFormat.RGB565);
        levelstar = g.newImage("levelstar.png", ImageFormat.RGB565);
        levelmedal = g.newImage("levelmedal.png", ImageFormat.RGB565);
        locked = g.newImage("locked.png", ImageFormat.RGB565);

        //tiles
        iTile = g.newImage("emptytile.png", ImageFormat.RGB565); tiles.add(iTile); charCodes.add( "  ");
        iWarpTile = g.newImage("warp.png", ImageFormat.RGB565); tiles.add(iWarpTile); charCodes.add( "w0");
        iFinishTile = g.newImage("finish.png", ImageFormat.RGB565); tiles.add(iFinishTile); charCodes.add( "f!");
        iChaser = g.newImage("chaser.png", ImageFormat.RGB565); tiles.add(iChaser); charCodes.add("Ca");
        iOrb = g.newImage("orb.png", ImageFormat.RGB565); tiles.add(iOrb); charCodes.add( "Oa");
        iBasicTile = g.newImage("basictile.png", ImageFormat.RGB565); tiles.add(iBasicTile); charCodes.add( "b+");
        iRockTile = g.newImage("midtile.png", ImageFormat.RGB565); tiles.add(iRockTile); charCodes.add( "b-");
        iStoneTile = g.newImage("stonetile.png", ImageFormat.RGB565); tiles.add(iStoneTile); charCodes.add( "b/");
        iFireTile = g.newImage("firetile.png", ImageFormat.RGB565); tiles.add(iFireTile); charCodes.add( "d*");
        iIceTile = g.newImage("icetile.png", ImageFormat.RGB565); tiles.add(iIceTile); charCodes.add( "s_");
        iMetalTile = g.newImage("metaltile.png", ImageFormat.RGB565); tiles.add(iMetalTile); charCodes.add( "b&");
        iSnowTile = g.newImage("snowtile.png", ImageFormat.RGB565); tiles.add(iSnowTile); charCodes.add( "b^");
        iCloudTile = g.newImage("cloudtile.png", ImageFormat.RGB565); tiles.add(iCloudTile); charCodes.add( "vc");

        paint1 = new Paint();
        paint1.setTextSize(C.pauseArea);
        paint1.setTextAlign(Paint.Align.RIGHT);
        paint1.setAntiAlias(true);
        paint1.setColor(Color.WHITE);
        paint2 = new Paint();
        paint2.setTextSize(200);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);
    }}

    public static void backgroundByString(String bg)
    {
        if (bg.equals(currentBG)) return;
        currentBG = bg;
        if (bg.equals("del")) {
            background = null;
            return;
        }
        background =  g.newImage("background" + bg + ".png", ImageFormat.RGB565);
    }

    public static void menuByString(String mn) {
        if (mn.equals(currentMenu)) return;
        currentMenu = mn;
        menu = g.newImage(mn + "menu.png", ImageFormat.RGB565);
    }

    public static void packByString(int pk) {
        if (pk == -1) pack = g.newImage("packc.png", ImageFormat.RGB565);
        else if (pk == -3) pack = null;
        else pack = g.newImage("pack" + pk + ".png", ImageFormat.RGB565);
    }

    public static double distance(Coord a, Coord b) {
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    public static boolean inBounds(Coord a, Coord b, Coord c) {
        return a.x>b.x && a.x<c.x && a.y>b.y && a.y < c.y;
    }

    public static void writeToMemory(String fileName, String text)
    {
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