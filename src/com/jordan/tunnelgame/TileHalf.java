package com.jordan.tunnelgame;

public class TileHalf extends Tile {
    public TileHalf(Coord coord, char id) {
        super(coord, 'h', id);
        switch(id)
        {
            case '+': anim = new Anim(Assets.iHalfTile,new int[]{0}, 100); break;
        }
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        switch (type) {
            case TOP: basicTopCollision(chaser);
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
            case BOTTOM:
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
