package com.pway.util;

import android.graphics.Canvas;


import android.graphics.Canvas;

public abstract class GameCoisas {
	private int x;
	private int y;
	private int width;
	private int height;
	private BoundingBox boundingBox;
	
	public GameCoisas() {
		boundingBox = new BoundingBox();
	}
	
	public abstract void update();
	public abstract void draw(Canvas canvas);	
	public void updateDistocion(){
		width = (int)(width*GameParamenterSingleton.DISTORTION);
		height = (int)(height*GameParamenterSingleton.DISTORTION);
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	
}
