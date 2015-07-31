package com.pway.visual;

import com.gameton.pway.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends Activity implements Runnable{
	private AlertDialog exit;
	private MediaPlayer button;	
	static MediaPlayer musica;
	private Boolean cntrl = true;
	static boolean sActive;
	public static final String PREFS_NAME = "SCORES";
	public static final String SOM = "CNTRL_SOM";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent startSplash = new Intent(this,Abertura.class);//splash
		startActivity(startSplash);
		
		
		musica = MediaPlayer.create(this,R.raw.helena);
		musica.setLooping(true);
		
		setContentView(R.layout.menu_inicial);		
		button = MediaPlayer.create(this,R.raw.button);
		button.setLooping(false); 	
		verif();		
		
		new Thread(this).start();
	}
	
	@Override
	public void run(){       
            try {           	
                Thread.sleep(8500);//delay  
               if(cntrl){
            	   musica.start();           	  
            	   musica.setOnCompletionListener(new OnCompletionListener() {
       			    public void onCompletion(MediaPlayer mp) {
       			        mp.release();
       			    };
       			});
               }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }       
       
	}
	
	
	public void StartButton(View view){
		button.start();
		Intent trocaTela = new Intent(this,GameActivity.class);
		startActivity(trocaTela);
	}
	public void RankButton(View view){
		//Toast.makeText(MenuActivity.this,"Botão Rank!",Toast.LENGTH_LONG).show();
		button.start();
		Intent trocaTela = new Intent(this,RankActivity.class);
		startActivity(trocaTela);
	}
	public void SairButton(View view){
		button.start();	
		onCreateExitDialog();
	}
	public void Musica(View view){		
		
		ImageButton som = (ImageButton)findViewById(R.id.imageButtonMusica);
		if(cntrl){
			musica.stop();
			cntrl = false;			
			som.setImageResource(R.drawable.somoff);
			sActive = true;
			Toast.makeText(MenuActivity.this,"MÚSICA:OFF",Toast.LENGTH_LONG).show();
		}else{
			musica = MediaPlayer.create(this,R.raw.helena);
			musica.setLooping(true);
			musica.start();		
			musica.setOnCompletionListener(new OnCompletionListener() {
			    public void onCompletion(MediaPlayer mp) {
			        mp.release();

			    };
			});
			cntrl = true;
			som.setImageResource(R.drawable.somon);
			sActive = false;
			Toast.makeText(MenuActivity.this,"MÚSICA:ON",Toast.LENGTH_LONG).show();
		}		
		
	}
	private void onCreateExitDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Deseja Realmente Sair?")
		.setTitle("Sair");
		// Add the buttons
		builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				if(cntrl){
					musica.stop();
				}
				
				finish();
			}
		});
		builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		//create dialog
		exit = builder.create();
		exit.show();

	}
	@Override
	public void onBackPressed() {
		button.start();
		onCreateExitDialog();
	}
	public void Som(View view){		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);	
	    SharedPreferences.Editor editor = settings.edit();	   
		boolean cntrl_som = settings.getBoolean(SOM,true);
		ImageButton som = (ImageButton)findViewById(R.id.imageButtonSom);
		if(cntrl_som){	
			Toast.makeText(MenuActivity.this,"SOM:OFF",Toast.LENGTH_LONG).show();
			cntrl_som  = false;
			som.setImageResource(R.drawable.off);				 
			
		}else{
			Toast.makeText(MenuActivity.this,"SOM:ON",Toast.LENGTH_LONG).show();
			cntrl_som  = true;			
			som.setImageResource(R.drawable.on);			
			
		}		
		editor.putBoolean(SOM, cntrl_som); 
		editor.commit();
	}
	public void verif(){
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);		   
		boolean cntrl_som = settings.getBoolean(SOM,true);
		ImageButton som = (ImageButton)findViewById(R.id.imageButtonSom);
		if(!cntrl_som){	
			//Toast.makeText(MenuActivity.this,"SONS OFF",Toast.LENGTH_LONG).show();			
			som.setImageResource(R.drawable.off);				 		
		}else{
			//Toast.makeText(MenuActivity.this,"SONS ON",Toast.LENGTH_LONG).show();		
			som.setImageResource(R.drawable.on);						
		}				
	}
	
	
	
	
		
    

   
	
}
