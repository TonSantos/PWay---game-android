package com.pway.visual;



import com.gameton.pway.R;
import com.pway.util.GameParamenterSingleton;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {


	private GameScreen gameScreen;
	private Handler handler;
	public static final String PREFS_NAME = "SCORES";
	public static final String BOB = "SCORES_BOB";
	public static final String BOOKS = "SCORES_BOOK";
	public static final String BUGGER = "SCORES_BUGGER";
	public static final String BURTON = "SCORES_BURTON";
	public static final String FINN = "SCORES_FINN";
	public static final String GERARD = "SCORES_GERARD";
	public static final String HEART = "SCORES_HEART";
	public static final String PIZZA = "SCORES_PIZZA";
	public static final String INSETO = "SCORES_ISENTO";
	public static final String MISSAO = "CNTRL_MISSAO";
	public static final String AUX = "VALOR_AUX";
	public static final String CNTRL = "CNTRL_ESTADO";
	public static final String SOM = "CNTRL_SOM";
	
	private MediaPlayer jump;
	private MediaPlayer bum;
	private MediaPlayer item;
	private MediaPlayer button;
	private MediaPlayer alien;
	private MediaPlayer barata;
	private MediaPlayer eyes;
	private MediaPlayer hand;
	private MediaPlayer lingua;
	private MediaPlayer meleca;
	private MediaPlayer sol;
	private MediaPlayer caveira;
	private MediaPlayer shark;
	private MediaPlayer sarlak;
	
	
	private boolean cntrl_som;
	private boolean controle = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		 cntrl_som = settings.getBoolean(SOM,true);
		
		setSons();
		
		this.setupParameters();	
		this.gameScreen = new GameScreen(this);
						
		
		handler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				if(msg.what == 100){
					mostraViewFinish();
				}
				if(cntrl_som){
					if(msg.what == 50){					
						jump.start();	
						
					}
					if(msg.what == 25){
						bum.start();
						
						
					}
					if(msg.what == 20){
						item.start();
						
					}	
					if(msg.what == 1){
						alien.start();
						
					}
					if(msg.what == 2){
						barata.start();
						
					}
					if(msg.what == 3){					
						eyes.start();
						
					}
					if(msg.what == 4){				
						hand.start();
						
					}
					if(msg.what == 5){				
						lingua.start();
						
					}
					if(msg.what == 6){				
						meleca.start();
						
					}
					if(msg.what == 7){						
						sol.start();
						
					}
					if(msg.what == 8){				
						caveira.start();
						
					}
					if(msg.what == 9){				
						shark.start();
						
					}
					if(msg.what == 10){				
						sarlak.start();
						
					}
				}
				
				
			}
		};
		gameScreen.setHandler(handler);
		
		super.setContentView(gameScreen);
		
		Thread t = new Thread(gameScreen);
		t.start();
	}
	public void setupParameters(){
		GameParamenterSingleton.ORIENTATION = GameParamenterSingleton.LANDSCAPE;
		GameParamenterSingleton.SCREEN_HEIGHT = getWindowManager().getDefaultDisplay().getHeight();
		GameParamenterSingleton.SCREEN_WIDTH = getWindowManager().getDefaultDisplay().getWidth();
		
		GameParamenterSingleton.assetManager = getAssets();
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//retrato
		//tela cheia
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//sem titulo
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}
	
	public void mostraViewFinish(){
        setContentView(R.layout.activity_game);
        TextView txtPontos = (TextView)findViewById(R.id.textView1pontos);
        txtPontos.setText(String.valueOf(GameParamenterSingleton.PONTOS));
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		int scores = settings.getInt("SCORE", 0);
		if(GameParamenterSingleton.PONTOS > scores){
			Toast.makeText(GameActivity.this,"MAIOR PONTUAÇÃO NOVA!",Toast.LENGTH_LONG).show();				 
				armazenar(GameParamenterSingleton.PONTOS);		 
		}else{
				armazenar(scores);
		}
       
      

	}
	public void HomeButton(View view){
		 button.start();
		 
		 finish();
	  	} 
	public void NovamenteButton(View view){
		 button.start();
		 controle = true;
		 setContentView(gameScreen);
		 setSons();
	     gameScreen.init();
	     
	  	} 
	private void armazenar(int score){
	       // Precisamos de um objeto Editor para fazer mudanças de preferência.
	       // Todos os objetos são de android.context.Context
	      SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);	
	      SharedPreferences.Editor editor = settings.edit();
	      if(controle){
	    	  controle = false;
	    	  
	    	  editor.putInt("SCORE",  score);
		      editor.putInt(BOB, GameParamenterSingleton.BOB + settings.getInt(BOB, 0));
		      editor.putInt(BOOKS,GameParamenterSingleton.BOOK + settings.getInt(BOOKS, 0));
		      editor.putInt(BUGGER,GameParamenterSingleton.BUGGER + settings.getInt(BUGGER, 0));
		      editor.putInt(BURTON, GameParamenterSingleton.BURTON + settings.getInt(BURTON, 0));
		      editor.putInt(FINN, GameParamenterSingleton.FINN + settings.getInt(FINN,  0));
		      editor.putInt(GERARD, GameParamenterSingleton.GERARD + settings.getInt(GERARD, 0));
		      editor.putInt(HEART, GameParamenterSingleton.CORACAO + settings.getInt(HEART, 0));
		      editor.putInt(PIZZA, GameParamenterSingleton.PIZZA + settings.getInt(PIZZA, 0));	
		      editor.putInt(INSETO, GameParamenterSingleton.INSETO + settings.getInt(INSETO,0));		     
	    	  	    	 
	      }else{
	    	   
	    	  editor.putInt("SCORE",  score);
		      editor.putInt(BOB, GameParamenterSingleton.BOB);
		      editor.putInt(BOOKS,GameParamenterSingleton.BOOK);
		      editor.putInt(BUGGER,GameParamenterSingleton.BUGGER);
		      editor.putInt(BURTON, GameParamenterSingleton.BURTON);
		      editor.putInt(FINN, GameParamenterSingleton.FINN);
		      editor.putInt(GERARD, GameParamenterSingleton.GERARD);
		      editor.putInt(HEART, GameParamenterSingleton.CORACAO);
		      editor.putInt(PIZZA, GameParamenterSingleton.PIZZA);	
		      editor.putInt(INSETO, GameParamenterSingleton.INSETO);  
	    	  
	      }
	      
	      editor.putBoolean(CNTRL, true);
	       
	     
	      // Commit as edições
	      editor.commit();
  
	    }	
	public void setSons(){
		jump = MediaPlayer.create(this,R.raw.jump);
		jump.setLooping(false); 
		bum = MediaPlayer.create(this, R.raw.bum);
		bum.setLooping(false);
		item = MediaPlayer.create(this, R.raw.item);
		item.setLooping(false);
		button = MediaPlayer.create(this,R.raw.button);
		button.setLooping(false);
		alien = MediaPlayer.create(this,R.raw.alien);
		alien.setLooping(false);
		barata = MediaPlayer.create(this, R.raw.barata);
		barata.setLooping(false);
		eyes = MediaPlayer.create(this, R.raw.eyes);
		eyes.setLooping(false);
		hand = MediaPlayer.create(this, R.raw.hand);
		hand.setLooping(false);
		lingua = MediaPlayer.create(this, R.raw.lingua);
		lingua.setLooping(false);
		meleca = MediaPlayer.create(this, R.raw.meleca);
		meleca.setLooping(false);
		sol = MediaPlayer.create(this, R.raw.sol);	
		sol.setLooping(false);
		caveira = MediaPlayer.create(this, R.raw.caveira);
		caveira.setLooping(false);	
		shark = MediaPlayer.create(this, R.raw.tubarao);
		shark.setLooping(false);
		sarlak = MediaPlayer.create(this, R.raw.sarlak);
		sarlak.setLooping(false);
	}
	
		    
		
}
