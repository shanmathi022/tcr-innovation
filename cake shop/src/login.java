import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class login extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;

    login(){
        setTitle("TCR INNOVATION INTERSHIP PROJECT");
        l1 = new JLabel("CAKE SHOP MANAGEMENT SYSTEM");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200,40,450,45);
        add(l1);

        l2 = new JLabel("Consumer ID:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125,150,375,30);
        add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(310,150,230,30);
        tf1.setFont(new Font("Arial", Font.BOLD, 25));
        add(tf1);

        l3 = new JLabel("PASSWORD:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125,220,375,30);
        add(l3);

        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 25));
        pf2.setBounds(310,220,230,30);
        add(pf2);

        b1 = new JButton("SIGN UP");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300,300,100,30);
        add(b1);
        b1.addActionListener(this);

        b2 = new JButton("SIGN IN");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430,300,100,30);
        add(b2);
        b2.addActionListener(this);

        setLayout(null);
        getContentPane().setBackground(Color.GREEN);
        setSize(800,480);
        setLocation(360,180);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        try{
            if(ae.getSource()==b2){
                Conn c1 = new Conn();
                String cid  = tf1.getText();
                String pass  = pf2.getText();
                String q  = "select * from login where cid = '"+cid+"' and pass = '"+pass+"'";
                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Services(cid).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "INVALID CREDENTIALS");
                    tf1.setText("");
                    pf2.setText("");
                }
            }else if(ae.getSource()==b1){
                setVisible(false);
                new signup().setVisible(true);
            }else if(ae.getSource()==b3){
                setVisible(false);
                new reset().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new login().setVisible(true);
    }
}