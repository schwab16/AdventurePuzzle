package com.jordan.tunnelgame;

import com.jordan.framework.Graphics;
import com.jordan.framework.Image;

public class GameDrawer {

    public static void draw(Graphics g, Level level, float deltaTime, float levelTime)
    {
        g.drawImage(Assets.background, 0,0);

        for (int x = 0; x < C.xBlocks; x++) {
            for (int y = 0; y < C.yBlocks; y++) {
                Tile t = level.tiles[x][y];
                Image i = t.getImage(deltaTime);
                if (!(t instanceof TileEmpty))
                    g.drawImage(i,(int)t.coord.x,(int)t.coord.y);
            }
        }

        for (Chaser c: level.chasers)
        {
            Image i = c.getImage(deltaTime);
            int x = (int)c.coord.x;
            int y = (int)c.coord.y;
            g.drawImage(i, x, y);
        }

        for (Star s: level.stars)
        {
            if (s.caughtYet) continue;
            Image i = s.getImage(deltaTime);
            int x = (int)s.coord.x - i.getWidth()/2;
            int y = (int)s.coord.y - i.getHeight()/2;
            g.drawImage(i, x, y);
        }

        for (Orb o: level.orbs)
        {
            Image i = o.getImage(deltaTime);
            int x = (int)o.coord.x - i.getWidth()/2;
            int y = (int)o.coord.y - i.getHeight()/2;
            g.drawImage(i, x, y);
        }

        if (levelTime == -1) return;
        levelTime = (int)levelTime / 100.0f;
        levelTime = (int)(levelTime*10) /10.0f;
        String j = "";
        g.drawString("" + levelTime + j, C.width - C.pauseArea*2, C.pauseArea, Assets.paint1);
    }
}
