/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.services;

import com.rando.entities.Event;
import com.rando.techniques.ConnectionRando;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author esprit
 */
public class CRUDevent {
 Connection cnx;
    public CRUDevent() {
         cnx=ConnectionRando.getInstance().getConnection();
    }
     public void ajouterEvent(Event e){
          try {
              if((e.getTypelog()!="")&&(e.getTypetransp()!="")){
    String requette="INSERT INTO evenement (  idus , nom , DescE , lieu , date , durée , typelog , typetransp , nbrparticipant ) VALUES ('"+e.getIdus()+"','"+e.getNom()+"','"+e.getDescE()+"','"+e.getLieu()+"','"+e.getDate()+"','"+e.getDuree()+"','"+e.getTypelog()+"','"+e.getTypetransp()+"', 0)";
            Statement t=cnx.createStatement(); 
             t.executeUpdate(requette);
            System.out.println("Ajouté");
              }else if((e.getTypelog()=="")&&(e.getTypetransp()!="")){
                  String requette="INSERT INTO evenement (  idus , nom , DescE , lieu , date , durée , typetransp , nbrparticipant  ) VALUES ('"+e.getIdus()+"','"+e.getNom()+"','"+e.getDescE()+"','"+e.getLieu()+"','"+e.getDate()+"','"+e.getDuree()+"','"+e.getTypetransp()+"', 0)";
            Statement t=cnx.createStatement(); 
             t.executeUpdate(requette);
            System.out.println("Ajouté");
            }else if((e.getTypelog()!="")&&(e.getTypetransp()=="")){
                    String requette="INSERT INTO evenement (  idus , nom , DescE , lieu , date , durée , typelog , nbrparticipant) VALUES ('"+e.getIdus()+"','"+e.getNom()+"','"+e.getDescE()+"','"+e.getLieu()+"','"+e.getDate()+"','"+e.getDuree()+"','"+e.getTypelog()+"' , 0)";
            Statement t=cnx.createStatement(); 
             t.executeUpdate(requette);
            System.out.println("Ajouté"); 
            }else if((e.getTypelog()=="")&&(e.getTypetransp()=="")){
                   String requette="INSERT INTO evenement ( idus , nom , DescE , lieu , date , durée , nbrparticipant) VALUES ('"+e.getIdus()+"','"+e.getNom()+"','"+e.getDescE()+"','"+e.getLieu()+"','"+e.getDate()+"','"+e.getDuree()+"' , 0)";
            Statement t=cnx.createStatement(); 
             t.executeUpdate(requette);
            System.out.println("Ajouté");
            }
            
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    
    }
     
 public void modifierEvent(Event e){
    
            try {
                String requete = "update evenement set nom=?, DescE=? , lieu=? , date=? , durée=? , typelog=? , typetransp=? where idevent=?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                
                pst.setString(1,e.getNom());
                pst.setString(2, e.getDescE());
                pst.setString(3,e.getLieu());
                 pst.setDate(4,e.getDate());
                  pst.setInt(5,e.getDuree());
                   pst.setString(6,e.getTypelog());
                    pst.setString(7,e.getTypetransp());
                    pst.setInt(8,e.getIdevent());
               pst.executeUpdate();
                System.out.println("uppp");
            } catch (SQLException ex) {
                Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            
    }     
 public Event displayEvent(int id) throws SQLException {
     Event e = new Event();
      String req="select * from evenement where idevent='"+id+"' ";
     Statement t =cnx.createStatement();
                 ResultSet rs = t.executeQuery(req);
     rs.next();
     e.setIdus(rs.getInt("idus"));
     e.setNom(rs.getString(3));
              e.setDescE(rs.getString(4));
              e.setLieu(rs.getString(5));
              e.setDate((rs.getDate(6)).toString());//hedhi e date 
              e.setDuree(rs.getInt(7));
              e.setTypelog(rs.getString(8));
              e.setTypetransp(rs.getString(9));
              e.setNbrparticipant(rs.getInt("nbrparticipant"));
     return e;
 }
 
 public ObservableList<Event> displayAll(){
 ObservableList<Event> mal =  FXCollections.observableArrayList();
 
            try {   
    String req="select * from evenement";
                Statement t =cnx.createStatement();
                 ResultSet rs = t.executeQuery(req);
                
                 
                while(rs.next()){ 
                    Event e= new Event();
            
              e.setNom(rs.getString(3));
              e.setDescE(rs.getString(4));
              e.setLieu(rs.getString(5));
             
              
              e.setDate((rs.getDate(6)).toString());//hedhi e date 
              e.setDuree(rs.getInt(7));
              e.setTypelog(rs.getString(8));
              e.setTypetransp(rs.getString(9));
              mal.add(e);
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
            }
    return mal;
    }
  
     
     public void supprimerEvent(int id) {
         System.out.println("k!!");
         
       
         String requette = "DELETE FROM evenement where ('" + id + "'=idevent)";
       
         
         try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requette);
        
            System.out.println("event Supprimer");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
     
     
     
      public ObservableList<Event> rechercheevent(String s) {
        ObservableList<Event> mal = FXCollections.observableArrayList();
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String like = "%"+s+"%";
            String req = "select idevent ,  nom from evenement where (( nom like ?) or ( lieu like ? )) ";
            PreparedStatement t = cnx.prepareStatement(req);
            t.setString(1 , like);
              t.setString(2 , like);
            ResultSet rs = t.executeQuery();

            while (rs.next()) {
                
                
                Event e = new Event();
                e.setIdevent(rs.getInt("idevent"));
                e.setNom(rs.getString("nom"));

                mal.add(e);
               // list.add(e.getNomlieu());
            
            
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mal;
    }
      public void incrementnbr(int id) throws SQLException{
           String req = "select nbrparticipant from evenement where idevent=? ";
            PreparedStatement t = cnx.prepareStatement(req);
            t.setInt(1 , id);
            
            ResultSet rs = t.executeQuery();
            rs.next();
            int i =   rs.getInt("nbrparticipant");
            i++;
              String requete = "update evenement set nbrparticipant=? where idevent=?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                
                pst.setInt(1,i);
                 pst.setInt(2,id);
                 pst.executeUpdate();
                System.out.println("uppp");
              
      }
       public void decrementnbr(int id) throws SQLException{
           String req = "select nbrparticipant from evenement where idevent=? ";
            PreparedStatement t = cnx.prepareStatement(req);
            t.setInt(1 , id);
            
            ResultSet rs = t.executeQuery();
           while(rs.next()) {
            int i =   rs.getInt("nbrparticipant");
               System.out.println(""+i+"");
            i--;
              String requete = "update evenement set nbrparticipant=? where idevent=?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                
                pst.setInt(1,i);
                 pst.setInt(2,id);
                 pst.executeUpdate();
                System.out.println("uppp");
           }
      }
       public int getuppnbr(int id) throws SQLException{
    
           String req = "select nbrparticipant from evenement where idevent=? ";
            PreparedStatement t = cnx.prepareStatement(req);
            t.setInt(1 , id);
            int i =0;
            ResultSet rs = t.executeQuery();
           while(rs.next()) {
            i=   rs.getInt("nbrparticipant");
           }
           
            return i;
       }
}
