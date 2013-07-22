package com.jordan.tunnelgame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

public class ScreenLevelSelect extends Screen {

    public int packID;

    public ScreenLevelSelect(Game game, int packID) {
        super(game);
        packID = packID;
    }

    @Override
    public void update(float deltaTime) {


        boolean pass = false;
        for (TouchEvent t : game.getInput().getTouchEvents())
        {
            if (t.type == TouchEvent.TOUCH_UP)
                pass = true;
        }

        int levelNum = 1;

        if (pass) game.setScreen(new ScreenGame(game, packID, levelNum));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.levelselect,0,0);
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

    }
}
