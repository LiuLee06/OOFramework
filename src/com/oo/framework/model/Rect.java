package com.oo.framework.model;

import com.oo.framework.API.Shape;

import java.awt.*;

public class Rect implements Shape{
    private int x,y,w,h;

    //构造器
    public Rect(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public Rect(){}

    @Override
    public void drawMyself(Graphics g) {
        
    }
}
