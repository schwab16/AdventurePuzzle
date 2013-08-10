package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class TileFinish extends Tile {
    public TileFinish(Coord coord, char id) {
        super(coord, id);
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
    public void update() {

    }

    @Override
    public void onTouch() {

    }


    public float frameDT = 0;
    public int currentFrame = (int)((Math.random()*Assets.iFinishTile.getWidth()-2)/C.blocksSize) + 1;
    public float frameLength = 5;
    public boolean up = Math.random() < .5;
    int max = Assets.iFinishTile.getWidth()/C.blocksSize;
    @Override
    public Anim getImage(float deltaTime) {
        Image i = Assets.iFinishTile;
        frameDT += deltaTime;

        if (frameDT > frameLength)
        {
            frameDT -= frameLength;
            if (up) currentFrame++;
            else currentFrame--;

            if (currentFrame == max-1) up = false;
            if (currentFrame == 0) up = true;
        }

        Coord cc = new Coord(currentFrame * C.blocksSize,0);
        return new Anim(i,cc);
    }
}
