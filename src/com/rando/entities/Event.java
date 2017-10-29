/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.entities;

import java.sql.Date;
import static java.sql.Date.valueOf;


/**
 *
 * @author esprit
 */
public class Event {
    private int idevent;    
    private int idus;
    private String nom;
    private String lieu;
    private  Date date;
    private int duree;
    private String typelog;
    private String typetransp;
    private float avis;
    private String DescE;
    private int nbrparticipant;

    public Event(int idevent, int idus, String nom,String DescE, String lieu, Date dat, int duree, String typelog, String typetransp, float avis,int nbrparticipant) {
        this.idevent = idevent;
        this.idus = idus;
        this.nom = nom;
        this.lieu = lieu;
        this.date =dat;
        this.duree = duree;
        this.typelog = typelog;
        this.typetransp = typetransp;
        this.avis = avis;
        this.DescE = DescE;
        this.nbrparticipant=nbrparticipant;
    }

    public int getNbrparticipant() {
        return nbrparticipant;
    }

    public void setNbrparticipant(int nbrparticipant) {
        this.nbrparticipant = nbrparticipant;
    }

    public Event() {
    }

    public int getIdevent() {
        return idevent;
    }

    public int getIdus() {
        return idus;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public Date getDate() {
        return date;
    }

    public int getDuree() {
        return duree;
    }

    public String getTypelog() {
        return typelog;
    }

    public String getTypetransp() {
        return typetransp;
    }

    public float getAvis() {
        return avis;
    }

    public String getDescE() {
        return DescE;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public void setIdus(int idus) {
        this.idus = idus;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(String d) {
        date =valueOf(d);
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setTypelog(String typelog) {
        this.typelog = typelog;
    }

    public void setTypetransp(String typetransp) {
        this.typetransp = typetransp;
    }

    public void setAvis(float avis) {
        this.avis = avis;
    }

    public void setDescE(String DescE) {
        this.DescE = DescE;
    }

    @Override
    public String toString() {
        return "event{" + "idevent=" + idevent + ", idus=" + idus + ", nom=" + nom + ", lieu=" + lieu + ", date=" + date.toString() + ", duree=" + duree + ", typelog=" + typelog + ", typetransp=" + typetransp + ", avis=" + avis + ", DescE=" + DescE + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idevent;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.idevent != other.idevent) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
