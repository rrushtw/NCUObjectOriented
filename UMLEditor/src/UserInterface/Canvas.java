package UserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.JPanel;

import MouseMode.Mode;
import Shape.Group;
import Shape.Shape;

public class Canvas extends JPanel {
    private static Canvas Instance = null;

    private EventListener Listener = null;
    protected Mode currentMode = null;

    private ArrayList<Shape> ShapeList = new ArrayList<Shape>();

    public Shape tempLine = null;
    public Shape SelectedObject = null;
    public Rectangle SelectedArea = new Rectangle();

    private Canvas() {
    }

    public static Canvas GetInstance() {
        if (Instance == null) {
            Instance = new Canvas();
        }

        return Instance;
    }

    public void SetMode() {
        removeMouseListener((MouseListener) Listener);
        removeMouseMotionListener((MouseMotionListener) Listener);

        Listener = currentMode;

        addMouseListener((MouseListener) Listener);
        addMouseMotionListener((MouseMotionListener) Listener);
    }

    public void Reset() {
        if (SelectedObject != null) {
            // for selected shape inside the group
            SelectedObject.ResetSelectedShape();
            SelectedObject = null;
        }

        SelectedArea.setBounds(0, 0, 0, 0);
    }

    public void AddShape(Shape shape) {
        ShapeList.add(shape);
    }

    public ArrayList<Shape> GetShapeList() {
        return ShapeList;
    }

    public void GroupShape() {
        Group group = new Group();
        for (int i = 0; i < ShapeList.size(); i++) {
            Shape shape = ShapeList.get(i);

            if (shape.group_selected) {
                group.addShapes(shape);
                ShapeList.remove(i);
                i--;
            }
        }

        group.SetBounds();
        ShapeList.add(group);
    }

    public void RemoveGroup() {
        Group group = (Group) SelectedObject;
        ArrayList<Shape> groupShapes = group.getShapes();
        for (int i = 0; i < groupShapes.size(); i++) {
            Shape shape = groupShapes.get(i);
            ShapeList.add(shape);
        }
        ShapeList.remove(SelectedObject);
    }

    public void UpdateObjectName(String name) {
        if (SelectedObject != null) {
            SelectedObject.UpdateName(name);
            repaint();
        }
    }

    private boolean IsSelectedArea(Shape shape) {
        Point upperleft = new Point(shape.GetLeftX(), shape.GetTopY());
        Point lowerright = new Point(shape.GetRightX(), shape.GetBottomY());
        /* show ports of selected objects */
        if (SelectedArea.contains(upperleft) && SelectedArea.contains(lowerright)) {
            return true;
        }
        return false;
    }

    public void paint(Graphics g) {
        /* set canvas area */
        Dimension dim = getSize();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, dim.width, dim.height);
        /* set painting color */
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));

        // paint all
        for (int i = 0; i < ShapeList.size(); i++) {
            Shape shape = ShapeList.get(i);
            shape.Draw(g);
            shape.group_selected = false;
            // group
            if (!SelectedArea.isEmpty() && IsSelectedArea(shape)) {
                shape.Show(g);
                shape.group_selected = true;
            }
        }

        // dragged line
        if (tempLine != null) {
            tempLine.Draw(g);
        }

        // show ports
        if (this.SelectedObject != null) {
            SelectedObject.Show(g);
        }

        // group area
        if (!SelectedArea.isEmpty()) {
            int alpha = 85; // 33% transparent
            g.setColor(new Color(37, 148, 216, alpha));
            g.fillRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
            g.setColor(new Color(37, 148, 216));
            g.drawRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
        }
    }
}
