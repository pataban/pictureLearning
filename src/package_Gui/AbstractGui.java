package package_Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public abstract class AbstractGui {
	protected JFrame frame;

	public AbstractGui() {
		frame = new JFrame();
	}

	public AbstractGui(String name) {
		frame = new JFrame(name);
	}

	public AbstractGui(JFrame frame) {
		this.frame = frame;
	}

	protected File openDir(String s) {
		File f = null;
		try {
			f = new File(s);
			if (!f.isDirectory()) {
				System.out.println("data is not a directory");
				System.exit(-1);
			}
		} catch (Exception e) {
			System.out.println("Error loading data directory");
			System.exit(-1);
		}
		return f;
	}

	// SHORTCUTS _________________________________________________________________

	protected void frameRefresh() {
		frame.validate();
		frame.repaint();
	}

	protected void toFrame(String place, Component a) {
		switch (place) {
		case "c":
			frame.getContentPane().add(BorderLayout.CENTER, a);
			break;
		case "n":
			frame.getContentPane().add(BorderLayout.NORTH, a);
			break;
		case "s":
			frame.getContentPane().add(BorderLayout.SOUTH, a);
			break;
		case "e":
			frame.getContentPane().add(BorderLayout.EAST, a);
			break;
		case "w":
			frame.getContentPane().add(BorderLayout.WEST, a);
			break;
		default:
			System.out.print("sprawdz break\n");
			break;
		}
	}

	protected JButton mkButton(ActionListener al) {
		JButton b = new JButton();
		b.addActionListener(al);
		return b;
	}

	protected JButton mkButton(String s, ActionListener al) {
		JButton b = new JButton(s);
		b.addActionListener(al);
		return b;
	}

	protected JPanel mkPanel(Component a) {
		JPanel pan = new JPanel();
		pan.add(a);
		return pan;
	}

	protected Box mkXBox() {
		return new Box(BoxLayout.X_AXIS);
	}

	protected Box mkXBox(Component a, Component b) {
		Box xBox = new Box(BoxLayout.X_AXIS);
		xBox.add(a);
		xBox.add(b);
		return xBox;
	}

	protected Box mkXBox(Component a, Component b, Component c) {
		Box xBox = new Box(BoxLayout.X_AXIS);
		xBox.add(a);
		xBox.add(b);
		xBox.add(c);
		return xBox;
	}

	protected Box mkYBox() {
		return new Box(BoxLayout.Y_AXIS);
	}

	protected Box mkYBox(Component a, Component b) {
		Box yBox = new Box(BoxLayout.Y_AXIS);
		yBox.add(a);
		yBox.add(b);
		return yBox;
	}

	protected Box mkYBox(Component a, Component b, Component c) {
		Box yBox = new Box(BoxLayout.Y_AXIS);
		yBox.add(a);
		yBox.add(b);
		yBox.add(c);
		return yBox;
	}

	protected JScrollPane mkScroller(Component a) {
		JScrollPane sc = new JScrollPane(a);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		return sc;
	}

}
