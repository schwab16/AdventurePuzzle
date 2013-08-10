package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Orb {

    public Coord coord;
    public final char color;
    public boolean trackable = true;
    public int pointerID = -1;

    public Orb(Coord start, char color)
    {
        this.color = color;
        this.coord = start;
    }

    public Anim getImage(float deltaTime)
    {
        switch(color)
        {
            case 'a': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'b': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'c': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'd': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'e': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'f': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'g': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'h': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'i': return new Anim(Assets.iOrb,new Coord(0,0));
            case 'j': return new Anim(Assets.iOrb,new Coord(0,0));
        }
        return new Anim(Assets.iOrb,new Coord(0,0));
    }

}
