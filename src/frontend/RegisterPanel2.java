package frontend;

import backend.Accounts;
import backend.User;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegisterPanel2 extends JPanel {
	
	public JLayeredPane layeredPane;
    private int animationTracker = 0;
	
    public RegisterPanel2(MainApplication frame) throws IOException {
    	JTextField textField2Email;
    	JButton button2CreateAccount, button2ReturnToLogin, button2Exit;
    	
    	// configure this class which extends jpanel
        setBounds(0, 0, 1280, 770);
		setVisible(true);
		setLayout(null);
		setBackground(Color.red);        
        
        // since this is a type of panel,
        // initialise the layered pane and add to this
        layeredPane = new JLayeredPane();
	    layeredPane.setBounds(0, 0, 1280, 770);
	    layeredPane.setVisible(true);
	    layeredPane.setBackground(Color.GREEN);
	    add(layeredPane);
        
	    // initialise background
        BufferedImage spriteEasyBackground;										
		spriteEasyBackground = ImageIO.read(new File("easyBackground.jpg"));				// read in image
		JLabel labelBackground = new JLabel(new ImageIcon(spriteEasyBackground));			// JLabel to hold image
		labelBackground.setBounds(0, 0, 1280, 770);		// (position, size)					// set bounds
		layeredPane.add(labelBackground, 10);	
        
        // initialise title image
		BufferedImage spriteGameTitle;										
		spriteGameTitle = ImageIO.read(new File("imageGameTitle.png"));						// read in image
		JLabel labelGameTitle = new JLabel(new ImageIcon(spriteGameTitle));					// JLabel to hold image
		labelGameTitle.setBounds(241, 20, 800, 400);	// (position, size)					// set bounds
		layeredPane.add(labelGameTitle, 0);	
		
		// initialise player image
		BufferedImage spritePlayer;										
		spritePlayer = ImageIO.read(new File("c1.png"));									// read in image
		JLabel labelPlayer = new JLabel(new ImageIcon(spritePlayer));						// JLabel to hold image
		labelPlayer.setBounds(10, 290, 364, 415);	// (position, size)						// set bounds
		layeredPane.add(labelPlayer, 0);			
		
		// initialise enemy image
		BufferedImage spriteEnemy;										
		spriteEnemy = ImageIO.read(new File("m1.png"));										// read in image
		JLabel labelEnemy = new JLabel(new ImageIcon(spriteEnemy));							// JLabel to hold image
		labelEnemy.setBounds(850, 240, 350, 411);	// (position, size)						// set bounds
		layeredPane.add(labelEnemy, 0);	
				
		//////////////////////////////////////////////////////////
    	
    	
		// user email label
        JLabel labelEmail = new JLabel("Email:");											// set text labels
        labelEmail.setBounds(546, 456, 100, 20);
        layeredPane.add(labelEmail, 0);

        // email input field
        textField2Email = new JTextField(20);											// set input text fields
        textField2Email.setBounds(541, 476, 200, 50);
        layeredPane.add(textField2Email, 0);

        // log in button
        button2CreateAccount = new JButton("Create Account"); 												// Log In Button					
        button2CreateAccount.setBounds(541, 531, 200, 50);
        layeredPane.add(button2CreateAccount, 0);
        button2CreateAccount.addActionListener(e -> {      					 						// Action Listener for the button
        	String userEmail = textField2Email.getText();
            try {
            	if (Accounts.exist(userEmail)) {
            		frame.switchToLogInPanel(userEmail);
            	}
            	else {
            		Accounts.create(new User(userEmail));
            		Accounts.logIn(userEmail);
            		frame.switchToMenuPanel(userEmail);
            	}
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Button return to login
        button2ReturnToLogin = new JButton("Return to Login");
        button2ReturnToLogin.setBounds(541, 586, 200, 50);
        layeredPane.add(button2ReturnToLogin, 0);
        button2ReturnToLogin.addActionListener(e -> {
            try {
                frame.switchToLogInPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // exit button
        button2Exit = new JButton("Exit");
        button2Exit.setBounds(541, 641, 200, 50);
        layeredPane.add(button2Exit, 0);
        button2Exit.addActionListener(e -> {      		// Action Listener for the button
            System.exit(0);
        });
        
        //////////////////////////////////////////////////////////
        
        // Timer config for animation
        Timer timer = new Timer(500, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		animate(labelPlayer, labelEnemy);
        		animationTracker = (animationTracker + 1) % 4;
        	}
        });
        timer.start(); // Start the countdown
        
    } // constructor end
    
    private void animate(JLabel labelPlayer, JLabel labelEnemy) {		// sequences the title screen animation
    	if (animationTracker == 0) {
    		moveNE(labelPlayer);
    		moveSW(labelEnemy);
    	}
    	else if (animationTracker == 1) {
    		moveSE(labelPlayer);
    		moveNE(labelEnemy);
    	}
    	else if (animationTracker == 2) {
    		moveNW(labelPlayer);
    		moveSE(labelEnemy);
    	}
    	else {
    		moveSW(labelPlayer);
    		moveNW(labelEnemy);
    	}
    }
    
    private void moveNE(JLabel image) {									// moves JLabel 50 px in NE 
    	int newX = getX(image);
    	int newY = getY(image);
    	
    	newX += 50;
    	newY -= 50;
    	
    	image.setLocation(newX, newY);
    }
    private void moveSE(JLabel image) {									// moves JLabel 50 px in SE 
    	int newX = getX(image);
    	int newY = getY(image);
    	
    	newX += 50;
    	newY += 50;
    	
    	image.setLocation(newX, newY);
    }
    private void moveSW(JLabel image) {									// moves JLabel 50 px in SW 
    	int newX = getX(image);
    	int newY = getY(image);
    	
    	newX -= 50;
    	newY += 50;
    	
    	image.setLocation(newX, newY);
    }
    private void moveNW(JLabel image) {									// moves JLabel 50 px in NW 
    	int newX = getX(image);
    	int newY = getY(image);
    	
    	newX -= 50;
    	newY -= 50;
    	
    	image.setLocation(newX, newY);
    }
    
    private int getX(JLabel image) {									// get x pos of JLabel 
    	Point location = image.getLocation();
    	return location.x;
    }
    private int getY(JLabel image) {									// get y pos of JLabel 
    	Point location = image.getLocation();
    	return location.y;
    }

} // class end

