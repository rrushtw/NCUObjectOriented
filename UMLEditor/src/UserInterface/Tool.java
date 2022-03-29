package UserInterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import MouseMode.CreateLine;
import MouseMode.CreateObject;
import MouseMode.Mode;
import MouseMode.Select;

public class Tool extends JToolBar{
	private int ToolNum = 6;
	private JButton holdBtn = null;
	private Canvas canvas;

	public Tool() {
		canvas = Canvas.GetInstance();   // Canvas is singleton 
		setLayout(new GridLayout(ToolNum, 1, 2, 2));
		setBackground(Color.WHITE);
		setFloatable(false);
		
		ImageIcon selectIcon = new ImageIcon("img/select.png");
		ToolBtn selectBtn = new ToolBtn("select", selectIcon, new Select());
		add(selectBtn);
		
		ImageIcon associateIcon = new ImageIcon("img/associate.png");
		ToolBtn associateBtn = new ToolBtn("associate", associateIcon, new CreateLine("associate"));
		add(associateBtn);
		
		ImageIcon generalIcon = new ImageIcon("img/general.png");
		ToolBtn generalBtn = new ToolBtn("general", generalIcon, new CreateLine("general"));
		add(generalBtn);
		
		ImageIcon compositeIcon = new ImageIcon("img/composite.png");
		ToolBtn compositeBtn = new ToolBtn("composite", compositeIcon, new CreateLine("composite"));
		add(compositeBtn);
		
		ImageIcon classIcon = new ImageIcon("img/class.png");
		ToolBtn classBtn = new ToolBtn("class", classIcon, new CreateObject("class"));
		add(classBtn);
		
		ImageIcon usecaseIcon = new ImageIcon("img/usecase.png");
		ToolBtn usecaseBtn = new ToolBtn("usecase", usecaseIcon, new CreateObject("usecase"));
		add(usecaseBtn);

	}
	private class ToolBtn extends JButton{
		Mode ToolMode;
		public ToolBtn(String ToolName, ImageIcon icon, Mode ToolMode) {
			this.ToolMode = ToolMode;
			setToolTipText(ToolName);
			setIcon(icon);
			setFocusable(false);
			setBackground(Color.WHITE);
			setBorderPainted(false);
			setRolloverEnabled(true);
			addActionListener(new toolListener());
		}
		class toolListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(holdBtn != null)
					holdBtn.setBackground(Color.WHITE);
				holdBtn = (JButton) e.getSource();
				holdBtn.setBackground(Color.GRAY);
				canvas.currentMode = ToolMode;
				canvas.SetMode();
				canvas.Reset();
				canvas.repaint();
			}
		}
	}
}
