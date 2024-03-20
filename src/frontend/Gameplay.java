package frontend;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Gameplay {

	JFrame frame;
	JPanel panel;

	public Gameplay(String difficulty) {
		frame = new JFrame("Monster Beat Gameplay " + difficulty);
		initialisePanel();
	}

	private void initialisePanel() {
		panel = new JPanel();
		panel.setBounds(0, 0, 1280, 770);
		panel.setVisible(false);
		panel.setLayout(null);
	}




	public JPanel getPanel() {
		return panel;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
