package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Star {

    public Coord coord;
    public boolean caughtYet = false;

    public Star(Coord coord)
    {
        this.coord = coord;
    }

    public void checkCollision(Chaser chaser)
    {
        if (Math.abs(coord.x - chaser.coord.x - C.blocksSize/2) < C.starRatio*C.blocksSize && Math.abs(coord.y - chaser.coord.y - C.blocksSize/2) < C.starRatio*C.blocksSize)
            caughtYet = true;
    }

    public Image getImage(float deltaTime)
    {
        return Assets.star;
    }
}
