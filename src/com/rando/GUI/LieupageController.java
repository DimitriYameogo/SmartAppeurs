/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.rando.entities.Lieu;
import com.rando.services.CRUDlieu;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class LieupageController implements Initializable {

    @FXML
    private Label affichnom;
    @FXML
    private JFXTextArea affichdesc;
    @FXML
    private JFXTextField affichgov;
    @FXML
    private JFXTextField affichvlle;
    @FXML
    private ImageView affichim;
    @FXML
    private JFXButton descmicon;
    @FXML
    private JFXButton govmicon;
    @FXML
    private JFXButton villemicon;
    @FXML
    private JFXButton descsicon;
    @FXML
    private JFXButton villesicon;
    @FXML
    private JFXButton govsicon;
    @FXML
    private Label idl;
    @FXML
    private Pane panedesc;
    @FXML
    private Pane panegov;
    @FXML
    private Pane paneville;
    @FXML
    private Label descmlab;
    @FXML
    private Label govmlab;
    @FXML
    private Label villemlab;
    @FXML
    private JFXButton picmicon;
    @FXML
    private JFXTextField inputim;
    @FXML
    private Label picmlab;
    @FXML
    private JFXButton picsicon;

    /**
     * Initializes the controller class.
     */
    public void affichpage(String id) throws MalformedURLException {
        CRUDlieu tools = new CRUDlieu();
        Lieu l = new Lieu();
        l = tools.displayLieu(id);
        System.out.println(l.getPiclieu());
       Image image = new Image(new File(l.getPiclieu()).toURI().toURL().toExternalForm());
      affichim.setImage(image);
        affichnom.setText(l.getNomlieu());
        affichdesc.setText(l.getDesclieu());
        affichgov.setText(l.getGovlieu());
        affichvlle.setText(l.getVillelieu());
        idl.setText(id);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void descModif(ActionEvent event) {
        affichdesc.setEditable(true);
        panedesc.setOpacity(0);
        descsicon.setOpacity(1);
        descmicon.setOpacity(0);
    }

    @FXML
    private void govModif(ActionEvent event) {
        affichgov.setEditable(true);
        panegov.setOpacity(0);
        govsicon.setOpacity(1);
        govmicon.setOpacity(0);
    }

    @FXML
    private void villeModif(ActionEvent event) {

        affichvlle.setEditable(true);
        paneville.setOpacity(0);
        villesicon.setOpacity(1);
        villemicon.setOpacity(0);

    }

    @FXML
    private void descSub(ActionEvent event) {
       CRUDlieu tools = new CRUDlieu();
        System.out.println(affichdesc.getText()); 
        
       tools.modifierDescLieu(idl.getText(), affichdesc.getText());
        descmlab.setOpacity(1);
        descmlab.setVisible(true);
        affichdesc.setEditable(false);
        panedesc.setOpacity(1);
        descsicon.setOpacity(0);
        descmicon.setOpacity(1);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished((ActionEvent event1) -> {
            descmlab.setVisible(false);
        });
        visiblePause.play();
    }

    @FXML
    private void villeSub(ActionEvent event) {
      CRUDlieu tools = new CRUDlieu();
     
       tools.modifierVilleLieu(idl.getText(),affichvlle.getText());
        villemlab.setOpacity(1);
        villemlab.setVisible(true);
        affichvlle.setEditable(false);
        paneville.setOpacity(1);
        villesicon.setOpacity(0);
        villemicon.setOpacity(1);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished((ActionEvent event1) -> {
            villemlab.setVisible(false);
        });
        visiblePause.play();
    }

    @FXML
    private void govSub(ActionEvent event) {
         CRUDlieu tools = new CRUDlieu();
             
       tools.modifierGovLieu(idl.getText(), affichgov.getText());
        govmlab.setOpacity(1);
        govmlab.setVisible(true);
        affichgov.setEditable(false);
        panegov.setOpacity(1);
        govsicon.setOpacity(0);
        govmicon.setOpacity(1);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished((ActionEvent event1) -> {
            govmlab.setVisible(false);
        });
        visiblePause.play();
    }

    @FXML
    private void picModif(ActionEvent event) throws MalformedURLException {
if(handle()!=""){
     inputim.setText(handle());
}

 




picsicon.setOpacity(1);
        picmicon.setOpacity(0);
    }
   
    @FXML
    private void picSub(ActionEvent event) {
         CRUDlieu tools = new CRUDlieu();
             
       tools.modifierPicLieu(idl.getText(), inputim.getText());
        picmlab.setOpacity(1);
        picmlab.setVisible(true);

        picsicon.setOpacity(0);
        picmicon.setOpacity(1);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished((ActionEvent event1) -> {
            picmlab.setVisible(false);
        });
        visiblePause.play();
    }
  public String handle() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
 
        try {       String filePath = file.getAbsolutePath();
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            affichim.setImage(image);
   return filePath;
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return "";
     
    }
}
