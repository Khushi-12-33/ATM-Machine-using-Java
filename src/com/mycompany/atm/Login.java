package com.mycompany.atm;

//for importing jframe
import javax.swing.*;
//for image we imported all the class present in class java.awt
import java.awt.*;

//for event listner
import java.awt.event.*;
import java.sql.*;

//here jframe is inherited
public class Login extends JFrame implements ActionListener
{
    JButton login,signup,clear;
    JTextField CardTextField;
    JPasswordField pinTextField;
    Login()
    {
         setTitle("AUTOMATED TALOR MACHINE");
         
         setLayout(null);
//         adding image we have a class imageicon
            ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/logo.jpg"));
            Image i2=i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            ImageIcon i3=new ImageIcon(i2);
            JLabel label=new JLabel(i3);
            label.setBounds(70,10,100,100);
        add(label);

//        1
        JLabel text=new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Osward",Font.BOLD,38));
        text.setBounds(200,40,400,40);
        add(text);

//        2
        JLabel cardno=new JLabel("Card No: ");
        cardno.setFont(new Font("Railway",Font.BOLD,28));
        cardno.setBounds(120,150,400,30);
        add(cardno);
       
         CardTextField = new JTextField();
        CardTextField.setBounds(300,150,230,30);
        CardTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(CardTextField);
//3
JLabel pin=new JLabel("PIN: ");
        pin.setFont(new Font("Osward",Font.BOLD,28));
        pin.setBounds(120,220,400,30);
        add(pin);
       
         pinTextField=new JPasswordField();
        pinTextField.setBounds(300,220,230,30);
       pinTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);
       
//        BUTTON 1

 login=new JButton("SIGN IN");
login.setBounds(300,300,100,30);
login.setBackground(Color.BLACK);
login.setForeground(Color.WHITE);
login.addActionListener(this);
add(login);

//butoon 2
 clear=new JButton("CLEAR");
clear.setBounds(430,300,100,30);
clear.setBackground(Color.BLACK);
clear.setForeground(Color.WHITE);
clear.addActionListener(this);
add(clear);

//3 button
 signup=new JButton("SIGN UP");
signup.setBounds(300,350,230,30);
signup.setBackground(Color.BLACK);
signup.setForeground(Color.WHITE);
signup.addActionListener(this);
add(signup);

        getContentPane().setBackground(Color.WHITE);
        setSize(800,480);
        setVisible(true);
//        bring in center
        setLocation(350,200);
    }
            public void actionPerformed(ActionEvent ae)
            {
                if(ae.getSource()==clear)
                {
                    CardTextField.setText("");
                                         pinTextField.setText("");

                }
                else if(ae.getSource()==login)
                {
                    Conn conn=new Conn();
                    String cardnumber=CardTextField.getText();
                    String pinnumber=pinTextField.getText();
                    String query="select *from login where card_num='"+cardnumber+"'and pin='"+pinnumber+"'";
                    try{
                       ResultSet rs= conn.s.executeQuery(query);
                       if(rs.next()){
                       setVisible(false);
                       new Transactions(pinnumber).setVisible(true);
                       }
                       else {
                       JOptionPane.showMessageDialog(null,"Incorrect Card number or pin");
                       }
                    }catch(Exception e){
                    System.out.println(e);
                    }
                    
                }
                else if(ae.getSource()==signup)
                {
                    setVisible(false);
                    new SignupOne().setVisible(true);
                }
            }
    public static void main(String args[])
    {
        new Login();
       
    }
}