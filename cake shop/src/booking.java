import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class booking extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    JButton b,b1, b2;
    JComboBox c;
    String cid,price;
    booking(String cid){
        super("details");
        this.cid=cid;

        l1 = new JLabel("Booking details");
        l1.setBounds(90, 20, 250, 33);
        l1.setFont(new Font("Raleway", Font.BOLD, 28));
        add(l1);

        l2 = new JLabel("Please verify the details");
        l2.setBounds(80, 60, 300, 23);
        l2.setFont(new Font("System", Font.BOLD, 20));
        add(l2);

        l3 = new JLabel();
        l3.setBounds(20, 100, 300, 23);
        l3.setFont(new Font("System", Font.BOLD, 18));
        add(l3);

        l4 = new JLabel();
        l4.setBounds(20, 135, 300, 23);
        l4.setFont(new Font("System", Font.BOLD, 18));
        add(l4);

        l5 = new JLabel();
        l5.setBounds(20, 170, 300, 23);
        l5.setFont(new Font("System", Font.BOLD, 18));
        add(l5);

        l6 = new JLabel();
        l6.setBounds(20, 205, 350, 23);
        l6.setFont(new Font("System", Font.BOLD, 18));
        add(l6);

        l7 = new JLabel();
        l7.setBounds(20, 240, 300, 23);
        l7.setFont(new Font("System", Font.BOLD, 18));
        add(l7);

        l8 = new JLabel();
        l8.setBounds(20, 275, 300, 23);
        l8.setFont(new Font("System", Font.BOLD, 18));
        add(l8);

        l9 = new JLabel();
        l9.setBounds(20, 310, 300, 23);
        l9.setFont(new Font("System", Font.BOLD, 18));
        add(l9);

        l10 = new JLabel();
        l10.setBounds(20, 345, 300, 23);
        l10.setFont(new Font("System", Font.BOLD, 18));
        add(l10);

        l11 = new JLabel(" Select Cake:");
        l11.setBounds(20, 380, 150, 23);
        l11.setFont(new Font("System", Font.BOLD, 18));
        add(l11);

        c = new JComboBox();
        c.setModel(new DefaultComboBoxModel(new String[] {"","BLACK FOREST","BLACK CURRANT","STRAWBERRY"}));
        c.setBounds(140,380,150,23);
        add(c);

        b = new JButton("Fetch");
        b.setBounds(300, 380, 80, 23);
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        add(b);
        b.addActionListener(this);

        l12 = new JLabel();
        l12.setBounds(20, 415, 150, 23);
        l12.setFont(new Font("System", Font.BOLD, 18));
        add(l12);

        b1 = new JButton("Back");
        b1.setBounds(30, 515, 100, 25);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);
        b1.addActionListener(this);

        b2 = new JButton("Proceed to Payment");
        b2.setBounds(170,515,200,25);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        add(b2);
        b2.addActionListener(this);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from details where cid = '"+cid+"'");
            if(rs.next()){

                l3.setText(" Consumer ID: "+rs.getString("cid"));
                l4.setText(" Consumer Name: " +rs.getString("cname"));
                l5.setText(" Phone no: "+rs.getString("phone"));
                l6.setText(" Email ID: "+rs.getString("email"));
                l7.setText(" Address: "+rs.getString("address"));
                l8.setText(" City: "+rs.getString("city"));
                l9.setText(" State: "+rs.getString("state"));
                l10.setText(" PinCode: "+rs.getString("pin") );

            }
        }catch(Exception e){
            e.printStackTrace();
        }


        setLayout(null);
        getContentPane().setBackground(Color.BLUE);
        setSize(400,600);
        setLocation(570,90);
    }
    public void actionPerformed(ActionEvent ae) {
        try{
           String c1 = (String) c.getSelectedItem();
        if (ae.getSource() == b) {
            if (c1.equals("")) {
                l12.setText("");
                JOptionPane.showMessageDialog(null, "Select Cake");
            }
            else if (c1.equals("BLACK FOREST")) {
                price = "350";
                l12.setText(" Price: " + price);
            }
            else if (c1.equals("BLACK CURRANT")) {
                price = "450";
                l12.setText(" Price: " + price);
            }
            else if (c1.equals("STRAWBERRY")) {
                price = "750";
                l12.setText(" Price: " + price);
            }
        } else if (ae.getSource() == b1) {
            setVisible(false);
            new Services(cid).setVisible(true);
        } else if (ae.getSource() == b2) {
            if(c1.equals(""))
                JOptionPane.showMessageDialog(null, "Select Cake");
            else {
                setVisible(false);
                new payment(cid, price).setVisible(true);
            }
        }
    }catch (Exception e) {
        e.printStackTrace();
    }
    }
    public static void main(String[] args){
        new booking("").setVisible(true);
    }

}
