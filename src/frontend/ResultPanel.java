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

    public ResultPanel(MainApplication frame, Game game, int timeLeft) throws IOException {
        int score = game.calculateMarks(timeLeft);
        // Creating the text area for displaying results
        resultTextArea = new JTextArea("" + score);
        resultTextArea.setEditable(false); // Make the text area non-editable
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);

        add(resultTextArea);
    }
}
