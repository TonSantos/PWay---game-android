package com.pway.util;

import com.pway.visual.Obstacle;

import android.graphics.Canvas;


public class Combo {
	private Obstacle up;
	private Obstacle down;	
	private Gap gap;
	private Boolean valid;
	private int cima,baixo;
	
	public Combo() {
		valid = true;	
		//cima = (int)(Math.random()*100);
		//baixo = (int)(Math.random()*100);
		up = new Obstacle();
		up.setX(GameParamenterSingleton.SCREEN_WIDTH * 2);
		//int y = ((int)Math.random()%1000)%up.getWidth();
	//	if(y>0){
	//		y=y*(-1);
	//	}
		up.setY(GameParamenterSingleton.SCREEN_HEIGHT - 200);
		
		up.updateDistocion();
		//gap = new Gap(up.getX(),up.getHeight(),40,60);
		//gap.updateDistocion();
		
		down = new Obstacle();
		down.setX(up.getX());
		//down.setY(gap.getY() + gap.getHeight());
		down.setY(up.getY());
		down.updateDistocion();
	
		 up.getBoundingBox().setX(up.getX());
	     up.getBoundingBox().setY(up.getY());
	     up.getBoundingBox().setWidth(up.getWidth());
	     up.getBoundingBox().setHeight(up.getHeight());
	        
	      down.getBoundingBox().setX(down.getX());
	      down.getBoundingBox().setY(down.getY());
	      down.getBoundingBox().setWidth(down.getWidth());
	      down.getBoundingBox().setHeight(down.getHeight());
	        
	   //     gap.getBoundingBox().setX(gap.getX());
	   //     gap.getBoundingBox().setY(gap.getY());
	   //     gap.getBoundingBox().setWidth(gap.getWidth());
	   //     gap.getBoundingBox().setHeight(gap.getHeight());
	}
	
	public void update(){
		up.update();
	//	gap.update();
		down.update();		
	}
	
	public void draw(Canvas canvas){
		up.draw(canvas);
	//	gap.draw(canvas);
		down.draw(canvas);
		
	}

	public Obstacle getUp() {
		return up;
	}

	public void setUp(Obstacle up) {
		this.up = up;
	}

	public Obstacle getDown() {
		return down;
	}

	public void setDown(Obstacle down) {
		this.down = down;
	}

	public Gap getGap() {
		return gap;
	}

	public void setGap(Gap gap) {
		this.gap = gap;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	
	
}
