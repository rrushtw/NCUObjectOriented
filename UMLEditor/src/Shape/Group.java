package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Group extends Shape {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Rectangle bounds = new Rectangle();
    private Shape selectedShape = null;

    public void Draw(Graphics g) {
        // show bounds
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            shape.Draw(g);
        }
    }

    public void Show(Graphics g) {
        int alpha = 85; // 33% transparent
        int offset = 10;
        g.setColor(new Color(110, 219, 181, alpha));
        g.fillRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
        g.setColor(new Color(110, 219, 181));
        g.drawRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
        g.setColor(Color.BLACK);
        if (selectedShape != null) {
            selectedShape.Show(g);
        }
    }

    public void Relocate(int moveX, int moveY) {
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            shape.Relocate(moveX, moveY);
        }
        resetBounds(moveX, moveY);
    }

    public String Inside(Point p) {
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            String judgeInside = shape.Inside(p);
            if (judgeInside != null) {
                selectedShape = shape;
                return "insideGroup";
            }
        }
        return null;
    }

    public void UpdateName(String name) {
        selectedShape.UpdateName(name);
    }

    public void ResetSelectedShape() {
        selectedShape = null;
    }
    
    public Shape GetSelectedShape() {
        return selectedShape;
    }
    
    public void SetBounds() {
        /* find bounderies and set group bounds */
        Point topLeft;
        Point bottomRight;

        int leftX = Integer.MAX_VALUE;
        int rightX = Integer.MIN_VALUE;
        int upY = Integer.MAX_VALUE;
        int bottomY = Integer.MIN_VALUE;

        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            if (shape.GetLeftX() < leftX) {
                leftX = shape.GetLeftX();
            }
            if (shape.GetRightX() > rightX) {
                rightX = shape.GetRightX();
            }
            if (shape.GetTopY() < upY) {
                upY = shape.GetTopY();
            }
            if (shape.GetBottomY() > bottomY) {
                bottomY = shape.GetBottomY();
            }
        }

        topLeft = new Point(leftX, upY);
        bottomRight = new Point(rightX, bottomY);
        bounds.setBounds(topLeft.x, topLeft.y, Math.abs(topLeft.x - bottomRight.x), Math.abs(topLeft.y - bottomRight.y));

        // set parent shape coordinate
        BeginPoint = new Point(bounds.x, bounds.y);
        EndPoint = new Point(bounds.x + bounds.width, bounds.y + bounds.height);
    }

    protected void resetBounds(int moveX, int moveY) {
        bounds.setLocation(bounds.x + moveX, bounds.y + moveY);
        BeginPoint = new Point(bounds.x, bounds.y);
        EndPoint = new Point(bounds.x + bounds.width, bounds.y + bounds.height);
    }

    public void addShapes(Shape shape) {
        shapes.add(shape);
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
