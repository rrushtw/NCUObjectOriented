package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class UsecaseObject extends BasicObject {
    public UsecaseObject(Point point) {
        this.Width = 120;
        this.Height = 90;

        BeginPoint = point;
        EndPoint = new Point(point.x + Width, point.y + Height);
        CreatePorts();
    }

    public void Draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(BeginPoint.x, BeginPoint.y, Width, Height);
        g.setColor(Color.BLACK);
        g.drawOval(BeginPoint.x, BeginPoint.y, Width, Height);

        // find the width for the specified string.
        int stringWidth = g.getFontMetrics(ObjectFont).stringWidth(ObjectName);
        double empty = (Math.abs(BeginPoint.x - EndPoint.x) - stringWidth) / 2;
        g.setFont(ObjectFont);
        g.drawString(ObjectName, BeginPoint.x + (int) empty, BeginPoint.y + 50);
    }
}
