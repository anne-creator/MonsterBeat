package frontend;

import backend.DataProcessing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LoginPanel extends JPanel {
    public JPanel buttonPanel = new JPanel();
    public LoginPanel(MainApplication frame) throws IOException {
        JButton button0LogIn, button0NewUser, button0Exit;
        JTextField textField0UserEmail;

        // background color
        Color cGreenBG = new Color(96, 199, 96);
        setBackground(cGreenBG);// set background colour

        // Menu panel with BoxLayout for vertical alignment.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Add some vertical glue before the image
        add(Box.createVerticalGlue());

        // picture label
        BufferedImage spriteGameTitle; // game logo
        spriteGameTitle = ImageIO.read(new File("m1.png")); // read in image
        JLabel picLabel = new JLabel(new ImageIcon(spriteGameTitle)); // JLabel to hold image
        picLabel.setMinimumSize(new Dimension(1220, 400));
        picLabel.setMaximumSize(new Dimension(1220, 400));
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(picLabel); // add to panel

        // space between picture and buttons
        add(Box.createRigidArea(new Dimension(0, 30))); // Increase this value to push buttons lower

        // Nested buttonPanel section for input field and  buttons
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Or X_AXIS for horizontal arrangement
        buttonPanel.setPreferredSize(new Dimension(1220, 200));
        buttonPanel.setMinimumSize(new Dimension(1220, 200));
        buttonPanel.setMaximumSize(new Dimension(1220, 200));
        buttonPanel.setBackground(cGreenBG);

        //user email label
        JLabel labelUserEmail = new JLabel("Email:");						// set text labels
        labelUserEmail.setAlignmentX(Component.RIGHT_ALIGNMENT);
        buttonPanel.add(labelUserEmail);


        // email input field
        textField0UserEmail = new JTextField(10);						// set input text fields
        textField0UserEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField0UserEmail.setPreferredSize(new Dimension(100, 24));
        textField0UserEmail.setMinimumSize(new Dimension(100, 24));
        textField0UserEmail.setMaximumSize(new Dimension(100, 24));
        buttonPanel.add(textField0UserEmail);

        // login button
        button0LogIn = new JButton("Log In"); 								// set buttons
        button0LogIn.setSize(40, 60);
        button0LogIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(button0LogIn);
        // Action Listener for the button
        button0LogIn.addActionListener(e -> {
            if (DataProcessing.ifUserExsist(textField0UserEmail.getText())) {
                try {
                    frame.switchToMenuPanel(textField0UserEmail.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                frame.switchToRegisterPanel();
            }
        });

        // create account button
        button0NewUser = new JButton("Create Account");
        button0NewUser.addActionListener(e -> {
                frame.switchToRegisterPanel();
        });
        button0NewUser.setSize(40, 60);
        button0NewUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(button0NewUser);

        // exit button
        button0Exit = new JButton("Exit");
        button0Exit.setSize(40, 60);
        button0Exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(button0Exit);

        add(buttonPanel);
        add(Box.createVerticalGlue());
    }
}
