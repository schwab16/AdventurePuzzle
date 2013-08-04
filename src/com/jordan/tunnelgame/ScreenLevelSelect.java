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

        for (int k = 0; k < touchEventList.size(); k++)
        {
            TouchEvent t = touchEventList.get(k);
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width - C.pauseArea && t.y < C.pauseArea) {
                backButton();
                return;
            }
            if (t.type == Input.TouchEvent.TOUCH_DOWN)
            {
                double x = (double)(t.x - C.initX)/(C.sizeX + C.gapX);
                double y = (double)(t.y - C.initY)/(C.sizeY + C.gapY);
                if (x >= 0 && x < 5 && y >=0 && y < 5 && x-(int)x <= (double)C.sizeX/(C.gapX + C.sizeX) && y-(int)y <= (double)C.sizeY/(C.gapY + C.sizeY))
                    game.setScreen(new ScreenGame(game, packID, (int)x + 5*(int)y + 1));
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuByString("level");
        g.drawImage(Assets.menu,0,0);
        g.drawImage(Assets.returnicon,C.width-C.pauseArea,0);
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
