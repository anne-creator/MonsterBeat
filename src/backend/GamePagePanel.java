package backend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePagePanel extends JPanel {
    private Timer timer;
    private int timeLeft = 60; // 60 seconds
    private JLabel timeLabel;


    public GamePagePanel(MainApplication frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());

        // Timer Label
        timeLabel = new JLabel("Time left: 60");
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        add(timeLabel);

        // Break Button
        JButton breakButton = new JButton("Break");
        breakButton.addActionListener(e -> {
            timer.stop();
            frame.switchToMainPanel(timeLeft); // Switch back to the main panel with the time left
        });
        breakButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        add(breakButton);

        add(Box.createVerticalGlue()); // add space below

        // Timer config
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timeLabel.setText("Time left: " + timeLeft);
                if (timeLeft <= 0) {
                    timer.stop();
                    frame.switchToMainPanel(timeLeft); // Switch back when no time is left
                }
            }
        });
        timer.start(); // Start the countdown

    }
}

