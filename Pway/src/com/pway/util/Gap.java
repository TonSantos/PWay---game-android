package com.pway.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Gap extends GameCoisas {
    
    private static final int STEP=5;
    private Rect dst;
    private Paint paint;
    
    public Gap(int x, int y, int width, int heigth){
        setX(x);
        setY(y);        
        setWidth(width);
        setHeight(heigth);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        dst = new Rect();
    }
    

    @Override
    public void update() {
        // TODO Auto-generated method stub
        int passoDistorcido = (int)(this.STEP*GameParamenterSingleton.DISTORTION);
        setX(getX()-passoDistorcido);
        getBoundingBox().setX(getX() -passoDistorcido);
        dst.top = getY();        
        dst.bottom = getY()+getHeight();
        dst.left = getX();
        dst.right = getX() + getWidth();                
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        //canvas.drawRect(dst, paint);
        /*canvas.drawRect(getBoundingBox().getX(),
                        getBoundingBox().getY(),
                        getBoundingBox().getWidth()+getBoundingBox().getX(),
                        getBoundingBox().getHeight()+getBoundingBox().getY(),
                     paint);*/
    }
}
