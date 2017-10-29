/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.services;

import com.rando.techniques.ConnectionRando;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author esprit
 */
public class AppModel {
     Connection cnx;
       private  String text;

    public AppModel() {
    cnx=ConnectionRando.getInstance().getConnection(); }

    public String getText() {
        return text ;
    }

    

    public void setText(String text) {
      this.text=text;
    }

public String nompre(int id) throws SQLException{
    String req="select nomU, prenom from users where idus='"+id+"' ";
     Statement t =cnx.createStatement();
                 ResultSet rs = t.executeQuery(req);
                 rs.next();
                 String np =rs.getString("nomU")+" "+rs.getString("prenom");
                 return np;
}
}
