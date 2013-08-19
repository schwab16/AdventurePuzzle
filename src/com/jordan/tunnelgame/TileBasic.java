package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileBasic extends Tile {
    public TileBasic(Coord coord, char id) {
        super(coord, 'b', id);
        switch(id)
        {
            case '+': anim = new Anim(Assets.iBasicTile,new int[]{0}, 100); break;
            case '<': anim = new Anim(Assets.iDirtTile,new int[]{0}, 100); break;
            case '&': anim = new Anim(Assets.iMetalTile,new int[]{0}, 100); break;
            case '^': anim = new Anim(Assets.iSnowTile,new int[]{0}, 100); break;
            case '~': anim = new Anim(Assets.iGrassTile,new int[]{0}, 100); break;
        }
    }

    /*public TileBasic(Coord coord, char id, boolean ul, boolean ur, boolean dl, boolean dr) {
        super(coord, 'b', id);
        switch(id)
        {
            case '+': anim = new Anim(Assets.iBasicTile,Assets.iBasicCorner,new int[]{0}, 100,ul,ur,dl,dr); break;
            case '<': anim = new Anim(Assets.iDirtTile,new int[]{0}, 100,ul,ur,dl,dr); break;
            case '&': anim = new Anim(Assets.iMetalTile,new int[]{0}, 100,ul,ur,dl,dr); break;
            case '^': anim = new Anim(Assets.iSnowTile,new int[]{0}, 100,ul,ur,dl,dr); break;
        }
    }*/

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
