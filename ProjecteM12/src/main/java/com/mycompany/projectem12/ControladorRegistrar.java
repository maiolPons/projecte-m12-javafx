package com.mycompany.projectem12;

import com.mycompany.controlAccess.user;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorRegistrar {
    //atributs
    @FXML
    private Label errorRegistrar;
    @FXML
    private TextField usuariregistrar;
    @FXML
    private TextField nomRegistrar;
    @FXML
    private TextField cognomsRegistrar;
    @FXML
    private TextField dniRegistrar;
    @FXML
    private TextField nacionalitatRegistrar;
    @FXML
    private TextField telefonRegistrar;
    @FXML
    private TextField correuRegistrar;
    @FXML
    private TextField contrasenyaRegistrar;
    //get and set

    public Label getErrorRegistrar() {
        return errorRegistrar;
    }

    public TextField getUsuariregistrar() {
        return usuariregistrar;
    }

    public TextField getNomRegistrar() {
        return nomRegistrar;
    }

    public TextField getCognomsRegistrar() {
        return cognomsRegistrar;
    }

    public TextField getDniRegistrar() {
        return dniRegistrar;
    }

    public TextField getNacionalitatRegistrar() {
        return nacionalitatRegistrar;
    }

    public TextField getTelefonRegistrar() {
        return telefonRegistrar;
    }

    public TextField getCorreuRegistrar() {
        return correuRegistrar;
    }

    public TextField getContrasenyaRegistrar() {
        return contrasenyaRegistrar;
    }
    
    //metodes
    @FXML
    private void registrarUsuari(ActionEvent event) throws IOException {
        user usuariAregistrar = new user(getDniRegistrar().getText(),getNomRegistrar().getText(),getUsuariregistrar().getText(),getCognomsRegistrar().getText(),getContrasenyaRegistrar().getText(), getNacionalitatRegistrar().getText(), getTelefonRegistrar().getText(), getCorreuRegistrar().getText());
        if(usuariAregistrar.crearUsuari()){
            errorRegistrar.setText("Usuari registrar correctament!");
        }else{
            errorRegistrar.setText("Error amb el registre de usuari!");
        }
    }
    
}
