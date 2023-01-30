package com.mycompany.atm;

import java.sql.*;

public class Conn {
    Connection c= null;
    Statement s;
    public Conn(){
      try{  
        String connectionURL = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "");
        s =c.createStatement();
         //c=DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","khushi");// Creating connection
         //s=c.createStatement();
      }
      catch(Exception e){
          System.out.println("No such driver");
          }
    }
    
    
}
