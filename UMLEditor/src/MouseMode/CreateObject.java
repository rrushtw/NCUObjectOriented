package MouseMode;

import java.awt.event.MouseEvent;

import Shape.BasicObject;

public class CreateObject extends Mode {
    private String ObjectType = null;
    private ShapeFactory Factory = new ShapeFactory();

    public CreateObject(String objType) {
        this.ObjectType = objType;
    }

    public void mousePressed(MouseEvent e) {
        BasicObject basicObj = Factory.CreateObject(ObjectType, e.getPoint());
        canvas.AddShape(basicObj);
        canvas.repaint();
    }
}
