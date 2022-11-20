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
import java.io.IOException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class HomeScreen extends javax.swing.JFrame {

    public static JFrame frame = new JFrame("Home Screen");
    public static int selectedIndex = 0;
    ImageIcon imageIcon[] = { new ImageIcon("chair.png"), new ImageIcon("table.png"), 
    new ImageIcon("couch.png"), new ImageIcon("bed.png") };
    Integer array[] = {1,2,3,4};
    String[] names = new String[4];
    Integer quantities[] = {0,0,0,0};
    Integer prices[] = {0,0,0,0};
    public static Integer[] Cart = {0,0,0,0};

    ComboBoxRenderer renderer = new ComboBoxRenderer();
    
    JComboBox box = new JComboBox(array);


    
    public void readCSV() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stock.csv"));
        String line;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            
            if(values[0].equals("Chair")) {
                names[0] = values[0];
                if (Integer.parseInt(values[1]) > 0) {
                    quantities[0] = Integer.parseInt(values[1]);
                } else {
                    quantities[0] = 0;
                }
                prices[0] = Integer.parseInt(values[2]);
            } else if (values[0].equals("Table")) {
                names[1] = values[0];
                if (Integer.parseInt(values[1]) > 0) {
                    quantities[1] = Integer.parseInt(values[1]);
                } else {
                    quantities[1] = 0;
                }
                prices[1] = Integer.parseInt(values[2]);
            } else if (values[0].equals("Couch")) {
                names[2] = values[0];
                if (Integer.parseInt(values[1]) > 0) {
                    quantities[2] = Integer.parseInt(values[1]);
                } else {
                    quantities[2] = 0;
                }
                prices[2] = Integer.parseInt(values[2]);
            } else if (values[0].equals("Bed")) {
                names[3] = values[0];
                if (Integer.parseInt(values[1]) > 0) {
                    quantities[3] = Integer.parseInt(values[1]);
                } else {
                    quantities[3] = 0;
                }
                prices[3] = Integer.parseInt(values[2]);
            }
        }
        br.close();
    }
    public class ComboBoxRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, 
                                                    Object value, 
                                                    int index, 
                                                    boolean isSelected, 
                                                    boolean cellHasFocus) {
        
        

        

        selectedIndex = ((Integer)value).intValue() - 1;
        int offset = ((Integer)value).intValue() - 1 ;    

        ImageIcon icon = imageIcon[offset];
        String name = names[offset];

        setIcon(icon);
        setText(name + " - " + quantities[offset] + " left - $" + prices[offset]);

        return this;
    }


    }

    public void homeScreen() throws IOException {

        
        readCSV();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setLayout(null);

        box.setRenderer(renderer);
        box.setBounds(100, 200, 500, 100);
        frame.add(box);

        JLabel label = new JLabel("Welcome to iFurnex");
        label.setBounds(100, 70, 800, 100);
        label.setFont(new Font("Ariel", Font.PLAIN, 30));
        frame.add(label);


        JButton addButton = new JButton("Add to Cart");
        addButton.setBounds(100, 400, 250, 50);
        frame.add(addButton);

        JButton cartButton = new JButton("View Cart");
        cartButton.setBounds(400, 400, 250, 50);
        frame.add(cartButton);


        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(600, 50, 150, 50);
        logoutButton.setFont(new Font("Ariel", Font.PLAIN, 20));
        frame.add(logoutButton);

        JLabel addedLabel = new JLabel("Added to cart");
        addedLabel.setBounds(110, 280, 800, 100);
        addedLabel.setFont(new Font("Ariel", Font.PLAIN, 15));
        frame.add(addedLabel);
        addedLabel.setVisible(false);

    
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(names[selectedIndex] != null) {
                    if(Cart[selectedIndex] < quantities[selectedIndex]) {
                        Cart[selectedIndex] += 1;
                        addedLabel.setVisible(true);
                    } else {
                        addedLabel.setText("Not enough stock (" + Cart[selectedIndex] + " selected)");
                        addedLabel.setVisible(true);
                        return;
                    }
                }
                addedLabel.setVisible(true);
                addedLabel.setText("Added " + names[selectedIndex] + " to cart - " + Cart[selectedIndex] + " in cart");
            }
        });

        


        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CartScreen C = new CartScreen();
                try {
                    C.cartScreen(Cart, quantities, prices);
                    addedLabel.setVisible(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(selectedIndex);
                frame.dispose();
                Login L = new Login();
                L.loginScreen();
                
            }
        });
    }
}
