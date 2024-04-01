package backend;
import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * Represents a heart in the game, used for visualizing the health of characters or monsters.
 * Each heart can change its color to indicate gained or lost health.
 */
public class Heart {
	JPanel tempHeart;
	/**
	 * Constructs a heart and initializes its visual representation.
	 *
	 * @param xpos The x-position of the heart on the screen.
	 * @param ypos The y-position of the heart on the screen.
	 * @param size The size of the heart.
	 */
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
