package com.jordan.tunnelgame;

import com.jordan.framework.Image;

public class Button {
    public int pointerID = -1;
    public boolean down = false;
    public Image image;

    public Button(Image image)
    {
        this.image = image;
    }
}
