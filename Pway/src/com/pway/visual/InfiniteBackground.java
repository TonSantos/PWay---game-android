package com.pway.visual;

import java.io.InputStream;



import com.pway.util.GameParamenterSingleton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class InfiniteBackground {
	private Bitmap figura;	
	private int heigth;
	private int width;
	private Rect src;
	private Rect first;
	private Rect second;
	
	private static final String TAG = "InfiniteBackgroung";
	
	private static final int STEP = 5;
	
	public InfiniteBackground() {
		try{
			InputStream is = GameParamenterSingleton.assetManager.open("sky.png");
			figura = BitmapFactory.decodeStream(is);
			heigth = figura.getHeight();
			width = figura.getWidth();
			
			src  = new Rect(0,0,width,heigth);
			first = new Rect();
			second = new Rect();
		}catch(Exception e){
			Log.d(TAG,"Erro ao decodificar imagem!");
		}
	}
	
	public void update(){
		//como ele se move
		int passoDistorcido = (int)(STEP*GameParamenterSingleton.DISTORTION);
		first.left -= passoDistorcido;
		first.right -= passoDistorcido;
		first.top = 0;
		first.bottom = getHeigth();
		
		
		second.left -= passoDistorcido;
		second.right -= passoDistorcido;
		second.top = 0;
		second.bottom = getHeigth();
		
		if(first.right <= 0){
			first.left = second.right;
			first.right = second.right + width;					
		}
		if(second.right <= 0){
			second.left = first.right;
			second.right = first.right + width;
		}
		
	}
	
	public void updateDistortion(){
		setWidth((int)(getWidth()*GameParamenterSingleton.DISTORTION));
		setHeigth((int)(getHeigth()*GameParamenterSingleton.DISTORTION));
		
		first.left = 0;
		first.right = width;
		first.top = 0;
		first.bottom = heigth;
		
		second.top = 0;
		second.left = width;
		second.right = second.left + width;
		second.bottom = heigth;
		System.out.println("Estou no Distorcion!!");
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(figura,src, first,null);
		canvas.drawBitmap(figura, src, second,null);
	}
	
	public Bitmap getFigura() {
		return figura;
	}

	public void setFigura(Bitmap figura) {
		this.figura = figura;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Rect getSrc() {
		return src;
	}

	public void setSrc(Rect src) {
		this.src = src;
	}

	public Rect getFirst() {
		return first;
	}

	public void setFirst(Rect first) {
		this.first = first;
	}

	public Rect getSecond() {
		return second;
	}

	public void setSecond(Rect second) {
		this.second = second;
	}

	public static String getTag() {
		return TAG;
	}
}
