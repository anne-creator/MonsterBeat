package backend;
import java.awt.*;
//import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * Represents an enemy in the game. Each enemy has a visual representation, difficulty level, and health points.
 * Enemies are visualized using images and can lose or gain health during the game.
 */
public class Enemy {

	JLabel labelEnemy;
	Color cEnemyBlue = new Color(0, 230, 255);
	String difficulty;
	int health = 3;
	/**
	 * Constructs an Enemy object with a specified difficulty and image.
	 * @param difficulty The difficulty level of the enemy.
	 * @param num The identifier for selecting the enemy image.
	 * @throws IOException If there is an issue reading the image file.
	 */
	public Enemy(String difficulty, int num) throws IOException {
		if(difficulty.equals("Easy")) {
			if (num == 1) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/resources/m2.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
			else if (num == 2) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/resources/m4.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
		}
		else if(difficulty.equals("Medium")) {
			if (num == 1) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/resources/m1.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
			else if (num == 2) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/resources/m5.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
		}
		if(difficulty.equals("Hard")) {
			if (num == 1) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/resources/m3.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
			else if (num == 2) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/resources/m6.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
		}
	}

	public void gainHealth() {
		health++;
	}
	public void loseHealth() {
		health--;
	}

	public JLabel getLabelEnemy() {
		return labelEnemy;
	}
	public int getHealth() {
		return health;
	}


}
