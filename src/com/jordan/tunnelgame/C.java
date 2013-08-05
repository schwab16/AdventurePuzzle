package com.jordan.tunnelgame;

public class C {

    public static boolean cheats = true;
    public static float maxDeltaTime = 3.15f;

    //dimensions of screen
    public static int width = 1280, height = 800;

    //size of tiles array
    public static int xBlocks = 16;
    public static int yBlocks = 10;
    public static int blocksSize = 80;

    //size of pause area and return areas
    public static int pauseArea = 50;

    //buffer of orbs&chasers
    public static int buffer = 25;
    //chaser speeds
    public static double chaserGravity = -0.1, chaserMomentum = 1, chaserResistance = .75;
    public static double maxVelocity = 5, maxFall = -10;

    //collision area ratios for star, blocks
    public static double starRatio = 0.75;
    public static double blockInsideRatio = .5;

    //level select and editor select touch areas
    public static int initX = 150, initY = 200, sizeX = 150, sizeY = 100, gapX = 57, gapY = 15;

    //special level editor select constants
    public static int editorSelectWidth = 350, editorCurrentBlockX = 600, editorCurrentBlockY = 660;
    public static String fileName = "lvld", defaultLevelName = "le";
    public static int specialLoadConstant = -1;
    public static int[][] medalLocs = {{400,140},{400,240},{400,340},{600,140},{600,240},{600,340},{400,540},{400,640},{400,740},{600,540},{600,640},{600,740},{400,940},{400,1040},{400,1140},{600,940},{600,1040},{600,1140},{500,240},{500,640},{500,1040}};
    public static int medalWidth = 75, paintSize = 75;

    //darkness when used
    public static int darkness = 100;

    //button locations
    public static int[][] buttonNormal3 = {{290,300},{290,475},{290,650}}, buttonNormal2 = {{290,400},{290,600}}, buttonNormal1 = {{290,600}}, buttonHelp = {{-400,0}};
}
