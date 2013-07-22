package com.jordan.tunnelgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.res.AssetFileDescriptor;
import android.util.Log;

import com.jordan.framework.Screen;
import com.jordan.framework.implementation.AndroidGame;

public class StartGame extends AndroidGame {

	@Override
	public Screen getInitScreen() {

        //loadStrings();

		return new ScreenSplashLoading(this);

	}

    /*private void loadStrings() {
        for (int k = 0; k < Assets.packNames.length; k++)
        {
            for (int num = 1; num < 2; num++)
            {
                String everything = "";
                AssetFileDescriptor descriptor = null;
                try {
                    descriptor = getAssets().openFd("test1.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileReader reader = new FileReader(descriptor.getFileDescriptor());
                BufferedReader br = new BufferedReader(reader);
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append("\n");
                        line = br.readLine();
                    }
                    everything = sb.toString();
                } catch (IOException e) {

                } finally {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Assets.levelStrings[k][num] = everything;
            }
        }
    }*/

    @Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	/*private String convertStreamToString(int location) {

        InputStream is = getResources().openRawResource(R.raw.map1);

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}*/

	@Override
	public void onResume() {
		super.onResume();

		//Assets.theme.play();

	}

	@Override
	public void onPause() {
		super.onPause();
		//Assets.theme.pause();

	}
	
	
}