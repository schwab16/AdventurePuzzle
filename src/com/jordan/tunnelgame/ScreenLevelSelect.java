package com.jordan.tunnelgame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

import java.util.List;

public class ScreenLevelSelect extends Screen {

    public int packID;
    public String saved;

    public ScreenLevelSelect(Game game, int packID) {
        super(game);
        this.packID = packID;
        saved = LevelPack.read(packID);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEventList = game.getInput().getTouchEvents();

        for (int k = 0; k < touchEventList.size(); k++) {
            TouchEvent t = touchEventList.get(k);
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width - C.pauseArea && t.y < C.pauseArea) {
                backButton();
                return;
            }
            if (t.type == Input.TouchEvent.TOUCH_DOWN) {
                double x = (double)(t.x - C.initX)/(C.sizeX + C.gapX);
                double y = (double)(t.y - C.initY)/(C.sizeY + C.gapY);
                int j = (int)x + 5*(int)y + 1;
                if ((packID != -1 || !Assets.readFromMemory(C.fileName + j + ".txt").equals("")) && (C.cheats || saved.charAt(3*( j )) == 'u' || packID == -1) && x >= 0 && x < 5 && y >=0 && y < 5 && x-(int)x <= (double)C.sizeX/(C.gapX + C.sizeX) && y-(int)y <= (double)C.sizeY/(C.gapY + C.sizeY))
                    game.setScreen(new ScreenGame(game, packID, j));
            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuByString("level");
        g.drawImage(Assets.menu,0,0);
        g.drawImage(Assets.returnicon,C.width-C.pauseArea,0);
        for (int k = 0; k < 25; k++) {
            if (packID == -1) {
                int kk = k+1;
                if (Assets.readFromMemory(C.fileName + kk + ".txt").equals(""))
                    g.drawImage(Assets.locked,(k%5)*(C.sizeX+C.gapX) + C.initX, (k/5)*(C.sizeY + C.gapY) + C.initY);
            }
            else {
                int star = saved.charAt(3*(k+1)+1) - '0';
                int medal = saved.charAt(3*(k+1)+2) - '0';
                char un = saved.charAt(3*(k+1));
                if (un == 'l') {
                    g.drawImage(Assets.locked,(k%5)*(C.sizeX+C.gapX) + C.initX, (k/5)*(C.sizeY + C.gapY) + C.initY);
                }
                else {
                    g.drawImage(Assets.levelstar,(k%5)*(C.sizeX+C.gapX) + C.initX, (k/5)*(C.sizeY + C.gapY) + C.initY + C.sizeY - Assets.levelstar.getHeight()/4*3,(star-1)*Assets.levelstar.getWidth()/3,0,Assets.levelstar.getWidth()/3,Assets.levelstar.getHeight());
                    g.drawImage(Assets.levelmedal,(k%5)*(C.sizeX+C.gapX) + C.initX + C.sizeX/2, (k/5)*(C.sizeY + C.gapY) + C.initY + C.sizeY - Assets.levelmedal.getHeight()/4*3,(medal-1)*Assets.levelmedal.getWidth()/3,0,Assets.levelmedal.getWidth()/3,Assets.levelmedal.getHeight());
                }
            }
        }
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
        ScreenPackSelect.packID = packID;
        game.setScreen(new ScreenPackSelect(game));
    }
}
