package MouseMode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Shape.Line;
import Shape.Shape;

public class CreateLine extends Mode {
    private ShapeFactory factory = new ShapeFactory();
    private ArrayList<Shape> ShapeList;
    private String LineType = null;
    private Point StartPoint = null;
    private int portIndex_1 = -1;
    private int portIndex_2 = -1;
    private Shape shape_1 = null;
    private Shape shape_2 = null;

    public CreateLine(String lineType) {
        this.LineType = lineType;
    }

    public void mousePressed(MouseEvent e) {
        ShapeList = canvas.GetShapeList();
        StartPoint = FindConnected(e.getPoint(), "first");
    }

    public void mouseDragged(MouseEvent e) {
        // show dragged line
        if (StartPoint != null) {
            Line line = factory.CreateLine(LineType, StartPoint, e.getPoint());
            canvas.tempLine = line;
            canvas.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        Point endPoint = null;
        if (StartPoint != null) {
            /* find which basic object, record its reference and port number */
            endPoint = FindConnected(e.getPoint(), "second");

            /* if end of line inside the basic object */
            if (endPoint != null) {
                Line line = factory.CreateLine(LineType, StartPoint, endPoint);
                canvas.AddShape(line);

                /* add relative ports to line */
                line.SetPorts(shape_1.GetPort(portIndex_1), shape_2.GetPort(portIndex_2));

                /* add line to relative port of two basic object */
                shape_1.GetPort(portIndex_1).AddLine(line);
                shape_2.GetPort(portIndex_2).AddLine(line);
            }
            // reset
            canvas.tempLine = null;
            canvas.repaint();
            StartPoint = null;
        }
    }

    private Point FindConnected(Point p, String target) {
        for (int i = 0; i < ShapeList.size(); i++) {
            Shape shape = ShapeList.get(i);

            /* check if or not mouse pressed inside the basic object */
            int portIndex;
            String judgeInside = shape.Inside(p);
            if (judgeInside != null && judgeInside != "insideLine") {

                /* if shape inside the group */
                if (judgeInside == "insideGroup") {
                    shape = shape.GetSelectedShape();
                    portIndex = Integer.parseInt(shape.Inside(p));
                } else
                    portIndex = Integer.parseInt(judgeInside);

                /* if inside the basic object, get the location of relative port */
                switch (target) {
                    case "first":
                        shape_1 = shape;
                        portIndex_1 = portIndex;
                        break;
                    case "second":
                        shape_2 = shape;
                        portIndex_2 = portIndex;
                        break;
                }
                Point portLocation = new Point();
                portLocation.setLocation(shape.GetPort(portIndex).getCenterX(), shape.GetPort(portIndex).getCenterY());
                return portLocation;
            }
        }

        return null;
    }
}
