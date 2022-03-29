package MouseMode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Shape.Line;
import Shape.Shape;

public class Select extends Mode {
    private ArrayList<Shape> ShapeList;
    private Point StartPoint = null;
    private Line SelectedLine = null;
    private String InsideString = null;

    public void mousePressed(MouseEvent e) {
        StartPoint = e.getPoint();
        ShapeList = canvas.GetShapeList();

        canvas.Reset();

        // 倒著數。從最頂圖層算到最底圖層
        for (int i = ShapeList.size() - 1; i >= 0; i--) {
            Shape shape = ShapeList.get(i);
            InsideString = shape.Inside(e.getPoint());
            if (InsideString != null) {
                canvas.SelectedObject = shape;
                break;
            }
        }

        canvas.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        int moveX = e.getX() - StartPoint.x;
        int moveY = e.getY() - StartPoint.y;

        // object selected
        if (canvas.SelectedObject != null) {
            // move Line object
            if (InsideString == "insideLine") {
                SelectedLine = (Line) canvas.SelectedObject;
                SelectedLine.ResetPoint(e.getPoint());
            } else {
                canvas.SelectedObject.Relocate(moveX, moveY);
            }

            StartPoint.x = e.getX();
            StartPoint.y = e.getY();
        } else {// select group
            int x = Math.min(StartPoint.x, e.getX());
            int y = Math.min(StartPoint.y, e.getY());

            canvas.SelectedArea.setBounds(x, y, Math.abs(moveX), Math.abs(moveY));
        }

        canvas.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        // object select
        if (canvas.SelectedObject != null) {
            // move Line object
            if (InsideString == "insideLine") {
                SelectedLine = (Line) canvas.SelectedObject;

                Point point = e.getPoint();
                for (int i = 0; i < ShapeList.size(); i++) {
                    Shape shape = ShapeList.get(i);
                    int portIndex;
                    String judgeInside = shape.Inside(point);
                    if (judgeInside != null && judgeInside != "insideLine") {
                        if (judgeInside == "insideGroup") {
                            shape = shape.GetSelectedShape();
                            portIndex = Integer.parseInt(shape.Inside(point));
                        } else
                            portIndex = Integer.parseInt(judgeInside);

                        SelectedLine.ResetPort(shape.GetPort(portIndex), SelectedLine);
                        SelectedLine.Relocate();
                    }
                }
            }
        } else {// select group
            canvas.SelectedArea.setSize(Math.abs(e.getX() - StartPoint.x), Math.abs(e.getY() - StartPoint.y));
        }
        canvas.repaint();
    }
}
