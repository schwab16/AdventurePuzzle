package com.jordan.tunnelgame;

import android.util.Log;

public class TileBomb extends Tile {
    public TileBomb(Coord coord, char id) {
        super(coord, id);
        anim = new Anim(Assets.iBomb,new int[]{0},100);
        smoke = new Anim(Assets.iSmoke,new int[]{0,1,2},100,0);
    }

    public Anim smoke;
    public boolean primed = false, exploded = false;

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        switch (type) {
            case TOP: //basicTopCollision(chaser);
                break;
            case LEFT: //basicLeftCollision(chaser);
                break;
            case RIGHT:// basicRightCollision(chaser);
                break;
            case BOTTOM: //basicBottomCollision(chaser);
                break;
            case IN:
                if (!exploded) {
                    primed = true;
                    exploded = true;
                    anim = smoke;
                }
                break;
            case NONE:
                break;
        }
    }

    public void explode(Tile[][] tiles) {
        if (primed) {
            for (int x = (int)coord.x/C.blocksSize - 2; x <= coord.x/C.blocksSize + 2; x++)
                for (int y = (int)coord.y/C.blocksSize - 2; y <= coord.y/C.blocksSize + 2; y++)
                    if (x >= 0 && y >=0 && x < C.xBlocks && y < C.yBlocks && tiles[x][y] instanceof TileCracked)
                        ((TileCracked)tiles[x][y]).intact = false;

            primed = false;
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
        if (exploded && anim.currentFrame == 2) {
            anim.image = Assets.dummy;
            anim.setTile(0);
        }
        return anim;
    }
}
