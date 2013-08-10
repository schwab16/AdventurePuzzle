package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileEmpty extends Tile {

    public TileEmpty(Coord coord, char id) {
        super(coord, id);
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {}

    @Override
    public void update() {}

    @Override
    public void onTouch() {}

    @Override
    public Anim getImage(float deltaTime) {
        return new Anim(Assets.iTile,new Coord(0,0));
    }

}
