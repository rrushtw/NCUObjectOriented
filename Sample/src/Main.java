

import java.awt.BorderLayout;
import javax.swing.JFrame;

import UserInterface.Canvas;
import UserInterface.Menu;
import UserInterface.Tool;

public class Main{
    public static void main(String[] args) {
        Tool tool = new Tool();
        Menu menu = new Menu();
        Canvas canvas = Canvas.GetInstance();

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(menu, BorderLayout.NORTH);
        frame.getContentPane().add(tool, BorderLayout.WEST);
        frame.getContentPane().add(canvas, BorderLayout.CENTER);

        frame.setTitle("UMLEditor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
