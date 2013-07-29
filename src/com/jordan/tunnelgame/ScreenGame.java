package com.jordan.tunnelgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

public class ScreenGame extends Screen {
	enum GameState {
		Ready, Running, Paused, Fail, Finish
	}
	GameState state = GameState.Ready;

    private int time;
    private int ticks;
    private int pauseTicks;
	Paint paint;
    private LevelPack levelPack;
    private Level level;
    private float lastDeltaTime = 0.0f;
    private final int darkness = 100;

	public ScreenGame(Game game, int packID, int levelNum) {
		super(game);
        levelPack = new LevelPack(packID);
        levelPack.startOn(levelNum);
        level = levelPack.nextLevel();

        time = 0;
        ticks = 0;

        //how to animate
		/*hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);*/
		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);

	}


	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        boolean pauseable = true;
        for (Orb o: level.orbs)
            if (o.coord.x>1280-C.pauseArea-10 && o.coord.y<C.pauseArea + 10) pauseable = false;
        for (TouchEvent event: touchEvents)
            if (pauseable && event.x > 1280 - C.pauseArea && event.y < C.pauseArea && event.type == TouchEvent.TOUCH_DOWN)
            {
                pause();
                for (Orb o: level.orbs) o.pointerID = -1;
                return;
            }

        time++;

        state = GameRunner.update(touchEvents,deltaTime,level);

        //anim.update(10);
        lastDeltaTime = deltaTime;
	}
    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        g.drawImage(Assets.pauseicon, 1230,0);
        if (C.cheats)
            g.drawString(lastDeltaTime + " " + GameRunner.message, 400,30, paint);
    }


    private void updateReady(List<TouchEvent> touchEvents) {
        for (TouchEvent t: touchEvents)
            if (t.type == TouchEvent.TOUCH_UP)
                state = GameState.Running;
    }
    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g, level);
        g.drawARGB(darkness, 0, 0, 0);
        Assets.menuByString("ready");
        g.drawImage(Assets.menu,0,0);
    }

	private void updatePaused(List<TouchEvent> touchEvents) {
        switch (Assets.pauseButtons.update(touchEvents)) {
            case 0: resume(); break;
            case 1: retry(); break;
            case 2: levelSelect(); break;
        }
	}
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g, level);
        g.drawARGB(darkness, 0, 0, 0);
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
	private void drawFailUI() {
		Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        g.drawARGB(darkness, 0, 0, 0);
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
    private void drawFinishUI() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        g.drawARGB(darkness, 0, 0, 0);
        Assets.menuByString("finish");
        g.drawImage(Assets.menu,0,0);
        Assets.finishButtons.paint(g);
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
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.Fail)
            drawFailUI();
        if (state == GameState.Finish)
            drawFinishUI();
    }


    private void retry() {
        time = 0;
        ticks = 0;
        level = levelPack.thisLevel();
        state = GameState.Ready;
    }

    private void levelSelect() {
        game.setScreen(new ScreenLevelSelect(game,levelPack.packID));
    }

    private void nextLevel(){
        time = 0;
        ticks = 0;
        level = levelPack.nextLevel();
        if (level == null) levelSelect();
        state = GameState.Ready;
    }

	@Override
	public void pause() {
		if (state == GameState.Running)
        {
			state = GameState.Paused;
        }
	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

}