/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.services;

import com.rando.entities.Participants;
import com.rando.techniques.ConnectionRando;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esprit
 */
public class participantsdb {
 Connection cnx;

    public participantsdb() {
                cnx=ConnectionRando.getInstance().getConnection();
    }
    public void ajouterParticipants(Participants p) throws SQLException{
            try {
                String code=randomString(""+p.getIdevent()+"");
    String requette="INSERT INTO participants ( iduser , idevent , evcode ) VALUES (' "+p.getIdus()+" ',' "+p.getIdevent()+" ',' "+p.getEvcode()+" ')";
    
            Statement t=cnx.createStatement(); 
             t.executeUpdate(requette);
            System.out.println("Ajouté");
             CRUDevent ce= new CRUDevent();
          
   ce.incrementnbr(p.getIdevent());
            
        } catch (SQLException ex) {
            Logger.getLogger(participantsdb.class.getName()).log(Level.SEVERE, null, ex);
        }  
         
    }
    public String randomString(String id) {
  char[] chars = "0123456789AZERTYUIOPQSDFGHJKLMWXCVBNazertyuiopqsdfghjklmwxcvbn".toCharArray();
StringBuilder sb = new StringBuilder();
Random random = new Random();
for (int i = 0; i < 6; i++) {
    char c = chars[random.nextInt(chars.length)];
    sb.append(c);
}
String output = sb.toString();

        return id+output;
}
    public void SupprimerParticipants(Participants p) throws SQLException{
            try {
   String req="delete  from participants where ((iduser= ' "+p.getIdus()+" ') and(idevent = ' "+p.getIdevent()+" ' )) ";
    
            Statement t=cnx.createStatement(); 
             t.executeUpdate(req);
            System.out.println("Ajouté");
           
        
        } catch (SQLException ex) {
            Logger.getLogger(participantsdb.class.getName()).log(Level.SEVERE, null, ex);
        }
  CRUDevent ce= new CRUDevent();
          
   ce.decrementnbr(p.getIdevent());
            
    }
    public String verifParticipation(Participants p) throws SQLException{
         List<Participants> mal = new ArrayList<>();
         Participants pt= new Participants();
    
    String req="select * from participants where ((iduser= ' "+p.getIdus()+" ') and(idevent = ' "+p.getIdevent()+" ' ))";
                Statement t =cnx.createStatement();
                 ResultSet rs = t.executeQuery(req);
                
                 
                while(rs.next()){ 
                  
              pt.setIdevent(rs.getInt(1));
           pt.setIdus(rs.getInt(2));
              pt.setEvcode(rs.getString(3));
              mal.add(p);
                     
                }
                System.out.println(pt.getEvcode());
                  if(!mal.isEmpty()){ 
                      return pt.getEvcode();
                }
               return "";
            
    }
}
