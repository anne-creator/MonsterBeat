package backend;
import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {

    public MainApplication() {
        setTitle("Page Transition Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        switchToMainPanel();
    }

    public void switchToMainPanel() {
        JPanel mainPanel = new MainPagePanel(this);
        setContentPane(mainPanel);
        validate();
        repaint();
    }
    public void switchToMainPanel(int timeLeft) {
        JLabel timeLeftLabel = new JLabel("Time left: " + timeLeft + " seconds");
        JPanel mainPanel = new MainPagePanel(this);
        timeLeftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(timeLeftLabel);
        setContentPane(mainPanel);
        add(Box.createRigidArea(new Dimension(0, 100))); // Increase this value to push buttons lower
        validate();
        repaint();
    }

    public void switchToGamePanel() {
        JPanel gamePanel = new GamePagePanel(this);
        setContentPane(gamePanel);
        validate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApplication().setVisible(true));
    }
}
