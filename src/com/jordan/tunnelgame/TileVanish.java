package com.jordan.tunnelgame;

public class TileVanish extends Tile {
    public TileVanish(Coord coord, char id) {
        super(coord, id);

        int[] frames = new int[numFrames];
        for (int k = 0; k < numFrames; k++)
            frames[k] = k;

        switch (id)
        {
            case 'c': anim = new Anim(Assets.iCloudTile,frames,frameLength,0); break;
        }
    }

    public final int maxVanish = 100, maxReappear = 200, frameLength = 5, numFrames = Assets.iCloudTile.getWidth()/C.blocksSize;
    public boolean guyOn = false, walkable = true;
    public float timeUntilVanish = maxVanish, timeUntilReappear = maxReappear;

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        switch (type) {
            case TOP:
                if (walkable) {
                    guyOn = true;
                    basicTopCollision(chaser);
                }
                break;
            case LEFT: //basicLeftCollision(chaser);
                break;
            case RIGHT: //basicRightCollision(chaser);
                break;
            case BOTTOM: //basicBottomCollision(chaser);
                break;
            case IN:
                break;
            case NONE:
                break;
        }
    }

    @Override
    public void update(float deltaTime) {
        if (guyOn && walkable) {
            timeUntilVanish -= deltaTime;
        }
        else if (walkable) {
            timeUntilVanish += deltaTime;
            if (timeUntilVanish > maxVanish) timeUntilVanish = maxVanish;
        }
        else if (!walkable) {
            timeUntilReappear -= deltaTime;
        }

        if (timeUntilVanish < 0) {
            walkable = false;
            timeUntilVanish = 0;
            timeUntilReappear = maxReappear;
        }
        if (timeUntilReappear < 0) {
            walkable = true;
            timeUntilReappear = 0;
            timeUntilVanish = 25;
        }

        guyOn = false;
    }

    @Override
    public void onTouch() {

    }

    @Override
    public Anim getImage(float deltaTime) {
        anim.setTile((int)((maxVanish - timeUntilVanish)*(numFrames-1))/100);
        return anim;
    }
}
