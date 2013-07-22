package com.jordan.tunnelgame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

public class ScreenPackSelect extends Screen {
    public ScreenPackSelect(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

        boolean pass = false;
        for (TouchEvent t : game.getInput().getTouchEvents())
        {
            if (t.type == TouchEvent.TOUCH_UP)
                pass = true;
        }

        int packID = 0;
        //LevelPack lp = null;
        if (pass) game.setScreen(new ScreenLevelSelect(game, packID));


    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.packselect,0,0);

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
