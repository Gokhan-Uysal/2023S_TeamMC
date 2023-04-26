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

public class RegisterFrame1 extends JFrame implements ActionListener {

    JPasswordField password, passwordAgain;
    JTextField username;
    JLabel label_password, label_passwordAgain, label_username, message, title;
    JButton btn;
    JCheckBox showPassword;

    RegisterFrame1() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setTitle("Register");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        label_username = new JLabel("Username");
        label_username.setBounds(50,150,100,40);

        label_password = new JLabel("Password");
        label_password.setBounds(50,200,100,40);

        label_passwordAgain = new JLabel("Password Again");
        label_passwordAgain.setBounds(50,250,100,40);

        //message = new JLabel("Message here");
        //message.setBounds(350,360,300,40);

        username = new JTextField();
        username.setBounds(150,150,300,40);

        password = new JPasswordField();
        password.setBounds(150,200,300,40);

        passwordAgain = new JPasswordField();
        passwordAgain.setBounds(150,250,300,40);

        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(150,300,200,40);
        showPassword.addActionListener(this);

        btn = new JButton("Register");
        btn.setBounds(200,350,100,40);
        btn.addActionListener(this);
        btn.setForeground(Color.black);


        this.add(label_username);
        this.add(label_password);
        this.add(label_passwordAgain);
        this.add(username);
        this.add(password);
        this.add(passwordAgain);
        this.add(showPassword);
        this.add(btn);

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
                passwordAgain.setEchoChar((char) 0);
            }
            else {
                password.setEchoChar('●');
                passwordAgain.setEchoChar('●');
            }
        }

    }

}
