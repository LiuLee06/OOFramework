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

### 步骤3：创建游戏元素集合

`java
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import com.oo.framework.API.Shape;

public class ActorCollection {
    public static Collection<Shape> shapes = new CopyOnWriteArrayList<>();
}
`

### 步骤4：创建公共组件类

`java
import com.oo.framework.framework.DrawingPanel;
import com.oo.framework.framework.ControlPanel;
import com.oo.framework.framework.MyFrame;

public class Commons {
    // 创建画图面板对象，并令它关联游戏元素对象（shapes）
    public final static DrawingPanel drawingPanel = new DrawingPanel(ActorCollection.shapes);

    // 创建Player对象
    public static Rect player = new Rect(0, 0, 50, 50);

    // 创建控制面板对象，并令它关联游戏元素对象
    public final static ControlPanel controlPanel = new ControlPanel(player);

    // 创建框架对象，并令它关联画图面板对象和控制面板对象
    public final static MyFrame myFrame = new MyFrame(Commons.drawingPanel, Commons.controlPanel);
}
`

### 步骤5：创建游戏对象

`java
import java.awt.Graphics;
import com.oo.framework.API.Controllable;
import com.oo.framework.API.Shape;

public class Rect implements Shape, Controllable {
    private int x, y, w, h;

    {
        // 自动将对象添加到游戏元素集合中
        ActorCollection.shapes.add(this);
    }

    // 构造器
    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Rect() {
    }

    @Override
    public void drawMyself(Graphics g) {
        g.drawRect(x, y, w, h);
    }

    @Override
    public void moveUp() {
        this.y -= 5;
    }

    @Override
    public void moveDown() {
        this.y += 5;
    }

    @Override
    public void moveLeft() {
        this.x -= 5;
    }

    @Override
    public void moveRight() {
        this.x += 5;
    }
}
`

### 步骤6：创建主类

`java
public class Main {
    public static void main(String[] args) {
        // 设置框架的大小
        Commons.myFrame.setSize(1000, 680);
        // 令框架可见
        Commons.myFrame.setVisible(true);
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
