package com.jordan.tunnelgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Image;
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

        for (TouchEvent event: touchEvents)
        {
            if (event.x > 1280 - C.pauseArea && event.y < C.pauseArea && event.type == TouchEvent.TOUCH_UP)
            {
                pause();
                return;
            }
        }

        time++;

        state = GameRunner.update(touchEvents,deltaTime,level);

        //anim.update(10);
        lastDeltaTime = deltaTime;
	}
    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        GameDrawer.draw(g,level);
        if (C.cheats)
            g.drawString(lastDeltaTime + " " + GameRunner.message, 400,30, paint);
    }








    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0 && touchEvents.get(0).type == TouchEvent.TOUCH_UP)
            state = GameState.Running;
    }
    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.background, 0,0);

        g.drawARGB(200, 0, 0, 0);
        g.drawString("Tap to Start", 240, 400, paint);
    }


	private void updatePaused(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0 && touchEvents.get(0).type == TouchEvent.TOUCH_DOWN)
		    resume();
	}
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        //drawRunningUI();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(200, 0, 0, 0);
        g.drawString("Tap to Resume", 240, 400, paint);
    }


	private void updateFail(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0 && touchEvents.get(0).type == TouchEvent.TOUCH_UP)
        {
            nullify();
			state = GameState.Ready;
        }
    }
	private void drawFailUI() {
		Graphics g = game.getGraphics();
        //drawRunningUI();
        g.drawARGB(200, 0, 0, 0);
        g.drawString("GAME OVER", 240, 325, paint);
       // g.drawString("SCORE: " + (int)GameDisplay.guy.currentScore, 240, 400, paintc);
		g.drawString("Tap to Retry", 240, 475, paint);
	}


    private void updateFinish(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0 && touchEvents.get(0).type == TouchEvent.TOUCH_UP)
        {
            /*level = levelPack.nextLevel();
            if (level == null)
            {
                nullify();
                game.setScreen(new ScreenLevelSelect(game,levelPack.packID));
            }
            else {
                nullify();
                state = GameState.Ready;
            }
            //nullify();
            //game.setScreen(new ScreenGame(game));*/
        }
    }
    private void drawFinishUI() {
        Graphics g = game.getGraphics();
        //drawRunningUI();
        g.drawARGB(200, 0, 0, 0);
        g.drawString("COMPELTE", 240, 325, paint);
        //g.drawString("SCORE: " + (int)GameDisplay.guy.currentScore, 240, 400, paintc);
        //g.drawString("Tap to Retry", 240, 475, paintc);
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

    private void nullify() {

        time = 0;
        ticks = 0;
        // Call garbage collector to clean up memory.
        //System.gc();
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