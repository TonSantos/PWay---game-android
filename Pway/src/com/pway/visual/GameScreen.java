package com.pway.visual;


import java.util.ArrayList;

import com.gameton.pway.R;
import com.pway.util.Combo;
import com.pway.util.GameParamenterSingleton;
import com.pway.util.Sounds;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameScreen extends View implements Runnable{

	private boolean update;
	private int i;
	private Paint paint;
	private static final String TAG= "GameScreen";
	
	private InfiniteBackground bg;
	private GirlBubble girl,fgirl;
	private Obstacle obst;
	private Combo combo;
	private Criaturas criatura;
	private ArrayList<Criaturas> arrayCriaturas;
	private Itens item;
	private Efeitos efeito;
	private boolean colidiuGap;
	private int cntrl = 0;
	private Score unidadeScore,dezenaScore;
	private ArrayList<Integer> qtd_itens;
			
	private Handler handler;
	
	public GameScreen(Context context) {
		super(context);
				
		init();		
		
	}

	public void update(){		
		if(update){
			bg.update();
			girl.update();
			fgirl.update();
			obst.update();
			//combo.update();
			criatura.update();			
			item.update();		
			efeito.update();
			unidadeScore.update();
			dezenaScore.update();
			for(int i=0;i<arrayCriaturas.size();i++){
				arrayCriaturas.get(i).update();
			}
			if(cntrl > 1){
				if(cntrl > 20){
					cntrl = 0;						
					notifyFinish();
					
				}else{
					cntrl++;
				}
	        	
	        	      	
	        }
			else if (girl.getBoundingBox().getY() <= 0) {	
				notifyBum();
				int x = girl.getX();
	        	int y = girl.getY();
	        	girl.setX(GameParamenterSingleton.SCREEN_WIDTH);
	        	girl.setY(GameParamenterSingleton.SCREEN_HEIGHT);
	        	fgirl.setX(x);
	        	fgirl.setY(y);
	        	cntrl++;
	        }
	        else if (girl.getBoundingBox().getY() + girl.getBoundingBox().getHeight() >= GameParamenterSingleton.SCREEN_HEIGHT) {	
	        	notifyBum();
	        	int x = girl.getX();
	        	int y = girl.getY();
	        	girl.setX(GameParamenterSingleton.SCREEN_WIDTH);
	        	girl.setY(GameParamenterSingleton.SCREEN_HEIGHT);
	        	fgirl.setX(x);
	        	fgirl.setY(y);
	        	cntrl++;
	        }
	        
	        else if (GameParamenterSingleton.detectColision(girl, obst)){
	           // notifyFinish();	
	        	notifyBum();
	        	int x = girl.getX();
	        	int y = girl.getY();
	        	girl.setX(GameParamenterSingleton.SCREEN_WIDTH);
	        	girl.setY(GameParamenterSingleton.SCREEN_HEIGHT);
	        	fgirl.setX(x);
	        	fgirl.setY(y);
	        	cntrl++;
	        }
	        
	      /*  else if (GameParamenterSingleton.detectColision(girl, combo.getDown())){        	
	            notifyFinish();
	        } */
	        
	        else if(GameParamenterSingleton.detectColision(girl, criatura)){
	        	notifyBum();
	        	int x = girl.getX();
	        	int y = girl.getY();
	        	girl.setX(GameParamenterSingleton.SCREEN_WIDTH);
	        	girl.setY(GameParamenterSingleton.SCREEN_HEIGHT);
	        	fgirl.setX(x);
	        	fgirl.setY(y);
	        	cntrl++;
	        	
	        	
	        }
			
	        else if(GameParamenterSingleton.detectColision(girl, item)){	
	        	notifyItem();
	        	efeito.setX(girl.getX() + girl.getWidth());
	        	efeito.setY(girl.getY());	        	        	
	        	GameParamenterSingleton.PONTOS++;
	        	detectItem(item.getFlag());
                Log.d(TAG,"Pontos = "+GameParamenterSingleton.PONTOS);
                item = new Itens(); 
                item.getBoundingBox().setWidth(item.getWidth());
        		item.getBoundingBox().setHeight(item.getHeight());
        		item.getBoundingBox().setX(item.getX() + ((item.getWidth() - item.getBoundingBox().getWidth()))/2);
        		item.getBoundingBox().setY(item.getY() + ((item.getHeight() - item.getBoundingBox().getHeight()))/2);
                if(GameParamenterSingleton.PONTOS%5 == 0 && GameParamenterSingleton.PONTOS>=15){
                	arrayCriaturas.add(new Criaturas());
                	for(int i=0;i<arrayCriaturas.size();i++ ){
                    	if(arrayCriaturas.get(i).getX() == criatura.getX() && arrayCriaturas.get(i).getY() == criatura.getY()){
                    		arrayCriaturas.set(i, new Criaturas());
                    		
                    	}
                    	for(int j=0;j<i;j++){
                    		if(arrayCriaturas.get(i).getX() == arrayCriaturas.get(j).getX() && arrayCriaturas.get(i).getY() == arrayCriaturas.get(j).getY()){
                        		arrayCriaturas.set(j, new Criaturas());                      		
                        	}
                    	}
                    	detectCriatura(arrayCriaturas.get(i).getTipo());
                    }
                }
                if(GameParamenterSingleton.PONTOS<10){
                	unidadeScore = new Score(GameParamenterSingleton.PONTOS);
                	unidadeScore.setX(dezenaScore.getWidth());
                }else{
                	int u,d;
                	d = (int)(GameParamenterSingleton.PONTOS/10);
                	u = GameParamenterSingleton.PONTOS - d*10;
                	unidadeScore = new Score(u);
                	dezenaScore = new Score(d);
                	unidadeScore.setX(dezenaScore.getWidth());
                	
                }
	        	
	        } 
			
	        else if(GameParamenterSingleton.detectColision(obst,item)){
	        	item = new Itens(); 
	        	item.getBoundingBox().setWidth(item.getWidth());
	    		item.getBoundingBox().setHeight(item.getHeight());
	    		item.getBoundingBox().setX(item.getX() + ((item.getWidth() - item.getBoundingBox().getWidth()))/2);
	    		item.getBoundingBox().setY(item.getY() + ((item.getHeight() - item.getBoundingBox().getHeight()))/2);
	        }
			
			
			for(int i=0;i<arrayCriaturas.size();i++){
				if(GameParamenterSingleton.detectColision(girl, arrayCriaturas.get(i))){
					int x = girl.getX();
		        	int y = girl.getY();
		        	girl.setX(GameParamenterSingleton.SCREEN_WIDTH);
		        	girl.setY(GameParamenterSingleton.SCREEN_HEIGHT);
		        	fgirl.setX(x);
		        	fgirl.setY(y);
		        	cntrl++;
				}
			}
					
				
			
						
	        
	       /* if (!colidiuGap){
	            if (GameParamenterSingleton.detectColision(girl, combo.getGap())){
	                colidiuGap = true;
	            }
	        }
	        else{
	            if (!GameParamenterSingleton.detectColision(girl, combo.getGap())){
	                colidiuGap = false;
	                GameParamenterSingleton.PONTOS++;
	                Log.d(TAG,"Pontos = "+GameParamenterSingleton.PONTOS);
	            }
	        }*/
			if(obst.getX() + obst.getWidth() <= 0){
				if(GameParamenterSingleton.PONTOS >= 10){
					obst = new Obstacle();	
		        	obst.getBoundingBox().setWidth((int)(obst.getWidth()*0.8));
		    		obst.getBoundingBox().setHeight((int)(obst.getHeight()*0.7));
		    		obst.getBoundingBox().setX(obst.getX() + ((obst.getWidth() - obst.getBoundingBox().getWidth()))/2);
		    		obst.getBoundingBox().setY(obst.getY() + ((obst.getHeight() - obst.getBoundingBox().getHeight())));
		    		
		    		detectCriatura(obst.getTipo());
				}
	        	
	        	
	        }
	        if(criatura.getX() + criatura.getWidth() <= 0 ){
	        	if(criatura.getInseto()){
	        		GameParamenterSingleton.INSETO++;
	        	}
	        		        	
	        	criatura = new Criaturas();	
	        	detectCriatura(criatura.getTipo());
	        	criatura.getBoundingBox().setWidth(criatura.getWidth());
	    		criatura.getBoundingBox().setHeight(criatura.getHeight()/2);
	    		criatura.getBoundingBox().setX(criatura.getX() + ((criatura.getWidth() - criatura.getBoundingBox().getWidth()))/2);
	    		criatura.getBoundingBox().setY(criatura.getY() + ((criatura.getHeight() - criatura.getBoundingBox().getHeight()))/2);
	        	
	        	
	        }
	        if(item.getX() + item.getWidth() <= 0 ){	
	        	item = new Itens();
	        	item.getBoundingBox().setWidth(item.getWidth());
	    		item.getBoundingBox().setHeight(item.getHeight());
	    		item.getBoundingBox().setX(item.getX() + ((item.getWidth() - item.getBoundingBox().getWidth()))/2);
	    		item.getBoundingBox().setY(item.getY() + ((item.getHeight() - item.getBoundingBox().getHeight()))/2);
	        }
	        
	        for(int i=0;i<arrayCriaturas.size();i++){
				if(arrayCriaturas.get(i).getX() + arrayCriaturas.get(i).getWidth() <= 0 ){
					if(arrayCriaturas.get(i).getInseto()){
						GameParamenterSingleton.INSETO++;
					}
					arrayCriaturas.set(i, new Criaturas());
					detectCriatura(arrayCriaturas.get(i).getTipo());
				}
			}
	        
		}
		
		
	}
	@Override
	protected void onDraw(Canvas canvas){	
		super.onDraw(canvas);
		bg.draw(canvas);	
		girl.draw(canvas);
		fgirl.draw(canvas);
		obst.draw(canvas);
		//combo.draw(canvas);
		criatura.draw(canvas);	
		for(int i=0;i<arrayCriaturas.size();i++){
			arrayCriaturas.get(i).draw(canvas);
		}
		item.draw(canvas);	
		efeito.draw(canvas);
		unidadeScore.draw(canvas);
		dezenaScore.draw(canvas);
		//(GameParamenterSingleton.SCREEN_WIDTH-100,5)	
		//canvas.drawText("ITENS: "+GameParamenterSingleton.PONTOS, 50, 50, paint);
	}
	public void init(){
		qtd_itens = new ArrayList<Integer>();
		
		i=0;
		update = true;
		colidiuGap = false;
		
		GameParamenterSingleton.PONTOS = 0;
		GameParamenterSingleton.BOB = 0;
		GameParamenterSingleton.BOOK = 0;
		GameParamenterSingleton.BUGGER = 0;
		GameParamenterSingleton.BURTON = 0;
		GameParamenterSingleton.CORACAO = 0;
		GameParamenterSingleton.FINN = 0;
		GameParamenterSingleton.GERARD = 0;
		GameParamenterSingleton.PIZZA = 0;
		GameParamenterSingleton.INSETO = 0;
		
		paint = new Paint();
		paint.setColor(Color.WHITE);		
		paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
		
		//cria objetos de jogo
		bg = new InfiniteBackground();
		//definir fator de distorção
		GameParamenterSingleton.DISTORTION = (float)GameParamenterSingleton.SCREEN_HEIGHT / bg.getHeigth();
		
		bg.updateDistortion();
		
		girl = new GirlBubble(false);
		girl.setX(50);
		girl.setY(50);
		girl.updateDistocion();
		//ajustando bounding da menina
		girl.getBoundingBox().setWidth(girl.getWidth()/2);
        girl.getBoundingBox().setHeight(girl.getHeight()/2);
        girl.getBoundingBox().setX(girl.getX() + ((girl.getWidth() - girl.getBoundingBox().getWidth()))/2);
        girl.getBoundingBox().setY(girl.getY() + ((girl.getHeight() - girl.getBoundingBox().getHeight()))/2);
		
        
        //ajustando menina explodindo
        fgirl = new GirlBubble(true);
        fgirl.setX(GameParamenterSingleton.SCREEN_WIDTH);
		fgirl.setY(GameParamenterSingleton.SCREEN_HEIGHT);
		fgirl.updateDistocion();
		
	//	obst = new Obstacle();
	//	obst.setX(1000);
	//	obst.setY(0);
	//	obst.updateDistocio();
		
		//combo = new Combo();
		criatura = new Criaturas();	
		
		
		//ajustando bounding da criatura
		criatura.getBoundingBox().setWidth(criatura.getWidth());
		criatura.getBoundingBox().setHeight(criatura.getHeight()/2);
		criatura.getBoundingBox().setX(criatura.getX() + ((criatura.getWidth() - criatura.getBoundingBox().getWidth()))/2);
		criatura.getBoundingBox().setY(criatura.getY() + ((criatura.getHeight() - criatura.getBoundingBox().getHeight()))/2);
		
		arrayCriaturas = new ArrayList<Criaturas>();	
		
		obst = new Obstacle();		
		obst.setY(GameParamenterSingleton.SCREEN_HEIGHT);
		//ajustando bounding do obstaculo
		obst.getBoundingBox().setWidth((int)(obst.getWidth()*0.8));
		obst.getBoundingBox().setHeight((int)(obst.getHeight()*0.7));
		obst.getBoundingBox().setX(obst.getX() + ((obst.getWidth() - obst.getBoundingBox().getWidth()))/2);
		obst.getBoundingBox().setY(obst.getY() + ((obst.getHeight() - obst.getBoundingBox().getHeight())));
		
		item = new Itens();	
		//ajustando bounding do item
		item.getBoundingBox().setWidth(item.getWidth());
		item.getBoundingBox().setHeight(item.getHeight());
		item.getBoundingBox().setX(item.getX() + ((item.getWidth() - item.getBoundingBox().getWidth()))/2);
		item.getBoundingBox().setY(item.getY() + ((item.getHeight() - item.getBoundingBox().getHeight()))/2);
		
		unidadeScore = new Score(0);
		dezenaScore = new Score(0);
		unidadeScore.setX(dezenaScore.getWidth());
				
		qtd_itens.add(item.getFlag());
		efeito = new Efeitos();	
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent evt){
		if(evt.getAction() == MotionEvent.ACTION_DOWN){
			girl.setDirecao(GirlBubble.SOBE);
			notifyJump();
			return true;
		}else if(evt.getAction() == MotionEvent.ACTION_UP){
			girl.setDirecao(GirlBubble.DESCE);			
			return true;
		}
		
		return false;
	}

	
	public void run() {
	detectCriatura(criatura.getTipo());
		while(true){
			
			try{
				update();
				postInvalidate();
					
						Thread.sleep(50);
				
			}catch(Exception e){
				Log.d(TAG,"Erro no loop do game!");
			}
		}
		
	}
	public void notifyFinish(){
        update = false;        
        Message msg = new Message();
        msg.what = 100;  // tipo, acabou aquele jogo
        handler.sendMessage(msg);
        
        
    }	
	public void notifyJump(){
		 Message msg = new Message();
	     msg.what = 50;  // tipo, jump!
	     handler.sendMessage(msg);
	}
	public void notifyBum(){
		 Message msg = new Message();
	     msg.what = 25;  // tipo, bum!
	     handler.sendMessage(msg);
	}
	public void notifyItem(){
		Message msg = new Message();
	     msg.what = 20;  // tipo, item!
	     handler.sendMessage(msg);
	}	
	public void notifyAlien(){
		Message msg = new Message();
	     msg.what = 1;  // tipo,alie!
	     handler.sendMessage(msg);
	}
	public void notifyBarata(){
		Message msg = new Message();
	     msg.what = 2;  // tipo,barata!
	     handler.sendMessage(msg);
	}
	public void notifyEyes(){
		Message msg = new Message();
	     msg.what = 3;  // tipo,Eyes!
	     handler.sendMessage(msg);
	}
	public void notifyHand(){
		Message msg = new Message();
	     msg.what = 4;  // tipo,Hand!
	     handler.sendMessage(msg);
	}
	public void notifyLingua(){
		Message msg = new Message();
	     msg.what = 5;  // tipo,Hand!
	     handler.sendMessage(msg);
	}
	public void notifyMeleca(){
		Message msg = new Message();
	     msg.what = 6;  // tipo,Hand!
	     handler.sendMessage(msg);
	}
	public void notifySolar(){
		Message msg = new Message();
	     msg.what = 7;  // tipo,Hand!
	     handler.sendMessage(msg);
	}
	public void notifyCaveira(){
		Message msg = new Message();
	     msg.what = 8;  // tipo,Hand!
	     handler.sendMessage(msg);
	}
	public void notifyShark(){
		Message msg = new Message();
	     msg.what = 9;  // tipo,Hand!
	     handler.sendMessage(msg);
	}
	public void notifySarlak(){
		Message msg = new Message();
	     msg.what = 10;  // tipo,Hand!
	     handler.sendMessage(msg);
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	 public void detectItem(int qual){
		 switch (qual) {
		case 1:
			GameParamenterSingleton.BOB++;
			break;
		case 2:
			GameParamenterSingleton.BOOK++;
			break;
		case 3:
			GameParamenterSingleton.BUGGER++;
			break;
		case 4:
			GameParamenterSingleton.BURTON++;
			break;
		case 5:
			GameParamenterSingleton.FINN++;
			break;
		case 6:
			GameParamenterSingleton.GERARD++;
			break;
		case 7:
			GameParamenterSingleton.CORACAO++;
			break;
		case 8:
			GameParamenterSingleton.PIZZA++;
			break;
		}
	 }
	 public void detectCriatura(int tipo){
		 switch (tipo) {		
			case 1:
				notifyAlien();
				break;

			case 2:			
				notifyBarata();
				break;
				
			case 3:
				notifyEyes();			
				break;
				
			case 4:
				notifyHand();			
				break;
				
			case 5:
				notifyLingua();			
				break;
				
			case 6:
				notifyMeleca();			
				break;
				
			case 7:
				notifySolar();		
				break;
				
			case 8:
				notifyCaveira();		
				break;
				
			case 9:
				notifyShark();		
				break;
				
			case 10:
				notifySarlak();		
				break;
			}
	 }
}
