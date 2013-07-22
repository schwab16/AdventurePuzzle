package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Button {
    public int pointerID = -1;
    public boolean down = false;
    public Image image,downimage;

    public Button(Image image, Image downimage)
    {
        this.image = image;
        this.downimage = downimage;
    }

    public void reset()
    {
        down = false;
        pointerID = -1;
    }

    public Image getImage()
    {
        if (down) return downimage;
        else return image;
    }

}
