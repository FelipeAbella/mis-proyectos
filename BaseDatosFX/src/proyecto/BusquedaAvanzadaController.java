/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template


/**
 * FXML Controller class for Advanced Search
 * 
 * @author brand
 */
package proyecto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.stage.Stage;

public class BusquedaAvanzadaController implements Initializable {

    @FXML
    private ComboBox<String> Cmbxtabla1; // Tabla 1
    @FXML
    private ComboBox<String> Cmbxtabla2; // Tabla 2 para relacionar
    @FXML
    private ComboBox<String> cmbxidentificador1; // Identificador tabla 1
    @FXML
    private ComboBox<String> cmbxidentificador2; // Identificador tabla 2
    @FXML
    private ComboBox<String> cmbxoperador; // Operador de condición
    @FXML
    private TextField cmbxvalores; // Valores de la condición
    @FXML
    private ComboBox<String> cmbxoperador2; // Operador de condición 2
    @FXML
    private TextField cmbxvalores2; // Valores de la condición 2
    @FXML
    private TableView<ObservableList<Object>> tablaresultado; // Tabla resultado
    @FXML
    private Button botonbuscar; // Botón de búsqueda
    @FXML
    private TextField cmbxcantidaddatos; // Número de datos
    @FXML
    private CheckBox chbxdostablas; // Checkbox para relacionar tablas
    @FXML
    private CheckBox chbxcondiciones; // Checkbox para agregar una segunda condición
    @FXML
    private Button btncrearvista; // Crear vista
    @FXML
    private TextField cmbxnombrevista; // Nombre de la vista
    @FXML
    private Button btnVolver; // Botón para volver a la vista anterior
    @FXML
    private TextArea txtAreaQuery; // TextArea para mostrar la consulta en tiempo real
    @FXML
    private Button btnlimpiarcampos; // Botón para limpiar los campos
    private Button btnquitarcondicion; // Botón para quitar la condición
    private Button btnMostrarVistas; // Botón para mostrar vistas

    private Connection conexion;
    @FXML
    private CheckBox chckboxtodos;
    @FXML
    private Button btnIrAVistas; // Botón para ir a la vista de Vistas
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando controlador...");
        // Verificar que los elementos no sean null antes de usarlos
        if (Cmbxtabla2 != null) {
            Cmbxtabla2.setDisable(true);
        }
        if (cmbxidentificador2 != null) {
            cmbxidentificador2.setDisable(true);
        }
        if (cmbxoperador2 != null) {
            cmbxoperador2.setDisable(true);
        }
        if (cmbxvalores2 != null) {
            cmbxvalores2.setDisable(true);
        }
        if (chbxdostablas != null) {
            // Listener para habilitar/deshabilitar la segunda tabla y su identificador
            chbxdostablas.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (Cmbxtabla2 != null && cmbxidentificador2 != null) {
                    Cmbxtabla2.setDisable(!newValue);
                    cmbxidentificador2.setDisable(!newValue);

                    if (!newValue) {
                        Cmbxtabla2.getSelectionModel().clearSelection();
                        cmbxidentificador2.getSelectionModel().clearSelection();
                    }
                }
                actualizarConsultaEnTiempoReal();
            });
        }

        if (chbxcondiciones != null) {
            // Listener para habilitar/deshabilitar el operador y valores de la segunda condición
            chbxcondiciones.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (cmbxoperador2 != null && cmbxvalores2 != null) {
                    cmbxoperador2.setDisable(!newValue);
                    cmbxvalores2.setDisable(!newValue);

                    if (!newValue) {
                        cmbxoperador2.getSelectionModel().clearSelection();
                        cmbxvalores2.clear();
                    }
                }
                actualizarConsultaEnTiempoReal();
            });
        }

        // Agregar opciones al cmbxoperador, incluyendo LIKE variantes
        if (cmbxoperador != null) {
            cmbxoperador.setItems(FXCollections.observableArrayList(
                    "=", "!=", ">", "<", ">=", "<=", "LIKE", "NOT LIKE", "IS NULL", "IS NOT NULL",
                    "Empieza por", "Contiene", "Termina en"
            ));
            cmbxoperador.setOnAction(event -> actualizarConsultaEnTiempoReal());
        }

        if (cmbxoperador2 != null) {
            cmbxoperador2.setItems(FXCollections.observableArrayList(
                    "=", "!=", ">", "<", ">=", "<=", "LIKE", "NOT LIKE", "IS NULL", "IS NOT NULL",
                    "Empieza por", "Contiene", "Termina en"
            ));
            cmbxoperador2.setOnAction(event -> actualizarConsultaEnTiempoReal());
        }

        // Listener para cargar columnas al seleccionar una tabla
        if (Cmbxtabla1 != null) {
            Cmbxtabla1.setOnAction(event -> {
                cargarColumnas(Cmbxtabla1.getValue(), cmbxidentificador1);
                actualizarConsultaEnTiempoReal();
            });
        }
        if (Cmbxtabla2 != null) {
            Cmbxtabla2.setOnAction(event -> {
                cargarColumnas(Cmbxtabla2.getValue(), cmbxidentificador2);
                actualizarConsultaEnTiempoReal();
            });
        }
        if (cmbxidentificador1 != null) {
            cmbxidentificador1.setOnAction(event -> actualizarConsultaEnTiempoReal());
        }
        if (cmbxidentificador2 != null) {
            cmbxidentificador2.setOnAction(event -> actualizarConsultaEnTiempoReal());
        }
        if (cmbxvalores != null) {
            cmbxvalores.setOnKeyReleased(event -> actualizarConsultaEnTiempoReal());
        }
        if (cmbxvalores2 != null) {
            cmbxvalores2.setOnKeyReleased(event -> actualizarConsultaEnTiempoReal());
        }

        // Listener para el botón de quitar condición
        if (btnquitarcondicion != null) {
            btnquitarcondicion.setOnAction(event -> quitarCondicion());
        }

        // Listener para el botón de limpiar campos
        if (btnlimpiarcampos != null) {
            btnlimpiarcampos.setOnAction(event -> limpiarCampos());
        }

        // Listener para el botón de crear vista
        if (btncrearvista != null) {
            btncrearvista.setOnAction(event -> crearVista());
        }

        // Listener para el botón de mostrar vistas
        if (btnMostrarVistas != null) {
            btnMostrarVistas.setOnAction(event -> mostrarVistas());
        }
        
        // Listener para el botón de ir a la vista de Vistas
        
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
        cargarTablas(); // Cargar tablas al establecer la conexión
    }

    private void cargarTablas() {
        System.out.println("Cargando tablas...");
        ObservableList<String> tablas = FXCollections.observableArrayList();

        String query = "SHOW TABLES";
        try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tablas.add(rs.getString(1)); // Obtener el nombre de cada tabla
            }
            if (Cmbxtabla1 != null) {
                Cmbxtabla1.setItems(tablas);
            }
            if (Cmbxtabla2 != null) {
                Cmbxtabla2.setItems(tablas);
            }
        } catch (SQLException e) {
            mostrarError("Error de Carga", "No se pudieron cargar las tablas desde la base de datos.");
        }
    }

    private void cargarColumnas(String tabla, ComboBox<String> comboBox) {
        if (tabla == null || comboBox == null) {
            return;
        }
        System.out.println("Cargando columnas para la tabla: " + tabla);
        ObservableList<String> columnas = FXCollections.observableArrayList();
        String query = "DESCRIBE " + tabla;
        try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                columnas.add(rs.getString(1)); // Nombre de la columna
            }
            comboBox.setItems(columnas);
        } catch (SQLException e) {
            mostrarError("Error al cargar columnas", "No se pudieron cargar las columnas de la tabla " + tabla);
        }
    }

    @FXML
public void actualizarConsultaEnTiempoReal() {
    System.out.println("Actualizando la consulta en tiempo real...");

    if (txtAreaQuery == null || Cmbxtabla1 == null || cmbxidentificador1 == null || cmbxoperador == null || cmbxvalores == null) {
        System.out.println("Faltan componentes clave para construir el query.");
        return;
    }

    String tabla1 = Cmbxtabla1.getValue();
    if (tabla1 == null) {
        txtAreaQuery.setText("SELECT * FROM ...");
        System.out.println("No se ha seleccionado ninguna tabla.");
        return;
    }

    String identificador1 = cmbxidentificador1.getValue();
    String operador1 = cmbxoperador.getValue();
    String valor1 = cmbxvalores.getText();

    StringBuilder query = new StringBuilder("SELECT * FROM " + tabla1);

    // Si el checkbox para dos tablas está activado
    if (chbxdostablas != null && chbxdostablas.isSelected()) {
        System.out.println("El CheckBox para la relación de dos tablas está activado.");

        // Obtener valores para la segunda tabla y la relación
        String tabla2 = Cmbxtabla2 != null ? Cmbxtabla2.getValue() : null;
        String identificadorRelacion1 = cmbxidentificador1 != null ? cmbxidentificador1.getValue() : null;
        String identificadorRelacion2 = cmbxidentificador2 != null ? cmbxidentificador2.getValue() : null;

        if (tabla2 != null && identificadorRelacion1 != null && identificadorRelacion2 != null) {
            // Agregar la segunda tabla y la condición de relación con WHERE
            query.append(", ").append(tabla2)
                 .append(" WHERE ").append(tabla1).append(".").append(identificadorRelacion1)
                 .append(" = ").append(tabla2).append(".").append(identificadorRelacion2);
        } else {
            System.out.println("Faltan datos para construir la relación entre tablas.");
        }
    }

    // Primera condición (si no es una relación)
    if (identificador1 != null && operador1 != null) {
        if (!query.toString().contains("WHERE")) {
            query.append(" WHERE ");
        } else {
            query.append(" AND ");
        }

        switch (operador1) {
            case "Empieza por":
                query.append(tabla1).append(".").append(identificador1).append(" LIKE '").append(valor1).append("%'");
                break;
            case "Contiene":
                query.append(tabla1).append(".").append(identificador1).append(" LIKE '%").append(valor1).append("%'");
                break;
            case "Termina en":
                query.append(tabla1).append(".").append(identificador1).append(" LIKE '%").append(valor1).append("'");
                break;
            default:
                query.append(tabla1).append(".").append(identificador1).append(" ").append(operador1);
                if (!operador1.contains("NULL") && valor1 != null && !valor1.isEmpty()) {
                    query.append(" '").append(valor1).append("'");
                }
                break;
        }
    }

    // Segunda condición
    if (chbxcondiciones != null && chbxcondiciones.isSelected()) {
        System.out.println("El CheckBox para la segunda condición está activado.");
        String identificador2 = cmbxidentificador2 != null ? cmbxidentificador2.getValue() : null;
        String operador2 = cmbxoperador2 != null ? cmbxoperador2.getValue() : null;
        String valor2 = cmbxvalores2 != null ? cmbxvalores2.getText() : null;

        if (operador2 != null && valor2 != null && !valor2.isEmpty()) {
            query.append(" AND "); // La segunda condición siempre se conecta con AND

            switch (operador2) {
                case "Empieza por":
                    query.append(tabla1).append(".").append(identificador1).append(" LIKE '").append(valor2).append("%'");
                    break;
                case "Contiene":
                    query.append(tabla1).append(".").append(identificador1).append(" LIKE '%").append(valor2).append("%'");
                    break;
                case "Termina en":
                    query.append(tabla1).append(".").append(identificador1).append(" LIKE '%").append(valor2).append("'");
                    break;
                default:
                    query.append(tabla1).append(".").append(identificador1).append(" ").append(operador2);
                    if (!operador2.contains("NULL")) {
                        query.append(" '").append(valor2).append("'");
                    }
                    break;
            }
        } else {
            System.out.println("No se pudieron añadir las condiciones adicionales: identificador2=" + identificador2 + ", operador2=" + operador2 + ", valor2=" + valor2);
        }
    }

    boolean mostrarTodos = chckboxtodos != null && chckboxtodos.isSelected();
    int cantidadDatos = 0;

    if (!mostrarTodos) {
        try {
            if (cmbxcantidaddatos != null && !cmbxcantidaddatos.getText().isEmpty()) {
                cantidadDatos = Integer.parseInt(cmbxcantidaddatos.getText());
            }
        } catch (NumberFormatException e) {
            System.out.println("Cantidad de datos inválida: " + cmbxcantidaddatos.getText());
        }
    }

    if (!mostrarTodos && cantidadDatos > 0) {
        query.append(" LIMIT ").append(cantidadDatos);
    }

    System.out.println("Query final: " + query);
    txtAreaQuery.setText(query.toString());
}


    private void quitarCondicion() {
        System.out.println("Quitando condición...");
        if (cmbxidentificador1 != null) {
            cmbxidentificador1.getSelectionModel().clearSelection();
        }
        if (cmbxoperador != null) {
            cmbxoperador.getSelectionModel().clearSelection();
        }
        if (cmbxvalores != null) {
            cmbxvalores.clear();
        }
        if (cmbxoperador2 != null) {
            cmbxoperador2.getSelectionModel().clearSelection();
        }
        if (cmbxvalores2 != null) {
            cmbxvalores2.clear();
        }
        actualizarConsultaEnTiempoReal();
    }

    private void limpiarCampos() {
        System.out.println("Limpiando todos los campos...");
        if (Cmbxtabla1 != null) {
            Cmbxtabla1.getSelectionModel().clearSelection();
        }
        if (Cmbxtabla2 != null) {
            Cmbxtabla2.getSelectionModel().clearSelection();
        }
        if (cmbxidentificador1 != null) {
            cmbxidentificador1.getSelectionModel().clearSelection();
        }
        if (cmbxidentificador2 != null) {
            cmbxidentificador2.getSelectionModel().clearSelection();
        }
        if (cmbxoperador != null) {
            cmbxoperador.getSelectionModel().clearSelection();
        }
        if (cmbxvalores != null) {
            cmbxvalores.clear();
        }
        if (cmbxoperador2 != null) {
            cmbxoperador2.getSelectionModel().clearSelection();
        }
        if (cmbxvalores2 != null) {
            cmbxvalores2.clear();
        }
        if (chbxdostablas != null) {
            chbxdostablas.setSelected(false);
        }
        if (chbxcondiciones != null) {
            chbxcondiciones.setSelected(false);
        }
        if (txtAreaQuery != null) {
            txtAreaQuery.clear();
        }
        actualizarConsultaEnTiempoReal();
    }

    @FXML
    private void buscar(ActionEvent event) {
        System.out.println("Ejecutando búsqueda...");
        actualizarConsultaEnTiempoReal();
        String query = txtAreaQuery.getText();

        if (query == null || query.isEmpty() || query.equals("SELECT * FROM ...")) {
            mostrarError("Error de Entrada", "Por favor, complete todos los campos requeridos.");
            return;
        }

        ejecutarConsulta(query);
    }

    private void ejecutarConsulta(String query) {
        System.out.println("Ejecutando consulta: " + query);
        try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();
            int columnCount = rs.getMetaData().getColumnCount();

            tablaresultado.getColumns().clear();

            for (int i = 1; i <= columnCount; i++) {
                final int columnIndex = i;
                TableColumn<ObservableList<Object>, Object> column = new TableColumn<>(rs.getMetaData().getColumnName(i));
                column.setCellValueFactory(param -> {
                    ObservableList<Object> row = param.getValue();
                    if (row != null && columnIndex - 1 < row.size()) {
                        return new ReadOnlyObjectWrapper<>(row.get(columnIndex - 1));
                    } else {
                        return new ReadOnlyObjectWrapper<>(null);
                    }
                });
                tablaresultado.getColumns().add(column);
            }

            while (rs.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            tablaresultado.setItems(data);
        } catch (SQLException e) {
            mostrarError("Error de Consulta", "No se pudo ejecutar la consulta.");
        }
    }

    private void mostrarError(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    private void volver(ActionEvent event) {
        System.out.println("Volviendo a la vista anterior...");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Inicio.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Inicio");
            stage.show();
        } catch (IOException e) {
            mostrarError("Error al Volver", "No se pudo cargar la vista de inicio.");
        }
    }

    private void crearVista() {
    System.out.println("Creando vista...");
    if (cmbxnombrevista == null || txtAreaQuery == null) {
        mostrarError("Error al Crear Vista", "El nombre de la vista o la consulta no pueden estar vacíos.");
        return;
    }

    String nombreVista = cmbxnombrevista.getText().trim();
    String consulta = txtAreaQuery.getText().trim();

    if (nombreVista.isEmpty() || consulta.isEmpty() || consulta.equals("SELECT * FROM ...")) {
        mostrarError("Error al Crear Vista", "Por favor, ingrese un nombre para la vista y asegúrese de que la consulta sea válida.");
        return;
    }

    // Verificar si la vista ya existe y eliminarla si es necesario
    String queryEliminarVista = "DROP VIEW IF EXISTS " + nombreVista;

    try (PreparedStatement psEliminar = conexion.prepareStatement(queryEliminarVista)) {
        psEliminar.executeUpdate();
        System.out.println("Vista existente eliminada (si existía).");
    } catch (SQLException e) {
        System.out.println("No se pudo eliminar la vista existente: " + e.getMessage());
    }

    // Crear la nueva vista
    String queryCrearVista = "CREATE VIEW " + nombreVista + " AS " + consulta;

    try (PreparedStatement psCrear = conexion.prepareStatement(queryCrearVista)) {
        psCrear.executeUpdate();
        mostrarInformacion("Vista Creada", "La vista " + nombreVista + " ha sido creada exitosamente.");
    } catch (SQLException e) {
        mostrarError("Error al Crear Vista", "No se pudo crear la vista. Verifique que el nombre no exista o que la consulta sea correcta.\nError: " + e.getMessage());
    }
}


    private void mostrarVistas() {
        System.out.println("Mostrando vistas disponibles...");
        String query = "SHOW FULL TABLES WHERE Table_type = 'VIEW'";
        try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            StringBuilder vistas = new StringBuilder("Vistas disponibles:\n");
            while (rs.next()) {
                vistas.append(rs.getString(1)).append("\n");
            }
            mostrarInformacion("Vistas Disponibles", vistas.toString());
        } catch (SQLException e) {
            mostrarError("Error al Mostrar Vistas", "No se pudieron mostrar las vistas desde la base de datos.");
        }
    }

    
    private void mostrarInformacion(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    private void vistasvista(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vistas.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la nueva vista
        VistasController controller = loader.getController();

        // Pasar la conexión a la nueva vista
        controller.setConexion(this.conexion);

        // Crear una nueva escena y mostrarla en un nuevo stage
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Busqueda Avanzada");
        stage.setResizable(false);
        stage.show();

        
    }

    @FXML
    private void eliminarfila(ActionEvent event) {
    }

    @FXML
    private void editarfila(ActionEvent event) {
    }
}
