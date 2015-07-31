package com.pway.visual;


import java.util.ArrayList;

import com.gameton.pway.R;
import com.pway.util.GameParamenterSingleton;
import com.pway.util.Sounds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class RankActivity extends Activity{	
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
	
	
	private TextView premio1;	
	private TextView premio3;
	private TextView premio5;
	private TextView premio6;
	private TextView premio7;
	private TextView premio8;
	
	private ImageView trofeu0;
	private ImageView trofeu1;
	private ImageView trofeu2;
	private ImageView trofeu3;
	private ImageView trofeu4;
	private ImageView trofeu5;
	private ImageView trofeu6;
	private ImageView trofeu7;
	
	private TextView premio1Text;
	private TextView premio2Text;
	private TextView premio3Text;
	private TextView premio4Text;
	private TextView premio5Text;
	private TextView premio6Text;
	private TextView premio7Text;
	private TextView premio8Text;
	
	private ImageButton finalButton;
	private MediaPlayer button;
	
	
	
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_rank);	
		
				
		button = MediaPlayer.create(this,R.raw.button);
		button.setLooping(false);
		
		finalButton = (ImageButton)findViewById(R.id.imageButtonFinal);		
		finalButton.setClickable(false);
		
		setTextsView();		
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);		
		int missao =  settings.getInt(MISSAO, 1);			
		
		highScore();
		
		selectMissao(missao);		
	//	setImagens(missao);	
					
	}
	public void BackButton(View view){
		button.start();
		onBackPressed();
	}
	public void FinalButton(View view){		
		button.start();	
		Intent trocaTela = new Intent(this,FinalVideo.class);
		startActivity(trocaTela);
	}
	
	public void highScore(){
		//maior pontuação
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		int scores = settings.getInt("SCORE", 0);
		TextView bestScore = (TextView)findViewById(R.id.melhorscore);		
		bestScore.setText(Integer.toString(scores));
	}
	
	public void missao1(){
		//"Obtenha 50 itens"
		
		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		 boolean estado = settings.getBoolean(CNTRL,false);
		 int aux = settings.getInt(AUX, 0);
		 int m =  settings.getInt(MISSAO, 1);
		 setImagens(m);
		 premio1.setText(Integer.toString(aux));		 	
		 if(estado){
			 aux =    settings.getInt(AUX, 0)
		    		  +settings.getInt(BOB, 0)
		    		  +settings.getInt(BOOKS, 0)
		    		  +settings.getInt(BUGGER, 0)
		    		  +settings.getInt(BURTON, 0)
		    		  +settings.getInt(FINN,  0)
		    		  +settings.getInt(GERARD, 0)
		    		  +settings.getInt(HEART, 0)
		    		  +settings.getInt(PIZZA, 0);  
			 premio1.setText(Integer.toString(aux));
			
			 estado = false;
			 if(aux>=50){
				 premio1.setText(Integer.toString(50));		
				  m++;
				  aux = 0;	
				  setImagens(m);	
				  Zerar();
			  }
		     SharedPreferences.Editor editor = settings.edit();	     
		     editor.putInt(AUX,aux); 
		     editor.putBoolean(CNTRL, estado);
		     editor.putInt(MISSAO, m);
		     
		     editor.commit();
		     		    
		 } 
		// Toast.makeText(RankActivity.this,"Quantidade de itens: "+aux,Toast.LENGTH_LONG).show();
		 
	     
	}
	public void missao2(){
		//pegue o Bob e Finn na mesma partida
		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		 int m =  settings.getInt(MISSAO, 2);
		 setImagens(m);
		 //int aux = settings.getInt(BOB, 0) + settings.getInt(FINN, 0);
		 if(settings.getInt(BOB, 0) >=1 && settings.getInt(FINN, 0)>=1){
			// Toast.makeText(RankActivity.this,"CONSEGUIU MISSAO 2!",Toast.LENGTH_LONG).show();	
			 m++;
			 setImagens(m);	
			 Zerar();
		 }
		 SharedPreferences.Editor editor = settings.edit();	
		 editor.putInt(MISSAO, m);
		 
		 editor.commit();
	}
	public void missao3(){
		//pegue 8 pedações de pizzas
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		 boolean estado = settings.getBoolean(CNTRL,false);
		 int aux = settings.getInt(AUX, 0);
		 int m =  settings.getInt(MISSAO, 3); 
		 setImagens(m);
		 premio3.setText(Integer.toString(aux));
		 if(estado){
			 estado=false;
			 if(aux>=8){
				 m++;
				 setImagens(m);					
				 premio3.setText(Integer.toString(8));
				 aux=0;
				 Zerar();
			 }else{
				 aux = aux + settings.getInt(PIZZA, 0);
				 premio3.setText(Integer.toString(aux));
			 }
			 SharedPreferences.Editor editor = settings.edit();	     
		     editor.putInt(AUX,aux); 
		     editor.putBoolean(CNTRL, estado);
		     editor.putInt(MISSAO, m);
		     
		     editor.commit();
		 }
		
	}
	public void missao4(){
		//evite três baratas
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		int aux = settings.getInt(INSETO, 0);
		int m =  settings.getInt(MISSAO, 4);
		setImagens(m);
		if(aux>=3){
		//	Toast.makeText(RankActivity.this,"CONSEGUIU MISSAO 4!",Toast.LENGTH_LONG).show();	
			 m++;
			 setImagens(m);	
			 Zerar();
		}
		
		 SharedPreferences.Editor editor = settings.edit();	
		 editor.putInt(MISSAO, m);		 
		 editor.commit();
	}
	public void missao5(){
		//pegue 20 alimentos

		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		 boolean estado = settings.getBoolean(CNTRL,false);
		 int aux = settings.getInt(AUX, 0);
		 int m =  settings.getInt(MISSAO, 5);
		 setImagens(m);
		 premio5.setText(Integer.toString(aux));		 	
		 if(estado){
			 aux =    settings.getInt(AUX, 0)		    		  
		    		  +settings.getInt(BUGGER, 0)		    		  
		    		  +settings.getInt(PIZZA, 0);  
			 premio5.setText(Integer.toString(aux));
			
			 estado = false;
			 if(aux>=20){
				 premio5.setText(Integer.toString(20));		
				  m++;
				  aux = 0;	
				  setImagens(m);
				  Zerar();
			  }
		     SharedPreferences.Editor editor = settings.edit();	     
		     editor.putInt(AUX,aux); 
		     editor.putBoolean(CNTRL, estado);
		     editor.putInt(MISSAO, m);
		     
		     editor.commit();
		     		    
		 } 
		// Toast.makeText(RankActivity.this,"Quantidade de itens: "+aux,Toast.LENGTH_LONG).show();
		 
	}
	public void missao6(){
		//pegue 9 Gerard's
		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		 boolean estado = settings.getBoolean(CNTRL,false);
		 int aux = settings.getInt(AUX, 0);
		 int m =  settings.getInt(MISSAO, 6);
		 setImagens(m);
		 premio6.setText(Integer.toString(aux));		 	
		 if(estado){
			 aux =    settings.getInt(AUX, 0)		    		  
		    		  +settings.getInt(GERARD, 0);	    		    
			 premio6.setText(Integer.toString(aux));
			
			 estado = false;
			 if(aux>=9){
				 premio6.setText(Integer.toString(9));		
				  m++;
				  aux = 0;	
				  setImagens(m);	
				  Zerar();
			  }
		     SharedPreferences.Editor editor = settings.edit();	     
		     editor.putInt(AUX,aux); 
		     editor.putBoolean(CNTRL, estado);
		     editor.putInt(MISSAO, m);
		     
		     editor.commit();
		     		    
		 } 		 
	}
	public void missao7(){
		//pegue pelo menos 1 Tim Burton e 5 livros
		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		 boolean estado = settings.getBoolean(CNTRL,false);
		 int aux = settings.getInt(AUX, 0);
		 int m =  settings.getInt(MISSAO, 7);
		 setImagens(m);
		 premio7.setText(Integer.toString(aux));		 	
		 if(estado){			
				 aux =    settings.getInt(AUX, 0)		    		 
			    		  +settings.getInt(BOOKS, 0)		    		  
			    		  +settings.getInt(BURTON, 0);  
			
			 premio7.setText(Integer.toString(aux));
			
			 estado = false;
			 if(aux>=6){
				 premio7.setText(Integer.toString(6));		
				  m++;
				  aux = 0;	
				  setImagens(m);
				  Zerar();
			  }
		     SharedPreferences.Editor editor = settings.edit();	     
		     editor.putInt(AUX,aux); 
		     editor.putBoolean(CNTRL, estado);
		     editor.putInt(MISSAO, m);
		     
		     editor.commit();
		     		    
		 } 
		// Toast.makeText(RankActivity.this,"Quantidade de itens: "+aux,Toast.LENGTH_LONG).show();
	}
	public void missao8(){
		//pegue 18 corações

		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		 boolean estado = settings.getBoolean(CNTRL,false);
		 int aux = settings.getInt(AUX, 0);
		 int m =  settings.getInt(MISSAO, 8);
		 setImagens(m);
		 premio8.setText(Integer.toString(aux));		 	
		 if(estado){
			 aux =    settings.getInt(AUX, 0)		    		  
		    		  +settings.getInt(HEART, 0);  
			 premio8.setText(Integer.toString(aux));
			
			 estado = false;
			 if(aux>=18){
				 premio8.setText(Integer.toString(18));		
				  m++;
				  aux = 0;	
				  setImagens(m);	
				  Zerar();
				  Toast.makeText(RankActivity.this,"PARABÉNS!VOCÊ GANHOU TODOS OS TROFÉUS!CLIQUE NO CORAÇÃO!",Toast.LENGTH_LONG).show();				 
					finalButton.setClickable(true);
					finalButton.setImageResource(R.drawable.playfinal); 
			  }
		     SharedPreferences.Editor editor = settings.edit();	     
		     editor.putInt(AUX,aux); 
		     editor.putBoolean(CNTRL, estado);
		     editor.putInt(MISSAO, m);
		     
		     editor.commit();
		     		    
		 } 
		// Toast.makeText(RankActivity.this,"Quantidade de itens: "+aux,Toast.LENGTH_LONG).show();
		 
	}
	
	public void setTextsView(){
		
		premio1 = (TextView)findViewById(R.id.premio1);	
		premio3 = (TextView)findViewById(R.id.premio3);
		premio5 = (TextView)findViewById(R.id.premio5);
		premio6 = (TextView)findViewById(R.id.premio6);
		premio7 = (TextView)findViewById(R.id.premio7);
		premio8 = (TextView)findViewById(R.id.premio8);
		
		premio1Text = (TextView)findViewById(R.id.premio1Text);	
		premio2Text = (TextView)findViewById(R.id.premio2Text);	
		premio3Text = (TextView)findViewById(R.id.premio3Text);	
		premio4Text = (TextView)findViewById(R.id.premio4Text);	
		premio5Text = (TextView)findViewById(R.id.premio5Text);	
		premio6Text = (TextView)findViewById(R.id.premio6Text);	
		premio7Text = (TextView)findViewById(R.id.premio7Text);	
		premio8Text = (TextView)findViewById(R.id.premio8Text);	
	}
	public void selectMissao(int select){
		//Toast.makeText(RankActivity.this,"Missão Agora: "+select,Toast.LENGTH_LONG).show();		
		switch (select) {		
		case 1:
			missao1();
			break;

		case 2:			
			missao2();
			break;
			
		case 3:
			missao3();				
			break;
			
		case 4:
			missao4();
			break;
			
		case 5:
			missao5();
			break;
			
		case 6:
			missao6();
			break;
			
		case 7:
			missao7();
			break;
			
		case 8:
			missao8();
			break;
		case 9:
			setImagens(select);
			finalButton.setClickable(true);
			finalButton.setImageResource(R.drawable.playfinal);
			break;
		}
	}
	public void setImagens(int m){
		trofeu0 = (ImageView)findViewById(R.id.trofeu0);
		trofeu1 = (ImageView)findViewById(R.id.trofeu1);
		trofeu2 = (ImageView)findViewById(R.id.trofeu2);
		trofeu3 = (ImageView)findViewById(R.id.trofeu3);
		trofeu4 = (ImageView)findViewById(R.id.trofeu4);
		trofeu5 = (ImageView)findViewById(R.id.trofeu5);
		trofeu6 = (ImageView)findViewById(R.id.trofeu6);
		trofeu7 = (ImageView)findViewById(R.id.trofeu7);
		
		ArrayList<ImageView> arrayAux = new ArrayList<ImageView>();
		arrayAux.add(trofeu0);
		arrayAux.add(trofeu1);
		arrayAux.add(trofeu2);
		arrayAux.add(trofeu3);
		arrayAux.add(trofeu4);
		arrayAux.add(trofeu5);
		arrayAux.add(trofeu6);
		arrayAux.add(trofeu7);
		
		ArrayList<TextView> arrayAuxT = new ArrayList<TextView>();
		arrayAuxT.add(premio1Text);
		arrayAuxT.add(premio2Text);
		arrayAuxT.add(premio3Text);
		arrayAuxT.add(premio4Text);
		arrayAuxT.add(premio5Text);
		arrayAuxT.add(premio6Text);
		arrayAuxT.add(premio7Text);
		arrayAuxT.add(premio8Text);
		
		if(m<9){
			for(int j=0;j<m;j++){
				arrayAuxT.get(j).setBackgroundColor(getApplicationContext().getResources().getColor(R.color.cinza));
			}
		}
		
		
		if(m+1>2){
			for(int i=0;i<m-1;i++){
				arrayAux.get(i).setImageResource(R.drawable.trofeu);
				arrayAuxT.get(i).setBackgroundColor(getApplicationContext().getResources().getColor(R.color.verde));				
			}
		}
		
	}
	private void Zerar(){
	       // Precisamos de um objeto Editor para fazer mudanças de preferência.
	       // Todos os objetos são de android.context.Context
	      SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);	
	      SharedPreferences.Editor editor = settings.edit();

	    	   	    	  
		      editor.putInt(BOB, 0);
		      editor.putInt(BOOKS,0);
		      editor.putInt(BUGGER,0);
		      editor.putInt(BURTON,0);
		      editor.putInt(FINN, 0);
		      editor.putInt(GERARD,0);
		      editor.putInt(HEART, 0);
		      editor.putInt(PIZZA, 0);	
		      editor.putInt(INSETO, 0);  
	    	  	      	      	     
	       
	     
	      // Commit as edições
	      editor.commit();

	    }	
	
	
	
}
