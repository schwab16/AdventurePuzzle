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
            case 'a': return Assets.iOrb;
            case 'b': return Assets.iOrb;
            case 'c': return Assets.iOrb;
            case 'd': return Assets.iOrb;
            case 'e': return Assets.iOrb;
            case 'f': return Assets.iOrb;
            case 'g': return Assets.iOrb;
            case 'h': return Assets.iOrb;
            case 'i': return Assets.iOrb;
            case 'j': return Assets.iOrb;
        }
        return Assets.iOrb;
    }

}
