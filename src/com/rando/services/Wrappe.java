/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.services;

import com.rando.techniques.ConnectionRando;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;

/**
 *
 * @author esprit
 */
public class Wrappe {
    private int idevent; 
    private int iduser;
    private String nomu;
    private String prenom;
    private String evcode;
Connection cnx;
    public Wrappe() {
     cnx=ConnectionRando.getInstance().getConnection(); }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNomu() {
        return nomu;
    }

    public void setNomu(String nomu) {
        this.nomu = nomu;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEvcode() {
        return evcode;
    }

    public void setEvcode(String evcode) {
        this.evcode = evcode;
    }
    
      public List<Wrappe> displayAll(int idevent) throws MalformedURLException{
List<Wrappe> mal =  FXCollections.observableArrayList();
 
            try {   
    String req="select iduser , evcode from participants where idevent='"+idevent+"'";
   
                Statement t =cnx.createStatement();
                 ResultSet rs = t.executeQuery(req);
                Statement t2 =cnx.createStatement();
                 
                while(rs.next()){ 
                   Wrappe w =new Wrappe();
                   String ss =rs.getString("iduser");
             String req2="select nomU ,prenom from users where (idus ='"+ss+"') ";
               ResultSet rs1 = t2.executeQuery(req2);
               rs1.next();

    w.setNomu(rs1.getString("nomU"));
       w.setPrenom(rs1.getString("prenom"));
       w.setEvcode(rs.getString("evcode"));
            mal.add(w);
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
            }
    return mal;
    }
    
}
