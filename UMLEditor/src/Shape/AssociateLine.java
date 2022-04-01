package Shape;

import java.awt.Graphics;
import java.awt.Point;

public class AssociateLine extends Line{
    private int Width = 10;
    private int Height = 10;
    public AssociateLine(Point from, Point to) {
        BeginPoint = from;
        EndPoint = to;
    }

    public void Draw(Graphics g) {
        g.drawLine(BeginPoint.x, BeginPoint.y, EndPoint.x, EndPoint.y);

        int dx = EndPoint.x - BeginPoint.x;
        int dy = EndPoint.y - BeginPoint.y;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - Width, xn = xm, ym = Height, yn = -Height, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + BeginPoint.x;
        ym = xm * sin + ym * cos + BeginPoint.y;
        xm = x;

        x = xn * cos - yn * sin + BeginPoint.x;
        yn = xn * sin + yn * cos + BeginPoint.y;
        xn = x;

        g.drawLine(EndPoint.x, EndPoint.y, (int) xm, (int) ym);
        g.drawLine(EndPoint.x, EndPoint.y, (int) xn, (int) yn);
    }
}
