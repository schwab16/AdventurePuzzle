package com.jordan.tunnelgame;

import android.graphics.Color;

import com.jordan.framework.Graphics;
import com.jordan.framework.Image;

public class GameDrawer {

    public static void draw(Graphics g, Level level)
    {
        g.clearScreen(Color.BLACK);
        g.drawImage(Assets.background, 0,0);

        for (int x = 0; x < C.xBlocks; x++) {
            for (int y = 0; y < C.yBlocks; y++) {
                Tile t = level.tiles[x][y];
                Image i = t.getImage();
                if (!(t instanceof TileEmpty))
                    g.drawImage(i,(int)t.coord.x,(int)t.coord.y);
            }
        }

        for (Chaser c: level.chasers)
        {
            Image i = c.getImage();
            int x = (int)c.coord.x;
            int y = (int)c.coord.y;
            g.drawImage(i, x, y);
        }

        for (Orb o: level.orbs)
        {
            Image i = Assets.iOrb;
            int x = (int)o.coord.x - i.getWidth()/2;
            int y = (int)o.coord.y - i.getHeight()/2;
            g.drawImage(i, x, y);
        }


    }
}
