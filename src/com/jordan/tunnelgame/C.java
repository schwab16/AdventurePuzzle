package com.jordan.tunnelgame;

public class C {

    public static boolean cheats = true;
    public static float maxDeltaTime = 3.15f;

    //size of tiles array
    public static int xBlocks = 16;
    public static int yBlocks = 10;
    public static int blocksSize = 80;
    public static double blockInsideRatio = .5;

    //size of pause area
    public static int pauseArea = 50;

    //movement speed of chasers, buffer of orbs/chasers
    public static int buffer = 25;
    //chaser gravity
    public static double chaserGravity = -0.1;
    public static double chaserMomentum = 1, chaserResistance = .75;
    public static double maxVelocity = 5;

}
