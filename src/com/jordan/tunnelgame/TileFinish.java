package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileFinish extends Tile {
    public TileFinish(Coord coord, char id) {
        super(coord, id);
    }

    @Override
    public void collision(Chaser chaser, CollisionType type) {
        if (type == CollisionType.IN)
            chaser.finished = true;
    }

    @Override
    public void update() {

    }

    @Override
    public void onTouch() {

    }

    @Override
    public Image getImage(float deltaTime) {
        return Assets.iFinishTile;
    }
}
