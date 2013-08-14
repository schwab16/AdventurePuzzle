package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Anim {

    public Image image;
    public Coord coord;
    public float frameDT = 0;
    public int currentFrame;
    public float frameLength;
    public int[] frameOrder;
    //public boolean cornerUL = false; cornerUR = false, cornerDL = false; cornerDR = false;
    //public Image corners;

    public Anim(Image i, int[] frameOrder, float frameLength)
    {
        image = i;
        this.frameOrder = frameOrder;
        this.frameLength = frameLength;

        currentFrame = (int)(frameOrder.length * Math.random());

        coord = new Coord(C.blocksSize * frameOrder[currentFrame], 0);
    }

    /*
    public Anim(Image i, Image c, int[] frameOrder, float frameLength, boolean ul, boolean ur, boolean dl, boolean dr)
    {
        image = i;
        corner = c;
        this.frameOrder = frameOrder;
        this.frameLength = frameLength;

        currentFrame = (int)(frameOrder.length * Math.random());

        coord = new Coord(C.blocksSize * frameOrder[currentFrame], 0);

        cornerUL = ul;
        cornerUR = ur;
        cornerDR = dr;
        cornerDL = dl;
    }
    */

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

    public void addRandom(float deltaTime)
    {
        frameDT += deltaTime;

        if (frameDT > frameLength)
        {
            frameDT -= frameLength;
            currentFrame = (int)(Math.random() * frameOrder.length);
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
