import java.awt.*;
//import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Enemy {

	JLabel labelEnemy;
	Color cEnemyBlue = new Color(0, 230, 255);
	String difficulty;
	int health = 3;
	
	public Enemy(String difficulty, int num) throws IOException {
		if(difficulty.equals("Easy")) {
			if (num == 1) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/img/monsters/m2.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
			else if (num == 2) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/img/monsters/m4.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
		}
		else if(difficulty.equals("Medium")) {
			if (num == 1) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/img/monsters/m1.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
			else if (num == 2) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/img/monsters/m5.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
		}
		if(difficulty.equals("Hard")) {
			if (num == 1) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/img/monsters/m3.png"));	// read in image
				labelEnemy = new JLabel(new ImageIcon(spriteEnemy));		// JLabel to hold image
			}
			else if (num == 2) {
				BufferedImage spriteEnemy;										
				spriteEnemy = ImageIO.read(new File("src/img/monsters/m6.png"));	// read in image
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
