import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GuiTester implements ActionListener{

    // DEFINITIONS
    int panelStart = 0; // JPanel screen that game starts on
    int NUMPANELS = 5;
    int panelLogin = 0;
    int panelMain = 1;
    int panelAccount = 2;
    int panelPostWin = 3;
    int panelPostLose = 4;

    // COLOURS
    Color cGrey = new Color(183, 183, 183);
    Color cDark = new Color(51, 51, 51);

    Color cGreenBG = new Color(96, 199, 96);
    Color cBlueBG = new Color(96, 96, 199);
    Color cRedBG = new Color(199, 96, 96);
    Color cWinBG = new Color(0, 0, 255);
    Color cLoseBG = new Color(255, 0, 0);

    // instance variables separated by screen

    JFrame frame;
    JPanel[] panels = new JPanel[NUMPANELS];

    String testUsername = "Andrew Lee";
    String testEmail = "leeandrew135@gmail.com";
    String testPassword = "password123";

    String adminUsername = "admin";
    String adminEmail = "admin";
    String adminPassword = "admin";

    // (0) panelLogin
    JButton button0LogIn, button0NewUser, button0Exit;
    JTextField textField0Username, textField0Password;

    // (1) panelMain
    JButton button1Play, button1Tutorial, button1Leaderboard, button1Logout, button1Exit;
    String[] difficulties = {"Easy", "Medium", "Hard"};
    JComboBox<String> combobox1Difficulty;

    // (2) panelAccount
    JButton button2CreateAccount, button2ReturnToLogin, button2Exit;
    JButton buttonMainScreen;
    JTextField textField2Username, textField2Email, textField2Password;

    // (3) panelPostWin
    JLabel label3Header, label3Win;
    JButton button3PostWin;

    // (4) panelPostLose
    JLabel label4Header, label4Lose;
    JButton button4PostLose;

    // panelGaemplay
    JPanel panelGameplay;

    public GuiTester() {
        frame = new JFrame("Monster Beat");
    }

    public void initialiseFrame() { // note that the borders are around 30 px
        //frame = new JFrame("String title Game Title");		// constructor automatically initialises JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// exit game process
        frame.setResizable(false);								// resize window
        frame.setSize(1280, 770);								// set size(width, height)
        frame.setVisible(true); 								// make visible
        frame.setLayout(null);
    }

    // should break this into multiple methods such as
    // loadPanel1() and freePanel1()
    // to not have all elements loaded at once
    public void initialisePanels() throws IOException {
        try {
            for (int i = 0; i < NUMPANELS; i++) {
                panels[i] = new JPanel();
                panels[i].setBounds(0, 0, 1280, 770);
                panels[i].setVisible(false);
                panels[i].setLayout(null);

                if (i == panelLogin) { // (0) panelLogIn
                    panels[panelLogin].setBackground(cGreenBG);							// set background colour

                    /////////////////////////////////////////////////////

                    BufferedImage spriteGameTitle;										// game logo
                    spriteGameTitle = ImageIO.read(new File("spriteGameTitle.jpg"));		// read in image
                    JLabel picLabel = new JLabel(new ImageIcon(spriteGameTitle));			// JLabel to hold image
                    picLabel.setBounds(20, 20, 700, 340);	// (position, size)				// set bounds
                    panels[panelLogin].add(picLabel);										// add to panel

                    /////////////////////////////////////////////////////

                    textField0Username = new JTextField(10);						// set input text fields
                    textField0Username.setBounds(823, 183, 300, 60);
                    textField0Username.addActionListener(this);
                    panels[panelLogin].add(textField0Username);

                    textField0Password = new JTextField(10);
                    textField0Password.setBounds(823, 244, 300, 60);
                    textField0Password.addActionListener(this);
                    panels[panelLogin].add(textField0Password);

                    /////////////////////////////////////////////////////

                    button0LogIn = new JButton("Log In"); 								// set buttons
                    button0LogIn.setBounds(849, 347, 248, 80);
                    button0LogIn.addActionListener(this);
                    panels[panelLogin].add(button0LogIn);

                    button0NewUser = new JButton("Create Account");
                    button0NewUser.setBounds(849, 428, 248, 80);
                    button0NewUser.addActionListener(this);
                    panels[panelLogin].add(button0NewUser);

                    button0Exit = new JButton("Exit");
                    button0Exit.setBounds(849, 509, 248, 80);
                    button0Exit.addActionListener(this);
                    panels[panelLogin].add(button0Exit);


                    /////////////////////////////////////////////////////

                    JLabel labelUsername = new JLabel("Email:");						// set text labels
                    labelUsername.setBounds(780, 183, 40, 60);
                    panels[panelLogin].add(labelUsername);

                    JLabel labelPassword = new JLabel("Password:");
                    labelPassword.setBounds(755, 244, 80, 60);
                    panels[panelLogin].add(labelPassword);

                    /////////////////////////////////////////////////////

                    frame.add(panels[panelLogin]);										// add panel to frame
                }
                else if (i == panelMain) { // (1) panelMainScreen
                    panels[panelMain].setBackground(cBlueBG);

                    /////////////////////////////////////////////////////

                    button1Play = new JButton("Play"); 						// Set Buttons
                    button1Play.setBounds(849, 173, 248, 80);						// Play
                    button1Play.addActionListener(this);
                    panels[panelMain].add(button1Play);

                    button1Tutorial = new JButton("Tutorial"); 					// Tutorial
                    button1Tutorial.setBounds(849, 275, 248, 80);
                    button1Tutorial.addActionListener(this);
                    panels[panelMain].add(button1Tutorial);

                    button1Leaderboard = new JButton("Leaderboard"); 			// Leaderboard
                    button1Leaderboard.setBounds(849, 356, 248, 80);
                    button1Leaderboard.addActionListener(this);
                    panels[panelMain].add(button1Leaderboard);

                    button1Logout = new JButton("Logout"); 						// Logout
                    button1Logout.setBounds(849, 437, 248, 80);
                    button1Logout.addActionListener(this);
                    panels[panelMain].add(button1Logout);

                    button1Exit = new JButton("Exit"); 							// Exit
                    button1Exit.setBounds(849, 518, 248, 80);
                    button1Exit.addActionListener(this);
                    panels[panelMain].add(button1Exit);

                    /////////////////////////////////////////////////////

                    combobox1Difficulty = new JComboBox<String>(difficulties);
                    combobox1Difficulty.setBounds(849, 254, 248, 20);
                    panels[panelMain].add(combobox1Difficulty);

                    /////////////////////////////////////////////////////

                    frame.add(panels[panelMain]);
                }
                else if (i == panelAccount) { // (2) panelNewUser
                    panels[panelAccount].setBackground(cRedBG);

                    /////////////////////////////////////////////////////

                    textField2Username = new JTextField(10);					// set input text fields
                    textField2Username.setBounds(823, 151, 300, 60);
                    panels[panelAccount].add(textField2Username);

                    textField2Email = new JTextField(10);
                    textField2Email.setBounds(823, 212, 300, 60);
                    panels[panelAccount].add(textField2Email);

                    textField2Password = new JTextField(10);
                    textField2Password.setBounds(823, 273, 300, 60);
                    panels[panelAccount].add(textField2Password);

                    /////////////////////////////////////////////////////

                    button2CreateAccount = new JButton("Create Account"); 		// set buttons
                    button2CreateAccount.setBounds(849, 376, 248, 80);
                    button2CreateAccount.addActionListener(this);
                    panels[panelAccount].add(button2CreateAccount);

                    button2ReturnToLogin = new JButton("Return to Login");
                    button2ReturnToLogin.setBounds(849, 457, 248, 80);
                    button2ReturnToLogin.addActionListener(this);
                    panels[panelAccount].add(button2ReturnToLogin);

                    button2Exit = new JButton("Exit"); 							// Exit
                    button2Exit.setBounds(849, 538, 248, 80);
                    button2Exit.addActionListener(this);
                    panels[panelAccount].add(button2Exit);

                    /////////////////////////////////////////////////////

                    JLabel labelUsername = new JLabel("Username:");						// set text labels
                    labelUsername.setBounds(753, 151, 80, 60);
                    panels[panelAccount].add(labelUsername);

                    JLabel labelEmail = new JLabel("Email:");
                    labelEmail.setBounds(780, 212, 40, 60);
                    panels[panelAccount].add(labelEmail);

                    JLabel labelPassword = new JLabel("Password:");
                    labelPassword.setBounds(755, 273, 80, 60);
                    panels[panelAccount].add(labelPassword);

                    frame.add(panels[panelAccount]);
                }
                else if (i == panelPostWin) {
                    panels[panelPostWin].setBackground(cWinBG);
                    frame.add(panels[panelPostWin]);

                    button3PostWin = new JButton("Back to Main Screen"); 		// set buttons
                    button3PostWin.setBounds(849, 376, 248, 80);
                    button3PostWin.addActionListener(this);
                    panels[panelPostWin].add(button3PostWin);



                    label3Header = new JLabel("GAME OVER");						// set text labels
                    label3Header.setBounds(200, 151, 80, 60);
                    panels[panelPostWin].add(label3Header);

                    label3Win = new JLabel("YOU WIN");						// set text labels
                    label3Win.setBounds(200, 300, 80, 60);
                    panels[panelPostWin].add(label3Win);
                }
                else if (i == panelPostLose) {
                    panels[panelPostLose].setBackground(cLoseBG);
                    frame.add(panels[panelPostLose]);

                    button4PostLose = new JButton("Back to Main Screen");
                    button4PostLose.setBounds(849, 376, 248, 80);
                    button4PostLose.addActionListener(this);
                    panels[panelPostLose].add(button4PostLose);

                    label4Header = new JLabel("GAME OVER");						// set text labels
                    label4Header.setBounds(200, 151, 80, 60);
                    panels[panelPostLose].add(label4Header);

                    label4Lose = new JLabel("YOU LOSE");						// set text labels
                    label4Lose.setBounds(200, 300, 80, 60);
                    panels[panelPostLose].add(label4Lose);


                }


            } // for loop loading panels end

            panels[panelStart].setVisible(true); // after everything is loaded, set screen 1 visible

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    } // initialisePanels() END

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button0LogIn) { 								// for this e.getSource(), we can use if button is in array,
            System.out.println("From Log In (0), to Main Screen (1).");		// and then use a dictionary to get the correct destination
            String userInputUsername = textField0Username.getText();
            String userInputPassword = textField0Password.getText();
            if (userInputUsername.equals(testEmail) && userInputPassword.equals(testPassword) || userInputUsername.equals(adminUsername)) {
                System.out.println("Login Successful.");
                panels[panelLogin].setVisible(false);
                panels[panelMain].setVisible(true);
            }
            else {
                System.out.println("Login Unsuccessful.");
                System.out.println(userInputUsername);
                System.out.println(userInputPassword);
            }
        }
        if(e.getSource() == button0NewUser) {
            System.out.println("From Log In (0), to New Account (2)");
            panels[panelAccount].setVisible(true);
            panels[panelLogin].setVisible(false);
        }

        /////////////////////////////////////////////////////

        if(e.getSource() == button1Play) {													// CALL runGame()
            String gameDifficulty = combobox1Difficulty.getSelectedItem().toString();
            System.out.println("Game Difficulty: " + gameDifficulty);
            try {
                runGame(gameDifficulty);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        if(e.getSource() == button1Logout) {
            System.out.println("From Main Screen (1), to Log In (0)");
            panels[panelLogin].setVisible(true);
            panels[panelMain].setVisible(false);
        }

        /////////////////////////////////////////////////////

        if(e.getSource() == button2CreateAccount) {
            String inputNewUsername = textField2Username.getText();
            String inputNewEmail = textField2Email.getText();
            String inputNewPassword = textField2Password.getText();
            // may want to consider Confirm option?
            System.out.println(inputNewUsername + "\n" + inputNewEmail + "\n" + inputNewPassword);
        }
        if(e.getSource() == button2ReturnToLogin) {
            System.out.println("From Create Account (2), To Login Screen (0).");
            panels[panelLogin].setVisible(true);
            panels[panelAccount].setVisible(false);
        }

        /////////////////////////////////////////////////////

        if(e.getSource() == button3PostWin) {
            System.out.println("From Post Win (3), To Main Screen (1).");
            panels[panelPostWin].setVisible(false);
            panels[panelMain].setVisible(true);
        }

        ////////////////////////////////////////////////////

        if(e.getSource() == button4PostLose) {
            System.out.println("From Post Lose (4), To Main Screen (1).");
            panels[panelPostLose].setVisible(false);
            panels[panelMain].setVisible(true);
        }


        ////////////////////////////////////////////////////


        if(e.getSource() == button0Exit || e.getSource() == button1Exit || e.getSource() == button2Exit) {
            System.exit(0);
        }

    } // action performed END

    public void runGame(String difficulty) throws InterruptedException {					// runGame()
        System.out.println("From GuiTester, runGame() called.");
        // set all panels visible false, and show game panel
        for (int i = 0; i < NUMPANELS; i++) {
            panels[i].setVisible(false);
        }
        Gameplay gameplay = new Gameplay(frame, panels[panelMain], panels[panelPostWin], panels[panelPostLose], difficulty);
        panelGameplay = gameplay.getPanelGameplay();
        panelGameplay.setVisible(true);
        //gameplay.checkQuestion();

    }

    public static void main(String[] args) {
        GuiTester gui = new GuiTester();
        gui.initialiseFrame();

        try {
            gui.initialisePanels();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    } // main() END

} // GuiTester.java END
