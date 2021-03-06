package com.jordan.tunnelgame;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Image;
import com.jordan.framework.Input;
import com.jordan.framework.Screen;

import java.util.List;
import java.util.Scanner;

public class ScreenLevelEditor extends Screen {
    public enum EditorType {
        Block, Placement, Warps, Select, Test, TooManyWarps, Save, Medal, Star
    }
    EditorType state = EditorType.Select;

    public static String levelName = C.defaultLevelName;
    public static String backgroundString = "";
    public static String starString = "(200,50)$(640,50)$(1080,50)";
    public static String medalString = "7.0$15.0$25.0";
    public static String levelString =
                    "                                " +
                    "                                " +
                    "                                " +
                    "                                " +
                    "                                " +
                    "                                " +
                    "                                " +
                    "        Oa                      " +
                    "  f!                        Ca  " +
                    "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";
    public static int levelNum;
    public Level level = new Level(0,C.specialLoadConstant);
    public Image selectedImage = Assets.iBasicTile;
    public int currentTile = 5;
    public int currentBackground = 0;
    public float levelTime = 0;

    public ScreenLevelEditor(Game game, int num) {
        super(game);
        state = EditorType.Select;
        Assets.menuByString("editor");
        levelNum = num;
        currentTile = 5;

        levelName = C.defaultLevelName;
        backgroundString = "";
        starString = "(200,50)$(640,50)$(1080,50)";
        medalString = "7.0$15.0$25.0";
        levelString =   "                                " +
                    "                                " +
                    "                                " +
                    "                                " +
                    "                                " +
                    "                                " +
                    "                                " +
                    "        Oa                      " +
                    "  f!                        Ca  " +
                    "b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+b+";

        String k = Assets.readFromMemory(C.fileName + levelNum + ".txt");
        if (!k.equals("")) {
            Log.d("olderorbgame", k);
            Scanner sc = new Scanner(k);
            sc.useDelimiter("#");
            levelName = sc.next();
            backgroundString = sc.next();
            starString = sc.next();
            medalString = sc.next();
            levelString = sc.next();
        }

        level.load();
    }

    private void updateSelect(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
        {
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x < C.editorSelectWidth) {
                int numOptions = 8;
                if (t.y > 0 && t.y < C.height/numOptions) state = EditorType.Placement;
                if (t.y > C.height/numOptions && t.y < 2*C.height/numOptions) {
                    state = EditorType.Block;
                    Assets.menuByString("block");
                }
                if (t.y > 2*C.height/numOptions && t.y < 3*C.height/numOptions) state = EditorType.Star;
                if (t.y > 3*C.height/numOptions && t.y < 4*C.height/numOptions) state = EditorType.Medal;
                if (t.y > 4*C.height/numOptions && t.y < 5*C.height/numOptions) state = EditorType.Warps;
                if (t.y > 5*C.height/numOptions && t.y < 6*C.height/numOptions) state = EditorType.Test;
                if (t.y > 6*C.height/numOptions && t.y < 7*C.height/numOptions) {
                    currentBackground = (currentBackground+1) % Assets.backgrounds.size();
                    backgroundString = Assets.backgrounds.get(currentBackground);
                    level.load();
                }
                if (t.y > 7*C.height/numOptions && t.y < 8*C.height/numOptions) state = EditorType.Save;
            }
        }
    }
    private void paintSelect() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level,0,-1);
        g.drawImage(Assets.menu,0,0);
        int k = (currentTile == 7) ? 80 : 0;
        g.drawImage(selectedImage,C.editorCurrentBlockX,C.editorCurrentBlockY,0+k,0,C.blocksSize,selectedImage.getHeight());
    }


    public boolean selected = false;
    public int warpCharLocation;
    private void updateWarps(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width - C.pauseArea && t.y < C.pauseArea)
            {
                selected = false;
                state = EditorType.Select;
                Assets.menuByString("editor");
                return;
            }
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN) {
                int x = t.x/C.blocksSize;
                int y = t.y/C.blocksSize;
                int xy = 2 * x + 2 * C.xBlocks * y;
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
                level.load();
            }
    }
    private void paintWarps() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level,0,-1);
        if (selected) g.drawImage(Assets.selectedwarp,((warpCharLocation/2) % C.xBlocks)*C.blocksSize,((warpCharLocation/2) / C.xBlocks)*C.blocksSize);
        g.drawImage(Assets.returnicon,C.width - C.pauseArea,0);
    }


    private void updatePlacement(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width - C.pauseArea && t.y < C.pauseArea)
            {
                state = EditorType.Select;
                Assets.menuByString("editor");
                return;
            }
        for (Input.TouchEvent t : touchEvents) {
            if (t.type == Input.TouchEvent.TOUCH_DOWN) {
                if (TileWarp.otherWarps.size() == 42 && currentTile == 1)
                {
                    state = EditorType.TooManyWarps;
                    Assets.menuByString("toomanywarps");
                    return;
                }
                int delWarp = -1;
                int x = t.x/C.blocksSize;
                int y = t.y/C.blocksSize;
                int xy = 2 * x + 2 * C.xBlocks * y;
                if (levelString.charAt(xy) == 'w') delWarp = ((TileWarp)level.tiles[x][y]).myID;
                levelString = levelString.substring(0,xy) + Assets.charCodes.get(currentTile) + levelString.substring(xy+2);
                level.load();
                if (currentTile == 1) {
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
                level.load();
            }
        }

    }
    private void paintPlacement() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level,0,-1);
        g.drawImage(Assets.returnicon,C.width-C.pauseArea,0);
    }

    private void updateBlock(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width-C.pauseArea && t.y < C.pauseArea) {
                state = EditorType.Select;
                Assets.menuByString("editor");
                return;
            }
        for (Input.TouchEvent t : touchEvents) {
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x < C.width - C.blocksSize) {
                try {
                    selectedImage = Assets.tiles.get((t.y/C.blocksSize)*(C.xBlocks - 1) + (t.x/C.blocksSize));
                    currentTile = (t.y/C.blocksSize)*(C.xBlocks-1) + (t.x/C.blocksSize);
                    state = EditorType.Placement;
                } catch (Exception e) {}
            }
        }
    }
    private void paintBlock() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menu,0,0);
        int x = 0, y= 0;
        for (int k = 0; k < Assets.tiles.size(); k++)
        {
            Image i = Assets.tiles.get(k);
            if (k == 7)
                g.drawImage(i,x,y,80,0,C.blocksSize,i.getHeight());
            else
                g.drawImage(i,x,y,0,0,C.blocksSize,i.getHeight());
            x+=C.blocksSize;
            if (x == C.width - C.blocksSize) {
                x = 0;
                y += C.blocksSize;
            }
        }
        g.drawImage(Assets.returnicon,1230,0);
    }


    private void updateTest(List<Input.TouchEvent> touchEvents, float deltaTime) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width - C.pauseArea && t.y < C.pauseArea) {
                state = EditorType.Select;
                Assets.menuByString("editor");
                level.load();
                levelTime = 0;
                return;
            }
        levelTime += deltaTime;
        ScreenGame.GameState s = GameRunner.update(touchEvents, deltaTime, level);
        if (s != ScreenGame.GameState.Running) {
            level.load();
            levelTime = 0;
        }
    }
    private void paintTest(float deltaTime) {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level,deltaTime, levelTime);
        g.drawImage(Assets.returnicon,C.width-C.pauseArea,0);
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
        g.drawARGB(C.darkness,0,0,0);
        g.drawImage(Assets.menu,0,0);
    }


    private void updateSave(List<Input.TouchEvent> touchEvents) {
        switch (Assets.saveButtons.update(touchEvents))
        {
            case 0:
                String j = ("\"" + levelName +"#"+ backgroundString + "#" + starString + "#" + medalString + "#\" +\n\""+ levelString.substring(0,32) + "\" +\n\"" + levelString.substring(32,64) + "\" +\n\"" + levelString.substring(64,96) + "\" +\n\"" + levelString.substring(96,128) + "\" +\n\"" + levelString.substring(128,160) + "\" +\n\"" + levelString.substring(160,192) + "\" +\n\"" + levelString.substring(192,224) + "\" +\n\"" + levelString.substring(224,256) + "\" +\n\"" + levelString.substring(256,288) + "\" +\n\"" + levelString.substring(288,320) + "\";");
                if (C.cheats) {
                    Log.d("olderorbgame", j);
                }
                Assets.writeToMemory(C.fileName + levelNum + ".txt", levelName + "#" + backgroundString + "#" + starString + "#" + medalString + "#" + levelString);
                game.setScreen(new ScreenEditorSelect(game));
                break;
            case 1:
                String jj = ("\"" + levelName +"#"+ backgroundString + "#" + starString + "#" + medalString + "#\" +\n\""+ levelString.substring(0,32) + "\" +\n\"" + levelString.substring(32,64) + "\" +\n\"" + levelString.substring(64,96) + "\" +\n\"" + levelString.substring(96,128) + "\" +\n\"" + levelString.substring(128,160) + "\" +\n\"" + levelString.substring(160,192) + "\" +\n\"" + levelString.substring(192,224) + "\" +\n\"" + levelString.substring(224,256) + "\" +\n\"" + levelString.substring(256,288) + "\" +\n\"" + levelString.substring(288,320) + "\";");
                if (C.cheats) {
                    Log.d("olderorbgame", jj);
                }
                game.setScreen(new ScreenEditorSelect(game));
                break;
            case 2:
                state = EditorType.Select;
                break;
        }
    }
    private void paintSave() {
        Graphics g = game.getGraphics();
        paintSelect();
        g.drawARGB(C.darkness,0,0,0);
        Assets.saveButtons.paint(g);
    }


    private void updateMedal(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width - C.pauseArea && t.y < C.pauseArea) {
                state = EditorType.Select;
                Assets.menuByString("editor");
                level.load();
                return;
            }
        Scanner sc = new Scanner(medalString);
        sc.useDelimiter("[$]+");
        Float gold = Float.parseFloat(sc.next());
        Float silver = Float.parseFloat(sc.next());
        Float bronze = Float.parseFloat(sc.next());
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN)
                for(int k = 0; k < 18; k++)
                    if (t.x > C.medalLocs[k][1] - C.medalWidth/2 && t.x < C.medalLocs[k][1] + C.medalWidth/2 && t.y > C.medalLocs[k][0] - C.medalWidth/2 && t.y < C.medalLocs[k][0] + C.medalWidth/2) {
                        if (k/6 == 0) {
                            switch (k%6) {
                                case 0: gold += 10; break;
                                case 1: gold += 1; break;
                                case 2: gold += 0.1f; break;
                                case 5: gold -= 0.1f; break;
                                case 4: gold -= 1; break;
                                case 3: gold -= 10; break;
                            }
                        }
                        else if (k/6 == 1) {
                            switch (k%6) {
                                case 0: silver += 10; break;
                                case 1: silver += 1; break;
                                case 2: silver += 0.1f; break;
                                case 5: silver -= 0.1f; break;
                                case 4: silver -= 1; break;
                                case 3: silver -= 10; break;
                            }
                        }
                        else if (k/6 == 2) {
                            switch (k%6) {
                                case 0: bronze += 10; break;
                                case 1: bronze += 1; break;
                                case 2: bronze += 0.1f; break;
                                case 5: bronze -= 0.1f; break;
                                case 4: bronze -= 1; break;
                                case 3: bronze -= 10; break;
                            }
                        }
                    }
        if (gold <= 1) gold = 1.0f;
        if (bronze > 100) bronze = 99.9f;
        if (gold >= silver) silver = gold + 0.1f;
        if (silver >= bronze) bronze = silver + 0.1f;
        gold = Math.round(100*gold) / 100.0f;
        silver = Math.round(100*silver) / 100.0f;
        bronze = Math.round(100*bronze) / 100.0f;
        medalString = gold + "$" + silver + "$" + bronze;
        level.load();
    }
    private void paintMedal() {
        Graphics g = game.getGraphics();
        Assets.menuByString("medal");
        g.drawImage(Assets.menu,0,0);
        g.drawImage(Assets.returnicon,C.width-C.pauseArea,0);
        for(int k = 0; k < 18; k++)
        {
            g.drawRect(C.medalLocs[k][1] - C.medalWidth/2,C.medalLocs[k][0] - C.medalWidth/2,C.medalWidth,C.medalWidth, Color.BLACK);
        }
        Scanner sc = new Scanner(medalString);
        sc.useDelimiter("[$]+");
        String gold = sc.next();
        String silver = sc.next();
        String bronze = sc.next();
        Paint paint = new Paint();
        paint.setTextSize(C.paintSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        g.drawString(gold,C.medalLocs[18][1],C.medalLocs[18][0] + C.medalWidth/3, paint);
        g.drawString(silver,C.medalLocs[19][1],C.medalLocs[19][0] + C.medalWidth/3, paint);
        g.drawString(bronze,C.medalLocs[20][1],C.medalLocs[20][0] + C.medalWidth/3, paint);
    }


    private void updateStar(List<Input.TouchEvent> touchEvents) {
        for (Input.TouchEvent t : touchEvents)
            if (t.type == Input.TouchEvent.TOUCH_DOWN && t.x > C.width - C.pauseArea && t.y < C.pauseArea) {
                state = EditorType.Select;
                Assets.menuByString("editor");
                level.load();
                return;
            }
        for (Input.TouchEvent event: touchEvents)
            switch(event.type)
            {
                case Input.TouchEvent.TOUCH_DOWN:
                    double distance = Assets.star.getWidth();
                    Star closest = null;
                    for (Star s: level.stars)
                    {
                        double starDistance = Assets.distance(s.coord, new Coord(event.x, event.y));
                        if (starDistance < distance)
                        {
                            distance = starDistance;
                            closest = s;
                        }
                    }
                    if (closest != null)
                    {
                        closest.pointerID = event.pointer;
                        closest.coord = new Coord(event.x, event.y);
                    }
                    break;
                case Input.TouchEvent.TOUCH_DRAGGED:
                    for (Star s: level.stars)
                        if (event.pointer == s.pointerID)
                            s.coord = new Coord(event.x, event.y);
                    break;
                case Input.TouchEvent.TOUCH_UP:
                    for (Star s: level.stars)
                        if (event.pointer == s.pointerID)
                        {
                            s.coord = new Coord(event.x, event.y);
                            s.pointerID = -1;
                        }
            }
        for (Star s: level.stars)
        {
            if (s.coord.x < C.blocksSize/2) s.coord.x = C.blocksSize/2;
            if (s.coord.x > C.width - C.blocksSize/2) s.coord.x = C.width - C.blocksSize/2;
            if (s.coord.y > C.height - C.blocksSize/2) s.coord.y = C.height - C.blocksSize/2;
            if (s.coord.y < C.blocksSize/2) s.coord.y = C.blocksSize/2;
        }
        int s0 = level.stars.get(0).pointerID;
        int s1 = level.stars.get(1).pointerID;
        int s2 = level.stars.get(2).pointerID;
        starString = level.stars.get(0).coord.toString(true) + "$" + level.stars.get(1).coord.toString(true) + "$" + level.stars.get(2).coord.toString(true);
        if (touchEvents.size() > 0) level.load();
        level.stars.get(0).pointerID = s0;
        level.stars.get(1).pointerID = s1;
        level.stars.get(2).pointerID = s2;
    }
    private void paintStar() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level,0,-1);
        g.drawImage(Assets.returnicon,C.width-C.pauseArea,0);
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
            case Test: updateTest(touchEvents, deltaTime); break;
            case Save: updateSave(touchEvents); break;
            case Star: updateStar(touchEvents); break;
            case Medal: updateMedal(touchEvents);
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
            case Test: paintTest(deltaTime); break;
            case Save: paintSave(); break;
            case Star: paintStar(); break;
            case Medal: paintMedal();
        }
    }

    @Override
    public void pause() {
        state = EditorType.Select;
        selected = false;
        Assets.menuByString("editor");
        level.load();
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        if (state == EditorType.Select) game.setScreen(new ScreenEditorSelect(game));
        else if (state == EditorType.Medal) return;
        else {
            state = EditorType.Select;
            selected = false;
            Assets.menuByString("editor");
            level.load();
        }
    }

}