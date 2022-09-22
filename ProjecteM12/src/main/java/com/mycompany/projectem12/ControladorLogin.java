
package com.mycompany.projectem12;
import static com.mycompany.projectem12.App.connection;
import static com.mycompany.projectem12.App.usuari;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorLogin {
    //atributs
    @FXML
    private Label errorLogin;
    @FXML
    private TextField usuariLogin;
    @FXML
    private TextField contrasenyaLogin;
    //get and set

    public Label getErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(Label errorLogin) {
        this.errorLogin = errorLogin;
    }

    public TextField getUsuariLogin() {
        return usuariLogin;
    }

    public void setUsuariLogin(TextField usuariLogin) {
        this.usuariLogin = usuariLogin;
    }

    public TextField getContrasenyaLogin() {
        return contrasenyaLogin;
    }

    public void setContrasenyaLogin(TextField contrasenyaLogin) {
        this.contrasenyaLogin = contrasenyaLogin;
    }
    
    @FXML
    private void login(ActionEvent event) throws UnsupportedEncodingException, IOException{
        usuari.login(getUsuariLogin().getText(), getContrasenyaLogin().getText());
        if(usuari.getLogged()){
            if(usuari.getAdmin()){
                App.setRoot("menuPrincipalAdmin");
            }else{
                App.setRoot("menuPrincipal");
            }
        }else{
            errorLogin.setText("Error amb la identificacio!");
        }
    }
    @FXML
    private void registrar(ActionEvent event) throws IOException {
        App.setRoot("registrarUsuari");
    }
}
