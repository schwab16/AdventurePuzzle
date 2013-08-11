package com.jordan.tunnelgame;

public class TileVanish extends Tile {
    public TileVanish(Coord coord, char id) {
        super(coord, id);
        switch (id)
        {
            case 'c': anim = new Anim(Assets.iCloudTile,new int[]{0,1,2,3},100); break;
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
