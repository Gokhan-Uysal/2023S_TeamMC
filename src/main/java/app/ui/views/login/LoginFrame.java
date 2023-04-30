package app.ui.views.login;

import app.ui.views.components.BaseJFrame;

import javax.swing.*;
import java.awt.*;



public class LoginFrame extends BaseJFrame {

    public JPasswordField passwordField;
    public JTextField usernameField;
    JLabel passwordLabel, usernameLabel;
    public JButton loginButton;
    public JButton registerButton;
    public JCheckBox showPasswordBox;



    public LoginFrame(String title, Dimension size) {
        super(title, size);
        this.setLayout(null);
        initializeComponents();

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(460,150,100,40);

        usernameField = new JTextField();
        usernameField.setBounds(540, 150, 200, 40);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(460,200,100,40);

        passwordField = new JPasswordField();
        passwordField.setBounds(540, 200, 200, 40);

        showPasswordBox = new JCheckBox("Show Password");
        showPasswordBox.setBounds(540,250,200,40);

        loginButton = new JButton("Login");
        loginButton.setBounds(540,300,100,40);

        registerButton = new JButton("Register");
        registerButton.setBounds(640, 300, 100, 40);

        buildComponents();
    }
    @Override
    public void initializeComponents() {
    }

    @Override
    public void buildComponents() {
        this.pack();
        this.setVisible(true);

        addComponents();

        this.refresh();
    }

    @Override
    public void addComponents() {
        this.add(usernameField);
        this.add(usernameLabel);

        this.add(passwordField);
        this.add(passwordLabel);

        this.add(showPasswordBox);

        this.add(loginButton);
        this.add(registerButton);


    }

}
