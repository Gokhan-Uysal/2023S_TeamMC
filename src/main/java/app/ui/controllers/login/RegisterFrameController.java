package app.ui.controllers.login;

import app.common.AppConfig;
import app.domain.services.FileService;
import app.ui.views.login.LoginFrame;
import app.ui.views.login.RegisterFrame;
import app.util.ActionListenerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterFrameController extends Component implements ActionListener {


    private RegisterFrame registerFrame;

    public RegisterFrameController(RegisterFrame registerFrame){
        this.registerFrame = registerFrame;
        ActionListenerUtil.addActionListener(registerFrame.backButton, this);
        ActionListenerUtil.addActionListener(registerFrame.registerButton, this);
        ActionListenerUtil.addActionListener(registerFrame.showPasswordBox, this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerFrame.registerButton){
            String inputName = registerFrame.usernameField.getText();
            String inputPassword = Arrays.toString(registerFrame.passwordField.getPassword());
            String inputPasswordAgain = Arrays.toString(registerFrame.passwordAgainField.getPassword());

            if (!inputName.isEmpty() && !inputPassword.isEmpty() && !inputPasswordAgain.isEmpty()){
                if (inputPassword.equals(inputPasswordAgain)){
                    FileService.writeData(inputName, inputPassword);
                    JOptionPane.showMessageDialog(this, "You successfully registered!");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Passwords do not match.");
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }

        }
        else if (e.getSource() == registerFrame.backButton){
            LoginFrame loginFrame = new LoginFrame("Register", AppConfig.appSize);
            LoginFrameController loginFrameController = new LoginFrameController(loginFrame);

        }
        else if (e.getSource() == registerFrame.showPasswordBox){
            if(registerFrame.showPasswordBox.isSelected()) {
                registerFrame.passwordField.setEchoChar((char) 0);
            }
            else {
                registerFrame.passwordField.setEchoChar('●');
            }
        }
    }
}
