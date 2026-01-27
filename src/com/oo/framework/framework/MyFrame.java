package com.oo.framework.framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class MyFrame extends JFrame {
    /**
     * 令本对象关联画图面板和控制面板、设置标题
     * @param drawingPanel 画图面板，负责画出各种物体
     * @param controlPanel 控制面板，负责控制物体移动
     */
    public MyFrame(DrawingPanel drawingPanel,
                   ControlPanel controlPanel) {
        super(MyFrame.TITLE);
        this.setDrawingPanel(drawingPanel);
        this.setControlPanel(controlPanel);
        this.setGui();
    }
    /**
     * 令本对象关联画图面板
     * @param drawingPanel 画图面板，负责画出各种物体
     */
    public MyFrame(DrawingPanel drawingPanel) {
        super(MyFrame.TITLE);
        this.setDrawingPanel(drawingPanel);
        this.setGui();
    }

    public MyFrame() throws HeadlessException {
        super(MyFrame.TITLE);
    }

    private void setGui() {

        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(MyFrame.this,
                        "确认退出吗", "提醒", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * 令本对象关联画图面板
     * @param drawingPanel 画图面板，负责画出各种物体
     */
    public void setDrawingPanel(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
        this.add(drawingPanel);
    }

    /**
     * 令本对象关联控制面板
     * @param controlPanel 控制面板，负责控制物体移动
     */
    public void setControlPanel(ControlPanel controlPanel){
        this.controlPanel = controlPanel;
        this.add(controlPanel, BorderLayout.SOUTH);
    }


    public DrawingPanel getDrawingPanel() {
        return this.drawingPanel;
    }

    private DrawingPanel drawingPanel;
    private ControlPanel controlPanel;
    private static String TITLE="信管专业面向对象课程大作业设计平台 by 佀同光";

}

