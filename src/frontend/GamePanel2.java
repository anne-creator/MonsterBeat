package frontend;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import backend.Game;
import backend.Question;


public class GamePanel2 extends JPanel {
    private Timer timer;
    private int timeLeft = 60; // 60 seconds
    private JLabel timeLabel;
    Game game;
    Question question;
    MainApplication frame;
    String monsterImg;
    int difficultyLevel, animationTracker = 0;
    String userEmail;

    // all Label and panel needs to be updated
    JLabel questionLabel;
    JButton[] buttonList = {new JButton(""), new JButton(""), new JButton(""), new JButton("")};
//    BufferedImage characterHeartImg, monsterHeartImg, characterOrg;
    JLabel monsterLabel, monsterHeartPanel, characterHeartPanel;
    int answerKey;
    Color cBlueBG = new Color(96, 96, 199);
    

    JLayeredPane layeredPane;
    
    public GamePanel2(MainApplication frame, String userEmail, int difficultyLevel) throws IOException {
    	this.difficultyLevel = difficultyLevel;
    	this.userEmail = userEmail;
    	
    	// configure this class which extends jpanel
        setBounds(0, 0, 1280, 770);
		setVisible(true);
		setLayout(null);
    	setBackground(Color.red);
    	
    	// initialise layered pane
        layeredPane = new JLayeredPane();
	    layeredPane.setBounds(0, 0, 1280, 770);
	    layeredPane.setVisible(true);
	    layeredPane.setBackground(Color.GREEN);
	    add(layeredPane);
    	
	    // initialise background
        BufferedImage spriteBackground;
        if (difficultyLevel == 1) spriteBackground = ImageIO.read(new File("easyBackground.jpg"));				
        else if (difficultyLevel == 2) spriteBackground = ImageIO.read(new File("mediumBackground.jpg"));				
        else spriteBackground = ImageIO.read(new File("hardBackground.jpg"));				
		JLabel labelBackground = new JLabel(new ImageIcon(spriteBackground));			// JLabel to hold image
		labelBackground.setBounds(0, 0, 1280, 770);		// (position, size)					// set bounds
		layeredPane.add(labelBackground, 10);
	    
		JButton buttonPause = new JButton("Back to Menu"); 									// set button
		buttonPause.setBounds(10, 10, 130, 40);	
		buttonPause.addActionListener(e -> {
            try {
				frame.switchToMenuPanel(userEmail);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		layeredPane.add(buttonPause, 0);
		
	    /////////////////////////////////////////////////////////
    	
    	this.game = new Game(userEmail, difficultyLevel);
        this.frame = frame;
        Question question = game.generateQuestion();
        this.answerKey = question.answerKey;
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Time and Level Panel
        JPanel timeLevelPanel = new JPanel();
        timeLevelPanel.setBounds(461, 230, 360, 70);
        timeLevelPanel.setVisible(true);
        timeLevelPanel.setLayout(null);
        timeLevelPanel.setBackground(Color.white);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		timeLevelPanel.setBorder(blackline);
		layeredPane.add(timeLevelPanel, 0);
        
        // Timer Label
        timeLabel = new JLabel("Time left: 60");
        Font labelFont = timeLabel.getFont();
        timeLabel.setFont(new Font(labelFont.getName(), labelFont.getStyle(), 48));
        timeLabel.setBounds(30, 0, 400, 70);
        timeLevelPanel.add(timeLabel);

        JLabel level = new JLabel("Level " + difficultyLevel);
        level.setHorizontalAlignment(JLabel.RIGHT);
        timeLevelPanel.add(level);
		

        // Question Panel ///////////////////////
        JPanel questionPanel = new JPanel();
		questionPanel.setBounds(151, 10, 980, 200);
		questionPanel.setVisible(true);
		questionPanel.setLayout(null);
		questionPanel.setBackground(cBlueBG);
				
		blackline = BorderFactory.createLineBorder(Color.black);
		questionPanel.setBorder(blackline);
		layeredPane.add(questionPanel, 0);
        
		questionLabel = new JLabel(question.questionString);			// add JLabel of question to the questionPanel
		questionLabel.setBounds(30, 30, 920, 100);		
		questionLabel.setFont(new Font(labelFont.getName(), labelFont.getStyle(), 24));
		questionPanel.add(questionLabel);	
		
		int xpos = 20;
        for (int i = 0; i < 4; i++) {
            // set bounds and assign question option to the button
        	buttonList[i].setBounds(xpos, 150, 220, 40);
			xpos += 240;
            buttonList[i].setText(question.optionList[i]);
            questionPanel.add(buttonList[i]);

            // when clicked
            int finalI = i;
            buttonList[i].addActionListener(e -> {
                if (finalI == answerKey - 1) {
                    try {
                        System.out.println("answer right");
                        handleStatus(game.answerRight());
                    } catch (IOException ex) {
                        throw new RuntimeException("error at game.answerRight method");
                    }
                } else {
                    try {
                        System.out.println("answer wrong");
                        handleStatus(game.answerWrong());
                    } catch (IOException ex) {
                        throw new RuntimeException("error at game.answerWrong method");
                    }
                }
            });
        }
        
        // Load images for Player and Enemy
        BufferedImage spritePlayer;
        if (difficultyLevel == 1) spritePlayer = ImageIO.read(new File("c1.png"));								
        else if (difficultyLevel == 2) spritePlayer = ImageIO.read(new File("c2.png"));	
        else spritePlayer = ImageIO.read(new File("c3.png"));
        spritePlayer = resizeImage(spritePlayer, 250, 340);
        JLabel labelSpritePlayer = new JLabel(new ImageIcon(spritePlayer));
        labelSpritePlayer.setBounds(40, 390, 250, 340);
		labelSpritePlayer.setVisible(true);
		layeredPane.add(labelSpritePlayer, 0);
        
		BufferedImage spriteEnemy;
        if (difficultyLevel == 1) spriteEnemy = ImageIO.read(new File("m2.png"));								
        else if (difficultyLevel == 2) spriteEnemy = ImageIO.read(new File("m1.png"));	
        else spriteEnemy = ImageIO.read(new File("m3.png"));	
        spriteEnemy = resizeImage(spriteEnemy, 300, 340);
        monsterLabel = new JLabel(new ImageIcon(spriteEnemy));
        monsterLabel.setBounds(890, 340, 300, 340);
        monsterLabel.setVisible(true);
		layeredPane.add(monsterLabel, 0);
		
		// create heart labels
		BufferedImage characterHeartImg = ImageIO.read(new File("heart3.png"));
        characterHeartPanel = new JLabel(new ImageIcon(characterHeartImg));
        characterHeartPanel.setBounds(174, 250, 113, 32);
        characterHeartPanel.setVisible(true);
        layeredPane.add(characterHeartPanel, 0);
        
        BufferedImage monsterHeartImg = ImageIO.read(new File("heart3.png"));
        monsterHeartPanel = new JLabel(new ImageIcon(monsterHeartImg));
        monsterHeartPanel.setBounds(994, 250, 113, 32);
        monsterHeartPanel.setVisible(true);
        layeredPane.add(monsterHeartPanel, 0);

        // Timer config
        timer = new Timer(1000, e -> {
            timeLeft--;
            timeLabel.setText("Time left: " + timeLeft);
            if (timeLeft <= 0) {
                timer.stop();
                try {
                    frame.switchToResultPanel(game, timeLeft, userEmail); // game ends when run out of time
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        timer.start(); // Start the countdown
        
        //////////////////////////////////////////////////////////
        
        // Timer config for animation
        Timer timer2 = new Timer(500, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		animate(labelSpritePlayer, monsterLabel);
        		animationTracker = (animationTracker + 1) % 4;
        	}
        });
        timer2.start(); // Start the countdown
        
    }

    // handle button click
    public void handleStatus(String status) throws IOException {
        System.out.println(status);
        if (status.equals("game end")) {
            frame.switchToResultPanel(game, timeLeft, userEmail);
        } else if (status.equals("c1- change question")) { // character lost a heart
            question = game.generateQuestion();
            updateGamePanel(question, false, "character");
        } else if (status.equals("m1- change question")) { // monster1 lost a heart
            question = game.generateQuestion();
            updateGamePanel(question, false, "monster1");
        } else if (status.equals("m2- change question")) {
            question = game.generateQuestion();
            updateGamePanel(question, false, "monster2");
        } else if (status.equals("change monster and question")) {
            question = game.generateQuestion();
            updateGamePanel(question, true, "monster");
        }
        System.out.println("monster1 life: " + game.monster1.getLivesLeft() + " mosnter2 life: " + game.monster2.getLivesLeft() + " character life: " + game.character.getLivesLeft());
    }

    // repaint panel
    public void updateGamePanel(Question question, Boolean changeMonster, String heartChangingPlayer) throws IOException {
        this.answerKey = question.answerKey;
        System.out.println("questionstring: " + question.questionString + "  answerKey: " + question.answerKey);
        // Update the question
        questionLabel.setText(question.questionString);
        for (int i = 0; i < 4; i++) {
            buttonList[i].setText(question.optionList[i]);
        }

        // update the monster and refill monster's heart
        if (changeMonster) {
            BufferedImage monsterOrg;
            if (difficultyLevel == 1) monsterOrg = ImageIO.read(new File("m4.png"));
            else if (difficultyLevel == 2) monsterOrg = ImageIO.read(new File("m5.png"));
            else monsterOrg = ImageIO.read(new File("m6.png"));
            
            BufferedImage monsterImg = resizeImage(monsterOrg, 300, 340);
            monsterLabel.setIcon(new ImageIcon(monsterImg));

            // monsterHeartPanel
            BufferedImage monsterHeartImg = ImageIO.read(new File("heart3.png"));
            monsterHeartPanel.setIcon(new ImageIcon(monsterHeartImg));
            this.repaint();
            return;
        } else {
            if (heartChangingPlayer.equals("monster1")) {
                int heartNumber = game.monster1.getLivesLeft();
                String address = "heart" + heartNumber + ".png";
                BufferedImage monsterHeartImg = ImageIO.read(new File(address));
                monsterHeartPanel.setIcon(new ImageIcon(monsterHeartImg));
            } else if (heartChangingPlayer.equals("monster2")) {
                int heartNumber = game.monster2.getLivesLeft();
                String address = "heart" + heartNumber + ".png";
                BufferedImage monsterHeartImg = ImageIO.read(new File(address));
                monsterHeartPanel.setIcon(new ImageIcon(monsterHeartImg));
            } else if (heartChangingPlayer.equals("character")) { // change character heart
                System.out.println("character heart change");
                int heartNumber = game.character.getLivesLeft();
                String address = "heart" + heartNumber + ".png";
                BufferedImage characterHeartImg = ImageIO.read(new File(address));
                characterHeartPanel.setIcon(new ImageIcon(characterHeartImg));
            }
        }

      this.repaint();
    }
    
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
    

    /*/ used for generate both character and monster
    private JPanel createPlayerPanel(String labelText) throws IOException {
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));

        // heart panel
        BufferedImage heartImg = ImageIO.read(new File("heart2.png"));
        JLabel heartPanel = new JLabel(new ImageIcon(heartImg));
        heartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //monster / character panel
        BufferedImage org = ImageIO.read(new File(labelText));
        BufferedImage characterImg = resizeImage(org, 200, 260);
        JLabel characterLabel = new JLabel(new ImageIcon(characterImg));
        characterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        characterPanel.add(heartPanel);
        characterPanel.add(Box.createRigidArea(new Dimension(200, 30)));
        characterPanel.add(characterLabel);

        return characterPanel;
    } */
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(resultingImage, 0, 0, null);
        graphics2D.dispose();

        return outputImage;
    }
}
