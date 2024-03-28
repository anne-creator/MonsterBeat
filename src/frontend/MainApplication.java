package frontend;

import backend.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainApplication extends JFrame  {

    public MainApplication() throws IOException {
        setTitle("Monster Beat"); // title of the game
        setSize(1280, 770);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        switchToLogInPanel();
    }
    public void switchToLogInPanel() throws IOException {
        JPanel loginPanel = new LoginPanel(this);
        setContentPane(loginPanel);
        validate();
        repaint();
    }
    public void switchToLogRegisterPanel() {
        JPanel registerPanel = new RegisterPanel(this);
        setContentPane(registerPanel);
        validate();
        repaint();
    }
    public void switchToMenuPanel(String userEmail) throws IOException {
        JPanel menuPanel = new MenuPanel(this, userEmail);
        setContentPane(menuPanel);
        validate();
        repaint();
    }
    public void switchToResultPanel(Game game, int timeLeft) throws IOException {
        JLabel timeLeftLabel = new JLabel("Time left: " + timeLeft + " seconds");
        JPanel resultPanel = new ResultPanel(this, game, timeLeft);
        timeLeftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(timeLeftLabel);
        setContentPane(resultPanel);
        add(Box.createRigidArea(new Dimension(0, 100))); // Increase this value to push buttons lower
        validate();
        repaint();
    }

    public void switchToGamePanel(String userEmail, int selectedDifficulty) throws IOException {
        JPanel gamePanel = new GamePanel(this, userEmail, selectedDifficulty);
        setContentPane(gamePanel);
        validate();
        repaint();
    }

}
