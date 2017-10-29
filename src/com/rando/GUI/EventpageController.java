/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.rando.entities.Event;
import com.rando.entities.Lieu;
import com.rando.entities.Participants;
import com.rando.services.AppModel;
import com.rando.services.CRUDevent;
import com.rando.services.CRUDlieu;
import com.rando.services.participantsdb;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class EventpageController implements Initializable {

    @FXML
    private JFXTextField transpevent;
    @FXML
    private JFXTextField titevent;
    @FXML
    private JFXTextField lieuevent;
    @FXML
    private JFXDatePicker datevent;
    @FXML
    private ImageView imgevent;
    @FXML
    private JFXTextField nbrevent;
    @FXML
    private JFXTextField logevent;
    @FXML
    private JFXTextArea descevent;
    @FXML
    private JFXTextField dureevent;
    @FXML
    private Label affichnom;
    @FXML
    private Label idl;
    @FXML
    private Label idevent;
    @FXML
    private Label jourlab;
    @FXML
    private JFXButton thePbutt;
    private int idus;
    @FXML
    private Pane paypane;
    @FXML
    private JFXCheckBox checktogg;
    @FXML
    private Label thesecretCode;
    @FXML
    private JFXButton goaway;
    @FXML
    private Pane modalpane;

    @FXML
    private ToggleButton tgl;
    @FXML
    private JFXButton confP;
    /**
     * Initializes the controller class.
     */
    participantsdb p = new participantsdb();
    Participants part = new Participants();
    int id;
    @FXML
    private AnchorPane paypane1;
    @FXML
    private JFXButton goaway1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void affichevent(String i, String user) throws SQLException, MalformedURLException {
        id = Integer.parseInt(i);
        CRUDevent e = new CRUDevent();
        CRUDlieu l = new CRUDlieu();
        Event ev = new Event();
        ev = e.displayEvent(id);

        Lieu li = new Lieu();
        li = l.displayLieu(ev.getLieu());
        Image image = new Image(new File(li.getPiclieu()).toURI().toURL().toExternalForm());
        imgevent.setImage(image);
        titevent.setText(ev.getNom());
        descevent.setText(ev.getDescE());
        nbrevent.setText("" + ev.getNbrparticipant() + "");
        lieuevent.setText(li.getNomlieu());
        if (ev.getDuree() == 0) {
            jourlab.setVisible(false);
            dureevent.setText("Une journée");
        } else {
            dureevent.setText("" + ev.getDuree() + "");
        }
        datevent.setValue(ev.getDate().toLocalDate());
        if (ev.getTypelog() != "") {
            logevent.setVisible(true);
            logevent.setText(ev.getTypelog());
        } else {
            logevent.setVisible(false);
        }
        if (ev.getTypetransp() != "") {
            transpevent.setVisible(true);
            transpevent.setText(ev.getTypelog());
        } else {
            transpevent.setVisible(false);
        }

        AppModel np = new AppModel();
        getidusfromskt(user);
        affichnom.setText(np.nompre(ev.getIdus()));
        if (ev.getIdus() == idus) {
            thePbutt.setDisable(true);
            thePbutt.setText("createur de l evenement");
        }
        
        if(p.verifParticipation(new Participants(idus,id,""))!=""){
            thePbutt.setText("Participé");
            thePbutt.setStyle("-fx-background-color: #ca6f1e ");
              idevent.setText("Votre Code :"+p.verifParticipation(new Participants(idus,id,"")));
        }
    }

    public void getidusfromskt(String i) {
        idus = Integer.parseInt(i);
    }

    @FXML
    private void ptogglehandle(ActionEvent event) {

        if (tgl.isSelected()) {
            checktogg.setSelected(true);
            thesecretCode.setText(p.randomString("" + idus + ""));
            confP.setDisable(false);
        } else {
            checktogg.setSelected(false);
            thesecretCode.setText("");
            confP.setDisable(true);
        }
    }

    @FXML
    private void goawayha(ActionEvent event) {
        TranslateTransition closeNav1 = new TranslateTransition(new Duration(450), paypane);
        closeNav1.setToY(-(paypane.getHeight()));
        closeNav1.play();
        modalpane.setVisible(false);
        thePbutt.setDisable(false);
    }

    @FXML
    private void thePhandle(ActionEvent event) {
        if (thePbutt.getText().equals("Participer")) {
            confP.setDisable(true);
            modalpane.setVisible(true);
            thePbutt.setDisable(true);
            TranslateTransition closeNav1 = new TranslateTransition(new Duration(350), paypane);
            closeNav1.setToY((paypane.getHeight()) - 155);
            closeNav1.play();

        }
          if(thePbutt.getText().equals("Annuler participation")){
        thePbutt.setText("Participé"); 
         modalpane.setVisible(true);
            thePbutt.setDisable(true);
            TranslateTransition closeNav1 = new TranslateTransition(new Duration(350), paypane1);
            closeNav1.setToY((paypane1.getHeight()) - 105);
            closeNav1.play();
          }
    }

    @FXML
    private void cParticiper(ActionEvent event) throws SQLException {
        part.setIdevent(id);
        part.setIdus(idus);
        part.setEvcode(thesecretCode.getText());
p.ajouterParticipants(part) ;
thePbutt.setText("Participé");
CRUDevent e=new CRUDevent();
nbrevent.setText(""+e.getuppnbr(id)+"");
  TranslateTransition closeNav1 = new TranslateTransition(new Duration(450), paypane);
        closeNav1.setToY(-(paypane.getHeight()));
        closeNav1.play();
        modalpane.setVisible(false);
        thePbutt.setDisable(false);
        idevent.setText("Votre Code : "+part.getEvcode());
         thePbutt.setStyle("-fx-background-color: #ca6f1e ");

    }

    @FXML
    private void onexit(MouseEvent event) {
              if(thePbutt.getText().equals("Annuler participation")){
        thePbutt.setText("Participé"); }
    }

    @FXML
    private void onentr(MouseEvent event) {
        
      
  
       
           if(thePbutt.getText().equals("Participé")){
             thePbutt.setText("Annuler participation");      
             idevent.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished((ActionEvent event1) -> {
           idevent.setVisible(false);
        }); visiblePause.play();
        }
 
    }

    @FXML
    private void cAnnulep(ActionEvent event) throws SQLException {
     part.setIdevent(id);
        part.setIdus(idus);
        part.setEvcode(thesecretCode.getText());
        p.SupprimerParticipants(part);

    thePbutt.setText("Participer");
           thePbutt.setStyle("-fx-background-color:  #009688 ");
    modalpane.setVisible(false);
        thePbutt.setDisable(false);
     TranslateTransition closeNav1 = new TranslateTransition(new Duration(350), paypane1);
            closeNav1.setToY(-(paypane1.getHeight()) );
            closeNav1.play();
            CRUDevent e=new CRUDevent();
nbrevent.setText(""+e.getuppnbr(id)+"");
    }

    @FXML
    private void goawayho(ActionEvent event) {
              TranslateTransition closeNav1 = new TranslateTransition(new Duration(450), paypane1);
        closeNav1.setToY(-(paypane1.getHeight()));
        closeNav1.play();
        modalpane.setVisible(false);
        thePbutt.setDisable(false);
    }


}
