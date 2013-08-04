package com.jordan.tunnelgame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input;
import com.jordan.framework.Screen;

import java.util.List;

public class ScreenEditorSelect extends Screen {
    public ScreenEditorSelect(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEventList = game.getInput().getTouchEvents();
        switch (Assets.helpButtons.update(touchEventList))
        {
            case 0: game.setScreen(new ScreenHelp(game)); return;
        }

        for (int k = 0; k < touchEventList.size(); k++)
        {
            Input.TouchEvent t = touchEventList.get(k);
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width-C.pauseArea && t.y < C.pauseArea) {
                backButton();
                return;
            }
            if (t.type == Input.TouchEvent.TOUCH_DOWN)
            {
                double x = (double)(t.x - C.initX)/(C.sizeX + C.gapX);
                double y = (double)(t.y - C.initY)/(C.sizeY + C.gapY);
                if (x >= 0 && x < 5 && y >=0 && y < 5 && x-(int)x <= (double)C.sizeX/(C.gapX + C.sizeX) && y-(int)y <= (double)C.sizeY/(C.gapY + C.sizeY))
                    game.setScreen(new ScreenLevelEditor(game,(int)x + 5*(int)y + 1));
            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuByString("editorselect");
        g.drawImage(Assets.menu,0,0);
        Assets.helpButtons.paint(g);
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
        game.setScreen(new ScreenMainMenu(game));
    }
}
