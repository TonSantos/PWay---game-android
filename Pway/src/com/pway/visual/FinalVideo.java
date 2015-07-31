package com.pway.visual;

import com.gameton.pway.R;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

public class FinalVideo extends Activity implements SurfaceHolder.Callback, Runnable{

	private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean pausing = false;
    static boolean sActive;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_video);
                 
         
        getWindow().setFormat(PixelFormat.UNKNOWN);
        
        //Displays a video file.   
        VideoView mVideoView = (VideoView)findViewById(R.id.videoView1);
        
         
        String uriPath = "android.resource://com.gameton.pway/"+R.raw.pway;
        Uri uri = Uri.parse(uriPath);
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.start();
        new Thread(this).start();  
     }
    
    @Override
	public void run(){
        if (!isFinishing()) // checking activity is finishing or not
        {
            try {           	
                Thread.sleep(100000);//delay
                finish();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
		this.finish();
	}
      

    
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
    protected void onPause() {
        sActive = false;

        super.onPause();
    }

    @Override
    protected void onStop() {

        // pausing the player in case of exiting from the app
        if (!MenuActivity.musica.isPlaying() && !MenuActivity.sActive) {
        	MenuActivity.musica.start();
        	MenuActivity.musica.setLooping(true);        	
        }

        super.onStop();
    }

    @Override
    protected void onResume() {
        sActive = true;

        if (MenuActivity.musica.isPlaying()) {
        	MenuActivity.musica.pause();        	
        }

        super.onResume();
        }
	
	
	
}
