package com.jordan.tunnelgame;

import android.graphics.Color;
import android.util.Log;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Image;
import com.jordan.framework.Input;
import com.jordan.framework.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScreenLevelEditor extends Screen {
    public enum EditorType {
        Block, Placement, Warps, Select, Test
    }
    EditorType state = EditorType.Select;

    //public final String fileName = "LevelFromEditor.txt";

    public static String levelName;
    public static String backgroundString;
    public static String levelString;
    public Level level = new Level(0,-1);
    public Image selectedImage = Assets.iBasicTile;
    public int currentTile = 3;
    public int currentBackground = 0;

    public ScreenLevelEditor(Game game) {
        super(game);
        levelName = "le0";
        backgroundString = "";
        levelString =   "                                " +
                        "                                " +
                        "            Oa                  " +
                        "                                " +
                        "                                " +
                        "                                " +
                        "                                " +
                        "                                " +
                        "  f!                        Ca  " +
                        "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
        reload();
    }

    private void updateSelect(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
        {
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x < 640) {
                if (t.y > 0 && t.y < 800/6) state = EditorType.Placement;
                if (t.y > 800/6 && t.y < 2*800/6) state = EditorType.Block;
                if (t.y > 2*800/6 && t.y < 3*800/6) state = EditorType.Warps;
                if (t.y > 3*800/6 && t.y < 4*800/6) state = EditorType.Test;
                if (t.y > 4*800/6 && t.y < 5*800/6) {
                    currentBackground = (currentBackground+1) % Assets.backgrounds.size();
                    backgroundString = Assets.backgrounds.get(currentBackground);
                    reload();
                }
                if (t.y > 5*800/6 && t.y < 6*800/6) {
                    //Assets.writeToMemory(fileName, levelName + backgroundString + levelString);
                    String j = ("\"" + levelName +"#"+ backgroundString +"#\" +\n\""+ levelString.substring(0,32) + "\" +\n\"" + levelString.substring(32,64) + "\" +\n\"" + levelString.substring(64,96) + "\" +\n\"" + levelString.substring(96,128) + "\" +\n\"" + levelString.substring(128,160) + "\" +\n\"" + levelString.substring(160,192) + "\" +\n\"" + levelString.substring(192,224) + "\" +\n\"" + levelString.substring(224,256) + "\" +\n\"" + levelString.substring(256,288) + "\" +\n\"" + levelString.substring(288,320) + "\";");
                    Log.d("olderorbgame", j);
                }
            }
        }
    }
    private void paintSelect() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        g.drawImage(Assets.editoricons,0,0);
        g.drawImage(selectedImage,580,640);
    }

    private void updateWarps(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > 1280 - 50 && t.y < 50)
                state = EditorType.Select;

        reload();
    }
    private void paintWarps() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.editorreturn,1230,0);
    }

    private void updatePlacement(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > 1280 - 50 && t.y < 50)
            {
                state = EditorType.Select;
                return;
            }
        for (Input.TouchEvent t : touchEvents) {
            if (t.type == Input.TouchEvent.TOUCH_DOWN) {
                int x = t.x/80;
                int y = t.y/80;
                int xy = 2 * x + 2 * 16 * y;
                levelString = levelString.substring(0,xy) + Assets.charCodes.get(currentTile) + levelString.substring(xy+2);
                reload();
            }
        }

    }
    private void paintPlacement() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        g.drawImage(Assets.editorreturn,1230,0);
    }

    private void updateBlock(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > 1280 - 50 && t.y < 50) {
                state = EditorType.Select;
                return;
            }
        for (Input.TouchEvent t : touchEvents) {
            if (t.type == Input.TouchEvent.TOUCH_DOWN) {
                try {
                    selectedImage = Assets.tiles.get((t.y/80)*15 + (t.x/80));
                    currentTile = (t.y/80)*15 + (t.x/80);
                    state = EditorType.Select;
                } catch (Exception e) {}
            }
        }
    }
    private void paintBlock() {
        Graphics g = game.getGraphics();
        g.drawRect(0,0,1280,800, Color.WHITE);
        int x = 0, y= 0;
        for (Image i: Assets.tiles)
        {
            g.drawImage(i,x,y);
            x+=80;
            if (x == 1200) {x=0; y +=80;}
        }
        g.drawImage(Assets.editorreturn,1230,0);

    }

    private void updateTest(List<Input.TouchEvent> touchEvents, float deltaTime) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > 1280 - 50 && t.y < 50) {
                state = EditorType.Select;
                reload();
                return;
            }
        ScreenGame.GameState s = GameRunner.update(touchEvents, deltaTime, level);
        if (s != ScreenGame.GameState.Running) reload();
    }
    private void paintTest() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        g.drawImage(selectedImage,1240,760);
        g.drawImage(Assets.editorreturn,1230,0);
    }


    public void reload()
    {
        level.load();
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        switch (state)
        {
            case Block: updateBlock(touchEvents); break;
            case Placement: updatePlacement(touchEvents); break;
            case Warps: updateWarps(touchEvents); break;
            case Select: updateSelect(touchEvents); break;
            case Test: updateTest(touchEvents, deltaTime);
        }
    }

    @Override
    public void paint(float deltaTime) {
        switch (state)
        {
            case Block: paintBlock(); break;
            case Placement: paintPlacement(); break;
            case Warps: paintWarps(); break;
            case Select: paintSelect(); break;
            case Test: paintTest();
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
        game.setScreen(new ScreenPackSelect(game));
    }

}
