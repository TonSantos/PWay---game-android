package com.pway.visual;

import java.io.IOException;
import java.io.InputStream;
import java.util.Currency;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.pway.util.GameCoisas;
import com.pway.util.GameParamenterSingleton;

public class GirlBubble extends GameCoisas{
	
	private Bitmap figura;
	private Rect src;
	private Rect dest;
	
	private int sprinteWidht;
	private int sprinteHeight;
	private int currentSprinte;
		
	private static final String TAG = "GirlBubble";
	
	private int direcao;

	public static final int SOBE = 0;
	public static final int DESCE = 1;
	
	private static final int PASSO_SOBE=3;
	private static final int PASSO_DESCE=6;
	
	private Paint paint;
	private int frame=1;
	public GirlBubble(boolean flag) {		
		try{
			if(flag){						
					InputStream	is = GameParamenterSingleton.assetManager.open("fbubble.png");
					figura = BitmapFactory.decodeStream(is);
					frame = 3;	
			} else {
				   InputStream is = GameParamenterSingleton.assetManager.open("bubbles.png");
				   figura = BitmapFactory.decodeStream(is);
				   frame=4;
			}
			
			
			
			sprinteWidht = figura.getWidth() / frame;//10
			sprinteHeight = figura.getHeight();
			
			setWidth(sprinteWidht);
			setHeight(sprinteHeight);
			
			currentSprinte = 0;
			
			src = new Rect(0,0,getWidth(),getHeight());
			dest = new Rect();
			
			direcao = DESCE;
			
			paint = new Paint();
			paint.setColor(Color.BLACK);
		}catch(Exception e){
			Log.d(TAG,"Erro ao carregar imagem!");
		}
	}
	
	@Override
	public void update() {
		
		if(direcao == DESCE){
			setY(getY() + (int)(PASSO_DESCE*GameParamenterSingleton.DISTORTION));
			getBoundingBox().setY(getBoundingBox().getY()+ (int)(PASSO_DESCE*GameParamenterSingleton.DISTORTION));
		}else{
			setY(getY() - (int)(PASSO_SOBE*GameParamenterSingleton.DISTORTION));
			getBoundingBox().setY(getBoundingBox().getY()- (int)(PASSO_SOBE*GameParamenterSingleton.DISTORTION));
		}
		src.top = 0;
		src.bottom = sprinteHeight;
		src.left = currentSprinte*sprinteWidht;
		src.right = src.left + sprinteWidht;
		
		dest.top = getY();
		dest.bottom = getY() + getHeight();
		dest.left = getX();
		dest.right = getX() + getWidth();
		
		currentSprinte = (currentSprinte + 1)%frame;//10
		
	
	}

	@Override
	public void draw(Canvas canvas) {
		//Debug
		
	/*canvas.drawRect(getBoundingBox().getX(),
       getBoundingBox().getY(),
       getBoundingBox().getX() + getBoundingBox().getWidth(),
       getBoundingBox().getY() + getBoundingBox().getHeight(),paint);*/
		canvas.drawBitmap(figura, src, dest,null);
		
			
			//Log.d(TAG,getBoundingBox().toString());
		
		
	}
	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}
	
	

}
