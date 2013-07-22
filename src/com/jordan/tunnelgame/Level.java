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
                char charID = levelString.charAt(x + C.xBlocks*y);
                int pix = C.blocksSize;
                switch(charID)
                {
                    case ' ':
                        tiles[x][y] = new TileEmpty(new Coord(x*pix,y*pix),charID); break;
                    case '!':
                        tiles[x][y] = new TileFinish(new Coord(x*pix,y*pix),charID); break;


                    case '&':case '/':case '+': case '-':case '^':
                        tiles[x][y] = new TileBasic(new Coord(x*pix,y*pix),charID); break;
                    case '*':
                        tiles[x][y] = new TileDeath(new Coord(x*pix,y*pix),charID); break;
                    case '_':
                        tiles[x][y] = new TileIce(new Coord(x*pix,y*pix),charID); break;


                    case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
                        tiles[x][y] = new TileWarp(new Coord(x*pix,y*pix),'0',TileWarp.currentID++,charID);
                        TileWarp.otherWarps.add((TileWarp)tiles[x][y]); break;



                    case 'a': tiles[x][y] = new TileEmpty(new Coord(x*pix,y*pix),charID);
                    chasers.add(new Chaser(new Coord(x*pix,y*pix),'a')); break;

                    case 'A': tiles[x][y] = new TileEmpty(new Coord(x*pix,y*pix),charID);
                        orbs.add(new Orb(new Coord(x*pix+C.blocksSize/2,y*pix+C.blocksSize/2),'a',true)); break;
                }
            }
        }
    }

}
