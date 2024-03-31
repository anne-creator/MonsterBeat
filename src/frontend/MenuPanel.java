package frontend;

import backend.DataProcessing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MenuPanel extends JPanel {
    Color cGreenBG = new Color(96, 199, 96);
    String[] difficulties = {"Easy", "Medium", "Hard"};
    int selectedDifficulty  = 1; // defualt is easy mode;
    public MenuPanel(MainApplication frame, String userEmail) throws IOException {
        setBackground(cGreenBG);

        // Menu panel with BoxLayout for vertical alignment.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add some vertical glue before the heading to push it down towards the center.
        add(Box.createVerticalGlue());

        // Heading label
        JLabel heading = new JLabel("Menu");
        heading.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        add(heading);

        // space between heading and buttons
        add(Box.createRigidArea(new Dimension(0, 30))); // Increase this value to push buttons lower

        // Nested panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Or X_AXIS for horizontal arrangement

        // Start button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            try {
                System.out.println("pass difficulty Level " + selectedDifficulty);
                frame.switchToGamePanel(userEmail, selectedDifficulty);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(startButton);

        // difficulty toggle
        JComboBox<String> combobox1Difficulty = new JComboBox<>(difficulties);
        buttonPanel.add(combobox1Difficulty);
        // Get the selected item.
        combobox1Difficulty.addActionListener(e -> {
            String difficultyString = (String) combobox1Difficulty.getSelectedItem();
            System.out.println("select what in toggle: " + difficultyString);
            if (difficultyString.equals("Easy")) selectedDifficulty = 1;
            else if (difficultyString.equals("Medium")) selectedDifficulty = 2;
            else selectedDifficulty = 3;
        });

        // Leaderboard button
        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(e -> frame.switchToLeaderBoardPanel(userEmail)); // Pass userEmail here
        leaderboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(leaderboardButton);


        // Tutorial button
        JButton tutorialButton = new JButton("Tutorial");
        tutorialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(tutorialButton);

        // Add the nested panel to the menu panel
        add(buttonPanel);

        // Add some vertical glue after the nested panel to ensure it's centered vertically.
        add(Box.createVerticalGlue());

    }
}
