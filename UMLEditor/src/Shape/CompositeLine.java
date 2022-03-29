package Shape;

import java.awt.Graphics;
import java.awt.Point;

public class CompositeLine extends Line {
    private int DiamondWidth = 10;
    private int DiamondHeight = 10;

    public CompositeLine(Point from, Point to) {
        BeginPoint = from;
        EndPoint = to;
    }

    public void Draw(Graphics g) {
        g.drawLine(BeginPoint.x, BeginPoint.y, EndPoint.x, EndPoint.y);

        // 三角形的點, 考慮線條角度
        int dx = EndPoint.x - BeginPoint.x, dy = EndPoint.y - BeginPoint.y;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - DiamondWidth, xn = xm, ym = DiamondHeight, yn = -DiamondHeight, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + BeginPoint.x;
        ym = xm * sin + ym * cos + BeginPoint.y;
        xm = x;

        x = xn * cos - yn * sin + BeginPoint.x;
        yn = xn * sin + yn * cos + BeginPoint.y;
        xn = x;

        // 分點公式算出線上的點, 三角形的三個點與這個點連線即為一個菱形
        double xq = (DiamondHeight * 2 / D) * BeginPoint.x + ((D - DiamondHeight * 2) / D) * EndPoint.x;
        double yq = (DiamondHeight * 2 / D) * BeginPoint.y + ((D - DiamondHeight * 2) / D) * EndPoint.y;

        int[] xpoints = { EndPoint.x, (int) xm, (int) xq, (int) xn };
        int[] ypoints = { EndPoint.y, (int) ym, (int) yq, (int) yn };

        g.fillPolygon(xpoints, ypoints, 4);
    }
}
