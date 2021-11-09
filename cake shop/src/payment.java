import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class payment extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t4;
    JPasswordField t3;
    JRadioButton r1,r2,r3;
    JButton b1, b2;
    String cid,price,status;

    payment(String cid, String price) {
        super("PAYMENT");
        this.cid = cid;
        this.price = price;

        l1 = new JLabel(" Choose the payment option: ");
        l1.setBounds(240, 30, 350, 30);
        l1.setFont(new Font("System", Font.BOLD, 23));
        add(l1);

        r1 = new JRadioButton("Credit/Debit card");
        r1.setFont(new Font("Raleway", Font.BOLD, 18));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100, 90, 180, 30);
        add(r1);

        r2 = new JRadioButton("TCR UPI");
        r2.setFont(new Font("Raleway", Font.BOLD, 18));
        r2.setBackground(Color.WHITE);
        r2.setBounds(100, 270, 150, 30);
        add(r2);

        r3 = new JRadioButton("Cash on delivery");
        r3.setFont(new Font("Raleway", Font.BOLD, 18));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 320, 300, 30);
        add(r3);

        ButtonGroup groupgender = new ButtonGroup();
        groupgender.add(r1);
        groupgender.add(r2);
        groupgender.add(r3);

        l2 = new JLabel("Enter Card No: ");
        l2.setBounds(125, 130, 300, 23);
        l2.setFont(new Font("System", Font.BOLD, 18));
        add(l2);

        t1 = new JTextField(16);
        t1.setFont(new Font("Raleway", Font.BOLD, 18));
        t1.setBounds(270,130,200,25);
        add(t1);

        l3 = new JLabel("Expiry: ");
        l3.setBounds(125, 175, 300, 23);
        l3.setFont(new Font("System", Font.BOLD, 18));
        add(l3);

        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 18));
        t2.setBounds(200,175,50,25);
        add(t2);

        l4 = new JLabel("CVV: ");
        l4.setBounds(270, 175, 300, 23);
        l4.setFont(new Font("System", Font.BOLD, 18));
        add(l4);

        t3 = new JPasswordField();
        t3.setFont(new Font("Raleway", Font.BOLD, 18));
        t3.setBounds(320,175,50,25);
        add(t3);

        l5 = new JLabel("Name on card: ");
        l5.setBounds(125, 220, 300, 23);
        l5.setFont(new Font("System", Font.BOLD, 18));
        add(l5);

        t4 = new JTextField();
        t4.setFont(new Font("Raleway", Font.BOLD, 18));
        t4.setBounds(270,220,200,25);
        add(t4);

        b1 = new JButton("Back");
        b1.setBounds(230, 380, 100, 25);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);
        b1.addActionListener(this);

        b2 = new JButton("Confirm Booking");
        b2.setBounds(410, 380, 180, 25);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        add(b2);
        b2.addActionListener(this);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(800,480);
        setLocation(360,180);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            int i=0;
            if (ae.getSource() == b1) {
                setVisible(false);
                new booking(cid).setVisible(true);
            } else if (ae.getSource() == b2) {
                if(r1.isSelected())
                {
                    if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Fill all the required fields");
                    }
                    else{
                        String m = JOptionPane.showInputDialog("Enter OTP sent to your registered mobile number");
                        if(m.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Invalid OTP");
                        }
                        else{
                            i=1;
                            status = "PAID";
                        }

                    }
                }
                else if(r2.isSelected()){
                    String n = JOptionPane.showInputDialog("Enter UPI PIN");
                    if(n.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Invalid PIN");
                    }
                    else{
                        i=1;
                        status = "PAID";
                    }
                }
                else if(r3.isSelected()){
                    i=1;
                    status = "NOT PAID";
                }
                else
                    JOptionPane.showMessageDialog(null, "Choose a payment");
                if(i==1) {
                    JOptionPane.showMessageDialog(null, "Your Cylinder is booked");
                    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    java.util.Date date = Calendar.getInstance().getTime();
                    String booking = df.format(date);
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, 5);
                    String delivery = df.format(c.getTime());
                    Conn c1 = new Conn();
                    String q1 = "insert into booking values('" + cid + "','" + booking + "','" + delivery + "','" + price + "','" + status + "')";
                    c1.s.executeUpdate(q1);
                    setVisible(false);
                    new Services(cid).setVisible(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new payment("","").setVisible(true);
    }

}