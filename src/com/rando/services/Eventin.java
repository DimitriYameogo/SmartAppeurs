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
    private String datee;
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
        cnx = ConnectionRando.getInstance().getConnection();
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

    public String getDatee() {
        return datee;
    }

    public void setDatee(String date) {
        this.datee = date;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public List<Eventin> displayAll(int idus) throws MalformedURLException {
        List<Eventin> mal = FXCollections.observableArrayList();

        try {
            String req = "select idevent , evcode from participants where (iduser='" + idus + "')";

            Statement t = cnx.createStatement();
            ResultSet rs = t.executeQuery(req);

            while (rs.next()) {
                Eventin w = new Eventin();
                int ss = rs.getInt(1);
                  w.setEvcode(rs.getString(2));
                String req2 = "select nom , lieu , durée, date  from evenement where (idevent ='" + ss + "') ";
                Statement t2 = cnx.createStatement();
                ResultSet rs1 = t2.executeQuery(req2);

                String s;
                rs1.next();
                  w.setDatee(rs1.getDate("date").toString());
                  System.out.println("blakhhh"+w.getDatee());
        w.setDuree("" + rs1.getInt("durée") + "");
               w.setNom(rs1.getString("nom"));
                System.out.println("12345665" + rs1.getString(2));
                s = rs1.getString(2);
                String req3 = "select nomlieu from lieu where (idlieu ='" + s + "') ";
                
                
                Statement t3 = cnx.createStatement();
                ResultSet rs2 = t3.executeQuery(req3);

                rs2.next();
              
        
              
                w.setNoml(rs2.getString("nomlieu"));
         

                mal.add(w);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(mal);
        return mal;
    }
}
