/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.GUI;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.rando.entities.Event;
import com.rando.entities.Lieu;
import com.rando.services.CRUDevent;
import com.rando.services.CRUDlieu;
import com.rando.services.Eventin;
import com.rando.services.WrapEvLi;
import com.rando.services.Wrappe;
import static java.lang.String.valueOf;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
//import com.rando.services.ImageTableCell;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class TableviewmeseventsFXMLController implements Initializable {

    ObservableList<String> itemstransp = FXCollections.observableArrayList("Bus", "Metro", "Train", "Avion");
    ObservableList<String> itemslog = FXCollections.observableArrayList("Hotel", "Auberge", "Résidance", "Maison d'hôte");
    ObservableList<String> itemslieu = FXCollections.observableArrayList();
    @FXML
    private AnchorPane searchmainpane;
    @FXML
    private TableColumn<WrapEvLi, Image> Image;
    @FXML
    private TableColumn<WrapEvLi, String> nom;
    @FXML
    private TableColumn<WrapEvLi, Date> date;
    @FXML
    private TableColumn<WrapEvLi, String> lieu;
    @FXML
    private TableColumn<WrapEvLi, String> nbrP;
    @FXML
    private Label affichnom;
    WrapEvLi w = new WrapEvLi();
    @FXML
    private TableView<WrapEvLi> propev;
    @FXML
    private Label idus;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea descevt;
    @FXML
    private JFXComboBox<String> listli;
    @FXML
    private JFXTextField duree;
    @FXML
    private JFXDatePicker datevt;
    @FXML
    private JFXCheckBox clog;
    @FXML
    private JFXCheckBox ctr;
    @FXML
    private JFXComboBox<String> cmlog;
    @FXML
    private JFXComboBox<String> cmtran;
    @FXML
    private AnchorPane anchormodif;
    int idevtsel;
    public String idlisel;
    @FXML
    private AnchorPane anbutt;
    @FXML
    private Pane panemsl;
    @FXML
    private AnchorPane anp;
    @FXML
    private TableView<Wrappe> tablep;
    @FXML
    private TableColumn<Wrappe, String> nomp;
    @FXML
    private TableColumn<Wrappe, String> prenomp;
    @FXML
    private TableColumn<Wrappe, String> codep;
Wrappe lp= new Wrappe();
    @FXML
    private AnchorPane anpartevent;
    @FXML
    private TableView<Eventin> tabmyevent;
    @FXML
    private TableColumn<Eventin, String> nevtjp;
    @FXML
    private TableColumn<Eventin, String> lieujp;
    @FXML
    private TableColumn<Eventin, String> datejp;
    @FXML
    private TableColumn<Eventin,String> dureejp;
    @FXML
    private TableColumn<Eventin ,String> codeev;
  Eventin jp= new Eventin();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmlog.setItems(itemslog);
        cmtran.setItems(itemstransp);
        CRUDlieu tools = new CRUDlieu();
        for (int i = 0; i < tools.displayAll().size(); i++) {
            String data;
            data = tools.displayAll().get(i).getNomlieu() + " ," + tools.displayAll().get(i).getVillelieu() + " ," + tools.displayAll().get(i).getGovlieu();
            itemslieu.add(data);
            //   System.out.println(data);
        }
        listli.setItems(itemslieu);
        listli.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            int j = listli.getSelectionModel().getSelectedIndex();
            String s = tools.displayAll().get(j).getNomlieu() + " ," + tools.displayAll().get(j).getVillelieu() + " ," + tools.displayAll().get(j).getGovlieu();
            if (ov.getValue().equals(s)) {
                idlisel = tools.displayAll().get(j).getIdlieu();

            }
        });
   

    
    
        
    }

    public void resetTableData(List<WrapEvLi> list) throws MalformedURLException {

        System.out.println(idus.getText());
        int id = Integer.parseInt(idus.getText());
        List<WrapEvLi> res = w.displayAll(id);
        ObservableList<WrapEvLi> data = FXCollections.observableArrayList(res);

        Image.setCellValueFactory(new PropertyValueFactory<>("img"));
        Image.setCellFactory(param -> new ImageTableCell<>());

        nom.setCellValueFactory(
                new PropertyValueFactory<>("nom")
        );

        date.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );

        lieu.setCellValueFactory(
                new PropertyValueFactory<>("lieu")
        );
        nbrP.setCellValueFactory(
                new PropertyValueFactory<>("nbr")
        );

        propev.setItems(data);

    }
    public void resetTablejp(List<Eventin> list) throws MalformedURLException {


        List<Eventin> res = jp.displayAll(Integer.parseInt(idus.getText()));
        ObservableList<Eventin> data = FXCollections.observableArrayList(res);

      

        nevtjp.setCellValueFactory(
                new PropertyValueFactory<>("nom")
        );
 dureejp.setCellValueFactory(
                new PropertyValueFactory<>("duree")
        );
        datejp.setCellValueFactory(
                new PropertyValueFactory<>("datee")
        );

        lieujp.setCellValueFactory(
                new PropertyValueFactory<>("noml")
        );
        codeev.setCellValueFactory(
                new PropertyValueFactory<>("evcode")
        );

       tabmyevent.setItems(data);

    }
 public void resetTablelp(List<Wrappe> list) throws MalformedURLException {

        List<Wrappe> res = lp.displayAll(idevtsel);
        ObservableList<Wrappe> data = FXCollections.observableArrayList(res);

        nomp.setCellValueFactory(
                new PropertyValueFactory<>("nomu")
        );

        prenomp.setCellValueFactory(
                new PropertyValueFactory<>("prenom")
        );

        codep.setCellValueFactory(
                new PropertyValueFactory<>("evcode")
        );
      
        tablep.setItems(data);

    }
    @FXML
    private void ModifEventhand(ActionEvent event) throws SQLException, MalformedURLException {

        if (anchormodif.isVisible()) { //tsajal
            anchormodif.setVisible(false);
            panemsl.setVisible(false);
            Event em = new Event();
            em.setIdevent(idevtsel);
            em.setDate(valueOf(datevt.getValue()));
            em.setNom(titre.getText());
            em.setDescE(descevt.getText());
            em.setDuree(Integer.parseInt(duree.getText()));
            em.setLieu(idlisel);
            if (clog.isSelected() == true) {
                em.setTypelog(cmlog.getValue());
                System.out.println("ghjk");
            }
            if (ctr.isSelected() == true) {
                em.setTypetransp(cmtran.getValue());
            }
            CRUDevent t = new CRUDevent();
            t.modifierEvent(em);
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("les informations de l'event " + em.getNom() + " ont été modifier ave succées");
            a.show();
            refrechtab();
        } else { // ta9ra mil base

            if (propev.getSelectionModel().isEmpty()) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Veuillez selectionner un evenement s.v.p");
                a.show();
            } else {
                WrapEvLi me = (WrapEvLi) propev.getSelectionModel().getSelectedItem();
                anchormodif.setVisible(true);
                panemsl.setVisible(true);
                idevtsel = me.getId();
                CRUDevent e = new CRUDevent();
                Event ev = new Event();
                ev = e.displayEvent(me.getId());

                titre.setText(ev.getNom());
                descevt.setText(ev.getDescE());
                CRUDlieu l = new CRUDlieu();
                Lieu x = new Lieu();
                idlisel = ev.getLieu();
                x = l.displayLieu(idlisel);
                String s = x.getNomlieu() + " ," + x.getVillelieu() + " ," + x.getGovlieu();

                listli.setValue(s);
                if (ev.getTypelog() != null) {
                    clog.setSelected(true);
                    cmlog.setVisible(true);
                    cmlog.setValue(ev.getTypelog());
                } else {
                    cmlog.setVisible(false);
                }
                if (ev.getTypetransp() != null) {
                    ctr.setSelected(true);
                    cmtran.setVisible(true);
                    cmtran.setValue(ev.getTypetransp());
                } else {
                    cmtran.setVisible(false);
                }

                duree.setText("" + ev.getDuree() + "");

                datevt.setValue(ev.getDate().toLocalDate());
                //  e.displayEvent(idlisel);
                /* combotransp
        combolog
        CRUDlieu tools = new CRUDlieu();*/
            }
        }

    }

    public void setiduserevent(String id) {
        System.out.println(id);
        idus.setText(id);
        try {
            // TODO
                try {   List<Eventin> listtForm = new ArrayList<>();
            listtForm = jp.displayAll(Integer.parseInt(idus.getText()));
            resetTablejp(listtForm);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TableviewmeseventsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

            List<WrapEvLi> listForm = new ArrayList<>();

            int i = Integer.parseInt(id);
            listForm = w.displayAll(i);
            resetTableData(listForm);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TableviewmeseventsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refrechtab() throws MalformedURLException {

        List<WrapEvLi> listForm = new ArrayList<>();

        int i = Integer.parseInt(idus.getText());
        listForm = w.displayAll(i);
        resetTableData(listForm);
    }

    @FXML
    private void checkndcomb(ActionEvent event) {

        if (clog.isSelected() == true) {
            cmlog.setVisible(true);
        } else {
            cmlog.setVisible(false);
        }
        if (ctr.isSelected() == true) {
            cmtran.setVisible(true);
        } else {
            cmtran.setVisible(false);
        }

    }
////////////////////////////Supprimer event///////////////

    @FXML
    private void supprimerev(ActionEvent event) throws MalformedURLException {

        if (propev.getSelectionModel().isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Veuillez selectionner un evenement s.v.p");
            a.show();
        } else {
            panemsl.setVisible(true);
            Alert c = new Alert(AlertType.CONFIRMATION);
            Optional<ButtonType> result = c.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            WrapEvLi me = (WrapEvLi) propev.getSelectionModel().getSelectedItem();
           System.out.println(""+me.getId()+"fgfhj");
            if (button == ButtonType.OK) {
                CRUDevent e = new CRUDevent();
              
                e.supprimerEvent(me.getId());
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("Evenement supprimé");
                a.show();
                refrechtab();
                panemsl.setVisible(false);
            }else{
                panemsl.setVisible(false);
            }
        }
    }

    @FXML
    private void listpaffich(ActionEvent event) throws MalformedURLException {
    
        if (propev.getSelectionModel().isEmpty()) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Veuillez selectionner un evenement s.v.p");
            a.show();
        } else { if(!anp.isVisible()){
              panemsl.setVisible(true);
        anp.setVisible(true);
        WrapEvLi me = (WrapEvLi) propev.getSelectionModel().getSelectedItem();
          List<Wrappe> listForm = new ArrayList<>();
idevtsel=me.getId();
    
        listForm = lp.displayAll(idevtsel);
        resetTablelp(listForm);}else{
            
             panemsl.setVisible(false);
        anp.setVisible(false);
            
        }
        
        
        }
    
    
    
    
    
    
    
    }

    @FXML
    private void exitmodifev(ActionEvent event) {
        anchormodif.setVisible(false);
    }

    private class ImageTableCell<S> extends TableCell<S, Image> {

        final ImageView imageView = new ImageView();

        ImageTableCell() {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(Image item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {

                imageView.setImage(null);
                setText(null);
                setGraphic(null);
            }
            imageView.setFitHeight(80);
            imageView.setFitWidth(150);
            imageView.setImage(item);
            setGraphic(imageView);
        }
    }
}
