package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Anim {

    public Image image;
    public Coord coord;
    public float frameDT = 0;
    public int currentFrame;
    public float frameLength;
    public int[] frameOrder;

    public Anim(Image i, int[] frameOrder, float frameLength)
    {
        image = i;
        this.frameOrder = frameOrder;
        this.frameLength = frameLength;

        currentFrame = (int)(frameOrder.length * Math.random());

        coord = new Coord(C.blocksSize * frameOrder[currentFrame], 0);
    }

    public Anim(Image i, int[] frameOrder, float frameLength, int s)
    {
        image = i;
        this.frameOrder = frameOrder;
        this.frameLength = frameLength;

        currentFrame = s;

        coord = new Coord(C.blocksSize * frameOrder[currentFrame], 0);
    }

    public void add(float deltaTime)
    {
        frameDT += deltaTime;

        if (frameDT > frameLength)
        {
            frameDT -= frameLength;
            currentFrame++;
            if (currentFrame >= frameOrder.length) currentFrame = 0;
            coord = new Coord(C.blocksSize * frameOrder[currentFrame], 0);
        }
    }

    public void setTile(int frame)
    {
        frameDT = 0;
        currentFrame = frame;
        coord = new Coord(C.blocksSize * frameOrder[currentFrame], 0);
    }
}
