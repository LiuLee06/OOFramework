package com.oo.framework.framework;

import com.oo.framework.API.Controllable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControlPanel extends JPanel {
    private Controllable controllable;
    /**
     * 令本对象关联一个Controllable对象、并设置按钮
     * @param controllable 被关联的controable对象
     */
    public void setControllable(Controllable controllable) {
        this.controllable = controllable;
        addButtons(controllable);
    }

    public ControlPanel() {
        // 添加键盘监听器
        addKeyListener();
    }

    /**
     * 添加键盘监听器，支持WASD和方向键移动，以及Ctrl+P暂停/恢复功能
     */
    private void addKeyListener() {
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (controllable == null) return;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W: case KeyEvent.VK_UP:
                        controllable.moveUp(); break;
                    case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
                        controllable.moveDown(); break;
                    case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                        controllable.moveLeft(); break;
                    case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                        controllable.moveRight(); break;
                }
                ((MyFrame)getRootPane().getParent()).getDrawingPanel().repaint();
            }
        });
    };


    /**
     * 令本对象关联一个Controllable对象、并设置按钮
     * @param controllable 被关联的Controllable对象
     */
    public ControlPanel(Controllable controllable) {
        this.controllable = controllable;
        addButtons(controllable);
        // 添加键盘监听器
        addKeyListener();
    }

    private void addButtons(Controllable controllable) {
        JButton moveLeftBtn = new JButton("左移");
        this.add(moveLeftBtn);
        MoveLeftHandler moveLeftHandler = new MoveLeftHandler();
        moveLeftBtn.addActionListener(moveLeftHandler);

        JButton moveRightBtn = new JButton("右移");
        this.add(moveRightBtn);
        MoveRightHandler moveRightHandler = new MoveRightHandler();
        moveRightBtn.addActionListener(moveRightHandler);

        JButton moveDownBtn = new JButton("下移");
        this.add(moveDownBtn);
        MoveDownHandler moveDownHandler = new MoveDownHandler();
        moveDownBtn.addActionListener(moveDownHandler);

        JButton moveUpBtn = new JButton("上移");
        this.add(moveUpBtn);
        MoveUpHandler moveUpHandler = new MoveUpHandler();
        moveUpBtn.addActionListener(moveUpHandler);

        JButton closeBtn = new JButton("退出");
        this.add(closeBtn);
        CloseFrameHandler closeFrameHandler = new CloseFrameHandler();
//		closeBtn.addActionListener(closeFrameHandler);
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(((MyFrame)(ControlPanel.this.getRootPane().getParent())),
                        "确认退出吗", "提醒", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
    private class MoveRightHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            controllable.moveRight();
            MyFrame drawingFrame = (MyFrame)(ControlPanel.this.getRootPane().getParent());
            drawingFrame.getDrawingPanel().repaint();
        }
    }
    private class MoveLeftHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            controllable.moveLeft();
            ((MyFrame)(ControlPanel.this.getRootPane().getParent())).getDrawingPanel().repaint();
        }
    }
    private class MoveDownHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            controllable.moveDown();
            ((MyFrame)(ControlPanel.this.getRootPane().getParent())).getDrawingPanel().repaint();
        }
    }
    private class MoveUpHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            controllable.moveUp();
            ((MyFrame)(ControlPanel.this.getRootPane().getParent())).getDrawingPanel().repaint();
        }
    }

    private class CloseFrameHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


}
