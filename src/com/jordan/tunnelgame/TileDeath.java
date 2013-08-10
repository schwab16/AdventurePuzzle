package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileDeath extends Tile {
    public TileDeath(Coord coord, char id) {
        super(coord, id);
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        switch (type) {
            case TOP: basicTopCollision(chaser); chaser.dead = true;
                break;
            case LEFT: basicLeftCollision(chaser); chaser.dead = true;
                break;
            case RIGHT: basicRightCollision(chaser); chaser.dead = true;
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
            case '*': return new Anim(Assets.iFireTile,new Coord(0,0));
        }
        return new Anim(Assets.iFireTile,new Coord(0,0));
    }

}
