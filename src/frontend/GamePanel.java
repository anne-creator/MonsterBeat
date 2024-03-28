package frontend;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import backend.Game;
import backend.Question;


public class GamePanel extends JPanel {
    private Timer timer;
    private int timeLeft = 180; // 60 seconds
    private JLabel timeLabel;
    Game game;
    Question question;
    MainApplication frame;
    String monsterImg;

    // all Label and panel needs to be updated
    JLabel questionLabel;
    JButton[] buttonList = {new JButton(""), new JButton(""), new JButton(""), new JButton("")};
//    BufferedImage characterHeartImg, monsterHeartImg, characterOrg;
    JLabel monsterLabel, monsterHeartPanel, characterHeartPanel;
    int answerKey;

    public GamePanel(MainApplication frame, String userEmail, int difficultyLevel) throws IOException {
        this.game = new Game(userEmail, difficultyLevel);
        this.frame = frame;
        Question question = game.generateQuestion();
        this.answerKey = question.answerKey;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Time and Level Panel
        JPanel timeLevelPanel = new JPanel();
        timeLevelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Timer Label
        timeLabel = new JLabel("Time left: 60");
        Font labelFont = timeLabel.getFont();
        timeLabel.setFont(new Font(labelFont.getName(), labelFont.getStyle(), 48));
        timeLevelPanel.add(timeLabel);

        JLabel level = new JLabel("Level " + difficultyLevel);
        level.setHorizontalAlignment(JLabel.RIGHT);
        timeLevelPanel.add(level);

        // Question Panel ///////////////////////
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        Color cBlueBG = new Color(96, 96, 199);
        questionPanel.setBackground(cBlueBG);// set background colour

        questionLabel = new JLabel(question.questionString);
        questionPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(cBlueBG);// set background colour
        optionsPanel.setLayout(new GridLayout(1, 4, 5, 5)); // 1 row, 4 cols, hgap, vgap

        for ( int i = 0; i < 4; i++) {
            // assign question option to the button
            buttonList[i].setText(question.optionList[i]);
            optionsPanel.add(buttonList[i]);

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
            optionsPanel.add(buttonList[i]);
            questionPanel.add(optionsPanel, BorderLayout.SOUTH);
        }

        /**
         * bottomPanel     ///////////////////
         *      characterPanel "left" (heart panel (upper) + characterLabel(lower))
         *      monsterPanel "right" (heart panel (upper) + monsterLabel(lower))
         */
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        // characterPanel : composed by a heart panel and a character Label
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));

        // character Heart Panel
        BufferedImage characterHeartImg = ImageIO.read(new File("heart3.png"));
        characterHeartPanel = new JLabel(new ImageIcon(characterHeartImg));
        characterHeartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //characterLabel
        BufferedImage characterOrg = ImageIO.read(new File("c1.png"));
        BufferedImage characterImg = resizeImage(characterOrg, 200, 260);
        JLabel characterLabel = new JLabel(new ImageIcon(characterImg));
        characterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // character Panel layout
        characterPanel.add(characterHeartPanel);
        characterPanel.add(Box.createRigidArea(new Dimension(200, 30)));
        characterPanel.add(characterLabel);

        // monsterPanel : composed by a heart panel and a monster Label
        JPanel monsterPanel = new JPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));

        // monsterHeartPanel
        BufferedImage monsterHeartImg = ImageIO.read(new File("heart1.png"));
        monsterHeartPanel = new JLabel(new ImageIcon(monsterHeartImg));
        monsterHeartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //monsterLabel
        BufferedImage monsterOrg = ImageIO.read(new File("m1.png"));
        BufferedImage monsterImg = resizeImage(monsterOrg, 200, 260);
        monsterLabel = new JLabel(new ImageIcon(monsterImg));
        monsterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        monsterPanel.add(monsterHeartPanel);
        monsterPanel.add(Box.createRigidArea(new Dimension(200, 30)));
        monsterPanel.add(monsterLabel);

        // orgnized bottom panel layout
        characterPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        monsterPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        bottomPanel.add(characterPanel);
        bottomPanel.add(Box.createRigidArea(new Dimension(550,260)));
        bottomPanel.add(monsterPanel);

        // Add subpanels to main panel
        add(Box.createRigidArea(new Dimension(1280,50)));
        add(timeLevelPanel);
        add(Box.createRigidArea(new Dimension(1280,80)));
        add(questionPanel);
        add(Box.createRigidArea(new Dimension(1280,80)));
        add(bottomPanel);
        add(Box.createRigidArea(new Dimension(1280,60)));

        // Timer config
        timer = new Timer(1000, e -> {
            timeLeft--;
            timeLabel.setText("Time left: " + timeLeft);
            if (timeLeft <= 0) {
                timer.stop();
                try {
                    frame.switchToResultPanel(game, timeLeft); // game ends when run out of time
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        timer.start(); // Start the countdown
    }

    // handle button click
    public void handleStatus(String status) throws IOException {
        System.out.println(status);
        if (status.equals("game end")) {
            frame.switchToResultPanel(game, timeLeft);
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
            BufferedImage monsterOrg = ImageIO.read(new File("m2.png"));
            BufferedImage monsterImg = resizeImage(monsterOrg, 200, 260);
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

    // used for generate both character and monster
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
    }
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(resultingImage, 0, 0, null);
        graphics2D.dispose();

        return outputImage;
    }
}
