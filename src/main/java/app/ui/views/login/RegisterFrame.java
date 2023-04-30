package app.ui.views.login;

import app.ui.views.components.BaseJFrame;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends BaseJFrame {


    public JPasswordField passwordField, passwordAgainField;
    public JTextField usernameField;
    JLabel passwordLabel, passwordAgainLabel, usernameLabel;
    public JButton registerButton, backButton;
    public JCheckBox showPasswordBox;

    public RegisterFrame(String title, Dimension size) {

        super(title, size);
        this.setLayout(null);
        initilizeComponents();

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(460,150,100,40);

        usernameField = new JTextField();
        usernameField.setBounds(540, 150, 200, 40);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(460,200,100,40);

        passwordField = new JPasswordField();
        passwordField.setBounds(540, 200, 200, 40);

        passwordAgainField = new JPasswordField();
        passwordAgainField.setBounds(540, 250, 200, 40);

        passwordAgainLabel = new JLabel("Password Again");
        passwordAgainLabel.setBounds(420,250,100,40);

        showPasswordBox = new JCheckBox("Show Password");
        showPasswordBox.setBounds(540,300,200,40);

        backButton = new JButton("Back");
        backButton.setBounds(540, 350, 100, 40);

        registerButton = new JButton("Register");
        registerButton.setBounds(640, 350, 100, 40);

        buildComponents();
    }

    @Override
    public void initilizeComponents() {

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

        this.add(passwordAgainField);
        this.add(passwordAgainLabel);

        this.add(showPasswordBox);

        this.add(backButton);
        this.add(registerButton);


    }
}
