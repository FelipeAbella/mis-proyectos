package proyecto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstructuraBDController {

    @FXML
    private TableView<ObservableList<Object>> tableViewEstructura;
    @FXML
    private TableColumn<ObservableList<Object>, String> columnField;
    @FXML
    private TableColumn<ObservableList<Object>, String> columnType;
    @FXML
    private TableColumn<ObservableList<Object>, String> columnNull;
    @FXML
    private TableColumn<ObservableList<Object>, String> columnKey;
    @FXML
    private TableColumn<ObservableList<Object>, String> columnDefault;
    @FXML
    private TableColumn<ObservableList<Object>, String> columnExtra;
    @FXML
    private Button btnVolver;

    // Nueva lista de datos de la estructura de la tabla
    private ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Configuración de las columnas de la tabla para mostrar los datos de la estructura
        columnField.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().get(0).toString()));
        columnType.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().get(1).toString()));
        
        // Verifica si el índice 2 existe antes de asignarlo a columnNull
        columnNull.setCellValueFactory(cellData -> {
            if (cellData.getValue().size() > 2 && cellData.getValue().get(2) != null) {
                return new SimpleObjectProperty<>(cellData.getValue().get(2).toString());
            } else {
                return new SimpleObjectProperty<>("NO"); // Valor predeterminado si no hay datos
            }
        });  
        
        // Verifica si el índice 3 existe antes de asignarlo a columnKey
        columnKey.setCellValueFactory(cellData -> {
            if (cellData.getValue().size() > 3 && cellData.getValue().get(3) != null) {
                return new SimpleObjectProperty<>(cellData.getValue().get(3).toString());
            } else {
                return new SimpleObjectProperty<>("N/A");
            }
        });
        
        // Obtener el valor de la celda (en la posición 4, si está disponible)
        columnDefault.setCellValueFactory(cellData -> {
            Object value = cellData.getValue().get(4);
            // Verificar si el valor es null o está vacío
            if (value == null || value.toString().trim().isEmpty()) {
                return new SimpleObjectProperty<>("N/A");  // Valor predeterminado si es null o vacío
            } else {
                return new SimpleObjectProperty<>(value.toString());  // Devuelve el valor tal cual, ya sea numérico o String
            }
        });
        
        columnExtra.setCellValueFactory(cellData -> {
            if (cellData.getValue().size() > 5 && cellData.getValue().get(5) != null) {
                return new SimpleObjectProperty<>(cellData.getValue().get(5).toString());
            } else {
                return new SimpleObjectProperty<>("NO"); // Valor predeterminado si no hay datos
            }
        }); 
        
    }
    
    // Recibir los datos de la estructura de la tabla desde el PrincipalController
    public void setEstructuraTabla(ObservableList<ObservableList<Object>> estructura) {
        // Asignar los datos recibidos a la tabla
        this.data = estructura;
        // Llenar la tabla con los datos recibidos
        tableViewEstructura.setItems(data);
    }

    @FXML
    private void volver(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Inicio.fxml"));
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Inicio");
        stage.setResizable(false);
        stage.show();

        // Cerrar la ventana actual
        Stage currentStage = (Stage) btnVolver.getScene().getWindow();
        currentStage.close();
    }
}