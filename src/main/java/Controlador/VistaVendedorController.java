/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import VO.Producto;
import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.util.logging.PlatformLogger;
/**
 * FXML Controller class
 *
 * @author Laura Parada
 */
public class VistaVendedorController implements Initializable {

    @FXML
    private Tab pProductos;
    @FXML
    private Tab pGenerarFactura;
    @FXML
    private Button bActualizarFactura;
    @FXML
    private Button bImprimir;
    @FXML
    private TextArea textAreaFactura;
    @FXML
    private Label labelNombreFactura;
    @FXML
    private Button botonCerrarVendedor;
    @FXML
    private TableView<Producto> tablitaProductos;
    @FXML
    private TableColumn<Producto, String> cloumnaIdProducto;
    @FXML
    private TableColumn<Producto, String> columnaNombre;
    @FXML
    private TableColumn<Producto, String> columnaCatidad;
    @FXML
    private TableColumn<Producto, String> columnaPrecio;
    @FXML
    private TableColumn<Producto, String> columnaCodigoBarras;
    @FXML
    private TableColumn<Producto, String> columnaFechaEntrada;
    @FXML
    private TableColumn<Producto, String> columnaFechaCaducidad;
    @FXML
    private TableColumn<Producto, String> columnaProveedor;
    @FXML
    private Label labelCodigobarras;
    @FXML
    private Label labelCantidad;
    @FXML
    private TextField textFCodigoBarras;
    @FXML
    private TextField textFCantidad;
    @FXML
    private Button botonRegistrarproducto;
    @FXML
    private Button botonModificarProducto;
    @FXML
    private Button botonBorrar;
    @FXML
    private Label labelCliente;
    @FXML
    private Label labelidCliente;
    @FXML
    private Label labelCedula;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelTelefono;
    @FXML
    private Label labelDireccion;
    @FXML
    private TextField textFidCliente;
    @FXML
    private TextField textFCedula;
    @FXML
    private TextField textFNombre;
    @FXML
    private TextField textFTelefono;
    @FXML
    private TextField textFDireccion;
    @FXML
    private Button botonOK;
    @FXML
    private Label labelFacturaPara;
    @FXML
    private Label labelVendedor;
    @FXML
    private Label labelIdCaja;
    @FXML
    private Label labelDineroActual;
    @FXML
    private TextField textFvendedor;
    @FXML
    private TextField textFidCaja;
    @FXML
    private TextField textFdineroActual;
    
    
    private ObservableList<Producto>data;
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
    private void actualizarFactura(ActionEvent event) {
      try {
        Connection conn= conexion.getConnection();
        data=FXCollections.observableArrayList();
        ResultSet rs=conn.createStatement().executeQuery("UPDATE Producto;");
        
            while (rs.next()) {                
                data.add(new Producto());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
    }

    @FXML
    private void imprimir(ActionEvent event) {
         try {
        Connection conn= conexion.getConnection();
        data=FXCollections.observableArrayList();
        
        ResultSet rs=conn.createStatement().executeQuery("SHOW Producto;");
        
            while (rs.next()) {                
                data.add(new Producto());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
    }
    
    @FXML
    private void cerrarVendedor(ActionEvent event){
        
    }

    @FXML
    private void registrarproducto(ActionEvent event) {
        
       try {
        Connection conn= conexion.getConnection();
        data=FXCollections.observableArrayList();
        

        int idCli= Integer.parseInt(textFidCliente.getText());
        int cedula= Integer.parseInt(textFCedula.getText());
        String nombre= textFNombre.getText();
        int telefono=Integer.parseInt(textFTelefono.getText());
        String direccion= textFDireccion.getText();
    
        ResultSet rs=conn.createStatement().executeQuery("INSERT INTO Producto VALUES("+idCli+""+cedula+""+nombre+""+telefono+""+direccion+");");
        
            while (rs.next()) {                
                data.add(new Producto());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
    }

    @FXML
    private void modificarProducto(ActionEvent event) {
    }

    @FXML
    private void borrar(ActionEvent event) {
    }

    @FXML
    private void agregarClienteOK(ActionEvent event) {
    }
    
    private void loadDataFromDatabase(ActionEvent event) throws SQLException{
        try {
        Connection conn= conexion.getConnection();
        data=FXCollections.observableArrayList();
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM Producto");
        
            while (rs.next()) {                
                data.add(new Producto());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
        cloumnaIdProducto.cellValueFactoryProperty();
        columnaNombre.cellValueFactoryProperty();
        columnaCatidad.cellValueFactoryProperty();
        columnaPrecio.cellValueFactoryProperty();
        columnaCodigoBarras.cellValueFactoryProperty();
        columnaFechaEntrada.cellValueFactoryProperty();
        columnaFechaCaducidad.cellValueFactoryProperty();
        columnaProveedor.cellValueFactoryProperty();
        
        tablitaProductos.setItems(null);
        tablitaProductos.setItems(data);
        
    }


}
