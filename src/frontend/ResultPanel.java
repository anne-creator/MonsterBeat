package frontend;

import backend.DataProcessing;
import backend.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ResultPanel extends JPanel {
    private JTextArea resultTextArea;

    public ResultPanel(MainApplication frame, Game game, int timeLeft, String userEmail) throws IOException {
        int score = game.calculateMarks(timeLeft);
        // Creating the text area for displaying results
        resultTextArea = new JTextArea("" + score);
        resultTextArea.setEditable(false); // Make the text area non-editable
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);

        add(resultTextArea);
        
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
		add(buttonPause, 0);
    }
}
