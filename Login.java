import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Login extends javax.swing.JFrame {

    public static JFrame frame = new JFrame("Login");

    public int validateCredentials(String username, String password) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("credentials.csv"));
        String line;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            if(values[0].equals(username) && values[1].equals(password)) {
                br.close();
                return 0; // success (username and password match)
            }
        }
        br.close();

        return -1; // failure (username and password do not match)
    }

    public void loginScreen(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setLayout(null);

        JLabel label = new JLabel("Login");
        label.setBounds(320, 160, 800, 100);
        label.setFont(new Font("Ariel", Font.PLAIN, 50));
        frame.add(label);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(185, 305, 100, 25);
        usernameLabel.setFont(new Font("Ariel", Font.PLAIN, 20));
        frame.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(185, 358, 100, 25);
        passwordLabel.setFont(new Font("Ariel", Font.PLAIN, 20));
        frame.add(passwordLabel);

        JTextField usernameInput = new JTextField();
        usernameInput.setBounds(300, 300, 180, 40);
        frame.add(usernameInput);

        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setBounds(300, 350, 180, 40);
        frame.add(passwordInput);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(300, 400, 180, 40);
        frame.add(loginButton);

        JLabel signupLabel = new JLabel("Don't have an account? Signup");
        signupLabel.setBounds(290, 450, 300, 40);
        signupLabel.setFont(new Font("Ariel", Font.PLAIN, 14));
        signupLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.add(signupLabel);

        signupLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Signup s = new Signup();
                s.signupScreen();
                frame.dispose();
            }
        });


        JLabel errorLabel = new JLabel("Username or password is incorrect. Please try again.");
        errorLabel.setBounds(230, 500, 500, 40);
        errorLabel.setFont(new Font("Ariel", Font.PLAIN, 14));
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        frame.add(errorLabel);

        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String[] credentials = new String[2];
                credentials[0] = usernameInput.getText();
                credentials[1] = passwordInput.getText();
                if (credentials[0].equals("") || credentials[1].equals("")) {
                    System.out.println("Please enter a username and password");
                    errorLabel.setVisible(true);
                } else {
                    try {
                        if (validateCredentials(credentials[0], credentials[1]) == 0) {
                            frame.dispose();
                            HomeScreen h = new HomeScreen();
                            h.homeScreen();
                        } else {
                            errorLabel.setVisible(true);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
    }
        });
    }

    
        
    
}
