package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileSlip extends Tile {
    public TileSlip(Coord coord, char id) {
        super(coord, id);
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
    public void update() {

    }

    @Override
    public void onTouch() {

    }

    @Override
    public Image getImage(float deltaTime) {
        return Assets.iIceTile;
    }

}
