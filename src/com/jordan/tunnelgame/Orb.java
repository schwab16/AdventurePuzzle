package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Orb {

    public Coord coord;
    public Anim anim;
    public final char color;
    public boolean trackable = true;
    public int pointerID = -1;

    public Orb(Coord start, char color)
    {
        this.color = color;
        this.coord = start;

        switch(color)
        {
            case 'a': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'b': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'c': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'd': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'e': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'f': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'g': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'h': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'i': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
            case 'j': anim = new Anim(Assets.iOrb,new int[]{0},100); break;
        }
    }

    public Anim getImage(float deltaTime)
    {
        anim.add(deltaTime);
        return anim;
    }

}
