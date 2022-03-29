package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ClassObject extends BasicObject {
    public ClassObject(Point point) {
        this.Width = 100;
        this.Height = 120;

        BeginPoint = point;
        EndPoint = new Point(point.x + Width, point.y + Height);

        CreatePorts();
    }

    public void Draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(BeginPoint.x, BeginPoint.y, Width, Height);
        g.setColor(Color.BLACK);
        g.drawRect(BeginPoint.x, BeginPoint.y, Width, Height);

        int portion = Height / 3;
        g.drawLine(BeginPoint.x, BeginPoint.y + portion, EndPoint.x, BeginPoint.y + portion);
        g.drawLine(BeginPoint.x, BeginPoint.y + portion * 2, EndPoint.x, BeginPoint.y + portion * 2);

        // find the width for the specified string.
        int stringWidth = g.getFontMetrics(ObjectFont).stringWidth(ObjectName);
        double empty = (Math.abs(BeginPoint.x - EndPoint.x) - stringWidth) / 2;
        g.setFont(ObjectFont);
        g.drawString(ObjectName, BeginPoint.x + (int) empty, BeginPoint.y + 25);
    }
}
