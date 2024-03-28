package frontend;
import backend.Enemy;
import backend.Heart;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Gameplay implements ActionListener {

    // DEPTH DEFINITIONS
    int depthBackground = 10;

    JFrame guiFrame; 					// only used to add gameplayPanel to the GUI
    private JPanel panel, panelMain, panelPostWin, panelPostLose;
    JLayeredPane layeredPane;			// higher depth values put it "into" the screen, under other elements

    Player player;
    JLabel spritePlayer;
    Enemy enemy;
    JLabel spriteEnemy;
    Heart playerHeart1, playerHeart2, playerHeart3, enemyHeart1, enemyHeart2, enemyHeart3;
    JPanel[] hearts;
    boolean statusWin;

    JPanel questionPanel;
    JLabel labelQuestion;

    JButton buttonPause;
    JButton answerButton1, answerButton2, answerButton3, answerButton4;
    JButton[] buttons;
    int expectedAnswer, playerAnswer;
    boolean questionInProgress, result;

    String[] question1 = {"1", "question1: what is the sum of 5 and 10?", "15", "20", "25", "30"}; 		// answer is 1
    String[] question2 = {"2", "question2: what is the product of 5 and 6?", "25", "30", "35", "40"};	// answer is 2
    String[] question3 = {"3", "question3: what is the capital of Canada?", "toronto", "paris", "ottawa", "vancouver"};
    String[] questionBuffer = {"4", "question4: what is the tallest mountain in the world?", "K2", "Lhotse", "makalu", "everest"};
    String[] buffer = {"","","","","",""};

    String[][] questions = {question1, question2, question3, questionBuffer};
    int questionCounter = 0;

    public Gameplay(JFrame frame, JPanel mainScreen, JPanel postWin, JPanel postLose, String difficulty) {
        guiFrame = frame;
        panelMain = mainScreen;
        panelPostWin = postWin;
        panelPostLose = postLose;


        try {
            // initialises background, and pause button
            initPanel();
            initLayeredPane();
            initBackground();
            initButtonPause();

            // initialising game elements here
            initPlayer();
            initEnemy();
            initHearts();

            // begin asking questions
            initQuestion(questionCounter);

            //giveQuestion(questions[1], 2);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initPanel() {													// create gameplayPanel and add to frame
        panel = new JPanel();
        panel.setBounds(0, 0, 1280, 770);
        panel.setVisible(true);
        panel.setLayout(null);
        panel.setBackground(Color.red);
        guiFrame.add(panel);
    }
    private void initLayeredPane() {											// create layeredPane and add to gameplayPanel
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1280, 770);
        layeredPane.setVisible(true);
        panel.add(layeredPane);
    }
    private void initBackground() throws IOException {							// create JLabel to hold background, add to layeredPane at 10
        BufferedImage spriteEasyBackground;
        spriteEasyBackground = ImageIO.read(new File("easyBackground.jpg"));	// read in image
        JLabel picLabel = new JLabel(new ImageIcon(spriteEasyBackground));		// JLabel to hold image
        picLabel.setBounds(0, 0, 1280, 770);	// (position, size)				// set bounds

        layeredPane.add(picLabel, depthBackground);
    }
    private void initButtonPause() {											// create JButton to BACK, add to layeredPane at 0
        buttonPause = new JButton("TEMP BACK"); // set button
        buttonPause.setBounds(0, 0, 124, 40);
        buttonPause.addActionListener(this);
        //panel.add(buttonPause);
        layeredPane.add(buttonPause, 0);
    }

    private void initPlayer() {													// create Class Player on layeredPane at 0
        try {
            player = new Player(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        spritePlayer = player.getLabelPlayer();
        spritePlayer.setBounds(83, 340, 250, 350);
        spritePlayer.setVisible(true);
        layeredPane.add(spritePlayer, 0);

    }
    private void initEnemy() {													// create Class Enemy on layeredPane at 0
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
    private void initHearts() {													// create 6 instances of Class Heart on layeredPane at 0
        hearts = new JPanel[6];

        playerHeart1 = new Heart(150, 10, 50);
        hearts[0] = playerHeart1.getHeart();
        playerHeart2 = new Heart(210, 10, 50);
        hearts[1] = playerHeart2.getHeart();
        playerHeart3 = new Heart(270, 10, 50);
        hearts[2] = playerHeart3.getHeart();

        enemyHeart1 = new Heart(950, 10, 50);
        hearts[3] = enemyHeart1.getHeart();
        enemyHeart2 = new Heart(1010, 10, 50);
        hearts[4] = enemyHeart2.getHeart();
        enemyHeart3 = new Heart(1070, 10, 50);
        hearts[5] = enemyHeart3.getHeart();

        for (int i = 0; i < 6; i++) {
            layeredPane.add(hearts[i], 0);
        }
    }




    private void initQuestion(int question) {						// initialises a questionPanel to the layeredPane at

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
        labelQuestion = new JLabel(questions[question][1]);	// add JLabel of question to the questionPanel
        labelQuestion.setBounds(30, 30, 600, 100);					// set bounds of question text
        questionPanel.add(labelQuestion);							// add label to the screen

        buttons = new JButton[4];									// initiaise buttons to buttons[]
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

    private void nullQuestion() {
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

    // these methods display text and should queue the next question????
    private boolean checkAnswer() {
        if (playerAnswer == expectedAnswer) return true;
        else return false;
    }

    private void playerLoseHealth() {
        if (player.getHealth() == 3) {
            playerHeart3.loseHeart();
            player.loseHealth();
        }
        else if (player.getHealth() == 2) {
            playerHeart2.loseHeart();
            player.loseHealth();
        }
        else {
            statusWin = false;
            postGameLose();
        }
    }
    private void enemyLoseHealth() {
        if (enemy.getHealth() == 3) {
            enemyHeart3.loseHeart();
            enemy.loseHealth();
        }
        else if (enemy.getHealth() == 2) {
            enemyHeart2.loseHeart();
            enemy.loseHealth();
        }
        else {
            statusWin = true;
            postGameWin();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonPause) {
            panel.setVisible(false);
            panelMain.setVisible(true);
        }

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

        // the key is to sequence the arrays of questions, and then when the answer is correct, then change to the next panel in the array

    }

    public void postGameWin() {
        panel.setVisible(false);
        panelPostWin.setVisible(true);
    }
    public void postGameLose() {
        panel.setVisible(false);
        panelPostLose.setVisible(true);
    }


    public JPanel getPanelGameplay() {
        return panel;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
