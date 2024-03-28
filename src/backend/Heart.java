package backend;
import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
import javax.swing.*;

public class Heart {
	JPanel tempHeart;

	public Heart(int xpos, int ypos, int size) {
		tempHeart = new JPanel();
		tempHeart.setBounds(xpos, ypos, size, size);
		tempHeart.setBackground(Color.red);
		tempHeart.setVisible(true);
	}

	public void loseHeart() {
		tempHeart.setBackground(Color.black);
	}

	public void gainHeart() {
		tempHeart.setBackground(Color.red);
	}

	public JPanel getHeart() {
		return tempHeart;
	}


}
