package package_Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

//@SuppressWarnings({ "unused" })
public class Gui extends AbstractGui {
	private final static String FRAME_NAME = "Picture learning";
	private final static int FRAME_SIZ_W = 200;
	private final static int FRAME_SIZ_H = 250;

	private ImageToStringBuild imageToString;
	private StringToImageBuild stringToImage;

	private String[] dataDirList;
	private String dataDirName;

	private JButton startButton;
	private JLabel chooseDirLabel, chooseTypeLabel;
	private JList<String> dirList;
	private JCheckBox ImageToStringChkBox, StringToImageChkBox;

	public Gui(String dataDirName) {
		this.dataDirName = dataDirName;
		dataDirList = openDir(dataDirName).list();

		frame = new JFrame(FRAME_NAME);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		startButton = mkButton("Start", new StartButtonListener());
		startButton.addKeyListener(new BuildKeyListener());
		chooseDirLabel = new JLabel("Choose Directory:");
		chooseTypeLabel = new JLabel("Choose type:");
		dirList = new JList<String>(dataDirList);
		dirList.setSelectedIndex(0);
		dirList.addKeyListener(new BuildKeyListener());
		ImageToStringChkBox = new JCheckBox("Image To String");
		ImageToStringChkBox.setSelected(true);
		ImageToStringChkBox.addActionListener(new ChkBoxListener());
		ImageToStringChkBox.addKeyListener(new ChkBoxKeyListener(ImageToStringChkBox));
		StringToImageChkBox = new JCheckBox("String TO Image");
		StringToImageChkBox.setSelected(false);
		StringToImageChkBox.addActionListener(new ChkBoxListener());
		StringToImageChkBox.addKeyListener(new ChkBoxKeyListener(StringToImageChkBox));
		
		imageToString = new ImageToStringBuild(this, frame);
		stringToImage = new StringToImageBuild(this, frame);
		buildMenu();
	}

	public void buildMenu() {
		frame.getContentPane().removeAll();

		Box dirBox = mkYBox(chooseDirLabel, dirList);
		Box typeBox = mkYBox(chooseTypeLabel, ImageToStringChkBox, StringToImageChkBox);
		Box mainBox = mkYBox(dirBox, typeBox, startButton);

		toFrame("c", mainBox);
		frame.setBounds(300, 200, FRAME_SIZ_W, FRAME_SIZ_H);
		frame.pack();
		frame.setVisible(true);
		startButton.requestFocus();
	}

	// LISTENERS ______________________________________________________________

	private class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (ImageToStringChkBox.isSelected())
				imageToString.build(dataDirName + "/" + dirList.getSelectedValue());
			else
				stringToImage.build();
		}

	}

	private class ChkBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() != ImageToStringChkBox)
				ImageToStringChkBox.setSelected(!ImageToStringChkBox.isSelected());
			else
				StringToImageChkBox.setSelected(!StringToImageChkBox.isSelected());
		}

	}

	private class BuildKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				startButton.doClick();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

	private class ChkBoxKeyListener implements KeyListener {
		JCheckBox chkb;
		
		public ChkBoxKeyListener(JCheckBox chkb) {
			this.chkb = chkb;
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				chkb.doClick();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}
	
}
