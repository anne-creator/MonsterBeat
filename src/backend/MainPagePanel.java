package backend;

import javax.swing.*;
import java.awt.*;

public class MainPagePanel extends JPanel {
    public MainPagePanel(MainApplication frame) {

        // Menu panel with BoxLayout for vertical alignment.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add some vertical glue before the heading to push it down towards the center.
        add(Box.createVerticalGlue());

        // Heading label
        JLabel heading = new JLabel("Monster Beat");
        heading.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        add(heading);

        // space between heading and buttons
        add(Box.createRigidArea(new Dimension(0, 30))); // Increase this value to push buttons lower

        // Nested panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Or X_AXIS for horizontal arrangement

        // Start button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> frame.switchToGamePanel());
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(startButton);

        // Leaderboard button
        JButton leaderboardButton = new JButton("Leaderboard");
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

