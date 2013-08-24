package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileDeath extends Tile {
    public TileDeath(Coord coord, char id) {
        super(coord, 'd', id);
        switch(id)
        {
            case '*': anim = new Anim(Assets.iFireTile,new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67},4); break;
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
        anim.add(deltaTime);
        return anim;
    }

}
