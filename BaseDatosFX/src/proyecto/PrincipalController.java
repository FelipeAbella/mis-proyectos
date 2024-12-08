/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class PrincipalController implements Initializable {

    @FXML
    private Label textoBase;
    @FXML
    private ComboBox<String> comboTablas;
    @FXML
    private TableView<ObservableList<Object>> tableView;
    private Connection conexion;
    @FXML
    private TextField textfieldbusqueda;
    @FXML
    private Button btnbusqueda;
    @FXML
    private Button btnbusquedaA;
    @FXML
    private Button btnagregar;
    @FXML
    private Button btnVerEstructura;

    private String nombreTablaActual;
    private ObservableList<TableColumn<ObservableList<Object>, ?>> columnas;
    @FXML
    private VBox contenedorInputs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboTablas.setOnAction(event -> {
            String selectedTable = comboTablas.getSelectionModel().getSelectedItem();
            if (selectedTable != null) {
                nombreTablaActual = selectedTable;
                cargarDatosTabla(selectedTable);
            }
        });

        btnbusqueda.setOnAction(event -> buscarEnTabla());

        

        tableView.setEditable(true);
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setBaseSeleccionada(String baseDeDatos) {
        textoBase.setText(baseDeDatos);
    }

    public void setTablas(List<String> tablas) {
        comboTablas.getItems().setAll(tablas);
    }

    private void cargarDatosTabla(String nombreTabla) {
    tableView.getColumns().clear();
    contenedorInputs.getChildren().clear(); // Limpia los TextField existentes

    ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

    String query = "SELECT * FROM " + nombreTabla;
    try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        columnas = FXCollections.observableArrayList();

        for (int i = 1; i <= columnCount; i++) {
            final int colIndex = i - 1;
            String columnName = metaData.getColumnName(i);

            // Crear columna en el TableView
            TableColumn<ObservableList<Object>, Object> column = new TableColumn<>(columnName);
            column.setCellValueFactory(param -> 
                new SimpleObjectProperty<>(param.getValue().size() > colIndex ? param.getValue().get(colIndex) : null)
            );
            columnas.add(column);

            // Crear un TextField dinámico para cada columna
            HBox fieldContainer = new HBox(10);
            Label label = new Label(columnName + ":");
            TextField textField = new TextField();
            textField.setPromptText("Ingrese " + columnName);
            textField.setId(columnName); // ID del TextField para identificarlo
            fieldContainer.getChildren().addAll(label, textField);
            contenedorInputs.getChildren().add(fieldContainer);
        }

        tableView.getColumns().addAll(columnas);

        while (rs.next()) {
            ObservableList<Object> row = FXCollections.observableArrayList();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        tableView.setItems(data);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @FXML

private void agregarregistro(ActionEvent event) {
    if (nombreTablaActual == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Tabla no seleccionada");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, selecciona una tabla antes de agregar un registro.");
        alert.showAndWait();
        return;
    }

    try {
        ResultSetMetaData metaData = conexion.prepareStatement("SELECT * FROM " + nombreTablaActual).getMetaData();
        int columnCount = metaData.getColumnCount();

        String query = "INSERT INTO " + nombreTablaActual + " VALUES (" + generatePlaceholders(columnCount) + ")";
        PreparedStatement ps = conexion.prepareStatement(query);

        ObservableList<Object> nuevaFila = FXCollections.observableArrayList();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String value = null;

            // Buscar el valor ingresado en el TextField correspondiente
            for (Node node : contenedorInputs.getChildren()) {
                if (node instanceof HBox) {
                    HBox hbox = (HBox) node;
                    for (Node child : hbox.getChildren()) {
                        if (child instanceof TextField && ((TextField) child).getId().equals(columnName)) {
                            TextField textField = (TextField) child;
                            value = textField.getText();
                            break;
                        }
                    }
                }
            }

            // Validar claves foráneas antes de insertar
            if (columnName.equals("CountryCode")) { // Ajustar según tu columna
                if (!existeEnTablaReferenciada("country", "Code", value)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Clave Foránea Inválida");
                    alert.setHeaderText(null);
                    alert.setContentText("El valor '" + value + "' no existe en la tabla referenciada (country).");
                    alert.showAndWait();
                    return; // Detener si la clave foránea no es válida
                }
            }

            // Preparar el valor para la consulta
            if (value == null || value.isEmpty()) {
                ps.setNull(i, Types.VARCHAR); // Suponer tipo VARCHAR
                nuevaFila.add(null);
            } else {
                ps.setString(i, value);
                nuevaFila.add(value);
            }
        }

        ps.executeUpdate();

        // Agregar la nueva fila al TableView
        tableView.getItems().add(nuevaFila);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro Agregado");
        alert.setHeaderText(null);
        alert.setContentText("El registro se ha agregado correctamente.");
        alert.showAndWait();

    } catch (SQLException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al Agregar");
        alert.setHeaderText(null);
        alert.setContentText("Ocurrió un error al agregar el registro.");
        alert.showAndWait();
    }
}

// Método para verificar si un valor existe en la tabla referenciada
private boolean existeEnTablaReferenciada(String tableName, String columnName, String value) throws SQLException {
    String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?";
    PreparedStatement ps = conexion.prepareStatement(query);
    ps.setString(1, value);
    ResultSet rs = ps.executeQuery();
    rs.next();
    return rs.getInt(1) > 0;
}



private void guardarCambios(ActionEvent event) {
    if (nombreTablaActual == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Tabla no seleccionada");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, selecciona una tabla antes de guardar los cambios.");
        alert.showAndWait();
        return;
    }

    ObservableList<ObservableList<Object>> data = tableView.getItems();
    try {
        conexion.setAutoCommit(false);
        ResultSetMetaData metaData = conexion.prepareStatement("SELECT * FROM " + nombreTablaActual).getMetaData();
        int columnCount = metaData.getColumnCount();

        for (ObservableList<Object> row : data) {
            // Solo guardar filas que no estén vacías
            if (row.stream().allMatch(item -> item == null || item.toString().isEmpty())) {
                continue;
            }

            // Generar placeholders para la consulta SQL
            String placeholders = generatePlaceholders(columnCount);

            String query = "INSERT INTO " + nombreTablaActual + " VALUES (" + placeholders + ")";
            PreparedStatement ps = conexion.prepareStatement(query);

            for (int i = 1; i <= columnCount; i++) {
                ps.setObject(i, row.get(i - 1));
            }

            ps.executeUpdate();
        }

        conexion.commit();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guardado Exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Los registros han sido guardados correctamente.");
        alert.showAndWait();

    } catch (SQLException e) {
        e.printStackTrace();
        try {
            conexion.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } finally {
        try {
            conexion.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Método auxiliar para generar placeholders (?, ?, ?, ...)
   private String generatePlaceholders(int count) {
    StringBuilder placeholders = new StringBuilder();
    for (int i = 0; i < count; i++) {
        placeholders.append("?");
        if (i < count - 1) {
            placeholders.append(", ");
        }
    }
    return placeholders.toString();
}



    private void buscarEnTabla() {
        String searchText = textfieldbusqueda.getText();
        String selectedTable = comboTablas.getSelectionModel().getSelectedItem();

        if (selectedTable != null && searchText != null && !searchText.isEmpty()) {
            tableView.getColumns().clear();
            ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

            // Construir consulta SQL con LIKE en todas las columnas
            try {
                PreparedStatement psColumns = conexion.prepareStatement("SHOW COLUMNS FROM " + selectedTable);
                ResultSet rsColumns = psColumns.executeQuery();

                StringBuilder whereClause = new StringBuilder();
                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("Field");
                    if (whereClause.length() > 0) {
                        whereClause.append(" OR ");
                    }
                    whereClause.append(columnName).append(" LIKE ?");
                }

                String query = "SELECT * FROM " + selectedTable + " WHERE " + whereClause.toString();

                PreparedStatement psSearch = conexion.prepareStatement(query);
                int paramIndex = 1;
                String[] columnsArray = whereClause.toString().split(" OR ");
                for (int i = 0; i < columnsArray.length; i++) {
                    psSearch.setString(paramIndex++, "%" + searchText + "%");
                }

                ResultSet rs = psSearch.executeQuery();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                columnas = FXCollections.observableArrayList();

                for (int i = 1; i <= columnCount; i++) {
                    final int colIndex = i - 1;
                    TableColumn<ObservableList<Object>, Object> column = new TableColumn<>(metaData.getColumnName(i));

                    column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().get(colIndex)));

                    // Habilitar edición
                    column.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Object>() {
                        @Override
                        public String toString(Object object) {
                            return object == null ? "" : object.toString();
                        }

                        @Override
                        public Object fromString(String string) {
                            return string;
                        }
                    }));

                    column.setOnEditCommit((CellEditEvent<ObservableList<Object>, Object> event) -> {
                        ObservableList<Object> row = event.getRowValue();
                        row.set(event.getTablePosition().getColumn(), event.getNewValue());
                    });

                    columnas.add(column);
                }

                tableView.getColumns().addAll(columnas);

                while (rs.next()) {
                    ObservableList<Object> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getObject(i));
                    }
                    data.add(row);
                }

                tableView.setItems(data);

            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Búsqueda");
                alert.setHeaderText(null);
                alert.setContentText("Ocurrió un error al realizar la búsqueda.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Búsqueda Vacía");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingrese un término de búsqueda.");
            alert.showAndWait();
        }
    }

    @FXML
    private void vistabusqueda(ActionEvent event) throws IOException {
        // Cargar la nueva vista
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/busquedaAvanzada.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la nueva vista
        BusquedaAvanzadaController controller = loader.getController();

        // Pasar la conexión a la nueva vista
        controller.setConexion(this.conexion);

        // Crear una nueva escena y mostrarla en un nuevo stage
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Busqueda Avanzada");
        stage.setResizable(false);
        stage.show();

        // Cerrar la ventana actual
        Stage currentStage = (Stage) btnbusquedaA.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void verEstructura() {
        String selectedTable = comboTablas.getSelectionModel().getSelectedItem();

        if (selectedTable != null) {
            // Crear un StringBuilder para almacenar la estructura de la tabla
            ObservableList<ObservableList<Object>> estructura = FXCollections.observableArrayList();

            try (Statement stmt = conexion.createStatement()) {
                String query = "DESCRIBE " + selectedTable;
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    ObservableList<Object> row = FXCollections.observableArrayList();
                    row.add(rs.getString("Field"));
                    row.add(rs.getString("Type"));
                    row.add(rs.getString("Null"));
                    row.add(rs.getString("Key"));
                    row.add(rs.getString("Default"));
                    row.add(rs.getString("Extra"));
                    estructura.add(row);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                // Mostrar error si hay algún problema
                return;
            }

            // Cargar la vista de EstructuraBDController
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/EstructuraBD.fxml"));
                Parent root = loader.load();

                // Obtener el controlador de la nueva vista
                EstructuraBDController estructuraController = loader.getController();
                estructuraController.setEstructuraTabla(estructura);  // Pasar los datos de la estructura

                // Cambiar de vista
                Scene scene = new Scene(root);
                Stage stage = (Stage) btnVerEstructura.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Si no se ha seleccionado ninguna tabla
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección de Tabla");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione una tabla para ver su estructura.");
            alert.showAndWait();
        }
    }

    
}
