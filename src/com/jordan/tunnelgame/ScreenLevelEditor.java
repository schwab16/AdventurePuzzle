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
import java.util.Scanner;
import java.util.Set;

public class ScreenLevelEditor extends Screen {
    public enum EditorType {
        Block, Placement, Warps, Select, Test, TooManyWarps
    }
    EditorType state = EditorType.Select;

    //public final String fileName = "LevelFromEditor.txt";

    public static String levelName = "le";
    public static String backgroundString = "";
    public static String levelString =
            "                                " +
            "                                " +
            "            Oa                  " +
            "                                " +
            "                                " +
            "                                " +
            "                                " +
            "                                " +
            "  f!                        Ca  " +
            "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
    public static int levelNum;
    public Level level = new Level(0,-1);
    public Image selectedImage = Assets.iBasicTile;
    public int currentTile = 5;
    public int currentBackground = 0;

    public ScreenLevelEditor(Game game, int num) {
        super(game);
        state = EditorType.Select;
        Assets.menuByString("editor");
        levelNum = num;
        if (!C.cheats) {
            String k = Assets.readFromMemory("custom" + levelNum + ".txt");
            if (!k.equals("")) {
                Scanner sc = new Scanner(k);
                sc.useDelimiter("#");
                levelName = sc.next();
                backgroundString = sc.next();
                levelString = sc.next();
            }
        }
        reload();
    }

    private void updateSelect(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
        {
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x < 350) {
                if (t.y > 0 && t.y < 800/6) state = EditorType.Placement;
                if (t.y > 800/6 && t.y < 2*800/6) {state = EditorType.Block;Assets.menuByString("block");}
                if (t.y > 2*800/6 && t.y < 3*800/6) state = EditorType.Warps;
                if (t.y > 3*800/6 && t.y < 4*800/6) state = EditorType.Test;
                if (t.y > 4*800/6 && t.y < 5*800/6) {
                    currentBackground = (currentBackground+1) % Assets.backgrounds.size();
                    backgroundString = Assets.backgrounds.get(currentBackground);
                    reload();
                }
                if (t.y > 5*800/6 && t.y < 6*800/6) {
                    String j = ("\"" + levelName +"#"+ backgroundString +"#\" +\n\""+ levelString.substring(0,32) + "\" +\n\"" + levelString.substring(32,64) + "\" +\n\"" + levelString.substring(64,96) + "\" +\n\"" + levelString.substring(96,128) + "\" +\n\"" + levelString.substring(128,160) + "\" +\n\"" + levelString.substring(160,192) + "\" +\n\"" + levelString.substring(192,224) + "\" +\n\"" + levelString.substring(224,256) + "\" +\n\"" + levelString.substring(256,288) + "\" +\n\"" + levelString.substring(288,320) + "\";");
                    if (C.cheats) {
                        Log.d("olderorbgame", j);
                    }
                    else {
                        Assets.writeToMemory("custom" + levelNum + ".txt", j);
                        game.setScreen(new ScreenPackSelect(game));
                    }
                }
            }
        }
    }
    private void paintSelect() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        g.drawImage(Assets.menu,0,0);
        g.drawImage(selectedImage,600,660);
    }


    public boolean selected = false;
    public int warpCharLocation;
    private void updateWarps(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > 1280 - 50 && t.y < 50)
            {
                selected = false;
                state = EditorType.Select;
                Assets.menuByString("editor");
                return;
            }
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN) {
                int x = t.x/80;
                int y = t.y/80;
                int xy = 2 * x + 2 * 16 * y;
                if (!(level.tiles[x][y] instanceof TileWarp))
                    return;

                if (selected) {
                    char c = (char)(((TileWarp)(level.tiles[x][y])).myID + '0');
                    levelString = levelString.substring(0,warpCharLocation) + "w" + c + levelString.substring(warpCharLocation+2);
                    selected = false;
                }
                else {
                    warpCharLocation = xy;
                    selected = true;
                }
                reload();
            }

        reload();
    }
    private void paintWarps() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        if (selected) g.drawImage(Assets.selectedwarp,((warpCharLocation/2) % 16)*80,((warpCharLocation/2) / 16)*80);
        g.drawImage(Assets.editorreturn,1230,0);
    }


    private void updatePlacement(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > 1280 - 50 && t.y < 50)
            {
                state = EditorType.Select;
                Assets.menuByString("editor");
                return;
            }
        for (Input.TouchEvent t : touchEvents) {
            if (t.type == Input.TouchEvent.TOUCH_DOWN || t.type == Input.TouchEvent.TOUCH_DRAGGED) {
                if (TileWarp.otherWarps.size() == 42 && currentTile == 3)
                {
                    state = EditorType.TooManyWarps;
                    Assets.menuByString("toomanywarps");
                    return;
                }
                int delWarp = -1;
                int x = t.x/80;
                int y = t.y/80;
                int xy = 2 * x + 2 * 16 * y;
                if (levelString.charAt(xy) == 'w') delWarp = ((TileWarp)level.tiles[x][y]).myID;
                levelString = levelString.substring(0,xy) + Assets.charCodes.get(currentTile) + levelString.substring(xy+2);
                if (currentTile == 3) {
                    for (int k = 0; k < 320; k+=2)
                        if (levelString.charAt(k) == 'w')
                            if (levelString.charAt(k+1) >= ((TileWarp)(level.tiles[x][y])).myID + '0' && xy != k)
                                levelString = levelString.substring(0,k) + "w" + ((char)(levelString.charAt(k+1)+1)) + levelString.substring(k+2);
                }
                if (currentTile == 0 && delWarp != -1) {
                    for (int k = 0; k < 320; k+=2)
                        if (levelString.charAt(k) == 'w')
                            if (levelString.charAt(k+1) >= delWarp + '0')
                                levelString = levelString.substring(0,k) + "w" + ((char)(levelString.charAt(k+1)-1)) + levelString.substring(k+2);
                }
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
                Assets.menuByString("editor");
                return;
            }
        for (Input.TouchEvent t : touchEvents) {
            if (t.type == Input.TouchEvent.TOUCH_DOWN) {
                try {
                    selectedImage = Assets.tiles.get((t.y/80)*15 + (t.x/80));
                    currentTile = (t.y/80)*15 + (t.x/80);
                    state = EditorType.Placement;
                } catch (Exception e) {}
            }
        }
    }
    private void paintBlock() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menu,0,0);
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
                Assets.menuByString("editor");
                reload();
                return;
            }
        ScreenGame.GameState s = GameRunner.update(touchEvents, deltaTime, level);
        if (s != ScreenGame.GameState.Running) reload();
    }
    private void paintTest() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        g.drawImage(Assets.editorreturn,1230,0);
    }


    private void updateTooManyWarps(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN)
            {
                state = EditorType.Select;
                Assets.menuByString("editor");
            }

    }
    private void paintTooManyWarps() {
        Graphics g = game.getGraphics();
        paintPlacement();
        g.drawARGB(100,0,0,0);
        g.drawImage(Assets.menu,0,0);
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
            case TooManyWarps: updateTooManyWarps(touchEvents); break;
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
            case TooManyWarps: paintTooManyWarps(); break;
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
        state = EditorType.Select;
        selected = false;
        Assets.menuByString("editor");
        reload();
    }

}
