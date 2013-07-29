package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Button {
    public int pointerID = -1;
    public boolean down = false;
    public static Image image,downImage;
    public Image text;
    public static int width, height;
    public int w,h;

    public Button(Image text)
    {
        this.text = text;
        w = text.getWidth();
        h = text.getHeight();
    }

    public static void setButtons(Image timage, Image tdownImage)
    {
        image = timage;
        downImage = tdownImage;
        width = image.getWidth();
        height = image.getHeight();
    }

    public void reset()
    {
        down = false;
        pointerID = -1;
    }

    public Image getImage()
    {
        if (down) return downImage;
        else return image;
    }

}
