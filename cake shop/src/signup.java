import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class signup extends JFrame implements ActionListener{

    JLabel l1,l2,l3,l4,l8,l13;
    JTextField t1,t2,t4,t9;
    JButton b,b1;

    signup(){
        setTitle("NEW USERNAME");

        l1 = new JLabel("NEW CUSTOMER ");
        l1.setFont(new Font("Raleway", Font.BOLD, 38));
        l1.setBounds(230,20,600,40);
        add(l1);

        l2 = new JLabel("Enter Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(340,80,600,30);
        add(l2);

        l3 = new JLabel("Name:");
        l3.setFont(new Font("Raleway", Font.BOLD, 20));
        l3.setBounds(100,140,100,30);
        add(l3);

        l4 = new JLabel("Customer No:");
        l4.setFont(new Font("Raleway", Font.BOLD, 20));
        l4.setBounds(100,190,200,30);
        add(l4);

        l8 = new JLabel("Phone Number:");
        l8.setFont(new Font("Raleway", Font.BOLD, 20));
        l8.setBounds(100,240,200,30);
        add(l8);

        l13 = new JLabel("Password:");
        l13.setFont(new Font("Raleway", Font.BOLD, 20));
        l13.setBounds(100,290,200,30);
        add(l13);

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 14));
        t1.setBounds(300,140,400,30);
        add(t1);

        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 14));
        t2.setBounds(300,190,400,30);
        add(t2);

        t4 = new JTextField();
        t4.setFont(new Font("Raleway", Font.BOLD, 14));
        t4.setBounds(300,240,400,30);
        add(t4);

        t9 = new JTextField();
        t9.setFont(new Font("Raleway", Font.BOLD, 14));
        t9.setBounds(300,290,400,30);
        add(t9);

        b = new JButton("Submit");
        b.setFont(new Font("Raleway", Font.BOLD, 14));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setBounds(570,700,120,30);
        add(b);
        b.addActionListener(this);


        b1 = new JButton("Back");
        b1.setFont(new Font("Raleway", Font.BOLD, 14));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(200,700,80,30);
        add(b1);
        b1.addActionListener(this);


        setLayout(null);
        getContentPane().setBackground(Color.PINK);
        setSize(850,800);
        setLocation(340,10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        String cname = t1.getText();
        String cid = t2.getText();
        String phone = t4.getText();
        String pass = t9.getText();

        try{
            if (ae.getSource() == b1) {
                setVisible(false);
                new login().setVisible(true);
            }
            else if(ae.getSource()==b) {
                if (t1.getText().equals("") || t2.getText().equals("") || t4.getText().equals("")
                || t9.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill all the required fields");
                } else {
                    Conn c1 = new Conn();
                    String q1 = "insert into details values('" + cid + "','" + cname + "','" + phone + "')";
                    String q2 = "insert into login values('" + cid + "','" + pass + "')";
                    c1.s.executeUpdate(q1);
                    c1.s.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "CONNECTION ADDED SUCCESSFULLY");
                    new login().setVisible(true);
                    setVisible(false);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new signup().setVisible(true);
    }
}