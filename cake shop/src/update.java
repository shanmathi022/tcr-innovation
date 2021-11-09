import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;

public class update extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l8,l9,l12;
    JTextField t1,t2,t4,t5,t8;
    JButton b,b1;
    String cid;
    update(String cid){
        this.cid=cid;
        setTitle("UPDATE FORM");

        l1 = new JLabel("UPDATE CUSTOMER ");
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

        l4 = new JLabel("Password:");
        l4.setFont(new Font("Raleway", Font.BOLD, 20));
        l4.setBounds(100,190,200,30);
        add(l4);

        l8 = new JLabel("Phone Number:");
        l8.setFont(new Font("Raleway", Font.BOLD, 20));
        l8.setBounds(100,240,200,30);
        add(l8);

        l9 = new JLabel("Address:");
        l9.setFont(new Font("Raleway", Font.BOLD, 20));
        l9.setBounds(100,290,200,30);
        add(l9);

        l12 = new JLabel("Pin Code:");
        l12.setFont(new Font("Raleway", Font.BOLD, 20));
        l12.setBounds(100,340,200,30);
        add(l12);

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

        t5 = new JTextField();
        t5.setFont(new Font("Raleway", Font.BOLD, 14));
        t5.setBounds(300,290,400,30);
        add(t5);

        t8 = new JTextField();
        t8.setFont(new Font("Raleway", Font.BOLD, 14));
        t8.setBounds(300,340,400,30);
        add(t8);

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
        getContentPane().setBackground(Color.BLUE);
        setSize(850,800);
        setLocation(340,10);
        setVisible(true);

        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from details where cid = '"+cid+"'");

            if(rs.next()){
                t1.setText(rs.getString("cname"));
                t4.setText(rs.getString("phone"));
                t5.setText(rs.getString("address"));
                t8.setText(rs.getString("pin"));
            }
            ResultSet rss = c1.s.executeQuery("select * from login where cid = '"+cid+"'");
            if(rss.next()) {
                t2.setText(rss.getString("pass"));
            }
        }catch(Exception ee){
            ee.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae){

        String cname = t1.getText();
        String pass = t2.getText();
        String phone = t4.getText();
        String address = t5.getText();
        String pincode = t8.getText();

        try{
            if (ae.getSource() == b1) {
                setVisible(false);
                new Services(cid).setVisible(true);
            }
            else if(ae.getSource()==b) {
                if (t1.getText().equals("") || t2.getText().equals("") || t4.getText().equals("")
                        || t5.getText().equals("") || t8.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill all the required fields");
                } else {
                    Conn c1 = new Conn();
                    String q1 = "update details set cname='"+cname+"',phone='"+phone+"',address='"+address+"',pin='"+pincode+"' where cid='"+cid+"'";
                    String q2 = "update login set pass='"+pass+"' where cid='"+cid+"'";
                    c1.s.executeUpdate(q1);
                    c1.s.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "UPDATED SUCCESSFULLY");
                    new Services(cid).setVisible(true);

                    setVisible(false);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){new update("").setVisible(true);
    }
}