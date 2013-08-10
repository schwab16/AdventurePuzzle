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
    public Anim getImage(float deltaTime) {
        switch(id)
        {
            case '+': return new Anim(Assets.iBasicTile,new Coord(0,0));
            case '-': return new Anim(Assets.iRockTile,new Coord(0,0));
            case '/': return new Anim(Assets.iStoneTile,new Coord(0,0));
            case '&': return new Anim(Assets.iMetalTile,new Coord(0,0));
            case '^': return new Anim(Assets.iSnowTile,new Coord(0,0));
        }
        return new Anim(Assets.iBasicTile,new Coord(0,0));
    }

}
