/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.rando.entities.Event;
import com.rando.services.CRUDevent;
import com.rando.services.CRUDlieu;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.TemporalQueries.localTime;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AjouEventController implements Initializable {

    ObservableList<String> itemstransp = FXCollections.observableArrayList("Bus", "Metro", "Train", "Avion");
    ObservableList<String> itemslog = FXCollections.observableArrayList("Hotel", "Auberge", "Résidance", "Maison d'hôte");
    ObservableList<String> itemslieu = FXCollections.observableArrayList();
    @FXML
    private JFXTextField nomevent;
    @FXML
    private JFXTextField longlieu;
    @FXML
    private JFXTextArea descevent;
    @FXML
    private ImageView newtag;
    @FXML
    private Label nomlieuc;
    @FXML
    private JFXComboBox<String> combolieu;
    @FXML
    private JFXDatePicker datepick;
    @FXML
    private JFXTimePicker timepick;
    @FXML
    private JFXCheckBox checklog;
    @FXML
    private JFXCheckBox checktransp;
    @FXML
    private JFXComboBox<String> combotransp;
    @FXML
    private JFXComboBox<String> combolog;
    @FXML
    private ImageView uploadimg;

    Timeline shiver = new Timeline();
    @FXML
    private Label idlieu;
    @FXML
    private Label vlieue;
    @FXML
    private Label nomlieue;
    @FXML
    private Label govlieu;
    @FXML
    private Text buttaffichlieu;
    @FXML
    private AnchorPane infolieu;
    @FXML
    private Label iduser;
    private String nvlieu = "nouveau lieu . . .";
    @FXML
    private JFXButton nvlieubutton;

    public Boolean verif = false;

    public Boolean teste(Boolean X) {
        verif = X;
        return verif;
    }

    public void setVerif(Boolean X) {
        this.verif = X;
    }

    public Boolean getVerif() {
        return verif;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combotransp.setItems(itemstransp);
        combolog.setItems(itemslog);
        CRUDlieu tools = new CRUDlieu();
        //  affichnom.setText(s);
        itemslieu.add(nvlieu);
        for (int i = 0; i < tools.displayAll().size(); i++) {
            String data;
            data = tools.displayAll().get(i).getNomlieu() + " ," + tools.displayAll().get(i).getVillelieu() + " ," + tools.displayAll().get(i).getGovlieu();
            itemslieu.add(data);
            //   System.out.println(data);
        }
        combolieu.setItems(itemslieu);

        combolieu.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            int j = combolieu.getSelectionModel().getSelectedIndex();

            if (ov.getValue().equals(nvlieu)) {
                nvlieubutton.setVisible(true);
                buttaffichlieu.setVisible(false);
            } else {
                nvlieubutton.setVisible(false);
                String s = tools.displayAll().get(j - 1).getNomlieu() + " ," + tools.displayAll().get(j - 1).getVillelieu() + " ," + tools.displayAll().get(j - 1).getGovlieu();

                String id = tools.displayAll().get(j - 1).getIdlieu();
                System.out.println(s + "-" + id);
                if (ov.getValue().equals(s)) {

                    buttaffichlieu.setVisible(true);
                    idlieu.setText(id);
                    govlieu.setText(tools.displayAll().get(j - 1).getGovlieu());
                    nomlieue.setText(tools.displayAll().get(j - 1).getNomlieu());
                    vlieue.setText(tools.displayAll().get(j - 1).getVillelieu());
                    Image image;
                    try {
                        if (!tools.displayAll().get(j - 1).getPiclieu().equals("")) {
                            image = new Image(new File(tools.displayAll().get(j).getPiclieu()).toURI().toURL().toExternalForm());
                            uploadimg.setImage(image);
                        }
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(AjouEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println(s + "-" + id);
                } else {
                    buttaffichlieu.setVisible(false);
                }
            }

        });

    }

    public void setiduserevent(String id) {
        iduser.setText(id);
    }

    @FXML
    private void handleajoutevent(ActionEvent event) {
        LocalDateTime t = timepick.getValue().atDate(datepick.getValue());
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(t.toLocalDate());
        String log = "";
        String transp = "";
        if (checklog.isSelected() == true) {
            log = combolog.getValue();
        }
        if (checktransp.isSelected() == true) {
            transp = combotransp.getValue();
        }
        System.out.println(Integer.parseInt(iduser.getText()));
        Event e = new Event(1, Integer.parseInt(iduser.getText()), nomevent.getText(), descevent.getText(), idlieu.getText(), gettedDatePickerDate, Integer.parseInt(longlieu.getText()), log, transp, 0, 0);
        CRUDevent tools = new CRUDevent();
        tools.ajouterEvent(e);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(e.getNom() + " ajouté !");
        a.setTitle("");
        a.setHeaderText("");
        a.setGraphic(null);
        //  a.
        a.show();

    }

    @FXML
    private void shiverImage(MouseEvent event) {
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

    @FXML
    private void affichinfolieu(MouseEvent event) {
        infolieu.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished((ActionEvent event1) -> {
            infolieu.setVisible(false);
        });
        visiblePause.play();
    }

    @FXML
    private void ckeckloghandle(ActionEvent event) {
        if (checklog.isSelected() == true) {
            combolog.setVisible(true);
        } else {
            combolog.setVisible(false);
        }
    }

    @FXML
    private void checktranshandle(ActionEvent event) {
        if (checktransp.isSelected() == true) {
            combotransp.setVisible(true);
        } else {
            combotransp.setVisible(false);
        }
    }

    @FXML
    private void newlieuhandle(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajoutlieu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ajoutlieuFXML a = fxmlLoader.<ajoutlieuFXML>getController();
        a.tester(true);
        Stage secondStage = new Stage();
        secondStage.initStyle(StageStyle.UTILITY);

        secondStage.setScene(new Scene(root));

        secondStage.show();

    }

    @FXML
    private void comborefresh(MouseEvent event) {

        itemslieu.clear();

        CRUDlieu tools = new CRUDlieu();
        //  affichnom.setText(s);
        itemslieu.add(nvlieu);
        for (int i = 0; i < tools.displayAll().size(); i++) {
            String data;
            data = tools.displayAll().get(i).getNomlieu() + " ," + tools.displayAll().get(i).getVillelieu() + " ," + tools.displayAll().get(i).getGovlieu();
            itemslieu.add(data);
            //   System.out.println(data);
        }
        combolieu.setItems(itemslieu);

    }

}
