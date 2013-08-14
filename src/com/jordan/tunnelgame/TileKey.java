package com.jordan.tunnelgame;

public class TileKey extends Tile {
    public TileKey(Coord coord, char id) {
        super(coord, 'K', id);
        switch(id)
        {
            case 'r': anim = new Anim(Assets.iKeyRed,new int[]{0}, 100); break;
            case 'b': anim = new Anim(Assets.iKeyBlue,new int[]{0}, 100); break;
            case 'y': anim = new Anim(Assets.iKeyYellow,new int[]{0}, 100); break;
        }
    }

    public boolean caught = false;

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        switch (type) {
            case TOP: //basicTopCollision(chaser);
                break;
            case LEFT: //basicLeftCollision(chaser);
                break;
            case RIGHT: //basicRightCollision(chaser);
                break;
            case BOTTOM: //basicBottomCollision(chaser);
                break;
            case IN:
                if (!caught) {
                    switch(id)
                    {
                        case 'r': chaser.keyRed = true; break;
                        case 'b': chaser.keyBlue = true; break;
                        case 'y': chaser.keyYellow = true; break;
                    }
                    caught = true;
                    anim.image = Assets.dummy;
                }
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
