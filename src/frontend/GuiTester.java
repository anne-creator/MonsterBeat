package frontend;//change something only for testing the new branch pull

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GuiTester implements ActionListener{

	// DEFINITIONS
	int NUMPANELS = 2;
	int panelLogIn = 0;
	int panelMainScreen = 1;

	// COLOURS
	Color cGrey = new Color (183, 183, 183);
	Color cRed = Color.red;

	// instance variables
	JFrame frame;
	JPanel[] panels = new JPanel[NUMPANELS];
	JButton buttonLogIn, buttonMainScreen;

	public GuiTester() {
		frame = new JFrame("Insert Game Title");
	}



	public void initialiseFrame() {
		//frame = new JFrame("String title Game Title");		// constructor automatically initialises JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// exit game process
		frame.setResizable(false);								// resize window
		frame.setSize(1280, 720);								// set size(width, height)
		frame.setVisible(true); 								// make visible
	}

	public void initialisePanels() throws IOException {
		try {
			for (int i = 0; i < NUMPANELS; i++) {
				panels[i] = new JPanel();
				//panels[panelLogIn].setBackground(cGray);
				panels[i].setBounds(0, 0, 1280, 720);
				panels[i].setVisible(false);
				panels[i].setLayout(null);

				if (i == panelLogIn) { // (0) panelLogIn
					panels[panelLogIn].setBackground(cGrey);

					buttonLogIn = new JButton("Log In");
					buttonLogIn.setBounds(480, 504, 320, 72);
					buttonLogIn.addActionListener(this);

					panels[panelLogIn].add(buttonLogIn);
					frame.add(panels[panelLogIn]);

					BufferedImage myPicture = ImageIO.read(getClass().getResource("img/spriteGameTitle.jpg"));

					JLabel picLabel = new JLabel(new ImageIcon(myPicture));
					picLabel.setBounds(320, 72, 640, 360);
					panels[panelLogIn].add(picLabel);
				}
				else if (i == panelMainScreen) { // (1) panelMainScreen
					panels[panelMainScreen].setBackground(cRed);

					buttonMainScreen = new JButton("Go Back");
					buttonMainScreen.setBounds(480, 504, 320, 72);
					buttonMainScreen.addActionListener(this);

					panels[panelMainScreen].add(buttonMainScreen);
					frame.add(panels[panelMainScreen]);
				}
			} // for loop loading panels end

			panels[0].setVisible(true);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	} // initialisePanels() END

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buttonLogIn) {
			System.out.println("BUTTON PRESSED");
			panels[panelLogIn].setVisible(false);
			panels[panelMainScreen].setVisible(true);
		}
		if(e.getSource() == buttonMainScreen) {
			System.out.println("BUTTON PRESSED");
			panels[panelLogIn].setVisible(true);
			panels[panelMainScreen].setVisible(false);
		}
	} // action performed END

	public static void main(String[] args) {
		GuiTester gui = new GuiTester();
		gui.initialiseFrame();

		try {
			gui.initialisePanels();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	} // main() END

} // class END
