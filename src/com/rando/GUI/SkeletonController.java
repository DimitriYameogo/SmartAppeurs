/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.GUI;

import com.rando.GUI.AjouEventController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import com.rando.GUI.SearchFXMLController;
import com.rando.services.AppModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class SkeletonController implements Initializable {

    ObservableList<String> items = FXCollections.observableArrayList("Lieu", "Event");
    @FXML
    private JFXButton searchpane;
    @FXML
    private AnchorPane ansearch;
    @FXML
    private JFXButton searchpane1;
    @FXML
    private AnchorPane anadd;
    @FXML
    private AnchorPane mainap;
    private JFXCheckBox checklieu;
    @FXML
    private JFXTextField searchinput;
    @FXML
    private JFXComboBox<String> combosearch;
    @FXML
    private Label useridlab;
    @FXML
    private JFXButton closebutt;
    @FXML
    private JFXButton myeventHandle;
    @FXML
    private MediaView mediaView;

    /**
     * Initializes the controller class.
     */
    private void handlePane(JFXButton b, AnchorPane a, AnchorPane d) {

        TranslateTransition openNav = new TranslateTransition(new Duration(350), a);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), a);
        b.setOnAction((ActionEvent evt) -> {
            if (a.getTranslateX() != 0) {

                TranslateTransition closeNav1 = new TranslateTransition(new Duration(350), d);
                closeNav1.setToX(-(d.getWidth()));
                closeNav1.play();
                openNav.play();
            } else {
                closeNav.setToX(-(a.getWidth()));
                closeNav.play();
            }
        });
    }

    @FXML
    private void loadajoutlieu(ActionEvent event) throws IOException {
           URL mediaUrl = getClass().getResource("Attempt2.mp4");

        String source = mediaUrl.toExternalForm();
        Media media = new Media(source);
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
Pane p = new Pane();
p.setPrefHeight(559);
p.setPrefWidth(708);
p.setStyle("-fx-background-color:  #005452 ");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        MediaView meediaView = new MediaView(mediaPlayer);
        meediaView.setFitWidth(400);
        meediaView.setX(155);
        meediaView.setY(60);
        meediaView.setFitHeight(450);
        meediaView.setMediaPlayer(mediaPlayer);

        meediaView.setSmooth(true);
        p.getChildren().add(meediaView);
        mainap.getChildren().add(p);

        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(2)
        );   
        visiblePause.setOnFinished((ActionEvent event1) -> {
        AnchorPane newLoadedPane;
               try {
                   newLoadedPane = FXMLLoader.load(getClass().getResource("ajoutlieu.fxml"));        
        mainap.getChildren().add(newLoadedPane);
               } catch (IOException ex) {
                   Logger.getLogger(SkeletonController.class.getName()).log(Level.SEVERE, null, ex);
               }

                 });
        visiblePause.play();
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), anadd);
        closeNav.setToX(-(anadd.getWidth()));
        closeNav.play();
    }

    @FXML
    private void handlesearch(ActionEvent event) throws IOException {
              
        
        if ((combosearch.getValue()).equals("Lieu")) {
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchFXML.fxml"));

            AnchorPane pane = (AnchorPane) (Parent) fxmlLoader.load();
               URL mediaUrl = getClass().getResource("Attempt2.mp4");

        String source = mediaUrl.toExternalForm();
        Media media = new Media(source);
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
Pane p = new Pane();
p.setPrefHeight(559);
p.setPrefWidth(708);
p.setStyle("-fx-background-color:  #005452 ");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        MediaView meediaView = new MediaView(mediaPlayer);
        meediaView.setFitWidth(400);
        meediaView.setX(155);
        meediaView.setY(60);
        meediaView.setFitHeight(450);
        meediaView.setMediaPlayer(mediaPlayer);

        meediaView.setSmooth(true);
        p.getChildren().add(meediaView);
        mainap.getChildren().add(p);

        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(2)
        );   
        visiblePause.setOnFinished((ActionEvent event1) -> {
            SearchFXMLController con = fxmlLoader.<SearchFXMLController>getController();
        
            con.rec(   "Recherche du " + combosearch.getValue() + " : " + searchinput.getText(), searchinput.getText());
            mainap.getChildren().add(pane);
 });
        visiblePause.play();
            TranslateTransition closeNav = new TranslateTransition(new Duration(350), ansearch);
            closeNav.setToX(-(ansearch.getWidth()));
            closeNav.play();
        }
        if ((combosearch.getValue()).equals("Event")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchFXML.fxml"));

            AnchorPane pane = (AnchorPane) (Parent) fxmlLoader.load();
            
                URL mediaUrl = getClass().getResource("Attempt2.mp4");

        String source = mediaUrl.toExternalForm();
        Media media = new Media(source);
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
Pane p = new Pane();
p.setPrefHeight(559);
p.setPrefWidth(708);
p.setStyle("-fx-background-color:  #005452 ");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        MediaView meediaView = new MediaView(mediaPlayer);
        meediaView.setFitWidth(400);
        meediaView.setX(155);
        meediaView.setY(60);
        meediaView.setFitHeight(450);
        meediaView.setMediaPlayer(mediaPlayer);

        meediaView.setSmooth(true);
        p.getChildren().add(meediaView);
        mainap.getChildren().add(p);

        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(2)
        );   
        visiblePause.setOnFinished((ActionEvent event1) -> {
            
            
            SearchFXMLController con = fxmlLoader.<SearchFXMLController>getController();
          String  labelmsg;
                labelmsg = "Recherche de l " + combosearch.getValue() + " : " + searchinput.getText();
            con.recevent(labelmsg, searchinput.getText(), useridlab.getText());
            mainap.getChildren().add(pane);
            });
        visiblePause.play();

            TranslateTransition closeNav = new TranslateTransition(new Duration(350), ansearch);
            closeNav.setToX(-(ansearch.getWidth()));
            closeNav.play();
                
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Create the media source.
        URL mediaUrl = getClass().getResource("Attempt2.mp4");

        String source = mediaUrl.toExternalForm();
        Media media = new Media(source);

        // Create the player and set to play automatically.
        final MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        /*mediaPlayer.setStartTime(Duration.seconds(0));
mediaPlayer.setStopTime(Duration.seconds(5));
mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });*/
        // Create the view and add it to the Scene.

        /*  MediaView mediaView = new MediaView(mediaPlayer);
       mediaView.setFitWidth(300);
mediaView.setX(213);
mediaView.setY(127);
	        mediaView.setFitHeight(305);       

	        mediaView.setSmooth(true);
      mainap.getChildren().add(mediaView);*/
        mediaView.setMediaPlayer(mediaPlayer);
        combosearch.setItems(items);
        handlePane(searchpane, ansearch, anadd);
        handlePane(searchpane1, anadd, ansearch);
        useridlab.setText("10");

    }

    @FXML
    private void loadajoutevent(ActionEvent event) throws IOException {
        FXMLLoader newLoadedPane = new FXMLLoader(getClass().getResource("AjouEvent.fxml"));
        AnchorPane pane = (AnchorPane) (Parent) newLoadedPane.load();
             URL mediaUrl = getClass().getResource("Attempt2.mp4");

        String source = mediaUrl.toExternalForm();
        Media media = new Media(source);
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
Pane p = new Pane();
p.setPrefHeight(559);
p.setPrefWidth(708);
p.setStyle("-fx-background-color:  #005452 ");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        MediaView meediaView = new MediaView(mediaPlayer);
        meediaView.setFitWidth(400);
        meediaView.setX(155);
        meediaView.setY(60);
        meediaView.setFitHeight(450);
        meediaView.setMediaPlayer(mediaPlayer);

        meediaView.setSmooth(true);
        p.getChildren().add(meediaView);
        mainap.getChildren().add(p);

        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(2)
        );   
        visiblePause.setOnFinished((ActionEvent event1) -> {
        AjouEventController a = newLoadedPane.<AjouEventController>getController();
        a.setiduserevent(useridlab.getText());
        mainap.getChildren().add(pane);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), anadd);
        closeNav.setToX(-(anadd.getWidth()));
        closeNav.play();   });
        visiblePause.play();
    }

    @FXML
    private void closeGuidepage(ActionEvent event) {
        Stage stage = (Stage) closebutt.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void myeventHandle(ActionEvent event) throws IOException {
        FXMLLoader newLoadedPane = new FXMLLoader(getClass().getResource("TableviewmeseventsFXML.fxml"));
        AnchorPane pane = (AnchorPane) (Parent) newLoadedPane.load();
      //////////////////////////////////////////
        URL mediaUrl = getClass().getResource("Attempt2.mp4");

        String source = mediaUrl.toExternalForm();
        Media media = new Media(source);
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
Pane p = new Pane();
p.setPrefHeight(559);
p.setPrefWidth(708);
p.setStyle("-fx-background-color:  #005452 ");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        MediaView meediaView = new MediaView(mediaPlayer);
        meediaView.setFitWidth(400);
        meediaView.setX(155);
        meediaView.setY(60);
        meediaView.setFitHeight(450);
        meediaView.setMediaPlayer(mediaPlayer);

        meediaView.setSmooth(true);
        p.getChildren().add(meediaView);
        mainap.getChildren().add(p);

        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(2)
        );
        visiblePause.setOnFinished((ActionEvent event1) -> {
            TableviewmeseventsFXMLController a = newLoadedPane.<TableviewmeseventsFXMLController>getController();
            a.setiduserevent(useridlab.getText());
            // a.setiduserevent(useridlab.getText());
            mainap.getChildren().add(pane);
        });
        visiblePause.play();
        //////////////////////////////////////

    }

}
