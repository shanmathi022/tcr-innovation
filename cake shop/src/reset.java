import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class reset extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JDateChooser d;
    JButton b1,b2;

    reset(){
        setTitle("RESET PASSWORD");

        l1 = new JLabel("Confirm your identity");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(180,40,450,40);
        add(l1);

        l2 = new JLabel("Consumer ID:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125,150,375,30);
        add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(300,150,230,30);
        tf1.setFont(new Font("Arial", Font.BOLD, 25));
        add(tf1);

        l3 = new JLabel("Date of birth:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125,220,375,30);
        add(l3);

        d = new JDateChooser();
        d.setFont(new Font("Arial", Font.BOLD, 25));
        d.setBounds(300,220,230,30);
        add(d);

        b1 = new JButton("BACK");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("VERIFY");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        setLayout(null);

        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300,300,100,30);
        add(b1);
        b1.addActionListener(this);

        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430,300,100,30);
        add(b2);
        b2.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(800,480);
        setLocation(360,180);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        try{
            if(ae.getSource()==b2){
                Conn c1 = new Conn();
                String cid  = tf1.getText();
                String dob = ((JTextField) d.getDateEditor().getUiComponent()).getText();
                String q  = "select * from details where cid = '"+cid+"'";
                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()) {
                    if (rs.getString("dob").equals(dob)) {
                        setVisible(false);
                        new reset1(cid).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "INCORRECT DETAILS");
                        tf1.setText("");
                        d.setCalendar(null);
                    }
                }
            }else if(ae.getSource()==b1){
                setVisible(false);
                new login().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new reset().setVisible(true);
    }
}