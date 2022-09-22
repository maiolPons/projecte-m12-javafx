/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectem12;
import static com.mycompany.projectem12.App.usuari;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Maiol
 */
public class ControladorMenuGlobal {
    
    @FXML
    private Label nomUsuariLabel;
    


    @FXML
    protected void initialize(){
        nomUsuariLabel.setText(usuari.getNom());
    }
    
    @FXML
    private void tancarSessio(ActionEvent event) throws IOException {
        usuari.tancarSessio();
        App.setRoot("Login");
    }
}
