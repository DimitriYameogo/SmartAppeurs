/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rando.services;
import java.io.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
 
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author esprit
 */
public class lieuModel {
    
    Timeline shiver = new Timeline();
    @FXML TextField text;
    @FXML  Tooltip ttip;
    
    public boolean OnlyChar(TextField text,Label l){
        if (!text.getText().matches("[A-Za-z]") && !text.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) {
             shiverTextField(text);
             l.setText("* Le nom du lieu ne doit pas etre vide et doit contenir que des lettres");
             l.setOpacity(1);
             return false;
          }
        return true;
 
    }

@FXML
    public void shiverTextField(TextField text) {

        DoubleProperty y = text.translateXProperty();

        shiver.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(y, 0)),
                new KeyFrame(new Duration(50), new KeyValue(y, +5)),
                new KeyFrame(new Duration(100), new KeyValue(y, 0)),
                new KeyFrame(new Duration(150), new KeyValue(y, -5)),
                new KeyFrame(new Duration(200), new KeyValue(y, 0))
        );

        shiver.play();
    }







}
