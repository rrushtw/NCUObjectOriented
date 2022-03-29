package Shape;

import java.awt.Graphics;
import java.awt.Point;

public class AssociateLine extends Line{
    public AssociateLine(Point from, Point to) {
        BeginPoint = from;
        EndPoint = to;
    }

    public void Draw(Graphics g) {
        g.drawLine(BeginPoint.x, BeginPoint.y, EndPoint.x, EndPoint.y);
    }
}
