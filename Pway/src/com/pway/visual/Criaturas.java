package com.pway.visual;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.pway.util.GameCoisas;
import com.pway.util.GameParamenterSingleton;

public class Criaturas extends GameCoisas {
    private static final String TAG="Criaturas";    
    private Bitmap figura;
    private Rect   src;
    private Rect   dst;
    private int    spriteColumn;
    
    private int    spriteWidth;
    private int    spriteHeigth;
    
    private Paint paint;
    private int control = 1;
    private double aleat;
    private int velocidade = 1;
    private int tipo = 0;
    
    private boolean flag = false;
    private static final int STEP=5;
    
    public Criaturas(){
    	InputStream is;
        try{
            paint = new Paint();
            paint.setColor(Color.GREEN);
            aleat = Math.random();
            if(aleat < 0.125){
            	  is = GameParamenterSingleton.assetManager.open("monstros/aliens.png");
            	  control=16;
            	  tipo = 1;
            }else if(aleat >= 0.125 && aleat < 0.25){
            	is = GameParamenterSingleton.assetManager.open("monstros/baratas.png");
            	control = 8;
            	flag = true;
            	tipo = 2;
            }else if(aleat >= 0.25 && aleat < 0.375){
            	is = GameParamenterSingleton.assetManager.open("monstros/eyes.png");
            	control = 10;
            	tipo = 3;
            }else if(aleat >= 0.375 && aleat < 0.5){
            	is = GameParamenterSingleton.assetManager.open("monstros/hand.png");
            	control = 16;
            	tipo = 4;
            }else if(aleat >= 0.5 && aleat < 0.625){
            	is = GameParamenterSingleton.assetManager.open("monstros/lingua.png");
            	control = 12;
            	tipo = 5;
            }else if(aleat >= 0.625 && aleat < 0.75){
            	is = GameParamenterSingleton.assetManager.open("monstros/meleca.png");
            	control = 24;
            	tipo = 6;
            }else if(aleat >= 0.75 && aleat < 0.875){
            	is = GameParamenterSingleton.assetManager.open("monstros/solar.png");
            	control = 20;
            	tipo = 70;
            }else{
            	is = GameParamenterSingleton.assetManager.open("monstros/caveira.png");
            	control = 14;
            	tipo = 8;
            }
            	
            
           // is = GameParamenterSingleton.assetManager.open("aliens.png");
            figura = BitmapFactory.decodeStream(is);
            int px = GameParamenterSingleton.SCREEN_WIDTH;
    		int py;
    		do{
    			py = (int)(GameParamenterSingleton.SCREEN_HEIGHT*Math.random());
    		}while(py + figura.getHeight() > GameParamenterSingleton.SCREEN_HEIGHT - 125);
    		setX(px);
    		setY(py);   		
           // setX(x);
           // setY(y);
    		
    		spriteWidth = figura.getWidth()/control;
            //spriteWidth = figura.getWidth()/16;//(4)
            spriteHeigth = figura.getHeight();
            setWidth(spriteWidth);
            setHeight(spriteHeigth);
            src = new Rect(0,0,getWidth(), getHeight());
            dst = new Rect();
            getBoundingBox().setX((getX()+getWidth()));
   	     	getBoundingBox().setY(getY()+getHeight());
   	     	getBoundingBox().setWidth(getWidth());
   	     	getBoundingBox().setHeight(getHeight());
        }
        catch(Exception e){
            Log.d(TAG,"Error on loading Image");
        }
        
        updateDistocion();
    }
    
    

    @Override
    public void update() {
        // TODO Auto-generated method stub
        int passoDistorcido = (int)(this.STEP * GameParamenterSingleton.DISTORTION);
        setX(getX()-passoDistorcido);
        getBoundingBox().setX(getX() - passoDistorcido);
        
        src.left = spriteColumn*spriteWidth;
        src.right = src.left + spriteWidth;
        src.top = 0;
        src.bottom = spriteHeigth;
        
        dst.left = getX();
        dst.right = dst.left + getWidth();
        dst.top = getY();
        dst.bottom = getY() + getHeight();       
        spriteColumn = (spriteColumn+1)%control;//4;
       
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        canvas.drawBitmap(figura,  src,  dst, null);
       /* canvas.drawRect(getBoundingBox().getX(), 
                        getBoundingBox().getY(),  
                        getBoundingBox().getX()+getBoundingBox().getWidth(), 
                        getBoundingBox().getY()+ getBoundingBox().getHeight(),
                        paint);*/
        
    }



	public int getSpriteWidth() {
		return spriteWidth;
	}



	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}



	public int getSpriteHeigth() {
		return spriteHeigth;
	}



	public void setSpriteHeigth(int spriteHeigth) {
		this.spriteHeigth = spriteHeigth;
	}
	
	public boolean getInseto(){
		return flag;
	}
	public int getTipo(){
		return tipo;
	}
		
}
