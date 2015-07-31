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

public class Itens extends GameCoisas {
    private static final String TAG="Itens";    
    private Bitmap figura;
    private Rect   src;
    private Rect   dst;
    private int    spriteColumn;
    private Canvas canvas;
    private int    spriteWidth;
    private int    spriteHeigth;
    private int flag = 0;
    private Paint paint;
    
    private double aleat;
    
    private int cntrl = 1;
    
    private static final int STEP=3;
    
    public Itens(){
    	
    	InputStream is=null;
        try{
            paint = new Paint();
            paint.setColor(Color.WHITE);
            aleat = Math.random();
            if(aleat < 0.125){
            	 is = GameParamenterSingleton.assetManager.open("itens/bob.png");
            	 cntrl = 3;
            	 flag = 1;
            }else if(aleat >= 0.125 && aleat < 0.25){
            	 is = GameParamenterSingleton.assetManager.open("itens/books.png");
            	 flag = 2;
            }else if(aleat >= 0.25 && aleat < 0.375){
            	 is = GameParamenterSingleton.assetManager.open("itens/bugger.png"); 
            	 flag = 3;
            }else if(aleat >= 0.375 && aleat < 0.5){
            	 is = GameParamenterSingleton.assetManager.open("itens/burton.png");   
            	 flag = 4;
            }else if(aleat >= 0.5 && aleat < 0.625){
            	is = GameParamenterSingleton.assetManager.open("itens/finn.png");
            	cntrl = 3;
            	flag = 5;
            }else if(aleat >= 0.625 && aleat < 0.75){
            	is = GameParamenterSingleton.assetManager.open("itens/gerard.png");
            	flag = 6;
            }else if(aleat >= 0.75 && aleat < 0.875){
            	is = GameParamenterSingleton.assetManager.open("itens/heart.png");
            	flag = 7;
            }else if(aleat >= 0.875){
            	is = GameParamenterSingleton.assetManager.open("itens/pizza.png");
            	flag = 8;
            }
            
            figura = BitmapFactory.decodeStream(is);
            int px = GameParamenterSingleton.SCREEN_WIDTH;   		
    		int py;
    		do{
    			py = (int)(GameParamenterSingleton.SCREEN_HEIGHT*Math.random());
    		}while(py + figura.getHeight() + 25 > GameParamenterSingleton.SCREEN_HEIGHT);
    		setX(px);
    		setY(py);   		
           // setX(x);
           // setY(y);
            spriteWidth = figura.getWidth()/cntrl;//(4)
            spriteHeigth = figura.getHeight();
            setWidth(spriteWidth);
            setHeight(spriteHeigth);
            src = new Rect(0,0,getWidth(), getHeight());
            dst = new Rect();
            getBoundingBox().setX(getX());
   	     	getBoundingBox().setY(getY());
   	     	getBoundingBox().setWidth(getWidth());
   	     	getBoundingBox().setHeight(getHeight());
                   
            }catch(Exception e){
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
        
        spriteColumn = (spriteColumn+1)%cntrl;//4;
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
    	this.canvas = canvas;
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



	public Canvas getCanvas() {
		return canvas;
	}
	
	public int getFlag(){
		return flag;
	}
	
}
