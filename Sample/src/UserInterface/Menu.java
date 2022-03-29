package UserInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JMenuBar {
    private Canvas canvas;

    public Menu() {
        canvas = Canvas.GetInstance(); // Canvas is singleton

        JMenu menu;
        JMenuItem mi;

        /* --- File menu --- */
        menu = new JMenu("File");
        add(menu);

        /* --- Edit menu --- */
        menu = new JMenu("Edit");
        add(menu);

        mi = new JMenuItem("Change object name");
        menu.add(mi);
        mi.addActionListener(new ChangeNameListener());

        mi = new JMenuItem("Group");
        menu.add(mi);
        mi.addActionListener(new GroupObjectListener());

        mi = new JMenuItem("Ungroup");
        menu.add(mi);
        mi.addActionListener(new UngroupObjectListener());
    }

    private void UpdateNameForm() {
        JFrame textFrame = new JFrame("Change Name");
        textFrame.setSize(300, 90);
        textFrame.getContentPane().setLayout(new GridLayout(0, 1));

        JPanel panel = null;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JTextField test = new JTextField("New Object");
        panel.add(test);
        textFrame.getContentPane().add(panel);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton confirm = new JButton("OK");
        panel.add(confirm);

        JButton cancel = new JButton("Cancel");
        panel.add(cancel);

        textFrame.getContentPane().add(panel);

        textFrame.setLocationRelativeTo(null);
        textFrame.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.UpdateObjectName(test.getText());
                textFrame.dispose();

            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFrame.dispose();
            }
        });

    }

    private class UngroupObjectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            canvas.RemoveGroup();
        }
    }

    private class GroupObjectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            canvas.GroupShape();
        }
    }

    private class ChangeNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            UpdateNameForm();
        }
    }
}
