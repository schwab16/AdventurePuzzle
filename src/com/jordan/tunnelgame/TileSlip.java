package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileSlip extends Tile {
    public TileSlip(Coord coord, char id) {
        super(coord, id);
        switch (id)
        {
            case '_': anim = new Anim(Assets.iIceTile, new int[]{0},100); break;
        }
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        switch (type) {
            case TOP: basicTopCollision(chaser);
                break;
            case LEFT: basicLeftCollision(chaser);
                break;
            case RIGHT: basicRightCollision(chaser);
                break;
            case BOTTOM: basicBottomCollision(chaser);
                break;
            case IN:
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
