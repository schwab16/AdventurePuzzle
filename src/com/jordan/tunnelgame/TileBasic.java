package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileBasic extends Tile {
    public TileBasic(Coord coord, char id) {
        super(coord, id);
        switch(id)
        {
            case '+': anim = new Anim(Assets.iBasicTile,new int[]{0}, 100); break;
            case '<': anim = new Anim(Assets.iDirtTile,new int[]{0}, 100); break;
            case '&': anim = new Anim(Assets.iMetalTile,new int[]{0}, 100); break;
            case '^': anim = new Anim(Assets.iSnowTile,new int[]{0}, 100); break;
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
