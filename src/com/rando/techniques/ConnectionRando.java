/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.techniques;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esprit
 */
public class ConnectionRando {
        public String url = "jdbc:mysql://localhost:3306/randonneedb";
    public String login = "root";
    public String pwd = "";
    Connection cnx;
 public static ConnectionRando instance;
    private ConnectionRando() {
         try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection done!");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionRando.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
    }}
    public static ConnectionRando getInstance() {
        if (instance == null) {
            instance = new ConnectionRando();
        }
        return instance;
    }

    public Connection getConnection() {

        return cnx;
    }
    
}
