package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Chaser {

    public Coord coord;
    public final char color;
    public Anim anim;

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

    public boolean keyRed = false;
    public boolean keyBlue = false;
    public boolean keyYellow = false;


    public Chaser(Coord start, char color)
    {
        this.color = color;
        this.coord = start;

        for(int k = 0; k < 43; k++)
            readyToWarp[k] = true;

        switch(color)
        {
            case 'a': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'b': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'c': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'd': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'e': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'f': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'g': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'h': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'i': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
            case 'j': anim = new Anim(Assets.iChaser,new int[]{0},100); break;
        }
    }

    public Anim getImage(float deltaTime)
    {
        anim.add(100);
        return anim;
    }

}