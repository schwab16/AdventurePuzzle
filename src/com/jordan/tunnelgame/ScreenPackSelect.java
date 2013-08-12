package com.jordan.tunnelgame;

import android.graphics.Color;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

import java.util.List;

public class ScreenPackSelect extends Screen {
    public ScreenPackSelect(Game game) {
        super(game);
        Assets.packByString(packID);
    }

    public static int packID = 0;

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEventList = game.getInput().getTouchEvents();
        for (int k = 0; k < touchEventList.size(); k++) {
            TouchEvent t = touchEventList.get(k);
            boolean b = false;
            try {
                b = Assets.readFromMemory(C.packFileName + (packID-1)).charAt(78) == 'u' || score() >= packID*100;
            } catch (Exception e) {}
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width-C.pauseArea && t.y < C.pauseArea) {
                backButton();
                return;
            }
            else if (t.type == TouchEvent.TOUCH_DOWN && t.x > C.width/2 - Assets.pack.getWidth()/2 && t.x < C.width/2 + Assets.pack.getWidth()/2 && t.y > C.height/2 - Assets.pack.getHeight()/2 && t.y < C.height/2 + Assets.pack.getHeight()/2 && (packID == 0 || packID == -1 || b || C.cheats)) {
                Assets.packByString(-3);
                if (C.full || packID == 0)
                    game.setScreen(new ScreenLevelSelect(game, packID));
                else game.setScreen(new ScreenBuy(game));
            }
            else if (t.type == TouchEvent.TOUCH_DOWN && t.x > C.packX1 && t.x < C.packX2 && t.y > C.packY1 && t.y < C.packY2) {
                packID--;
                if (packID < -1) packID = C.numPacks - 1;
                Assets.packByString(packID);
            }
            else if (t.type == TouchEvent.TOUCH_DOWN && t.x < C.width - C.packX1 && t.x > C.width - C.packX2 && t.y > C.packY1 && t.y < C.packY2) {
                packID++;
                if (packID >= C.numPacks) packID = -1;
                Assets.packByString(packID);
            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menuByString("pack");
        g.drawImage(Assets.menu,0,0);
        g.drawImage(Assets.returnicon,C.width-C.pauseArea,0);
        g.drawImage(Assets.pack,C.width/2 - Assets.pack.getWidth()/2, C.height/2 - Assets.pack.getHeight()/2);
        int j = packID - 1;
        try {
            if (packID != 0 && packID != -1 && score() < packID*100 && Assets.readFromMemory(C.packFileName + j).charAt(78) != 'u')
                g.drawImage(Assets.locked, C.width/2 - Assets.locked.getWidth()/2, C.height/2 - Assets.locked.getHeight()/2);
        } catch (Exception e) {
            g.drawImage(Assets.locked, C.width/2 - Assets.locked.getWidth()/2, C.height/2 - Assets.locked.getHeight()/2);
        }

        int t = score();

        Assets.paint1.setColor(Color.BLACK);
        g.drawString("Overall Score: " + t + "/900",C.width - 20, C.height - 20,Assets.paint1);
        Assets.paint1.setColor(Color.WHITE);
    }

    public int score() {
        int t = 0;
        for (int l = 0; l < 6; l++){
            String h = Assets.readFromMemory(C.packFileName + l);
            if (h.equals("")) continue;
            for (int k = 1; k<26; k++)
                t += h.charAt(3*k + 1) + h.charAt(3*k + 2) - '0' - '0';
        }
        return t;
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
