package com.pway.visual;

import java.io.InputStream;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.pway.util.GameCoisas;
import com.pway.util.GameParamenterSingleton;

public class Obstacle extends GameCoisas {
    private static final String TAG="Obstacle";    
    private Bitmap figura;
    private Rect   src;
    private Rect   dst;
    private int    spriteColumn;
    
    private int    spriteWidth;
    private int    spriteHeigth;
    
    private Paint paint;
    
    private double aleat;
    private int cntrl = 1;
    private int tipo = 0;
    
    //private static final int STEP=3;
    private int step = 3;
    
    public Obstacle(){
    	InputStream is;
        try{
            paint = new Paint();
            paint.setColor(Color.RED);
            aleat = Math.random();
            if(aleat < 0.5){
            	is = GameParamenterSingleton.assetManager.open("obstaculos/sarlak.png");
            	cntrl = 24;
            	tipo = 10;
            }else{
            	is = GameParamenterSingleton.assetManager.open("obstaculos/tubarao.png");
            	cntrl = 16;
            	step = 7;
            	tipo = 9;
            }
            
            
            figura = BitmapFactory.decodeStream(is);
            int px = GameParamenterSingleton.SCREEN_WIDTH;
    		int py = GameParamenterSingleton.SCREEN_HEIGHT - figura.getHeight() - 125;  		
    		setX(px);
    		setY(py);   		
           
    		
    		spriteWidth = figura.getWidth()/cntrl;
            //spriteWidth = figura.getWidth()/16;//(4)
            spriteHeigth = figura.getHeight();
            setWidth(spriteWidth);
            setHeight(spriteHeigth);
            src = new Rect(0,0,getWidth(), getHeight());
            dst = new Rect();
            getBoundingBox().setX(getX());
   	     	getBoundingBox().setY(getY());
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
        int passoDistorcido = (int)(step * GameParamenterSingleton.DISTORTION);
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
    public int getTipo(){
    	return tipo;
    }
}
