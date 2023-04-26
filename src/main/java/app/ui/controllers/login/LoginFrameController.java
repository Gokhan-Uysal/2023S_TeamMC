package app.ui.controllers.login;

import app.common.AppConfig;
import app.domain.services.FileService;
import app.ui.views.login.LoginFrame;
import app.ui.views.login.RegisterFrame;
import app.ui.views.menu.MainMenuFrame;
import app.util.ActionListenerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginFrameController extends Component implements ActionListener {

    private LoginFrame loginFrame;

    public LoginFrameController(LoginFrame loginFrame){
        this.loginFrame = loginFrame;
        ActionListenerUtil.addActionListener(loginFrame.loginButton, this);
        ActionListenerUtil.addActionListener(loginFrame.registerButton, this);
        ActionListenerUtil.addActionListener(loginFrame.showPasswordBox, this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginFrame.loginButton){
            String inputName = loginFrame.usernameField.getText();
            String inputPassword = Arrays.toString(loginFrame.passwordField.getPassword());
            boolean response = FileService.checkData(inputName, inputPassword);
            if (response){
                JOptionPane.showMessageDialog(this, "You successfully logged in");
                new MainMenuFrame("Main Menu", AppConfig.appSize);
            }
            else{
                JOptionPane.showMessageDialog(this, "Try again");
            }

        }
        else if (e.getSource() == loginFrame.registerButton){
            new RegisterFrame("Register", AppConfig.appSize);

        }
        else if (e.getSource() == loginFrame.showPasswordBox){
            if(loginFrame.showPasswordBox.isSelected()) {
                loginFrame.passwordField.setEchoChar((char) 0);
            }
            else {
                loginFrame.passwordField.setEchoChar('●');
            }
        }


    }
}
