package app.ui.views.login;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    JPasswordField passwordField;
    JTextField usernameField;
    JLabel passwordLabel, usernameLabel;
    JButton loginButton, registerButton;
    JCheckBox showPasswordBox;

    public LoginPanel() {
        super();
        setOpaque(false);
        setLayout(null);
        setBounds(300, 100, 360, 100);
        setBackground(Color.CYAN);
        setForeground(Color.black);
        BuildPanel();
    }

    public void BuildPanel() {

    }
}
