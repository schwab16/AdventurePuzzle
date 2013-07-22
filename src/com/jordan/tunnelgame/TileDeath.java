package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileDeath extends Tile {
    public TileDeath(Coord coord, char id) {
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
    public Image getImage(Level level) {
        switch(id)
        {
            case '*': return Assets.iFireTile;
        }
        return Assets.iFireTile;
    }

}
