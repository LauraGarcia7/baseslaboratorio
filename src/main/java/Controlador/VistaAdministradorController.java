/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.Conexion;
import VO.Inventario;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import VO.Producto;
import java.util.HashMap;

/**
 * FXML Controller class
 *
 * @author Johan Sánchez
 */
public class VistaAdministradorController implements Initializable {

    @FXML
    private Tab pInventario;
    @FXML
    private TableView<Inventario> tablitaInventario;
    @FXML
    private TableColumn<Inventario, String> columnaIdProducto;
    @FXML
    private TableColumn<Inventario, String> columnaNombre;
    @FXML
    private TableColumn<Inventario, String> columnaCantidad;
    @FXML
    private TableColumn<Inventario, String> columnaPrecio;
    @FXML
    private TableColumn<Inventario, String> columnaCodigoBarras;
    @FXML
    private TableColumn<Inventario, String> columnaFechaE;
    @FXML
    private TableColumn<Inventario, String> columnaFechaC;
    @FXML
    private TableColumn<Inventario, String> columnaProveedor;
    @FXML
    private Button botonGenerarTablaInventario;
    @FXML
    private Label labelidProducto;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelCantidad;
    @FXML
    private Label labelPrecio;
    @FXML
    private Label labelCodigoBarras;
    @FXML
    private Label labelFechaEntrada;
    @FXML
    private Label labelFechaCaducidad;
    @FXML
    private TextField textFidProducto;
    @FXML
    private TextField textFNombre;
    @FXML
    private TextField textFCantidad;
    @FXML
    private TextField textFPrecio;
    @FXML
    private TextField textFCodigoBarras;
    @FXML
    private TextField textFFechaEntrada;
    @FXML
    private TextField textFFechaCaducidad;
    @FXML
    private Button botonBuscarInventario;
    @FXML
    private Button botonNuevoProducto;
    @FXML
    private Button botonAñadirProducto;
    @FXML
    private Button botonEliminarProduto;
    @FXML
    private Button botonModificar;
    @FXML
    private Label labelProveedor;
    @FXML
    private TextField textFProveedor;
    @FXML
    private Tab pPorcentaje;
    @FXML
    private TableView<?> tablitaProductosvendidos;
    @FXML
    private TableColumn<?, ?> columnaProdutoTorta;
    @FXML
    private TableColumn<?, ?> columnaVentasTorta;
    @FXML
    private PieChart graficaPorcentajeTorta;
    @FXML
    private Button botongenerarPorcentajeProductos;
    @FXML
    private Tab pInforme;
    @FXML
    private TableView<?> tablitaVendedorVenta;
    @FXML
    private TableColumn<?, ?> columnaCadaVendedor;
    @FXML
    private TableColumn<?, ?> columnaVentaVendedor;
    @FXML
    private Button botonGenerarCadaVendedor;
    @FXML
    private BarChart<?, ?> graficaCadaVendedor;
    @FXML
    private Tab pVentasMes;
    @FXML
    private TableView<?> tablitaVentaMes;
    @FXML
    private TableColumn<?, ?> columnaMes;
    @FXML
    private TableColumn<?, ?> columnaVentames;
    @FXML
    private Button botonGenerar;
    @FXML
    private LineChart<?, ?> graficaVentasMes;
    @FXML
    private Tab pMeta;
    @FXML
    private BarChart<?, ?> graficaBarrasMeta;
    @FXML
    private Button botonGenerarMeta;
    @FXML
    private Label labelAdministrador;
    @FXML
    private TextField textFAdministrador;
    @FXML
    private Label labelId;
    @FXML
    private TextField textFId;
    @FXML
    private Button botonCerrar;
    
    private ObservableList<Inventario>data;
    private Conexion conexion;
    private PreparedStatement r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conexion= new Conexion();
    }    

    @FXML
    private void generarInventario(ActionEvent event) {
    }

    @FXML
    private void buscarEnInventario(ActionEvent event) {
    }

    @FXML
    private void nuevoProducto(ActionEvent event) throws SQLException {
        
        Connection conn= conexion.getConnection();
        data=FXCollections.observableArrayList();
        
        String sql= "INSERT INTO Inventario VALUES(?,?,?,?,?.?,?,?)";
        int id= Integer.parseInt(textFidProducto.getText());
        String nombre= textFNombre.getText();
        String cantidad=textFCantidad.getText();
        double precio=Double.valueOf(textFPrecio.getText());
        int codB=Integer.parseInt(textFCodigoBarras.getText());
        String fecha= textFFechaEntrada.getText();
        String fechaC= textFFechaCaducidad.getText();
        String proveedor=textFProveedor.getText();
        try {
         r =conn.prepareStatement(sql);
         r.setInt(1, id);
         r.setString(2, nombre);
         r.setString(3, cantidad);
         r.setDouble(4, precio);
         r.setInt(5, codB);
         r.setString(6, fecha);
         r.setString(7, fechaC);
         r.setString(8, proveedor);
         
         int i =r.executeUpdate();
            if (i==1) {
                System.out.println("Data insert successfully");
            }
        ResultSet rs=conn.createStatement().executeQuery("INSERT INTO Inventario VALUES("+id+""+nombre+""+cantidad+""+precio+""+codB+""+fecha+""+fechaC+""+proveedor+");");
        
            while (rs.next()) {                
                data.add(new Inventario());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
    }

    @FXML
    private void añadirProducto(ActionEvent event) {
    }

    @FXML
    private void modificar(ActionEvent event) {
    }

    @FXML
    private void generarCadadVendedor(ActionEvent event) {
    }

    @FXML
    private Map<Integer, ArrayList>generarVentasMes() throws SQLException {
        Connection conn= conexion.getConnection();
        String sql="";
        Map<Integer, ArrayList>retorno= new HashMap();
        Producto producto=new Producto();
        try {
            PreparedStatement statement= conn.prepareStatement(sql);
            ResultSet resultado=statement.executeQuery();
            
            while (resultado.next()) {                
                ArrayList lista = new ArrayList();
                if (!retorno.containsKey(resultado.getInt(producto.getIdProducto()))) {
                    //retorno.add(resultado.getInt(IDProducto));
                    //retorno.add(resultado.getInt(Cantidad));
                }
            }
        } catch (Exception e) {
        }
       return null;
    }

    @FXML
    private void generarMeta(ActionEvent event) {
    }

    @FXML
    private void cerrar(ActionEvent event) {
    }
    
        private void loadDataFromDatabase(ActionEvent event) throws SQLException{
        try {
        Connection conn= conexion.getConnection();
        data=FXCollections.observableArrayList();
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM Inventario");
        
            while (rs.next()) {                
                data.add(new Inventario());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
        columnaIdProducto.cellValueFactoryProperty();
        columnaNombre.cellValueFactoryProperty();
        columnaCantidad.cellValueFactoryProperty();
        columnaPrecio.cellValueFactoryProperty();
        columnaCodigoBarras.cellValueFactoryProperty();
        columnaFechaE.cellValueFactoryProperty();   
        columnaFechaC.cellValueFactoryProperty();
        columnaProveedor.cellValueFactoryProperty();
        
        tablitaInventario.setItems(null);
        tablitaInventario.setItems(data);        
    }
    
}
