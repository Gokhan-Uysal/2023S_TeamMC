package app.ui.controllers.login;

import app.ui.views.login.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrameController implements ActionListener {

    private LoginFrame loginFrame;

    public LoginFrameController(LoginFrame loginFrame){
        this.loginFrame = loginFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
