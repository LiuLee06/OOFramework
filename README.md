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
   - **IntelliJ IDEA**：File → Project Structure → Libraries → + → Java
   - **Eclipse**：右键项目 → Build Path → Add External Archives

### 步骤2：导入必要的类

```java
import com.oo.framework.API.Controllable;
import com.oo.framework.API.Shape;
import com.oo.framework.framework.ControlPanel;
import com.oo.framework.framework.DrawingPanel;
import com.oo.framework.framework.MyFrame;
````

### 步骤3：创建游戏元素集合

```java
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import com.oo.framework.API.Shape;

public class ActorCollection {
    public static Collection<Shape> shapes = new CopyOnWriteArrayList<>();
}
````

### 步骤4：创建公共组件类

```java
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
```

### 步骤5：创建游戏对象

```java
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
````

### 步骤6：创建主类

```java
public class Main {
    public static void main(String[] args) {
        // 设置框架的大小
        Commons.myFrame.setSize(1000, 680);
        // 令框架可见
        Commons.myFrame.setVisible(true);
    }
}
```

## 控制方式

- **按钮控制**：点击控制面板上的方向按钮
- **键盘控制**：使用 WASD 键或方向键
- **退出**：点击"退出"按钮或关闭窗口

## 扩展指南

1. **创建更多游戏对象**：创建更多游戏对象并自定义绘制：实现 Shape 接口，重写 drawMyself 方法创建不同形状的游戏对象（如圆形、三角形、精灵图等）。
2. **添加游戏逻辑**：使用 Helper 类提供的工具方法快速实现重叠检测、集合操作、线程延迟等功能，完成游戏设计。
```java
public class Helper {
    /**
     * 重叠检测
     * @param overlapper 参加重叠检测的第1个对象，即主动检测者
     * @param overlappable 参加重叠检测的第2个对象，即被检测者
     * @return 两个对象是否重叠
     */
    //监测两个对象是否重叠
    public static boolean overlapCheck(Overlapper overlapper, Overlappable overlappable) {
        //第一个对象的宽度和高度
        int w1 = overlapper.getW();
        int h1 = overlapper.getH();

        //第一个对象的中心点坐标
        int cx1 = overlapper.getCx();
        int cy1 = overlapper.getCy();

        //第二个对象的宽度和高度
        int w2 = overlappable.getW();
        int h2 = overlappable.getH();

        //第二个对象的中心点坐标
        int cx2 = overlappable.getCx();
        int cy2 = overlappable.getCy();

        //双方中心横距
        int dx = Math.abs(cx1 - cx2);

        //双方中心纵距
        int dy = Math.abs(cy1 - cy2);

        //在横向是否重叠
        boolean overlapInX = (w1 + w2)/2 >= dx;

        //在纵向是否重叠
        boolean overlapInY = (h1 + h2)/2 >= dy;

        //返回是否双向重叠
        return (overlapInX && overlapInY);
    }


    /**
     * @param toSearch 要查找的对象
     * @param searched 在该数组中查找
     * @return toSearch是否在searched数组中
     */
    public static boolean containedInArray(Object toSearch,  Collection<?> searched) {
        boolean contained = false;
        for (Object element : searched) {
            if (toSearch == element) {
                contained = true;
            }
        }
        return contained;
    }

    /**
     * 从多个集合类中删除指定对象
     * @param collectionCollection 保存集合类对象的集合类对象
     * @param toRemove 要从多个集合中删除的对象
     */
    public static void removeObjectFromCollection2(Collection<Collection> collectionCollection, Object toRemove) {
        for (Collection collection : collectionCollection) {
            collection.remove(toRemove);
        }
    }

    /**
     * 将对象添加到多个集合中，并将这些集合记录到一个主集合中
     * @param collectionCollection 保存集合类对象的主集合
     * @param toAdd 要添加的对象
     * @param collections 要添加到的多个集合
     */
    public static void addObjectToCollectionCollection(Collection<Collection> collectionCollection, Object toAdd, Collection<?>... collections) {
        for (Collection<?> collection : collections) {
            ((Collection<Object>) collection).add(toAdd);
            collectionCollection.add(collection);
        }
    }

    /**
     * 线程延迟
     * @param milliseconds 延迟的毫秒数
     */
    public static void delay(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

```
   
3. **扩展控制面板功能**：通过继承 ControlPanel 类，重写键盘事件处理方法，实现空格发射和Ctrl+P 键暂停游戏的功能。
4. **多线程进阶功能**：加入多线程实现游戏计时、自动发射、敌人 AI 等动态效果，让游戏体验更流畅。

## 技术说明

- **Java版本**：Java 8+
- **GUI库**：Swing
- **架构**：面向对象设计，使用接口分离关注点

## 作者

面向对象课程大作业框架 - 为学生提供游戏开发基础组件
