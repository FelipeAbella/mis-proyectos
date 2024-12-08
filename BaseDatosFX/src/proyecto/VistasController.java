/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.stage.Stage;

public class VistasController implements Initializable {

    @FXML
    private ComboBox<String> cmboxseleccion; // ComboBox para seleccionar las vistas
    @FXML
    private TableView<ObservableList<Object>> tableview; // TableView para mostrar los datos de la vista seleccionada
    @FXML
    private Button btnregresar; // Botón para regresar a la ventana anterior

    private Connection conexion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // El ComboBox se llenará después de que la conexión esté disponible
        cmboxseleccion.setOnAction(event -> mostrarVistaSeleccionada());
    }

    // Método para establecer la conexión
    void setConexion(Connection conexion) {
        this.conexion = conexion;
        cargarVistasEnComboBox();
    }

    // Método para cargar los nombres de las vistas en el ComboBox
    private void cargarVistasEnComboBox() {
        String query = "SHOW FULL TABLES WHERE Table_type = 'VIEW'";
        ObservableList<String> vistasList = FXCollections.observableArrayList();
        
        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String vistaNombre = rs.getString(1); // El nombre de la vista está en la primera columna del ResultSet
                vistasList.add(vistaNombre);
            }
            
            cmboxseleccion.setItems(vistasList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores: puedes mostrar un mensaje al usuario si ocurre un error
        }
    }

    // Método para mostrar los datos de la vista seleccionada en el TableView
    private void mostrarVistaSeleccionada() {
        String vistaSeleccionada = cmboxseleccion.getSelectionModel().getSelectedItem();
        if (vistaSeleccionada == null || conexion == null) {
            return; // No hacer nada si no hay vista seleccionada o no hay conexión
        }
        
        String query = "SELECT * FROM " + vistaSeleccionada;
        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            // Limpiar las columnas existentes en el TableView
            tableview.getColumns().clear();
            
            // Obtener metadatos para configurar las columnas dinámicamente
            int columnCount = rs.getMetaData().getColumnCount();
            ObservableList<TableColumn<ObservableList<Object>, Object>> columns = FXCollections.observableArrayList();
            
            for (int i = 1; i <= columnCount; i++) {
                final int columnIndex = i;
                TableColumn<ObservableList<Object>, Object> column = new TableColumn<>(rs.getMetaData().getColumnName(i));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnIndex - 1)));
                columns.add(column);
            }
            tableview.getColumns().addAll(columns);
            
            // Llenar el TableView con los datos de la vista seleccionada
            ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();
            while (rs.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }
            tableview.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores: puedes mostrar un mensaje al usuario si ocurre un error
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnregresar.getScene().getWindow();
        stage.close();
    }
}
