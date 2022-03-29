package Shape;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
    protected Point BeginPoint;
    protected Point EndPoint;
    public boolean group_selected = false;

    public abstract void Draw(Graphics g);

    public Point GetTopLeft() {
        int x = Math.min(BeginPoint.x, EndPoint.x);
        int y = Math.min(BeginPoint.y, EndPoint.y);

        return new Point(x, y);
    }

    public Point GetBottomRight() {
        int x = Math.max(BeginPoint.x, EndPoint.x);
        int y = Math.max(BeginPoint.y, EndPoint.y);

        return new Point(x, y);
    }

    public int GetLeftX() {
        return Math.min(BeginPoint.x, EndPoint.x);
    }

    public int GetTopY() {
        return Math.min(BeginPoint.y, EndPoint.y);
    }

    public int GetRightX() {
        return Math.max(BeginPoint.x, EndPoint.x);
    }

    public int GetBottomY() {
        return Math.max(BeginPoint.y, EndPoint.y);
    }

    //Line method
    public void Relocate() {
    }

    // BasicObject and Group
    public void Relocate(int moveX, int moveY) {
    }

    public void UpdateName(String name) {
    }

    public void Show(Graphics g) {
    }

    public String Inside(Point p) {
        return null;
    }

    // Basic object
    public Port GetPort(int portIndex) {
        return null;
    }

    // Group
    public void ResetSelectedShape() {
    }

    public Shape GetSelectedShape() {
        return null;
    }
}
