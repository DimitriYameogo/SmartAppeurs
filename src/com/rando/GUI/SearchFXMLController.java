/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.GUI;

import com.rando.services.AppModel;
import com.rando.services.CRUDevent;
import com.rando.services.CRUDlieu;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class SearchFXMLController implements Initializable {

    FXMLLoader fxmlLoader;
    @FXML
    private Label affichnom;
    @FXML
    private ListView<String> listSearch;
    private String customId;

    ObservableList<String> data = FXCollections.observableArrayList();
    @FXML
    private Label idlieu;
    @FXML
    private AnchorPane searchmainpane;
private int test=0;
private String idduser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void rec(String s, String r) {
        test=1;
        CRUDlieu tools = new CRUDlieu();
        affichnom.setText(s);
        for (int i = 0; i < tools.recherchelieu(r).size(); i++) {
            data.add(tools.recherchelieu(r).get(i).getNomlieu());
            System.out.println(tools.recherchelieu(r).get(i).getNomlieu());
        }
        listSearch.setItems(data);
        /*listSearch.setCellFactory(new Callback<ListView<String>, 
           ListCell<String>>() {
                @Override 
                public ListCell<String> call(ListView<String> list) {
                  
                int j=(list.getSelectionModel().getSelectedIndex());
                
                    return ;
                }
            }
        );*/

        listSearch.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            int j = listSearch.getSelectionModel().getSelectedIndex();
            if (ov.getValue().equals(tools.recherchelieu(r).get(j).getNomlieu())) {
                idlieu.setText(tools.recherchelieu(r).get(j).getIdlieu());
            }

        });
    }
public void recevent(String s, String r, String m) {
    test=2;  
    CRUDevent tools = new CRUDevent();
        affichnom.setText(s);
        for (int i = 0; i < tools.rechercheevent(r).size(); i++) {
            data.add(tools.rechercheevent(r).get(i).getNom());
            System.out.println(tools.rechercheevent(r).get(i).getNom());
        }
        listSearch.setItems(data);
        /*listSearch.setCellFactory(new Callback<ListView<String>, 
           ListCell<String>>() {
                @Override 
                public ListCell<String> call(ListView<String> list) {
                  
                int j=(list.getSelectionModel().getSelectedIndex());
                
                    return ;
                }
            }
        );*/

listSearch.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            int j = listSearch.getSelectionModel().getSelectedIndex();
            if (ov.getValue().equals(tools.rechercheevent(r).get(j).getNom())) {
                idlieu.setText(""+tools.rechercheevent(r).get(j).getIdevent()+"");
            }

        });
   idduser=m; }
    @FXML
    private void handlelieuPage(MouseEvent event) throws IOException, SQLException {
if(test==1){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lieupage.fxml"));

        AnchorPane pane = (AnchorPane) (Parent) fxmlLoader.load();
        LieupageController con = fxmlLoader.<LieupageController>getController();
        con.affichpage(idlieu.getText());
//  labelmsg= "Recherche du "+combosearch.getValue()+" : "+searchinput.getText();
        //     con.rec(labelmsg,searchinput.getText());
        searchmainpane.getChildren().add(pane);

    }
   
        if(test==2){
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Eventpage.fxml"));

        AnchorPane pane = (AnchorPane) (Parent) fxmlLoader.load();
       EventpageController con = fxmlLoader.<EventpageController>getController();
        con.affichevent(idlieu.getText(),idduser);
//  labelmsg= "Recherche du "+combosearch.getValue()+" : "+searchinput.getText();
        //     con.rec(labelmsg,searchinput.getText());
        searchmainpane.getChildren().add(pane);    
        }
        
        
        
    }
    }


