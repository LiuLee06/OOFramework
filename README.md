# OOFramework

面向对象课程大作业框架，为学生提供完整的游戏开发基础组件。

## 框架组件

- **ControlPanel**：控制面板，提供按钮和键盘控制功能
- **DrawingPanel**：绘图面板，负责绘制游戏中的各种图形
- **MyFrame**：主窗口框架，整合控制面板和绘图面板
- **Controllable接口**：定义游戏对象的移动方法
- **Shape接口**：定义游戏对象的绘制方法

## 快速开始

### 步骤1：添加依赖

1. 下载 OOFramework.jar 文件
2. 在IDE中添加该JAR作为项目依赖：
   - **IntelliJ IDEA**：File  Project Structure  Libraries  +  Java
   - **Eclipse**：右键项目  Build Path  Add External Archives

### 步骤2：导入必要的类

`java
import com.oo.framework.API.Controllable;
import com.oo.framework.API.Shape;
import com.oo.framework.framework.ControlPanel;
import com.oo.framework.framework.DrawingPanel;
import com.oo.framework.framework.MyFrame;
`

### 步骤3：创建游戏对象

`java
import java.awt.*;

public class Player implements Controllable, Shape {
    private int x, y;
    private int width = 50, height = 50;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void moveUp() {
        y -= 10;
    }

    @Override
    public void moveDown() {
        y += 10;
    }

    @Override
    public void moveLeft() {
        x -= 10;
    }

    @Override
    public void moveRight() {
        x += 10;
    }

    @Override
    public void drawMyself(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    // Getter methods
    public int getX() { return x; }
    public int getY() { return y; }
}
`

### 步骤4：创建游戏窗口

`java
import java.util.ArrayList;
import java.util.List;

public class GameDemo {
    public static void main(String[] args) {
        // 创建游戏对象
        Player player = new Player(100, 100);
        
        // 创建形状集合
        List<Shape> shapes = new ArrayList<>();
        shapes.add(player);
        
        // 创建面板
        DrawingPanel drawingPanel = new DrawingPanel(shapes);
        drawingPanel.setPreferredSize(new Dimension(800, 600));
        
        ControlPanel controlPanel = new ControlPanel(player);
        
        // 创建窗口
        MyFrame frame = new MyFrame(drawingPanel, controlPanel);
        frame.pack();
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
}
`

## 控制方式

- **按钮控制**：点击控制面板上的方向按钮
- **键盘控制**：使用 WASD 键或方向键
- **退出**：点击"退出"按钮或关闭窗口

## 扩展指南

1. **创建更多游戏对象**：实现 Shape 接口
2. **添加游戏逻辑**：在游戏对象中添加碰撞检测、计分等
3. **自定义绘制**：重写 drawMyself 方法创建不同形状
4. **添加游戏循环**：使用线程实现动画效果

## 技术说明

- **Java版本**：Java 8+
- **GUI库**：Swing
- **架构**：面向对象设计，使用接口分离关注点

## 作者

面向对象课程大作业框架 - 为学生提供游戏开发基础组件
