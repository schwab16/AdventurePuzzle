package com.jordan.tunnelgame;

public class TileVanish extends Tile {
    public TileVanish(Coord coord, char id) {
        super(coord, id);

        switch (id)
        {
            case 'c':
                loop = new Anim(Assets.iCloudTile,new int[]{0,1,2,3,4,5,6,7,8,9},frameLength);
                vanish = new Anim(Assets.iCloudTile,new int[]{10,11,12,13,14,15,16,17,18,19,20},frameLength,10);
                anim = loop;
                break;
        }
    }

    public final int maxVanish = 100, maxReappear = 200, frameLength = 5, numFrames = 10;
    public boolean guyOn = false, walkable = true;
    public float timeUntilVanish = maxVanish, timeUntilReappear = maxReappear;
    public Anim vanish, loop;

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
        if (timeUntilVanish >= maxVanish) {
            float fdt = anim.frameDT;
            loop = new Anim(Assets.iCloudTile,new int[]{0,1,2,3,4,5,6,7,8,9},frameLength,loop.currentFrame);
            loop.frameDT = fdt;
            anim = loop;
        }
        else {
            anim = vanish;
        }

        if (guyOn && walkable) {
            timeUntilVanish -= deltaTime;
        }
        else if (walkable) {
            timeUntilVanish += deltaTime;
            if (timeUntilVanish > maxVanish) {
                timeUntilVanish = maxVanish;
            }
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
        if (timeUntilVanish >= maxVanish) anim.add(deltaTime);
        else anim.setTile((int)(10 + (maxVanish - timeUntilVanish)*(numFrames-1))/100);
        return anim;
    }
}
