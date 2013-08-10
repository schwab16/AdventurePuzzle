package com.jordan.tunnelgame;

import com.jordan.framework.Image;

import java.util.ArrayList;

public class TileWarp extends Tile {

    public static int currentID;
    public static ArrayList<TileWarp> otherWarps;
    public int myID;
    public int targetID;

    public TileWarp(Coord coord, char id, int myID, char targetID) {
        super(coord, id);
        this.targetID = targetID - '0';
        this.myID = myID;
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        if (type == CollisionType.IN && chaser.readyToWarp[myID])
        {
            chaser.coord.x = otherWarps.get(targetID).coord.x;
            chaser.coord.y = otherWarps.get(targetID).coord.y;
            chaser.readyToWarp[targetID] = false;
        }
        else if (type == CollisionType.NONE)
        {
            chaser.readyToWarp[myID] = true;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void onTouch() {

    }

    public float frameDT = 0;
    public int currentFrame = (int)(Math.random()*Assets.iWarpTile.getWidth()/C.blocksSize);
    public float frameLength = 4;
    public int max = Assets.iWarpTile.getWidth()/C.blocksSize;
    @Override
    public Anim getImage(float deltaTime) {
        Image i = Assets.iWarpTile;
        frameDT += deltaTime;

        if (frameDT > frameLength)
        {
            frameDT -= frameLength;
            currentFrame++;
            if (currentFrame == max) currentFrame = 0;
        }

        Coord cc = new Coord(currentFrame * C.blocksSize,0);
        return new Anim(i,cc);
    }
}
