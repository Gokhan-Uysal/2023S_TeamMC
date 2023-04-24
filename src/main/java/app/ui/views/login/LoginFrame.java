package app.ui.views.login;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {

    JPasswordField password;
    JTextField username;
    JLabel label_password, label_username, message, title;
    JButton btn, register;
    JCheckBox showPassword;

    LoginFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setTitle("Login");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        label_username = new JLabel("Username");
        label_username.setBounds(200,150,100,40);

        label_password = new JLabel("Password");
        label_password.setBounds(200,200,100,40);

        //message = new JLabel("Message here");
        //message.setBounds(350,360,300,40);

        username = new JTextField();
        username.setBounds(300,150,300,40);

        password = new JPasswordField();
        password.setBounds(300,200,300,40);

        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(300,250,200,40);
        showPassword.addActionListener(this);

        btn = new JButton("Sign In");
        btn.setBounds(300,300,100,40);
        btn.addActionListener(this);
        btn.setForeground(Color.black);

        register = new JButton("Register");
        register.setBounds(400,300,100,40);
        register.addActionListener(this);
        register.setForeground(Color.black);


        this.add(label_username);
        this.add(label_password);
        this.add(username);
        this.add(password);
        this.add(showPassword);
        this.add(btn);
        this.add(register);
        //this.add(message);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if(e.getSource()==btn) {
            JOptionPane.showMessageDialog(this, "You successfully logged in ");
        }
        else if(e.getSource()==showPassword) {
            if(showPassword.isSelected()) {
                password.setEchoChar((char) 0);
            }
            else {
                password.setEchoChar('●');
            }
        }
        else if(e.getSource()==register) {
            //RegisterScreen registerScreen = new RegisterScreen();
        }

    }

}

