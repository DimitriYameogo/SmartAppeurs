/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.entities;

/**
 *
 * @author esprit
 */
public class Participants {
    private int idus;
    private int idevent;
private String evcode;
    public Participants() {
    }

    public Participants(int idus, int idevent, String evcode) {
        this.idus = idus;
        this.idevent = idevent;
        this.evcode = evcode;
    }

    public String getEvcode() {
        return evcode;
    }

    public void setEvcode(String evcode) {
        this.evcode = evcode;
    }

 

    public int getIdus() {
        return idus;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdus(int idus) {
        this.idus = idus;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idus;
        hash = 59 * hash + this.idevent;
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
        final Participants other = (Participants) obj;
        if (this.idus != other.idus) {
            return false;
        }
        if (this.idevent != other.idevent) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participants{" + "idus=" + idus + ", idevent=" + idevent + ", evcode=" + evcode + '}';
    }

   
    
    
}
