/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.services;

import com.rando.entities.Event;
import com.rando.entities.Lieu;
import com.rando.techniques.ConnectionRando;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author esprit
 */
public class CRUDlieu {

    Connection cnx;

    public CRUDlieu() {
        cnx = ConnectionRando.getInstance().getConnection();

    }

    public void ajouterLieu(Lieu e) {
        try {

            String requette = "INSERT INTO lieu (  idlieu , nomlieu , desclieu , piclieu , longlieu , latlieu , villelieu , govlieu ) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requette);
            pst.setString(1, e.getIdlieu());

            pst.setString(2, e.getNomlieu());
            pst.setString(3, e.getDesclieu());
            pst.setString(4, e.getPiclieu());
            pst.setDouble(5, e.getLonglieu());
            pst.setDouble(6, e.getLatlieu());
            pst.setString(7, e.getVillelieu());
            pst.setString(8, e.getGovlieu());

            pst.executeUpdate();
            System.out.println("Ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierLieu(Lieu e) {

        try {
            String requete = "update lieu set nomlieu=?, desclieu=? , piclieu=? , longlieu=? , latlieu=? ,villelieu=? , govlieu=? where idlieu=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, e.getNomlieu());
            pst.setString(2, e.getDesclieu());
            pst.setString(3, e.getPiclieu());
            pst.setDouble(4, e.getLonglieu());
            pst.setDouble(5, e.getLatlieu());
            pst.setString(6, e.getVillelieu());
            pst.setString(7, e.getGovlieu());
            pst.setString(8, e.getIdlieu());
            pst.executeUpdate();
            System.out.println("uppp");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public void modifierDescLieu(String id, String desc) {

        try {
            String requete = "update lieu set desclieu=? where idlieu=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, desc);
               pst.setString(2, id);
            pst.executeUpdate();
            System.out.println("uppp");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
         public void modifierVilleLieu(String id, String ville) {

        try {
            String requete = "update lieu set villelieu=? where idlieu=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, ville);
               pst.setString(2, id);
            pst.executeUpdate();
            System.out.println("uppp");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
          public void modifierGovLieu(String id, String gov) {

        try {
            String requete = "update lieu set govlieu=? where idlieu=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, gov);
               pst.setString(2, id);
            pst.executeUpdate();
            System.out.println("uppp");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
          public void modifierPicLieu(String id, String pic) {

        try {
            String requete = "update lieu set piclieu=? where idlieu=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, pic);
               pst.setString(2, id);
            pst.executeUpdate();
            System.out.println("uppp");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Lieu> displayAll() {
  ObservableList<Lieu> mal = FXCollections.observableArrayList();
        try {
            String req = "select * from lieu";
            Statement t = cnx.createStatement();
            ResultSet rs = t.executeQuery(req);

            while (rs.next()) {
                Lieu e = new Lieu();
 e.setIdlieu(rs.getString(1));
                e.setNomlieu(rs.getString(2));
                e.setDesclieu(rs.getString(3));
                e.setPiclieu(rs.getString(4));
                e.setLonglieu(rs.getDouble(5));
                e.setLatlieu(rs.getDouble(6));
                e.setVillelieu(rs.getString(7));
                e.setGovlieu(rs.getString(8));
                mal.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mal;
    }

    public ObservableList<Lieu> recherchelieu(String s) {
        ObservableList<Lieu> mal = FXCollections.observableArrayList();
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String req = "select idlieu , nomlieu from lieu where ( ( nomlieu = '" + s + "') or ( govlieu = '" + s + "' ) or ( villelieu = '" + s + "' ) )";
            Statement t = cnx.createStatement();
            ResultSet rs = t.executeQuery(req);

            while (rs.next()) {
                Lieu e = new Lieu();
                e.setIdlieu(rs.getString(1));
                e.setNomlieu(rs.getString(2));

                mal.add(e);
                list.add(e.getNomlieu());
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mal;
    }

    public void supprimerLieu(String id) {
        System.out.println("k!!");

        String requette = "DELETE FROM lieu where ('" + id + "'=idlieu)";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requette);

            System.out.println("event Supprimer");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Lieu displayLieu(String id) {
        System.out.println(id);
        Lieu e = new Lieu();
        try {
            String req = "select * from lieu where idlieu = '" + id + "'";
            Statement t = cnx.createStatement();
            ResultSet rs = t.executeQuery(req);

            rs.next();
            e.setIdlieu(rs.getString(1));
            e.setNomlieu(rs.getString(2));
            e.setDesclieu(rs.getString(3));
            e.setPiclieu(rs.getString(4));
            e.setLonglieu(rs.getDouble(5));
            e.setLatlieu(rs.getDouble(6));
            e.setVillelieu(rs.getString(7));
            e.setGovlieu(rs.getString(8));

        } catch (SQLException ex) {
            Logger.getLogger(CRUDevent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

}
