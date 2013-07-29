package com.jordan.tunnelgame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Screen;

public class ScreenMainMenu extends Screen {
    public ScreenMainMenu(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        switch (Assets.mainButtons.update(game.getInput().getTouchEvents()))
        {
            case 0: game.setScreen(new ScreenPackSelect(game)); break;
            case 1: game.setScreen(new ScreenLevelEditor(game,0)); break;
            case 2: game.setScreen(new ScreenAbout(game)); break;
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuByString("main");
        g.drawImage(Assets.menu,0,0);
        Assets.mainButtons.paint(g);
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
