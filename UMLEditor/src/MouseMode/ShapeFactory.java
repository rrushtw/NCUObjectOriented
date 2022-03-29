package MouseMode;

import java.awt.Point;

import Shape.AssociateLine;
import Shape.BasicObject;
import Shape.ClassObject;
import Shape.CompositeLine;
import Shape.GeneralizeLine;
import Shape.Line;
import Shape.UsecaseObject;

public class ShapeFactory {
    public BasicObject CreateObject(String type, Point point) {
        if (type.equals("class")) {
            return new ClassObject(point);
        }

        if (type.equals("usecase")) {
            return new UsecaseObject(point);
        }

        return null;
    }

    public Line CreateLine(String type, Point from, Point to) {
        if (type.equals("associate")) {
            return new AssociateLine(from, to);
        }

        if (type.equals("general")) {
            return new GeneralizeLine(from, to);
        }

        if (type.equals("composite")) {
            return new CompositeLine(from, to);
        }

        return null;
    }
}
