package com.jordan.tunnelgame;

import android.graphics.Color;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

public class ScreenLevelSelect extends Screen {

    public int packID;

    public ScreenLevelSelect(Game game, int packID) {
        super(game);
        this.packID = packID;
    }

    @Override
    public void update(float deltaTime) {

        int initX = 150, initY=200, sizeX = 150, sizeY=100, gapX = 57, gapY=15;
        boolean pass = false;
        int levelNum = 1;
        for (TouchEvent t : game.getInput().getTouchEvents())
        {
            if (t.type == TouchEvent.TOUCH_DOWN)
            {
                int x = t.x - initX;
                int y = t.y - initY;
                x /= sizeX + gapX;
                y /= sizeY + gapY;
                if (x >= 0 && x < 5 && y >=0 && y < 5)
                {
                    pass = true;
                    levelNum = x + 5*y + 1;
                }
            }
        }
        if (pass) game.setScreen(new ScreenGame(game, packID, levelNum));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuByString("level");
        g.drawImage(Assets.menu,0,0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        game.setScreen(new ScreenPackSelect(game));
    }
}
