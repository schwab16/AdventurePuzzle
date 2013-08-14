package com.jordan.tunnelgame;

public class TileCracked extends Tile {
    public TileCracked(Coord coord, char id) {
        super(coord, id);
        switch(id)
        {
            case '-': anim = new Anim(Assets.iRockTile,new int[]{0}, 100); break;
            case '/': anim = new Anim(Assets.iStoneTile,new int[]{0}, 100); break;
        }
        smoke = new Anim(Assets.iSmoke,new int[]{0,1,2},100,0);
    }

    public Anim smoke;
    public boolean intact = true;
    public boolean done = false;

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        if (!intact) return;

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
        if (!intact) anim = smoke;
        anim.add(deltaTime);
        if (anim.currentFrame == 2) {
            anim.image = Assets.dummy;
            anim.currentFrame = 0;
            anim.setTile(0);
            done = true;
        }
        return anim;
    }
}
