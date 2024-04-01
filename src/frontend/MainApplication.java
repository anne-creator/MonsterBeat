package frontend;

import backend.Accounts;
import backend.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainApplication extends JFrame  {

    public MainApplication() throws IOException {									// 
        setTitle("Monster Beat"); // title of the game
        setSize(1280, 770);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        new Accounts();
        switchToLogInPanel();
    }
    public void switchToLogInPanel() throws IOException {							// Frontend finished - 3/30 AL 
        JPanel loginPanel = new LoginPanel2(this);			// This line for testing new title screen - 3/30 AL
        setContentPane(loginPanel);
        validate();
        repaint();
    }
    public void switchToLogInPanel(String userEmail) throws IOException {			// Now displays error message - 3/30 AL 
        JLabel registerMessage = new JLabel(userEmail + " already registed, please login");
        registerMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //LoginPanel loginPanel = new LoginPanel(this);		// Cannot test here because of buttonPanel instance - 3/30 AL
        //loginPanel.buttonPanel.add(registerMessage);
        LoginPanel2 loginPanel2 = new LoginPanel2(this);		// Cannot test here because of buttonPanel instance - 3/30 AL
        loginPanel2.registerErrorMessage(registerMessage);
        
        //setContentPane(loginPanel);
        setContentPane(loginPanel2);
        
        validate();
        repaint();
    }
    public void switchToRegisterPanel() throws IOException {						// 
        JPanel registerPanel = new RegisterPanel2(this);
        setContentPane(registerPanel);
        validate();
        repaint();
    }
    public void switchToMenuPanel(String userEmail) throws IOException {			// Need to edit title image - 3/30 AL 
        JPanel menuPanel = new MenuPanel2(this, userEmail);	// This line for testing new main menu screen - 3/30 AL
        setContentPane(menuPanel);
        validate();
        repaint();
    }
    public void switchToResultPanel(Game game, int timeLeft) throws IOException {	// 
        JPanel resultPanel = new ResultPanel(this, game, timeLeft);
        setContentPane(resultPanel);
        add(Box.createRigidArea(new Dimension(0, 800))); // Increase this value to push buttons lower
        validate();
        repaint();
    }
    // In MainApplication class
    public void switchToLeaderBoardPanel(String userEmail) {
        // This method now expects the user's email.
        // Ensure you have the user's email when calling this method.
        LeaderBoardPanel leaderboardPanel = new LeaderBoardPanel(this, userEmail);
        setContentPane(leaderboardPanel);
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
