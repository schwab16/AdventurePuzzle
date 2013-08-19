package com.jordan.tunnelgame;

import android.graphics.Color;
import android.graphics.Paint;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

import java.util.List;

public class ScreenGame extends Screen {
	enum GameState {
		Ready, Running, Paused, Fail, Finish
	}
	GameState state = GameState.Ready;

    private float time = 0;
    private int ticks = 0;
    private int medal = -1, star = -1;
    private LevelPack levelPack;
    private Level level;
    private float lastDeltaTime = 0.0f;

	public ScreenGame(Game game, int packID, int levelNum) {
		super(game);
        levelPack = new LevelPack(packID);
        levelPack.startOn(levelNum);
        level = levelPack.nextLevel();
	}


	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        boolean pauseable = true;
        for (Orb o: level.orbs)
            if (o.coord.x>1280-C.pauseArea-10 && o.coord.y<C.pauseArea + 10) pauseable = false;
        for (TouchEvent event: touchEvents)
            if (pauseable && event.x > C.width - C.pauseArea && event.y < C.pauseArea && event.type == TouchEvent.TOUCH_DOWN)
            {
                pause();
                for (Orb o: level.orbs) o.pointerID = -1;
                return;
            }

        time += deltaTime;
        if (time > 99990) time = 99990f;

        state = GameRunner.update(touchEvents,deltaTime,level);

        if (state == GameState.Finish)
        {
            star = level.getStars();
            medal = level.getMedal((int)time/100.0);
            levelPack.setSave(level.levelNum + 1, 0, 'u');
            if (star > levelPack.saved.charAt(3*level.levelNum + 1) - '0')
                levelPack.setSave(level.levelNum, 1, (char)('0' + star));
            if (medal > levelPack.saved.charAt(3*level.levelNum + 2) - '0')
                levelPack.setSave(level.levelNum, 2, (char)('0' + medal));
        }

        lastDeltaTime = deltaTime;
	}
    private void drawRunningUI(float deltaTime) {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level,deltaTime,time);
        g.drawImage(Assets.pauseicon, C.width - C.pauseArea,0);
        if (C.cheats)
            g.drawString("dt: " + (int)(lastDeltaTime*10)/10.0 +" tic:"+ticks+ " fps: " + (int)(100/lastDeltaTime), 1000,50, Assets.paint1);
    }


    private void updateReady(List<TouchEvent> touchEvents) {
        for (TouchEvent t: touchEvents)
            if (t.type == TouchEvent.TOUCH_UP)
                state = GameState.Running;
    }
    private void drawReadyUI(float deltaTime) {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g, level,deltaTime,-1);
        g.drawARGB(C.darkness, 0, 0, 0);
        Assets.menuByString("ready");
        g.drawImage(Assets.menu,0,0);
    }


	private void updatePaused(List<TouchEvent> touchEvents) {
        switch (Assets.pauseButtons.update(touchEvents)) {
            case 0: resume1(); break;
            case 1: retry(); break;
            case 2: levelSelect(); break;
        }
	}
    private void drawPausedUI(float deltaTime) {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g, level,deltaTime,time);
        g.drawARGB(C.darkness, 0, 0, 0);
        Assets.menuByString("pause");
        g.drawImage(Assets.menu,0,0);
        Assets.pauseButtons.paint(g);
    }


	private void updateFail(List<TouchEvent> touchEvents) {
        switch (Assets.failButtons.update(touchEvents)) {
            case 0: retry(); break;
            case 1: levelSelect(); break;
        }
    }
	private void drawFailUI(float deltaTime) {
		Graphics g = game.getGraphics();
        GameDrawer.draw(g,level,deltaTime,-1);
        g.drawARGB(C.darkness, 0, 0, 0);
        Assets.menuByString("fail");
        g.drawImage(Assets.menu,0,0);
        Assets.failButtons.paint(g);
	}


    private void updateFinish(List<TouchEvent> touchEvents) {
        switch (Assets.finishButtons.update(touchEvents)) {
            case 0: nextLevel(); break;
            case 1: retry(); break;
            case 2: levelSelect(); break;
        }
    }
    private void drawFinishUI(float deltaTime) {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level,deltaTime,time);
        g.drawARGB(C.darkness, 0, 0, 0);
        Assets.menuByString("finish");
        g.drawImage(Assets.menu, 0, 0);
        Assets.finishButtons.paint(g);
        g.drawImage(Assets.finishstar,C.finStarX,C.finStarY,Assets.finishstar.getWidth()/4*star,0,Assets.finishstar.getWidth()/4,Assets.finishstar.getHeight());
        g.drawImage(Assets.finishmedal,C.width - Assets.finishmedal.getWidth()/4-C.finStarX,C.finStarY,Assets.finishmedal.getWidth()/4*medal,0,Assets.finishmedal.getWidth()/4,Assets.finishmedal.getHeight());
        float levelTime = time;
        levelTime = (int)levelTime / 100.0f;
        levelTime = (int)(levelTime*10) /10.0f;
        String j = "";
        g.drawString("" + levelTime + j, C.width/2, 375, Assets.paint2);
    }


    @Override
    public void update(float deltaTime) {
        ticks++;
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.Fail)
            updateFail(touchEvents);
        if (state == GameState.Finish)
            updateFinish(touchEvents);
    }

    @Override
    public void paint(float deltaTime) {
        if (state == GameState.Ready)
            drawReadyUI(deltaTime);
        if (state == GameState.Running)
            drawRunningUI(deltaTime);
        if (state == GameState.Paused)
            drawPausedUI(deltaTime);
        if (state == GameState.Fail)
            drawFailUI(deltaTime);
        if (state == GameState.Finish)
            drawFinishUI(deltaTime);
    }


    private void retry() {
        time = 0;
        ticks = 0;
        star = -1;
        medal = -1;
        level = levelPack.thisLevel();
        state = GameState.Ready;
    }

    private void levelSelect() {
        game.setScreen(new ScreenLevelSelect(game,levelPack.packID));
    }

    private void nextLevel(){
        time = 0;
        ticks = 0;
        star = -1;
        medal = -1;
        level = levelPack.nextLevel();
        if (level == null) levelSelect();
        state = GameState.Ready;
    }

    public void resume1() {
        state = GameState.Running;
    }

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

}