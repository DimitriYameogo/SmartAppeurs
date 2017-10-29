/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.GUI;

import java.io.*;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.rando.entities.Lieu;
import com.rando.services.CRUDlieu;
import com.rando.services.lieuModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 *
 * @author esprit
 */
public class ajoutlieuFXML implements Initializable {

    Timeline shiver = new Timeline();
    @FXML
    private ImageView uploadimg;
    @FXML
    private ImageView newtag;
    @FXML
    private Button uploadbutton;
    @FXML
    private TextField imagepath;
    @FXML
    private JFXTextField nomlieu;
    @FXML
    private JFXTextField govlieu;
    @FXML
    private JFXTextField longlieu;
    @FXML
    private JFXTextField latlieu;
    @FXML
    private JFXTextField villelieu;
    @FXML
    private JFXTextArea desclieu;
    private String pathimg;
    @FXML
    private Label nomlieuc;
    @FXML
    private AnchorPane annajoutlieu;
public Boolean verif=false;
public Boolean tester(Boolean X){
   verif=X;
   return verif;
}
    public void setVerif(Boolean X) {
        this.verif = X;
    }

    public Boolean getVerif() {
        return verif;
    }

    @FXML
    private void handleupload(ActionEvent event) {
        pathimg = handle();
        imagepath.setText(pathimg);
    }

    public String handle() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getAbsolutePath();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            uploadimg.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }
        return filePath;
    }

    @FXML
    public void shiverImage() {

        DoubleProperty y = newtag.translateYProperty();

        shiver.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(y, 0)),
                new KeyFrame(new Duration(50), new KeyValue(y, +5)),
                new KeyFrame(new Duration(100), new KeyValue(y, 0)),
                new KeyFrame(new Duration(150), new KeyValue(y, -5)),
                new KeyFrame(new Duration(200), new KeyValue(y, 0))
        );

        shiver.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleajoutlieu(ActionEvent event) throws IOException {
        lieuModel ctrl = new lieuModel();
        //if (ctrl.OnlyChar(nomlieu, nomlieuc)) {
            double lon = Double.parseDouble(longlieu.getText());
            double lat = Double.parseDouble(latlieu.getText());
            System.out.println(pathimg);

            Lieu l = new Lieu(nomlieu.getText(), desclieu.getText(), pathimg, lon, lat, villelieu.getText(), govlieu.getText());
            CRUDlieu tools = new CRUDlieu();
            tools.ajouterLieu(l);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText(l.getIdlieu()+" ajouté !");
            a.setTitle("");
            a.setHeaderText("");
            a.setGraphic(null);
          //  a.
          Optional<ButtonType> result = a.showAndWait();
ButtonType button = result.orElse(ButtonType.CANCEL);

if (button == ButtonType.OK) {
    System.out.println("Ok pressed");
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lieupage.fxml"));

        AnchorPane pane = (AnchorPane) (Parent) fxmlLoader.load();
        LieupageController con = fxmlLoader.<LieupageController>getController();
        con.affichpage(l.getIdlieu());
      annajoutlieu.getChildren().add(pane);
 
} 
   
      //  }

    

}



}
