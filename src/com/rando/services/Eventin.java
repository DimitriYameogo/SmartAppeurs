/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.services;

import com.rando.techniques.ConnectionRando;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;

/**
 *
 * @author esprit
 */
public class Eventin {
    private int idus;
    private int idevent;
    private String evcode;
    private String Lieu;
    private Date date;
    private String duree;
    private String noml;
        private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNoml() {
        return noml;
    }

    public void setNoml(String noml) {
        this.noml = noml;
    }
Connection cnx;
    public Eventin() {
         cnx=ConnectionRando.getInstance().getConnection();
    }

    public int getIdus() {
        return idus;
    }

    public void setIdus(int idus) {
        this.idus = idus;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public String getEvcode() {
        return evcode;
    }

    public void setEvcode(String evcode) {
        this.evcode = evcode;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
      public List<Eventin> displayAll(int idus) throws MalformedURLException{
List<Eventin> mal =  FXCollections.observableArrayList();
 
            try {   
    String req="select idevent , evcode from participants where iduser='"+idus+"'";
   
                Statement t =cnx.createStatement();
                 ResultSet rs = t.executeQuery(req);
                Statement t2 =cnx.createStatement();
                   Statement t3 =cnx.createStatement();
                while(rs.next()){ 
                  Eventin w =new Eventin();
                   String ss =rs.getString("idevent");
             String req2="select nom , lieu , durée, date from evenement where (idevent ='"+ss+"') ";
               ResultSet rs1 = t2.executeQuery(req2);
                 String s =rs.getString("idlieu");
   String req3="select nomlieu from lieu where (idevent ='"+ss+"') ";
       ResultSet rs2 = t3.executeQuery(req3);        
               rs1.next();
               rs2.next();
w.setDate(rs1.getDate("date"));
w.setDuree(""+rs1.getInt("durée")+"");
w.setEvcode(rs.getString("evcode"));
w.setNoml(rs2.getString("nomlieu"));
w.setNom(rs1.getString("nom"));

            mal.add(w);
                    
            
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
            }
    return mal;
    }
}
