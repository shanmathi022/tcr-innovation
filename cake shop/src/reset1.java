import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class reset1 extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JPasswordField pf1,pf2;
    JButton b1,b2;
    String cid;
    reset1(String cid){
        setTitle("NEW PASSWORD");
        this.cid=cid;
        l1 = new JLabel("Set new password");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200,40,450,40);
        add(l1);

        l2 = new JLabel("New password:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(110,150,375,30);
        add(l2);

        pf1 = new JPasswordField(15);
        pf1.setBounds(360,150,230,30);
        pf1.setFont(new Font("Arial", Font.BOLD, 25));
        add(pf1);

        l3 = new JLabel("Re-type password:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(110,220,375,30);
        add(l3);

        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 25));
        pf2.setBounds(360,220,230,30);
        add(pf2);

        b1 = new JButton("BACK");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300,300,100,30);
        add(b1);
        b1.addActionListener(this);

        b2 = new JButton("RESET");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430,300,100,30);
        add(b2);
        b2.addActionListener(this);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(800,480);
        setLocation(360,180);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        try{
            if(ae.getSource()==b2){
                String pass  = pf1.getText();
                String pass1  = pf2.getText();
                if(pass.equals(pass1)){
                    Conn c1 = new Conn();
                    String q  = "update login set pass = '"+pass+"' where cid = '"+cid+"'";
                    c1.s.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Password reset successfully");
                    setVisible(false);
                    new login().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "INCORRECT PASSWORD");
                    pf1.setText("");
                    pf2.setText("");
                }
            }else if(ae.getSource()==b1){
                setVisible(false);
                new reset().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new reset1("").setVisible(true);
    }
}