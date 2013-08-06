package com.jordan.tunnelgame;

import com.jordan.framework.Screen;
import com.jordan.framework.implementation.AndroidGame;

public class StartGame extends AndroidGame {

	@Override
	public Screen getInitScreen() {
        Assets.fileLocation = getFilesDir();
		return new ScreenSplashLoading(this);
	}

    @Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	@Override
	public void onResume() {
		super.onResume();
        getCurrentScreen().resume();
		//Assets.theme.play();
	}

	@Override
	public void onPause() {
		super.onPause();
        getCurrentScreen().pause();
		//Assets.theme.pause();
	}
	
	
}