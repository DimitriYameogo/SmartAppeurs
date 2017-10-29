/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.entities;

import java.util.Objects;
import java.util.Random;

/**
 *
 * @author esprit
 */
public class Lieu {
    private String idlieu;
    private String nomlieu;
    private String desclieu;
    private String piclieu;
    private Double longlieu;
    private Double latlieu;
    private String villelieu;
    private String govlieu;

    public Lieu() {
    }

    public Lieu( String nomlieu, String desclieu, String piclieu, Double longlieu, Double latlieu, String villelieu, String govlieu) {
        this.idlieu = randomString(nomlieu);
        this.nomlieu = nomlieu;
        this.desclieu = desclieu;
        this.piclieu = piclieu;
        this.longlieu = longlieu;
        this.latlieu = latlieu;
        this.villelieu = villelieu;
        this.govlieu = govlieu;
    }

    public String getIdlieu() {
        return idlieu;
    }

    public String getNomlieu() {
        return nomlieu;
    }

    public String getDesclieu() {
        return desclieu;
    }

    public String getPiclieu() {
        return piclieu;
    }

    public Double getLonglieu() {
        return longlieu;
    }

    public Double getLatlieu() {
        return latlieu;
    }

    public String getVillelieu() {
        return villelieu;
    }

    public String getGovlieu() {
        return govlieu;
    }

    public void setIdlieu(String idlieu) {
        this.idlieu = idlieu;
    }

    public void setNomlieu(String nomlieu) {
        this.nomlieu = nomlieu;
    }

    public void setDesclieu(String desclieu) {
        this.desclieu = desclieu;
    }

    public void setPiclieu(String piclieu) {
        this.piclieu = piclieu;
    }

    public void setLonglieu(Double longlieu) {
        this.longlieu = longlieu;
    }

    public void setLatlieu(Double latlieu) {
        this.latlieu = latlieu;
    }

    public void setVillelieu(String villelieu) {
        this.villelieu = villelieu;
    }

    public void setGovlieu(String govlieu) {
        this.govlieu = govlieu;
    }

    @Override
    public String toString() {
        return "Lieu{" + "idlieu=" + idlieu + ", nomlieu=" + nomlieu + ", desclieu=" + desclieu + ", piclieu=" + piclieu + ", longlieu=" + longlieu + ", latlieu=" + latlieu + ", villelieu=" + villelieu + ", govlieu=" + govlieu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.idlieu);
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
        final Lieu other = (Lieu) obj;
        if (!Objects.equals(this.idlieu, other.idlieu)) {
            return false;
        }
        return true;
    }
    
 public String randomString(String id) {
  char[] chars = "0123456789".toCharArray();
StringBuilder sb = new StringBuilder();
Random random = new Random();
for (int i = 0; i < 4; i++) {
    char c = chars[random.nextInt(chars.length)];
    sb.append(c);
}
String output = sb.toString();

        return id+"."+output;
}

}