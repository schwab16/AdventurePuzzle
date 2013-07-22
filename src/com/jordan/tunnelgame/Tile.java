package com.jordan.tunnelgame;

import com.jordan.framework.Image;

import java.util.ArrayList;

public abstract class Tile {

    public Coord coord;
    public char id;

    public Tile(Coord coord, char id)
    {
        this.coord = coord;
        this.id = id;
    }

    public abstract void collision(Chaser chaser, CollisionType type);
    public abstract void update();
    public abstract void onTouch();
    public abstract Image getImage(Level level);

    public void basicTopCollision(Chaser chaser)
    {
        if (chaser.upwardVelocity < 0)
            chaser.upwardVelocity = 0;
        chaser.coord.y = coord.y - C.blocksSize + 1;
    }

    public void basicLeftCollision(Chaser chaser)
    {
        if (chaser.sideVelocity > 0)
            chaser.sideVelocity = 0;
        chaser.coord.x = coord.x - C.blocksSize + 1;
    }

    public void basicRightCollision(Chaser chaser)
    {
        if (chaser.sideVelocity < 0)
            chaser.sideVelocity = 0;
        chaser.coord.x = coord.x + C.blocksSize - 1;
    }

    public void basicBottomCollision(Chaser chaser)
    {
        if (chaser.upwardVelocity > 0)
            chaser.upwardVelocity = -chaser.upwardVelocity/2;
        chaser.coord.y = coord.y + C.blocksSize - 1;
    }

    public static ArrayList<Tile> getAdjacentTiles(Tile[][] tiles, Coord c)
    {
        ArrayList<Tile> closeTiles = new ArrayList<Tile>();
        int x = (int)c.x / C.blocksSize;
        int y = (int)c.y / C.blocksSize;

        int adjArea = 2;

        for (int offX = 0-adjArea; offX < adjArea+1; offX++)
            for (int offY = 0-adjArea; offY < adjArea+1; offY++)
                if (x+offX >= 0 && y+offY >= 0 && x+offX < C.xBlocks && y+offY < C.yBlocks)
                    if (!(tiles[x+offX][y+offY] instanceof TileEmpty))
                        closeTiles.add(tiles[x+offX][y+offY]);

        return closeTiles;
    }

    public CollisionType checkForCollision(Chaser chaser)
    {
        int x = (int) chaser.coord.x - (int) coord.x;
        int y = (int) coord.y - (int) chaser.coord.y;

        if (Math.abs(y) < C.blockInsideRatio*C.blocksSize && Math.abs(x) < C.blockInsideRatio*C.blocksSize)
            return CollisionType.IN;
        if (Math.abs(y) < C.blocksSize && Math.abs(x) < C.blocksSize)
        {
            if (Math.abs(x) > Math.abs(y))
            {
                if (x>0) return CollisionType.RIGHT;
                else return CollisionType.LEFT;
            }
            else
            {
                if (y>0) return CollisionType.TOP;
                else return CollisionType.BOTTOM;
            }
        }

        return CollisionType.NONE;
    }

    public enum CollisionType {
        TOP, LEFT, RIGHT, BOTTOM, IN, NONE
    }
}
