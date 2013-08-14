package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileDeath extends Tile {
    public TileDeath(Coord coord, char id) {
        super(coord, id);
        switch(id)
        {
            case '*': anim = new Anim(Assets.iFireTile,new int[]{0,1,2,3,4,5,6,7,8,9},10); break;
        }
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        switch (type) {
            case TOP: //basicTopCollision(chaser); chaser.dead = true;
                break;
            case LEFT: basicLeftCollision(chaser); //chaser.dead = true;
                break;
            case RIGHT: basicRightCollision(chaser); //chaser.dead = true;
                break;
            case BOTTOM: basicBottomCollision(chaser);
                break;
            case IN: chaser.dead = true;
                break;
            case NONE:
                break;
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void onTouch() {

    }

    @Override
    public Anim getImage(float deltaTime) {
        anim.addRandom(deltaTime);
        return anim;
    }

}
