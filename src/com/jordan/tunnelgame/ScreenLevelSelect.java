package com.jordan.tunnelgame;

import android.graphics.Color;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

import java.util.List;

public class ScreenLevelSelect extends Screen {

    public int packID;

    public ScreenLevelSelect(Game game, int packID) {
        super(game);
        this.packID = packID;
    }

    @Override
    public void update(float deltaTime) {

        List<TouchEvent> touchEventList = game.getInput().getTouchEvents();
        int initX = 150, initY=200, sizeX = 150, sizeY=100, gapX = 57, gapY=15;
        for (int k = 0; k < touchEventList.size(); k++)
        {
            TouchEvent t = touchEventList.get(k);
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > 1280 - 50 && t.y < 50) {
                backButton();
                return;
            }
            if (t.type == Input.TouchEvent.TOUCH_DOWN)
            {
                double x = (double)(t.x - initX)/(sizeX + gapX);
                double y = (double)(t.y - initY)/(sizeY + gapY);
                if (x >= 0 && x < 5 && y >=0 && y < 5 && x-(int)x <= (double)sizeX/(gapX + sizeX) && y-(int)y <= (double)sizeY/(gapY + sizeY))
                    game.setScreen(new ScreenGame(game, packID, (int)x + 5*(int)y + 1));
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuByString("level");
        g.drawImage(Assets.menu,0,0);
        g.drawImage(Assets.returnicon,1280-50,0);
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
