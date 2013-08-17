package com.jordan.tunnelgame;

public class TileTouch extends Tile {
    public TileTouch(Coord coord, char id) {
        super(coord, 't', id);
        isFull = id == 'f';

        empty = new Anim(Assets.iTouchTile, new int[]{0}, 100);
        full = new Anim(Assets.iTouchTile, new int[]{1}, 100);

        if (isFull) anim = full;
        else anim = empty;
    }

    public boolean isFull;
    public Anim empty, full;

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        if (!isFull) return;

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
        isFull = !isFull;

        if (isFull) anim = full;
        else anim = empty;
    }

    @Override
    public Anim getImage(float deltaTime) {
        anim.add(deltaTime);
        return anim;
    }
}
