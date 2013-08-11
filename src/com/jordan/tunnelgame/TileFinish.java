package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileFinish extends Tile {
    public TileFinish(Coord coord, char id) {
        super(coord, id);
        anim = new Anim(Assets.iFinishTile,new int[]{0,1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1}, 5);
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        if (type == CollisionType.IN)
        {
            chaser.finished = true;
            chaser.coord = coord.clone();
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
