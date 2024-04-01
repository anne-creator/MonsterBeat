package frontend;


import backend.Accounts;
import backend.DataProcessing;
import backend.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Result page of the game. 
public class ResultPanel extends JPanel {
	private int marks;
    public JPanel buttonPanel = new JPanel();
    public ResultPanel(MainApplication frame, Game game, int timeLeft) throws IOException {
        JButton button0Back;
        marks = game.calculateMarks(timeLeft);
        if (game.difficultyLevel == 1) {
        	if (marks > Accounts.getUser().getLevel1HighestScore())
        	Accounts.getUser().setLevel1HighestScore(marks);
        }
        else if (game.difficultyLevel == 2) {
        	if (marks > Accounts.getUser().getLevel2HighestScore())
        	Accounts.getUser().setLevel2HighestScore(marks);
        }
        else if (game.difficultyLevel == 3) {
        	if (marks > Accounts.getUser().getLevel3HighestScore())
        	Accounts.getUser().setLevel3HighestScore(marks);
        }
        Accounts.save();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Or X_AXIS for horizontal arrangement
        buttonPanel.setPreferredSize(new Dimension(1220, 200));
        buttonPanel.setMinimumSize(new Dimension(1220, 200));
        buttonPanel.setMaximumSize(new Dimension(1220, 200));

        
        JLabel marksLabel = new JLabel("Game ended, your mark is : "+ marks);
        marksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(marksLabel);
        
        JLabel timeLeftLabel = new JLabel("Time left: " + timeLeft + " seconds");
        timeLeftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(timeLeftLabel);

        button0Back = new JButton("Go Back");
        button0Back.addActionListener(e -> {
        	try {
        		frame.switchToMenuPanel(game.character.toString());
        	}
        	catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        button0Back.setSize(40, 60);
        button0Back.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(button0Back);

        add(buttonPanel);
        add(Box.createVerticalGlue());
    }
}