package com.jordan.tunnelgame;

import com.jordan.framework.Graphics;
import com.jordan.framework.Image;

public class GameDrawer {

    public static void draw(Graphics g, Level level, float deltaTime, float levelTime)
    {
        g.drawImage(Assets.background, 0,0);

        for (int x = C.xBlocks-1; x >= 0; x--) {
            for (int y = C.yBlocks-1; y >= 0; y--) {
                Tile t = level.tiles[x][y];
                if (t instanceof TileEmpty) continue;
                Anim a = t.getImage(deltaTime);
                Image i = a.image;
                g.drawImage(i,(int)t.coord.x,(int)t.coord.y,(int)a.coord.x,(int)a.coord.y,C.blocksSize,i.getHeight());
                //draw corners
            }
        }

        for (Chaser c: level.chasers)
        {
            Anim a = c.getImage(deltaTime);
            Image i = a.image;
            int x = (int)c.coord.x;
            int y = (int)c.coord.y;
            g.drawImage(i, x, y,(int)a.coord.x,(int)a.coord.y,C.blocksSize,C.blocksSize);
        }

        for (Star s: level.stars)
        {
            if (s.caughtYet) continue;
            Anim a = s.getImage(deltaTime);
            Image i = a.image;
            int x = (int)s.coord.x - C.blocksSize/4;
            int y = (int)s.coord.y - C.blocksSize/4;
            g.drawImage(i, x, y,(int)a.coord.x,(int)a.coord.y,C.blocksSize/2,C.blocksSize/2);
        }

        for (Orb o: level.orbs)
        {
            Anim a =  o.getImage(deltaTime);
            Image i = a.image;
            int x = (int)o.coord.x - C.blocksSize/2;
            int y = (int)o.coord.y - C.blocksSize/2;
            g.drawImage(i, x, y,(int)a.coord.x,(int)a.coord.y,C.blocksSize,C.blocksSize);
        }

        if (levelTime == -1) return;
        levelTime = (int)levelTime / 100.0f;
        levelTime = (int)(levelTime*10) /10.0f;
        String j = "";
        g.drawString("" + levelTime + j, C.width - C.pauseArea*2, C.pauseArea, Assets.paint1);
    }
}
