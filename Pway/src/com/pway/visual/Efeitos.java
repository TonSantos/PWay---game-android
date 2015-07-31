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

public class Efeitos extends GameCoisas {
    private static final String TAG="Efeitos";    
    private Bitmap figura;
    private Rect   src;
    private Rect   dst;
    private int    spriteColumn;
    
    private int    spriteWidth;
    private int    spriteHeigth;
    
    private Paint paint;
    
    private double aleat;
    private int cntrl = 10;
    
    private static final int STEP=5;
    
    public Efeitos(){
    	InputStream is;
        try{
            paint = new Paint();
            paint.setColor(Color.BLACK);
            aleat = Math.random();
            
            is = GameParamenterSingleton.assetManager.open("efeito.png");                      	
        
            
            figura = BitmapFactory.decodeStream(is);
            
            spriteWidth = figura.getWidth()/cntrl;//(4)
            spriteHeigth = figura.getHeight();
            setWidth(spriteWidth);
            setHeight(spriteHeigth);
            src = new Rect(0,0,getWidth(), getHeight());
            dst = new Rect();
            
            setX(GameParamenterSingleton.SCREEN_WIDTH);
            setY(GameParamenterSingleton.SCREEN_HEIGHT);
        }
        catch(Exception e){
            Log.d(TAG,"Error on loading Image");
        }
        
        	getBoundingBox().setX(getX());
	     	getBoundingBox().setY(getY());
	     	getBoundingBox().setWidth(getWidth());
	     	getBoundingBox().setHeight(getHeight());
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
        canvas.drawBitmap(figura,  src,  dst, null);
        /*canvas.drawRect(getBoundingBox().getX(), 
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
}
