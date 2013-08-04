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

    @Override
    public Image getImage(float deltaTime) {
        return Assets.iWarpTile;
    }
}
