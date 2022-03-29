package Shape;

import java.awt.Graphics;
import java.awt.Point;

public class GeneralizeLine extends Line {
    private int ArrowWidth = 10;
    private int ArrowHeight = 10;

    public GeneralizeLine(Point from, Point to) {
        BeginPoint = from;
        EndPoint = to;
    }

    public void Draw(Graphics g) {
        g.drawLine(BeginPoint.x, BeginPoint.y, EndPoint.x, EndPoint.y);

        int dx = EndPoint.x - BeginPoint.x, dy = EndPoint.y - BeginPoint.y;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - ArrowWidth, xn = xm, ym = ArrowHeight, yn = -ArrowHeight, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + BeginPoint.x;
        ym = xm * sin + ym * cos + BeginPoint.y;
        xm = x;

        x = xn * cos - yn * sin + BeginPoint.x;
        yn = xn * sin + yn * cos + BeginPoint.y;
        xn = x;

        int[] xpoints = { EndPoint.x, (int) xm, (int) xn };
        int[] ypoints = { EndPoint.y, (int) ym, (int) yn };

        g.fillPolygon(xpoints, ypoints, 3);
    }
}
