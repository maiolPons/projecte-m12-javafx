/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectem12;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Maiol
 */
public class ControladorMenuAdmin extends ControladorMenuGlobal {
    @FXML
    private void obrirRecepcionistes(ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login" + ".fxml"));
        Parent root = FXMLLoader.load(App.class.getResource("finestraRecepcionistes" + ".fxml"));
        Stage stage = new Stage();
        Scene scene2 =new Scene(root);
        stage.setScene(scene2);
        stage.show();
    }
}
