package com.jordan.tunnelgame;

import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    public ArrayList<Chaser> chasers = new ArrayList<Chaser>();
    public ArrayList<Orb> orbs = new ArrayList<Orb>();
    public Tile[][] tiles = new Tile[C.xBlocks][C.yBlocks];

    public int packID;
    public int levelNum;

    public Level(int packID, int levelNum)
    {
        this.packID = packID;
        this.levelNum = levelNum;
    }

    public void load()
    {
        Scanner sc = new Scanner(Assets.getLevelString(packID, levelNum));
        sc.useDelimiter("#");

        String peachyDandy = sc.next();
        String levelString = sc.next();

        TileWarp.otherWarps = new ArrayList<TileWarp>();
        TileWarp.currentID = 0;

        for (int y = 0; y < C.yBlocks; y++) {
            for (int x = 0; x < C.xBlocks; x++) {
                char typeID = levelString.charAt(2*(x + C.xBlocks*y));
                char charID = levelString.charAt(2*(x + C.xBlocks*y) + 1);
                int pix = C.blocksSize;

                if (typeID == ' ') tiles[x][y] = new TileEmpty(new Coord(x*pix,y*pix),charID);
                else if (typeID == 'f') tiles[x][y] = new TileFinish(new Coord(x*pix,y*pix),charID);
                else if (typeID == 'b') tiles[x][y] = new TileBasic(new Coord(x*pix,y*pix),charID);
                else if (typeID == 'd') tiles[x][y] = new TileDeath(new Coord(x*pix,y*pix),charID);
                else if (typeID == 's') tiles[x][y] = new TileSlip(new Coord(x*pix,y*pix),charID);
                else if (typeID == 'w') {
                    tiles[x][y] = new TileWarp(new Coord(x*pix,y*pix),'0',TileWarp.currentID++,charID);
                    TileWarp.otherWarps.add((TileWarp)tiles[x][y]);
                }

                else if (typeID == 'C') {
                    tiles[x][y] = new TileEmpty(new Coord(x*pix,y*pix),charID);
                    chasers.add(new Chaser(new Coord(x*pix,y*pix),charID));
                }
                else if (typeID == 'O') {
                    tiles[x][y] = new TileEmpty(new Coord(x*pix,y*pix),charID);
                    orbs.add(new Orb(new Coord(x*pix+C.blocksSize/2,y*pix+C.blocksSize/2),charID,true));
                }

            }
        }
    }

}