import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Gameplay implements ActionListener {
	
	private JFrame guiFrame; 					// only used to add gameplayPanel to the GUI
	private JPanel panel, panelMain, panelPostWin, panelPostLose;
	private JLayeredPane layeredPane;			// higher depth values put it "into" the screen, under other elements
	private String difficulty, summary = "", activeEmail;
	
	// Player, Enemy, and Heart objects.
	private Player player;
	private JLabel spritePlayer;
	private Enemy enemy;
	private JLabel spriteEnemy;
	private Heart playerHeart1, playerHeart2, playerHeart3, enemyHeart1, enemyHeart2, enemyHeart3;
	private JPanel[] playerHearts, enemyHearts;

	// Elements for asking a question.
	private JPanel questionPanel;
	private JLabel labelQuestion;
	
	// All button elements.
	private JButton buttonPause;
	private JButton answerButton1, answerButton2, answerButton3, answerButton4;
	private JButton[] buttons;
	
	// Flags
	private int expectedAnswer, playerAnswer;
	private boolean questionInProgress, result;
	
	private String[] question1 = {"1", "question1: what is the sum of 5 and 10?", "15", "20", "25", "30"}; 		// answer is 1 
	private String[] question2 = {"2", "question2: what is the product of 5 and 6?", "25", "30", "35", "40"};	// answer is 2
	private String[] question3 = {"3", "question3: what is the capital of Canada?", "toronto", "paris", "ottawa", "vancouver"};
	private String[] questionBuffer = {"4", "question4: what is the tallest mountain in the world?", "K2", "Lhotse", "makalu", "everest"};
	//private String[] buffer = {"","","","","",""};
	
	private String[][] questions = {question1, question2, question3, questionBuffer};
	private int questionCounter = 0;
	
	// Timer
	private Timer timer;
	private int timeLeft = 60;
	private JLabel labelTimer;
	
	
	
	public Gameplay(String activeEmail, JFrame frame, JPanel mainScreen, JPanel postWin, JPanel postLose, String difficulty) {
		this.activeEmail = activeEmail;
		//this.activePassword = activePassword;
		guiFrame = frame;
		panelMain = mainScreen;
		panelPostWin = postWin;
		panelPostLose = postLose;
		this.difficulty = difficulty;
		
		try {
			initPanel(); 					// initialise gameplay panel
			initLayeredPane();				// initialise for gameplay elements
			initBackground();				// initialise background image
			initButtonPause();				// initialise pause button
				
			initPlayer();					// player object is always the same
			initEnemy();					// enemy objects depend on difficulty parameter
			initTimer();
			
			//initHearts();					// could split into initHearts for player and enemy
			initPlayerHearts();
			initEnemyHearts();
			
			initQuestion(questionCounter);	// init question elements and start gameplay
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	} // constructor END
	
	private void initPanel() {								// create gameplayPanel and add to frame 
		panel = new JPanel();
		panel.setBounds(0, 0, 1280, 770);
		panel.setVisible(true);
		panel.setLayout(null);
		panel.setBackground(Color.red);
		guiFrame.add(panel);
	}
	private void initLayeredPane() {						// create layeredPane and add to gameplayPanel 
		layeredPane = new JLayeredPane();
	    layeredPane.setBounds(0, 0, 1280, 770);
	    layeredPane.setVisible(true);
	    panel.add(layeredPane);
	}	
	private void initBackground() throws IOException {		// create JLabel to hold background, add to layeredPane at 10 
		BufferedImage spriteEasyBackground;										
		spriteEasyBackground = ImageIO.read(new File("easyBackground.jpg"));		// read in image
		JLabel picLabel = new JLabel(new ImageIcon(spriteEasyBackground));			// JLabel to hold image
		picLabel.setBounds(0, 0, 1280, 770);	// (position, size)					// set bounds
		
		layeredPane.add(picLabel, 10);												// add to layered pane
	}
	private void initButtonPause() {						// create JButton to BACK, add to layeredPane at 0 
		buttonPause = new JButton("TEMP BACK"); 									// set button
		buttonPause.setBounds(0, 0, 124, 40);										
		buttonPause.addActionListener(this);
		layeredPane.add(buttonPause, 0);											// add to layered pane
	}
	
	private void initPlayer() {								// create Class Player on layeredPane at 0 
		try {
			player = new Player(1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		spritePlayer = player.getLabelPlayer();									// create player object and save labelSprite to instance
		spritePlayer.setBounds(83, 340, 250, 350);
		spritePlayer.setVisible(true);
		layeredPane.add(spritePlayer, 0);
	}
	private void initEnemy() {								// create Class Enemy on layeredPane at 0 
		try {
			enemy = new Enemy("Easy");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		spriteEnemy = enemy.getLabelEnemy();
		spriteEnemy.setBounds(905, 340, 250, 350);
		spriteEnemy.setVisible(true);
		layeredPane.add(spriteEnemy, 0);
		
	}
	private void initPlayerHearts() {						// initialise hearts for the player, on layer 0 
		playerHearts = new JPanel[3];
		
		playerHeart1 = new Heart(150, 10, 50);
		playerHearts[0] = playerHeart1.getHeart();
		playerHeart2 = new Heart(210, 10, 50);
		playerHearts[1] = playerHeart2.getHeart();
		playerHeart3 = new Heart(270, 10, 50);
		playerHearts[2] = playerHeart3.getHeart();
		
		for (int i = 0; i < 3; i++) {
			layeredPane.add(playerHearts[i], 0);
		}
	}
	private void initEnemyHearts() {						// initialise hearts for the player, on layer 0 
		enemyHearts = new JPanel[3];
		
		enemyHeart1 = new Heart(950, 10, 50);
		enemyHearts[0] = enemyHeart1.getHeart();
		enemyHeart2 = new Heart(1010, 10, 50);
		enemyHearts[1] = enemyHeart2.getHeart();
		enemyHeart3 = new Heart(1070, 10, 50);
		enemyHearts[2] = enemyHeart3.getHeart();
		
		for (int i = 0; i < 3; i++) {
			layeredPane.add(enemyHearts[i], 0);
		}
	}
	private void initTimer() {								// initialises 60 second timer, if runs out, lose game 
		labelTimer = new JLabel("Time left: " + timeLeft);
		labelTimer.setBounds(1150, 250, 80, 60);
		labelTimer.setVisible(true);
		layeredPane.add(labelTimer, 0);
		
		// Timer config
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                labelTimer.setText("Time left: " + timeLeft);
                if (timeLeft <= 0) {
                    timer.stop();
                    postGameLose();
                }
            }
        });
        timer.start(); // Start the countdown
	}
	
	private void initQuestion(int question) {				// initialises a questionPanel to the layeredPane at 
		
		expectedAnswer = Integer.parseInt(questions[question][0]);
				
		// create questionPanel and add panel to layeredPane
		// while layering is done here, it is only for the panel,
		// so we can set the depth to 0
		questionPanel = new JPanel();
		questionPanel.setBounds(341, 10, 600, 300);
		questionPanel.setVisible(true);
		questionPanel.setLayout(null);
		questionPanel.setBackground(Color.white);
				
		Border blackline = BorderFactory.createLineBorder(Color.black);
		questionPanel.setBorder(blackline);
		layeredPane.add(questionPanel, 0);
				
		///////////////////////////////////////
				
		// add elements to the JPanel
		labelQuestion = new JLabel(questions[question][1]);			// add JLabel of question to the questionPanel
		labelQuestion.setBounds(30, 30, 600, 100);				
		questionPanel.add(labelQuestion);						
				
		buttons = new JButton[4];									// initialise buttons to buttons[]
		buttons[0] = answerButton1;
		buttons[1] = answerButton2;
		buttons[2] = answerButton3;
		buttons[3] = answerButton4;
			
		int xpos = 30;												// add buttons to the questionPanel
		for (int i = 0; i < 4; i++) {
			buttons[i] = new JButton(questions[question][i + 2]);
			buttons[i].setBounds(xpos, 250, 120, 40);
			buttons[i].addActionListener(this);
			questionPanel.add(buttons[i]);
			xpos += 130;
		}
				
	} // initQuestion END
	private void nullQuestion() {							// is called when a single question is answered 
		questionPanel.setVisible(questionInProgress);
		layeredPane.remove(questionPanel);
		questionPanel = null;

		layeredPane.remove(labelQuestion);
		labelQuestion = null;
		
		for (int i = 0; i < 4; i++) {
			layeredPane.remove(buttons[i]);
			buttons[i] = null;
		}
	}
	
	private boolean checkAnswer() {							// used to check if the question is right or wrong 
		if (playerAnswer == expectedAnswer) return true;
		else return false;
	}
	
	private void playerLoseHealth() {						// call when a question is wrong to update health 
		if (player.getHealth() == 3) {
			playerHeart3.loseHeart();
			player.loseHealth();
		}
		else if (player.getHealth() == 2) {
			playerHeart2.loseHeart();
			player.loseHealth();
		}
		else {
			postGameLose();
		}
	}
	private void enemyLoseHealth() {						// call when a question is right to update health 
		if (enemy.getHealth() == 3) {
			enemyHeart3.loseHeart();
			enemy.loseHealth();
		}
		else if (enemy.getHealth() == 2) {
			enemyHeart2.loseHeart();
			enemy.loseHealth();
		}
		else {
			postGameWin();
		}
	}
	
	public void actionPerformed(ActionEvent e) {			// tracks all button actions
		if(e.getSource() == buttonPause) {
			panel.setVisible(false);
			panelMain.setVisible(true);
		}
		
		/////////////////////////////////////////
		
		if(e.getSource() == buttons[0]) {
			playerAnswer = 1;
			result = checkAnswer();
			System.out.println(result);
			questionInProgress = false;
			if (result == true) {
				enemyLoseHealth();
				nullQuestion();
				initQuestion(++questionCounter);
			}
			else playerLoseHealth();
		}
		if(e.getSource() == buttons[1]) {
			playerAnswer = 2;
			result = checkAnswer();
			System.out.println(result);
			questionInProgress = false;
			if (result == true) {
				enemyLoseHealth();
				nullQuestion();
				initQuestion(++questionCounter);
			}
			else playerLoseHealth();
		}
		if(e.getSource() == buttons[2]) {
			playerAnswer = 3;
			result = checkAnswer();
			System.out.println(result);
			questionInProgress = false;
			if (result == true) {
				enemyLoseHealth();
				nullQuestion();
				initQuestion(++questionCounter);
			}
			else playerLoseHealth();
		}
		if(e.getSource() == buttons[3]) {
			playerAnswer = 4;
			result = checkAnswer();
			System.out.println(result);
			questionInProgress = false;
			if (result == true) {
				enemyLoseHealth();
				nullQuestion();
				initQuestion(++questionCounter);
			}
			else playerLoseHealth();
		}
		
		////////////////////////////////////////
		
	}
	
	public void postGameWin() {								// change panels when game is won 
		timer.stop();
		createSummary();
		System.out.println(summary); // TESTING
		panel.setVisible(false);
		panelPostWin.setVisible(true);
	}
	public void postGameLose() {							// change panels when game is lost 
		timer.stop();
		createSummary();
		System.out.println(summary); // TESTING
		panel.setVisible(false);
		panelPostLose.setVisible(true);
	}
	
	public JPanel getPanelGameplay() {						// get JPanel that is used for gameplay 
		return panel;
	}
	public String getDifficulty() {							// get difficulty as a string 
		return difficulty;
	}
	
	private void createSummary() {							// creates string that can be accessed 
		// Format: email,difficulty,timeLeft
		summary = activeEmail + "," + difficulty + "," + timeLeft;
	}
	public String getSummary() {							// get a string that summarises the game played 
		return summary;
	}
	
} // class end
