package com.jordan.tunnelgame;

public class TileKeyhole extends Tile {
    public TileKeyhole(Coord coord, char id) {
        super(coord, id);
        switch(id)
        {
            case 'r': anim = new Anim(Assets.iKeyholeRed,new int[]{0}, 100); break;
            case 'b': anim = new Anim(Assets.iKeyholeBlue,new int[]{0}, 100); break;
            case 'y': anim = new Anim(Assets.iKeyholeYellow,new int[]{0}, 100); break;
        }
    }

    public boolean opened = false;

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        if (!opened && type != CollisionType.NONE) {
            switch(id)
            {
                case 'r': if (chaser.keyRed) opened = true; break;
                case 'b': if (chaser.keyBlue) opened = true; break;
                case 'y': if (chaser.keyRed) opened = true; break;
            }
            if (opened) anim.image = Assets.dummy;
        }

        switch (type) {
            case TOP:
                if (!opened) basicTopCollision(chaser);
                break;
            case LEFT:
                if (!opened) basicLeftCollision(chaser);
                break;
            case RIGHT:
                if (!opened) basicRightCollision(chaser);
                break;
            case BOTTOM:
                if (!opened) basicBottomCollision(chaser);
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
