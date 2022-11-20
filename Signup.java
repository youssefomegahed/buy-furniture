import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Signup extends javax.swing.JFrame {

    public static JFrame frame = new JFrame("Signup");

    public int appendCredentials(String[] credentials) throws IOException {
        File csvFile = new File("credentials.csv");
        FileWriter fileWriter = new FileWriter(csvFile, true);


        BufferedReader br = new BufferedReader(new FileReader("credentials.csv"));
        String line;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            if(values[0].equals(credentials[0])) {
                br.close();
                return -1; // failure
            }
        }
        br.close();
        
        StringBuilder line1 = new StringBuilder();
        for (int i = 0; i < credentials.length; i++) {
            line1.append(credentials[i].replaceAll("\"","\"\""));
            if (i != credentials.length - 1) {
                line1.append(',');
            } 
        }
        line1.append('\n');
        fileWriter.write(line1.toString());
        
        fileWriter.close();

        return 0; // success
    }

    public void signupScreen() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setLayout(null);

        JLabel label = new JLabel("Create an Account");
        label.setBounds(180, 160, 800, 100);
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

        JTextField passwordInput = new JTextField();
        passwordInput.setBounds(300, 350, 180, 40);
        frame.add(passwordInput);

        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(300, 400, 180, 40);
        frame.add(signupButton);

        JLabel loginLabel = new JLabel("Already have an account? Login");
        loginLabel.setBounds(290, 450, 300, 40);
        loginLabel.setFont(new Font("Ariel", Font.PLAIN, 14));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.add(loginLabel);

        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.dispose();
                Login L = new Login();
                L.loginScreen();
            }
        });

        JLabel errorLabel = new JLabel("Username already exists. Please try again.");
        errorLabel.setBounds(260, 500, 300, 40);
        errorLabel.setFont(new Font("Ariel", Font.PLAIN, 14));
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        frame.add(errorLabel);


        signupButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Signup s = new Signup();
                String[] credentials = new String[2];
                credentials[0] = usernameInput.getText();
                credentials[1] = passwordInput.getText();
                if (credentials[0].equals("") || credentials[1].equals("")) {
                    System.out.println("Please enter a username and password");
                } else {
                    try {
                        if (s.appendCredentials(credentials) == 0) {
                            frame.dispose();
                            HomeScreen H = new HomeScreen();
                            H.homeScreen();
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
