package com.jordan.tunnelgame;

import com.jordan.framework.Image;

import java.util.ArrayList;

public class Chaser {

    public Coord coord;
    public final char color;
    public double upwardVelocity = 0;
    public double gravity = C.chaserGravity;
    public double sideVelocity = 0;
    public double momentum = C.chaserMomentum;
    public double resistance = C.chaserResistance;
    public double maxVelocity = C.maxVelocity;
    public boolean readyToWarp[] = new boolean[10];
    public boolean finished = false;
    public boolean dead = false;

    public Chaser(Coord start, char color)
    {
        this.color = color;
        this.coord = start;

        for(int k = 0; k < 10; k++)
        {
            readyToWarp[k] = true;
        }
    }

    public Image getImage()
    {
        switch(color)
        {
            case 'a': break;
            default:
        }
        return Assets.iChaser;
    }

}
