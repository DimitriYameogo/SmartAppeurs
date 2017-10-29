/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.services;

import com.rando.entities.Event;
import com.rando.techniques.ConnectionRando;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static java.util.Optional.of;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import static javax.management.Query.value;

/**
 *
 * @author esprit
 */
public class WrapEvLi {
    private int id; 
    private String nom;
    private Image img;
    private Date date;
    private String lieu;
    private String nbr;
Connection cnx;
    public WrapEvLi() {
         cnx=ConnectionRando.getInstance().getConnection();
    }

    public WrapEvLi(int i,String nom, Image img, Date date, String lieu, String nbr) {
       this.id=i;
        this.nom = nom;
        this.img = img;
        this.date = date;
        this.lieu = lieu;
        this.nbr = nbr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNbr() {
        return nbr;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }
    
    
    public List<WrapEvLi> displayAll(int id) throws MalformedURLException{
List<WrapEvLi> mal =  FXCollections.observableArrayList();
 
            try {   
    String req="select idevent , nom , lieu , date , nbrparticipant from evenement where idus ='"+id+"'";
   
                Statement t =cnx.createStatement();
                 ResultSet rs = t.executeQuery(req);
                Statement t2 =cnx.createStatement();
                 
                while(rs.next()){ 
                   WrapEvLi w =new WrapEvLi();
                   String ss =rs.getString("lieu");
             String req2="select nomlieu ,piclieu from lieu where (idlieu ='"+ss+"') ";
               ResultSet rs1 = t2.executeQuery(req2);
               rs1.next();
            if((rs1.getString("piclieu")).equals("")){
           
          Image i = new Image(new File("file:/C:/Users/esprit/Documents/Nouveau%20dossier/projetjava/src/com/rando/img/index.png").toURI().toURL().toExternalForm());
                w.setImg(i);
            }else{   Image i =new Image(new File(rs1.getString("piclieu")).toURI().toURL().toExternalForm());
               w.setImg(i);}
           w.setId(rs.getInt("idevent"));
             w.setNom(rs.getString("nom"));
              w.setDate((rs.getDate("date"))); //hedhi e date
              w.setLieu(rs1.getString("nomlieu"));
              int i=rs.getInt("nbrparticipant");
            String s =""+i+"";
              w.setNbr(s) ;
             
            mal.add(w);
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
            }
    return mal;
    }
    
    
    
    
    
}
