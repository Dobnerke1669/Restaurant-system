package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame {
    private JLabel usernameLabel;
    private JTextField usernameInput;
    private JLabel passLabel;
    private JPasswordField passwordInput;
    private JButton registerButton;
    public RegisterGUI()
    {
        this.setTitle("Login window");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300,450);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        usernameLabel=new JLabel("Username:");
        usernameLabel.setBounds(25,50,100,50);
        usernameInput=new JTextField(20);
        usernameInput.setBounds(125,50,100,25);
        passLabel=new JLabel("Password:");
        passLabel.setBounds(25,150,100,50);
        passwordInput=new JPasswordField(20);
        passwordInput.setBounds(125,150,100,25);
        registerButton=new JButton("REGISTER");
        registerButton.setBounds(75,200,100,25);
        getContentPane().add(registerButton);
        getContentPane().add(usernameLabel);
        getContentPane().add(usernameInput);
        getContentPane().add(passLabel);
        getContentPane().add(passwordInput);
    }
    public void addRegisterListener(ActionListener mal) {
        registerButton.addActionListener(mal);
    }
    public String getUser()
    {
        return usernameInput.getText();
    }
    public String getPass()
    {
        return new String(passwordInput.getPassword());
    }
}
