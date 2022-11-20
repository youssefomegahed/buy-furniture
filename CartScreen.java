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
import java.util.Scanner;


public class CartScreen extends javax.swing.JFrame {

    public static JFrame frame = new JFrame("Cart");

    public static int totalPrice = 0;
    public static String products = "";
    public static String formatted = "";
    

    public void cartScreen(Integer Cart[], Integer quantities[], Integer prices[]) throws IOException {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setLayout(null);

        JLabel label = new JLabel("Cart");
        label.setBounds(320, 160, 800, 100);
        label.setFont(new Font("Ariel", Font.PLAIN, 50));
        frame.add(label);

        
        JLabel productLabel = new JLabel("Products:");
        productLabel.setBounds(185, 305, 100, 25);
        productLabel.setFont(new Font("Ariel", Font.PLAIN, 20));
        frame.add(productLabel);

        products = "";
        // if an item is in the cart, add it to the products string
        if(Cart[0] > 0){
            System.out.println(Cart[0]);
            products += Cart[0] + " Chair(s) - $" + prices[0]*Cart[0] + " - " + (quantities[0]-Cart[0]) + " left in stock\n" ;
            totalPrice += prices[0]*Cart[0];
        } 
        if (Cart[1] > 0){
            products += Cart[1] + " Table(s) - $" + prices[1]*Cart[1] + " - " + (quantities[1]-Cart[1]) + " left in stock\n" ;
            totalPrice += prices[1]*Cart[1];
        } 
        if (Cart[2] > 0){
            products += Cart[2] + " Couch(es) - $" + prices[2]*Cart[2] + " - " + (quantities[2]-Cart[2])  + " left in stock\n" ;
            totalPrice += prices[2]*Cart[2];
        }
        if (Cart[3] > 0){
            products += Cart[3] + " Bed(s) - $" + prices[3]*Cart[3] + " - " + (quantities[3]-Cart[3]) + " left in stock\n" ;
            totalPrice += prices[3]*Cart[3];
        }

        formatted = products.replace("\n", "<br>");
        formatted = "<html><font size='5'>" + formatted + "</font></html>";
        JLabel productsLabel = new JLabel();
        if (products.equals("")){
            productsLabel.setText("No products in cart");
        } else {
            
            productsLabel.setText(formatted);
        }
        productsLabel.setBounds(295, -50, 800, 800);
        productsLabel.setFont(new Font("Ariel", Font.PLAIN, 9));
        frame.add(productsLabel);

        JLabel priceLabel = new JLabel("$" + totalPrice);
        priceLabel.setBounds(295, 460, 100, 25);
        priceLabel.setFont(new Font("Ariel", Font.PLAIN, 20));
        frame.add(priceLabel);



      
        JLabel totalLabel = new JLabel("Total");
        totalLabel.setBounds(185, 464, 100, 25);
        totalLabel.setFont(new Font("Ariel", Font.PLAIN, 20));
        frame.add(totalLabel);



        
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(300, 600, 180, 40);
        frame.add(checkoutButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(300, 650, 180, 40);
        frame.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                priceLabel.setVisible(false);
                System.out.print("Back button clicked");
                productsLabel.setVisible(false);
                totalPrice = 0;
                products = "";
                formatted = "";
                productsLabel.setText("");
                priceLabel.setText("");
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.dispose();
                HomeScreen H = new HomeScreen();
                try {
                    H.homeScreen();
                    
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                priceLabel.setVisible(false);
                productsLabel.setVisible(false);
                totalPrice = 0;
                products = "";
                productLabel.setText("");
                priceLabel.setText("");
                try {
                    updateCSV(Cart, quantities, prices);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.dispose();
                
                HomeScreen H = new HomeScreen();
                try {
                    H.homeScreen();
                    
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        



        
    }

    public void updateCSV(Integer Cart[], Integer quantities[], Integer prices[]) throws IOException {
        try {
            File file = new File("stock.csv");
            FileWriter writer = new FileWriter(file);


            quantities[0] -= Cart[0];
            quantities[1] -= Cart[1];
            quantities[2] -= Cart[2];
            quantities[3] -= Cart[3];
            
            Cart[0] = 0;
            Cart[1] = 0;
            Cart[2] = 0;
            Cart[3] = 0;

            writer.write("Chair,"+quantities[0]+","+prices[0]+"\n");
            writer.write("Table,"+quantities[1]+","+prices[1]+"\n");
            writer.write("Couch,"+quantities[2]+","+prices[2]+"\n");
            writer.write("Bed,"+quantities[3]+","+prices[3]+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
        
    
}
