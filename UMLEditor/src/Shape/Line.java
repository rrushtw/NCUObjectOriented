package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

public abstract class Line extends Shape {
    private String selectedFlag = null;
    protected Port[] ports = new Port[2];

    public abstract void Draw(Graphics g);

    public void SetPorts(Port port_1, Port port_2) {
        this.ports[0] = port_1;
        this.ports[1] = port_2;
    }

    public void Show(Graphics g) {
        g.setColor(new Color(50, 170, 170));
        this.Draw(g);
        // Set selected objects color while objects are connnected with line
        g.setColor(Color.BLACK);
    }

    public void Relocate() {
        BeginPoint = new Point((int) ports[0].getCenterX(), (int) ports[0].getCenterY());
        EndPoint = new Point((int) ports[1].getCenterX(), (int) ports[1].getCenterY());
    }

    public void ResetPoint(Point point) {
        if (selectedFlag == "start") {
            BeginPoint = point;
        }

        if (selectedFlag == "end") {
            EndPoint = point;
        }
    }

    public String Inside(Point point) {
        int tolerance = 5;

        Line2D line = new Line2D.Double(BeginPoint.x, BeginPoint.y, EndPoint.x, EndPoint.y);
        double distance = line.ptLineDist(point.getX(), point.getY());

        if (distance < tolerance) {
            double distToStart = Math.sqrt(Math.pow((point.x - BeginPoint.x), 2) + Math.pow((point.y - BeginPoint.y), 2));
            double distToEnd = Math.sqrt(Math.pow((point.x - EndPoint.x), 2) + Math.pow((point.y - EndPoint.y), 2));
            if (distToStart < distToEnd) {
                selectedFlag = "start";
            } else {
                selectedFlag = "end";
            }
            return "insideLine";
        } else
            return null;
    }

    public void ResetPort(Port port, Line line) {
        port.AddLine(line);

        if (selectedFlag == "start") {
            this.ports[0].RemoveLine(line);
            this.ports[0] = port;
        }

        if (selectedFlag == "end") {
            this.ports[1].RemoveLine(line);
            this.ports[1] = port;
        }
    }
}
