package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Orb {

    public Coord coord;
    public final char color;
    public boolean trackable = false;
    public int pointerID = -1;

    public Orb(Coord start, char color, boolean inPlay)
    {
        this.color = color;
        this.coord = start;
        trackable = inPlay;
    }

    public Image getImage()
    {
        switch(color)
        {
            case 'a': break;
            default:
        }
        return Assets.iOrb;
    }

}
