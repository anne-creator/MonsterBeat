package frontend;

import backend.Accounts;
import backend.DataProcessing;
import backend.User;

import javax.swing.*;
import java.io.IOException;

public class RegisterPanel extends JPanel {
    public RegisterPanel(MainApplication frame) {
        JTextField textField2Email = new JTextField(10);
        textField2Email.setBounds(823, 212, 300, 60);
        add(textField2Email);

        JButton button2CreateAccount = new JButton("Create Account");        // set buttons
        button2CreateAccount.setBounds(849, 376, 248, 80);
        button2CreateAccount.addActionListener(e -> {
            String userEmail = textField2Email.getText();
            try {
            	if (Accounts.exist(userEmail)) {
            		frame.switchToLogInPanel(userEmail);
            	}
            	else {
            		Accounts.create(new User(userEmail));
            		Accounts.logIn(userEmail);
            		frame.switchToMenuPanel(userEmail);
            	}
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(button2CreateAccount);

        JButton button2ReturnToLogin = new JButton("Return to Login");
        button2ReturnToLogin.setBounds(849, 457, 248, 80);
        button2ReturnToLogin.addActionListener(e -> {
            try {
                frame.switchToLogInPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(button2ReturnToLogin);

        JButton button2Exit = new JButton("Exit");                            // Exit
        button2Exit.setBounds(849, 538, 248, 80);
        add(button2Exit);
    }

}

