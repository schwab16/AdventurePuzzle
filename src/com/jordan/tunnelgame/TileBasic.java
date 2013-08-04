package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileBasic extends Tile {
    public TileBasic(Coord coord, char id) {
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
        switch(id)
        {
            case '+': return Assets.iBasicTile;
            case '-': return Assets.iRockTile;
            case '/': return Assets.iStoneTile;
            case '&': return Assets.iMetalTile;
            case '^': return Assets.iSnowTile;
        }
        return Assets.iBasicTile;
    }

}
