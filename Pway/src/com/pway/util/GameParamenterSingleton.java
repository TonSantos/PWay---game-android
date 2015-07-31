package com.pway.util;

import android.content.res.AssetManager;

public class GameParamenterSingleton {
	public static final int PORTRAIT = 0;
	public static final int LANDSCAPE = 0;
	public static int PONTOS;
	public static int BOB;
	public static int BUGGER;
	public static int BURTON;
	public static int BOOK;
	public static int FINN;
	public static int GERARD;
	public static int PIZZA;
	public static int CORACAO;
	public static int MISSAO;
	public static int INSETO;
	
	
	
	public static int ORIENTATION;
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	
	public static float DISTORTION = 1.0f;
	public static AssetManager assetManager;

	 public static boolean detectColision(GameCoisas coiso1, GameCoisas coiso2){
	        int coiso1Left, coiso1Right, coiso1Top, coiso1Bottom;
	        int coiso2Left, coiso2Right, coiso2Top, coiso2Bottom;
	        
	        coiso1Left   = coiso1.getBoundingBox().getX();
	        coiso1Right  = coiso1.getBoundingBox().getX() + coiso1.getBoundingBox().getWidth();
	        coiso1Top    = coiso1.getBoundingBox().getY();
	        coiso1Bottom = coiso1.getBoundingBox().getY() + coiso1.getBoundingBox().getHeight();
	        
	        coiso2Left   = coiso2.getBoundingBox().getX();
	        coiso2Right  = coiso2.getBoundingBox().getX() + coiso2.getBoundingBox().getWidth();
	        coiso2Top    = coiso2.getBoundingBox().getY();
	        coiso2Bottom = coiso2.getBoundingBox().getY() + coiso2.getBoundingBox().getHeight();
	        
	        
	    

	        return (coiso1Left   <= coiso2Right  &&
	                coiso1Right  >= coiso2Left   &&
	                coiso1Top    <= coiso2Bottom &&
	                coiso1Bottom >= coiso2Top);
	        
	    }
	 
	
}


