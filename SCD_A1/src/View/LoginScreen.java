package View;

import Controller.CustomerMenu;
import Controller.EmployeeMenu;
import Controller.LoginMenu;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class LoginScreen {
    private final JFrame frame1;
    private final JFormattedTextField usernameTextField;
    private final JPasswordField passwordTextField;
    private final JButton loginButton;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;

    public LoginScreen() {
        frame1 = new JFrame();
        usernameTextField = new JFormattedTextField();
        passwordTextField = new JPasswordField();
        loginButton = new JButton();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        init();
    }

    private void init() {
        initUsernameTextField();
        initPasswordTextField();
        initLoginButton();
        initUsernameLabel();
        initPasswordLabel();
        initFrame1();
    }

    private void initFrame1() {
        frame1.setTitle("Login to LESCO");
        ImageIcon logo = new ImageIcon("SCD_A1/src/Assets/lesco-pk-logo.png");
        frame1.setIconImage(logo.getImage());
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame1.setBounds(560, 190, 800, 600);
        frame1.setVisible(true);
        frame1.setLayout(null);
        frame1.add(usernameTextField);
        frame1.add(passwordTextField);
        frame1.add(loginButton);
        frame1.add(usernameLabel);
        frame1.add(passwordLabel);
        frame1.setMinimumSize(new Dimension(400, 300));
    }

    private void initUsernameTextField() {
        usernameTextField.setBounds(100, 10, 100, 20);
        usernameTextField.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    private void initPasswordTextField() {
        passwordTextField.setBounds(100, 70, 100, 20);
        passwordTextField.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    private void initUsernameLabel() {
        usernameLabel.setBounds(10, 10, 70, 20);
        usernameLabel.setText("Username");
        usernameLabel.setOpaque(true);
    }

    private void initPasswordLabel() {
        passwordLabel.setBounds(10, 70, 70, 20);
        passwordLabel.setText("Password");
        passwordLabel.setOpaque(true);
    }

    private void initLoginButton() {
        loginButton.setBounds(100, 110, 100, 30);
        loginButton.setText("Login");
        loginButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            char[] password = passwordTextField.getPassword();
            Controller.UserWrapper myUser = new Controller.UserWrapper();
            int loginStatus = myUser.getLoginStatus(username, String.valueOf(password));
            if (loginStatus == LoginMenu.CUSTOMER_ID) {
                CustomerMenu customerMenu = new CustomerMenu(myUser.getMyUser());
            } else if (loginStatus == LoginMenu.EMPLOYEE_ID) {
                EmployeeMenu employeeMenu = new EmployeeMenu(myUser.getMyUser());
            } else {
                JOptionPane.showMessageDialog(frame1, "Invalid login credentials! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                usernameTextField.setText("");
                passwordTextField.setText("");
            }
        });
    }
}
