package com.oo.framework.view;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import com.oo.framework.API.Shape;

public final class DrawingPanel extends JPanel {

    //空构造器
    public DrawingPanel() {
    }
    //有参构造器， param: Shape动态数组
    public DrawingPanel(Collection<Shape> shapes) {
        super();
        this.shapes = shapes;
    }

    //设置shape collection对象
    private Collection<Shape> shapes;


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Shape shape: shapes){
            shape.drawMyself(g);
        }
    }


    public void setShapes(Collection<Shape> shapes){
        this.shapes = shapes;
    }

}