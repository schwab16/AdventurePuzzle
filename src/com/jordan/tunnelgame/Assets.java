package com.jordan.tunnelgame;

import com.jordan.framework.Graphics;
import com.jordan.framework.Graphics.ImageFormat;
import com.jordan.framework.Image;

public class Assets {

    public static Image splash;
	public static Image packselect, levelselect, background;
	public static Image iChaser, iOrb, iTile;
    public static Image iBasicTile, iRockTile, iStoneTile, iFireTile, iIceTile, iMetalTile, iSnowTile, iWarpTile, iFinishTile;

	//public static Sound click;
	//public static Music theme;
	
	public static void loadSplash(Graphics g) {
        splash = g.newImage("splash.png", ImageFormat.RGB565);
	}

    public static void load(Graphics g) {

        background = g.newImage("background.png", ImageFormat.RGB565);
        packselect = g.newImage("packselect.png", ImageFormat.RGB565);
        levelselect = g.newImage("levelselect.png", ImageFormat.RGB565);

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

    public static double distance(Coord a, Coord b)
    {
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    public static String getLevelString(int packID, int levelNum) {

        switch (packID * 100 + levelNum)
        {
            case 1:
                return "test1#" +
                        " 0              " +
                        "         2!     " +
                        "      A         " +
                        "          * a   " +
                        "      -++/+__&  " +
                        "+1              " +
                        " &              " +
                        "///     a       " +
                        "/_-&^      0 *-^" +
                        "+++++^+++__*-+++";
            case 2:
                return " ";
            case 3:
                return " ";
            case 4:
                return " ";
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

    /*pack ids:
    0: test

    TILE CHAR IDS
    ' ' : empty space
    '!' : finish tile

    '+' : basic tile
    '-' : rock tile
    '/' : stone tile
    '^' : snow tile
    '&' : metal tile

    '*' : fire tile

    '_' : ice tile

    '0123456789' : warp tile (their id is as they appear, they target the # representing them )

    CHASER/ORB IDS 'a-j' 'A-J'
    'a' : default chaser
    'A' : default orb

    UNUSED SO FAR
    "$'(),.:;<>=?@KLMNOPQRSTUVWXYZ[]`klmnopqrstuvwxyz{}~|

    TO REMEMBER WHEN ADDING:
    1) add picture in assets folder
    2) load picture in assets class
    3) define char here
    4) make it recognised by the level class switch statement
    5) add a class that extends tile, implements methods
    6) getImage needs to return the correct image
    7) add functionality (in the class, in GameRunner)
    8) add it to a level
    */
}
