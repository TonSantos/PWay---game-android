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

public class Score extends GameCoisas {
    private static final String TAG="Score";    
    private Bitmap figura;
    private Rect   src;
    private Rect   dst;
    private int    spriteColumn;
    private Canvas canvas;
    private int    spriteWidth;
    private int    spriteHeigth;
    private int flag = 0;
    private Paint paint;
    
    
    private int cntrl = 1;
    
    private static final int STEP=3;
    
    public Score(int number){
    	
    	InputStream is=null;
        try{
            paint = new Paint();
            paint.setColor(Color.WHITE);
            
            switch (number) {
			case 0:
				is = GameParamenterSingleton.assetManager.open("numeros/zero.png");
				break;
			case 1:
				is = GameParamenterSingleton.assetManager.open("numeros/um.png");
				break;
			case 2:
				is = GameParamenterSingleton.assetManager.open("numeros/dois.png");
				break;
			case 3:
				is = GameParamenterSingleton.assetManager.open("numeros/tres.png");
				break;
			case 4:
				is = GameParamenterSingleton.assetManager.open("numeros/quatro.png");
				break;
			case 5:
				is = GameParamenterSingleton.assetManager.open("numeros/cinco.png");
				break;
			case 6:
				is = GameParamenterSingleton.assetManager.open("numeros/seis.png");
				break;
			case 7:
				is = GameParamenterSingleton.assetManager.open("numeros/sete.png");
				break;
			case 8:
				is = GameParamenterSingleton.assetManager.open("numeros/oito.png");
				break;
			case 9:
				is = GameParamenterSingleton.assetManager.open("numeros/nove.png");
				break;
			}
                  
            figura = BitmapFactory.decodeStream(is);
            int px = 25;   		
    		int py = 25;
    		
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
      //  int passoDistorcido = (int)(this.STEP * GameParamenterSingleton.DISTORTION);
     //   setX(getX()-passoDistorcido);
     //   getBoundingBox().setX(getX() - passoDistorcido);
        
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
