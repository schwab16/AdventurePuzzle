package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Chaser {

    public Coord coord;
    public final char color;
    public double upwardVelocity = 0;
    public double gravity = C.chaserGravity;
    public double sideVelocity = 0;
    public double momentum = C.chaserMomentum;
    public double resistance = C.chaserResistance;
    public double maxVelocity = C.maxVelocity;
    public boolean readyToWarp[] = new boolean[43];
    public boolean finished = false;
    public boolean dead = false;
    public boolean jumping = false;

    public Chaser(Coord start, char color)
    {
        this.color = color;
        this.coord = start;

        for(int k = 0; k < 10; k++)
            readyToWarp[k] = true;

    }

    public Anim getImage(float deltaTime)
    {
        switch(color)
        {
            case 'a': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'b': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'c': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'd': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'e': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'f': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'g': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'h': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'i': return new Anim(Assets.iChaser,new Coord(0,0));
            case 'j': return new Anim(Assets.iChaser,new Coord(0,0));
        }
        return new Anim(Assets.iChaser,new Coord(0,0));
    }

}