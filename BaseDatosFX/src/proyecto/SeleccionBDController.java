/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SeleccionBDController implements Initializable {

    @FXML
    private ComboBox<String> comboDB;
    @FXML
    private Button buttonse;

    private Connection conexion;
    private String ip;
    private String puerto;
    private String usuario;
    private String contraseña;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesario
    }

    public void inicializarBasesDeDatos(List<String> basesDeDatos, String ip, String puerto, String usuario, String contraseña) {
        comboDB.getItems().setAll(basesDeDatos);
        this.ip = ip;
        this.puerto = puerto;
        this.usuario = usuario;
        this.contraseña = contraseña;

        try {
            String urlConexion = String.format("jdbc:mysql://%s:%s", ip, puerto);
            conexion = DriverManager.getConnection(urlConexion, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void seleccion(ActionEvent event) {
        String selectedDB = comboDB.getSelectionModel().getSelectedItem();
        if (selectedDB != null) {
            cambiarAVistaNueva(selectedDB);
        } else {
            System.out.println("No se ha seleccionado ninguna base de datos.");
        }
    }

    private void cambiarAVistaNueva(String baseDeDatosSeleccionada) {
        try {
            // Obtener las tablas de la base de datos seleccionada
            List<String> tablas = obtenerTablas(baseDeDatosSeleccionada);
            
            // Cargar la nueva vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/principal.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva vista y pasarle los datos
            PrincipalController controller = loader.getController();
            controller.setBaseSeleccionada(baseDeDatosSeleccionada);
            controller.setTablas(tablas);
            
            // Configurar la conexión en PrincipalController usando los datos de conexión
            String urlConexion = String.format("jdbc:mysql://%s:%s/%s", ip, puerto, baseDeDatosSeleccionada);
            Connection nuevaConexion = DriverManager.getConnection(urlConexion, usuario, contraseña);
            controller.setConexion(nuevaConexion);

            // Crear una nueva escena y mostrarla en un nuevo stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Principal");
            stage.setResizable(false);
            stage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) buttonse.getScene().getWindow();
            currentStage.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> obtenerTablas(String nombreBaseDatos) {
        List<String> tablas = new ArrayList<>();
        try {
            conexion.setCatalog(nombreBaseDatos);

            try (PreparedStatement ps = conexion.prepareStatement("SHOW TABLES");
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    tablas.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablas;
    }
}
