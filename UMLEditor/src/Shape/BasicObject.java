package Shape;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public abstract class BasicObject extends Shape {
    private int Offset = 5;
    protected int Width;
    protected int Height;

    protected String ObjectName = "New Object";
    protected Font ObjectFont = new Font(Font.DIALOG, Font.BOLD, 14);

    protected Port[] Ports = new Port[4];

    public abstract void Draw(Graphics g);

    public void Show(Graphics g) {
        for (int i = 0; i < Ports.length; i++) {
            g.fillRect(Ports[i].x, Ports[i].y, Ports[i].width, Ports[i].height);
        }
    }

    public String Inside(Point p) {
        Point center = new Point();
        center.x = (BeginPoint.x + EndPoint.x) / 2;
        center.y = (BeginPoint.y + EndPoint.y) / 2;
        Point[] points = { new Point(BeginPoint.x, BeginPoint.y), new Point(EndPoint.x, BeginPoint.y),
                new Point(EndPoint.x, EndPoint.y), new Point(BeginPoint.x, EndPoint.y) };

        for (int i = 0; i < points.length; i++) {
            Polygon t = new Polygon();
            // (0,1,center) (1,2,center) (2,3,center) (3,0,center)
            int secondIndex = ((i + 1) % 4);
            t.addPoint(points[i].x, points[i].y);
            t.addPoint(points[secondIndex].x, points[secondIndex].y);
            t.addPoint(center.x, center.y);

            if (t.contains(p)) {
                return Integer.toString(i);
            }
        }
        return null;
    }

    public Port GetPort(int portIndex) {
        return Ports[portIndex];
    }

    public void Relocate(int moveX, int moveY) {
        int x = this.BeginPoint.x + moveX;
        int y = this.BeginPoint.y + moveY;

        BeginPoint = new Point(x, y);
        EndPoint = new Point(x + Width, y + Height);

        Ports[0].SetPort((BeginPoint.x + EndPoint.x) / 2, BeginPoint.y - Offset, Offset);
        Ports[0].ResetLines();

        Ports[1].SetPort(EndPoint.x + Offset, (BeginPoint.y + EndPoint.y) / 2, Offset);
        Ports[1].ResetLines();

        Ports[2].SetPort((BeginPoint.x + EndPoint.x) / 2, EndPoint.y + Offset, Offset);
        Ports[2].ResetLines();

        Ports[3].SetPort(BeginPoint.x - Offset, (BeginPoint.y + EndPoint.y) / 2, Offset);
        Ports[3].ResetLines();
    }

    public void UpdateName(String name) {
        this.ObjectName = name;
    }

    protected void CreatePorts() {
        Ports[0] = new Port();
        Ports[0].SetPort((BeginPoint.x + EndPoint.x) / 2, BeginPoint.y - Offset, Offset);

        Ports[1] = new Port();
        Ports[1].SetPort(EndPoint.x + Offset, (BeginPoint.y + EndPoint.y) / 2, Offset);

        Ports[2] = new Port();
        Ports[2].SetPort((BeginPoint.x + EndPoint.x) / 2, EndPoint.y + Offset, Offset);

        Ports[3] = new Port();
        Ports[3].SetPort(BeginPoint.x - Offset, (BeginPoint.y + EndPoint.y) / 2, Offset);
    }
}
