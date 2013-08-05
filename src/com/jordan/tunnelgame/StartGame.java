package com.jordan.tunnelgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.res.AssetFileDescriptor;
import android.os.Environment;
import android.util.Log;

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