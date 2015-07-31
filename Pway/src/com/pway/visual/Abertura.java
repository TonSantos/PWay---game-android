package com.pway.visual;

import com.gameton.pway.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;



public class Abertura extends Activity implements Runnable{
	private MediaPlayer abertura;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abertura);
		abertura = MediaPlayer.create(this,R.raw.abertura);
		abertura.setLooping(false); 
		abertura.start();
		StartAnimations();		
		new Thread(this).start();
	}
	@Override
	public void run(){
        if (!isFinishing()) // checking activity is finishing or not
        {
            try {           	
                Thread.sleep(8500);//delay
                finish();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
		this.finish();
	}
	
	private void StartAnimations() {
        Animation  anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.imageView1logo);
        iv.clearAnimation();
        iv.startAnimation(anim);
 
        RelativeLayout rl=(RelativeLayout) findViewById(R.id.rl);
        rl.clearAnimation();
        rl.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
       // TextView desen = (TextView)findViewById(R.id.textViewDesen);
       // desen.clearAnimation();
       // desen.startAnimation(anim);
        
        ImageView menina = (ImageView)findViewById(R.id.imageView1Menina);
        menina.clearAnimation();
        menina.startAnimation(anim);
          
        
    }
}
