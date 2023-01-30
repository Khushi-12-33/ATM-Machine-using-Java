package com.mycompany.atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public  class Withdrawl extends JFrame implements ActionListener {
        JTextField amount;
        JButton withdraw,back; 
        String pinnumber;
          Withdrawl(String pinnumber){
          this.pinnumber = pinnumber;
          setLayout(null);
          ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
          Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
          ImageIcon i3 = new ImageIcon(i2);
          JLabel image = new JLabel(i3);
          image.setBounds(0,0,900,900);
          add(image);
          JLabel text = new JLabel("Enter the amount you want to Withdraw");
          text.setForeground(Color.WHITE);
          text.setFont(new Font("System", Font.BOLD,16));
          text.setBounds(170,300,400,20);
          image.add(text);
          
          amount = new JTextField();
          amount.setFont(new Font("Raleway", Font.BOLD,22));
          amount.setBounds(170,350,320,25);
          image.add(amount);
          
          withdraw = new JButton("Withdraw");
          withdraw.setBounds(355,485,150,30);
          withdraw.addActionListener(this);
          image.add(withdraw);
          
          back = new JButton("Back");
          back.setBounds(355,520,150,30);
          back.addActionListener(this);
          image.add(back);
          
          
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
       
   }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==withdraw){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showInternalMessageDialog(null, "Please Enter the amonut you want to withdraw");
                
            }
            else{
                try{
                Conn conn = new Conn();
                    ResultSet rs = conn.s.executeQuery("select * from bank where pinNo='"+pinnumber+"'");
                    int balance =0;
                    while(rs.next()){
                        if(rs.getString("type").equals("Withdrawl")){
                            balance += Integer.parseInt(rs.getString("type"));
                        }
                        else{
                            balance -= Integer.parseInt(rs.getString("type"));
                        }
                    }
                        System.out.println(number+" "+balance);
                        
                        
                        if(ae.getSource()!=back && balance<Integer.parseInt(number)){
                            JOptionPane.showInternalMessageDialog(null, "Insufficient Balance!");
                            return;
                        }
                        String query= "insert into bank values ('"+pinnumber+"','"+date+"','Withdrawl' ,'"+number+"' )";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Rs. "+number+" Withdrawl Successfully");
                        setVisible(false);
                        new Transactions(pinnumber).setVisible(true);   
                        
                    
                
                
                }catch(Exception e){
                    System.out.println(e);
                }
                
                
            }
        }
        else if(ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
    
    public static void main(String [] args){
        new Withdrawl("");
   } 
}