import java.awt.*;
//import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Player {

	JLabel labelPlayer;
	Color cEnemyBlue = new Color(0, 230, 255);
	String difficulty;
	int health = 3;
	
	public Player(int model) throws IOException {
		if(model != 0) {
			
			// creates the label
			BufferedImage spritePlayer;										// game easy background
			spritePlayer = ImageIO.read(new File("playerTester.png"));	// read in image
			labelPlayer = new JLabel(new ImageIcon(spritePlayer));		// JLabel to hold image
			//labelEnemy.setBounds(905, 350, 250, 350);	// (position, size)		
			
		}
	}
	
	public void gainHealth() {
		health++;
	}
	public void loseHealth() {
		health--;
	}
	
	public JLabel getLabelPlayer() {
		return labelPlayer;
	}
	public int getHealth() {
		return health;
	}
	
	
}
