import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

public class Services extends JFrame implements ActionListener{
    JLabel l1,l3;
    JButton b1,b2,b3,b4,b5;
    String cid;
    Services(String cid){
        super("Service");
        this.cid = cid;

        l1 = new JLabel("Choose your service");
        l1.setForeground(Color.DARK_GRAY);
        l1.setBounds(100,55,700,40);
        l1.setFont(new Font("Raleway", Font.BOLD, 33));
        add(l1);

        l3 = new JLabel();
        l3.setForeground(Color.DARK_GRAY);
        l3.setFont(new Font("System", Font.BOLD, 23));
        l3.setBounds(60,20,400,27);
        add(l3);

        b1 = new JButton("BOOK YOUR CAKE");
        b1.setBounds(180,130,170,40);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);
        b1.addActionListener(this);


        b2 = new JButton("CHECK STATUS");
        b2.setBounds(180,210,170,40);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        add(b2);
        b2.addActionListener(this);

        b3 = new JButton("UPDATE DETAILS");
        b3.setBounds(180,290,170,40);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        add(b3);
        b3.addActionListener(this);

        b4 = new JButton("DELETE CONNECTION");
        b4.setBounds(180,370,170,40);
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        add(b4);
        b4.addActionListener(this);

        b5 = new JButton("logout");
        b5.setBounds(400,15,80,30);
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        add(b5);
        b5.addActionListener(this);

        setLayout(null);
        getContentPane().setBackground(Color.YELLOW);
        setSize(550,550);
        setLocation(500,150);
        setVisible(true);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from details where cid = '"+cid+"'");
            if(rs.next()){
                l3.setText("welcome: " + rs.getString("cname") + "!!!");
            }
        } catch(Exception e){
            e.printStackTrace();
            }
        }

    public void actionPerformed(ActionEvent ae){
        try {
            if (ae.getSource() == b1) {
                setVisible(false);
                new booking(cid).setVisible(true);
            }
            else if (ae.getSource() == b2) {
                Conn c1 = new Conn();
                String q = "select * from booking where cid = '" + cid + "'";
                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    Object[] options = {"Back", "Cancel Booking"};
                    int r = JOptionPane.showOptionDialog(null, "BOOKED !!!\n Booked on: " + rs.getString("booked_date")
                                    + "\n Expected delivery: " + rs.getString("expected_delivery")
                                    + "\n bill: " + rs.getString("bill"), "STATUS",
                            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (r == JOptionPane.NO_OPTION) {
                        if (JOptionPane.showConfirmDialog(null, "Do you want to cancel the booking?", "WARNING",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            Conn c = new Conn();
                            String s = "delete from booking where cid = '" + cid + "'";
                            c.s.executeUpdate(s);
                            JOptionPane.showMessageDialog(null, "Booking Cancelled !!");
                            setVisible(false);
                            new Services(cid).setVisible(true);
                        }
                    }
                }
                else {
                    Object[] option = {"BACK", "BOOK NOW"};
                    int r1 = JOptionPane.showOptionDialog(null, "NO BOOKING FOUND !!!\n BOOK NOW ? ", "STATUS",
                            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
                    if (r1 == JOptionPane.NO_OPTION) {
                        setVisible(false);
                        new booking(cid).setVisible(true);
                    }
                }
            } else if (ae.getSource() == b3) {
                setVisible(false);
                new update(cid).setVisible(true);
            } else if (ae.getSource() == b4) {
                if (JOptionPane.showConfirmDialog(null, "Do you wanna delete this connection?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Conn c1 = new Conn();
                    String q = "delete from login where cid = '" + cid + "'";
                    String q1 = "delete from details where cid = '" + cid + "'";
                    c1.s.executeUpdate(q);
                    c1.s.executeUpdate(q1);
                    JOptionPane.showMessageDialog(null, "Connection deleted !!");
                    setVisible(false);
                    new login().setVisible(true);
                }
            } else if (ae.getSource() == b5) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure?", "LOGOUT",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    new login().setVisible(true);
                }
            }
        }
        catch(Exception e){
                e.printStackTrace();
            }
    }

    public static void main(String[] args){
        new Services("").setVisible(true);
    }
}