package com.pway.util;

import com.gameton.pway.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Sounds extends Activity{	
	private MediaPlayer bubble;
	private MediaPlayer bum;
	private MediaPlayer item;
	
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		bubble = MediaPlayer.create(this,R.raw.jump);
		bubble.setLooping(false);
		
		bum = MediaPlayer.create(this, R.raw.bum);
		bum.setLooping(false);
		
	}
	
	public void jump(){
		bubble.start();
	}
	public void bum(){
		bum.start();
	}
		
}
