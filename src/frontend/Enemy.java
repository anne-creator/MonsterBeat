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
	
	public Enemy(String difficulty) throws IOException {
		if(difficulty.equals("Easy")) {
			
			// creates the label
			BufferedImage spriteEasyEnemy;										// game easy background
			spriteEasyEnemy = ImageIO.read(new File("spriteEasyEnemy.png"));	// read in image
			labelEnemy = new JLabel(new ImageIcon(spriteEasyEnemy));		// JLabel to hold image
			//labelEnemy.setBounds(905, 350, 250, 350);	// (position, size)		
			
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
