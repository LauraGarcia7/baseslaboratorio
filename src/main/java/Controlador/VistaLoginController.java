/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Laura Parada
 */
public class VistaLoginController implements Initializable {

    @FXML
    private ImageView imagenLoging;
    @FXML
    private PasswordField textFContraseña;
    @FXML
    private Label labelContraseña;
    @FXML
    private Label labelUsuario;
    @FXML
    private TextField textFUsuario;
    @FXML
    private RadioButton radioBAdministrador;
    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton radioBVendedor;
    @FXML
    private Button botonOK;
    @FXML
    private Label labelError;
    
     private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         conexion= new Conexion();
    }    

    @FXML
    private void accionEnviar(ActionEvent event) throws IOException {
        //radioBAdministrador radioBVendedor
        Stage stage = new Stage();
        if ((Button)event.getSource()==botonOK) {
             Parent root = FXMLLoader.load(getClass().getResource("VistaVendedor.fxml"));   
             Scene scene = new Scene(root);
        
        stage.setScene(scene);
        } else {
        }
    }
    
}
