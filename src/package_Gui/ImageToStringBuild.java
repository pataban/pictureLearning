package package_Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import package_Data.ImgData;
import package_Data.ImgPanel;
import package_Data.Pimgs;

//@SuppressWarnings({ "unused" })
public class ImageToStringBuild extends AbstractGui {
	private final static int FRAME_SIZ_W = 200;
	private final static int FRAME_SIZ_H = 250;
//	private final static int SINGLE_IMAGE_SIZ_W = 500;
//	private final static int SINGLE_IMAGE_SIZ_H = 500;

	private Gui gui;
	private ImgData data;
	private Pimgs cur;
	private LinkedList<Pimgs> mistakes;

	private JButton menuButton, nextButton, skipButton;
	private JTextField answerField;
	private JLabel correctAnswerLabel;
	private ImgPanel imgPanel;

	public ImageToStringBuild(Gui gui, JFrame frame) {
		super(frame);
		this.gui = gui;
	}

	public void build(String dirName) {
		data = new ImgData(dirName);
		mistakes = new LinkedList<Pimgs>();
		cur = data.next();
		frame.getContentPane().removeAll();

		menuButton = mkButton("Menu", new MenuButtonListener());
		nextButton = mkButton("Next", new NextButtonListener());
		skipButton = mkButton("Skip", new SkipButtonListener());
		answerField = new JTextField();
		answerField.setMaximumSize(new Dimension(500, 100));
		answerField.addKeyListener(new ButtonImageToStringKeyListener());
		correctAnswerLabel = new JLabel();
		imgPanel = new ImgPanel(cur.getA());

		Box mainBox = mkYBox();
		mainBox.add(imgPanel);
		mainBox.add(correctAnswerLabel);
		mainBox.add(answerField);
		mainBox.add(mkXBox(nextButton, skipButton, menuButton));

		toFrame("c", mainBox);
		frame.setBounds(300, 200, FRAME_SIZ_W, FRAME_SIZ_H);
		frame.setVisible(true);
		answerField.requestFocus();
	}

	// LISTENERS ______________________________________________________________

	private class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!answerField.getText().equals(cur.getB())) {
				correctAnswerLabel.setText(cur.getB());
				mistakes.add(cur);
			} else
				skipButton.doClick();
			answerField.setText("");
			frameRefresh();
			answerField.requestFocus();
		}

	}

	private class SkipButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			correctAnswerLabel.setText("");
			if ((data.isDone()) && (!mistakes.isEmpty()))
				cur = mistakes.removeFirst();
			else
				cur = data.next();
			imgPanel.setImage(cur.getA());
			answerField.setText("");
			frameRefresh();
			answerField.requestFocus();
		}

	}

	private class MenuButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.buildMenu();
		}

	}

	private class ButtonImageToStringKeyListener implements KeyListener {
		boolean isCtrl = false;

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				if (isCtrl)
					skipButton.doClick();
				else
					nextButton.doClick();
				break;
			case KeyEvent.VK_ESCAPE:
				menuButton.doClick();
				break;
			case KeyEvent.VK_CONTROL:
				isCtrl = true;
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_CONTROL)
				isCtrl = false;
		}

	}

}
