package com.jordan.tunnelgame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Screen;

public class ScreenHelp extends Screen {
    public ScreenHelp(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        switch (Assets.returnButtons.update(game.getInput().getTouchEvents()))
        {
            case 0: backButton();
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuByString("help");
        g.drawImage(Assets.menu,0,0);
        Assets.returnButtons.paint(g);
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
        game.setScreen(new ScreenEditorSelect(game));
    }
}
