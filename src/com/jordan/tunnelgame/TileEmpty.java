package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileEmpty extends Tile {

    public TileEmpty(Coord coord, char id) {
        super(coord, id);
        anim = new Anim(Assets.iTile,new int[]{0},100);
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {}

    @Override
    public void update(float deltaTime) {}

    @Override
    public void onTouch() {}

    @Override
    public Anim getImage(float deltaTime) {
        return anim;
    }

}
