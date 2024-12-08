/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class InicioController implements Initializable {

    @FXML
    private TextField txtFld1; // IP
    @FXML
    private TextField txtFld2; // Puerto
    @FXML
    private TextField txtFld3; // Usuario
    @FXML
    private TextField txtFld4; // Contraseña
    @FXML
    private Button btnIniciarSesion; // Botón para conectar

    private static final String URL_TEMPLATE = "jdbc:mysql://%s:%s/?useSSL=false&allowPublicKeyRetrieval=true";
    @FXML
    private AnchorPane inicio;
    @FXML
    private ImageView logoInicio;
    @FXML
    private Text txt1Inicio;
    @FXML
    private Text txt2Inicio1;
    @FXML
    private Text txt2Inicio2;
    @FXML
    private Text txt2Inicio3;
    @FXML
    private Text txt2Inicio4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnIniciarSesion.setOnAction(this::listarBasesDeDatos);
    }

    @FXML
    public void listarBasesDeDatos(ActionEvent event) {
        String ip = txtFld1.getText();
        String puerto = txtFld2.getText();
        String usuario = txtFld3.getText();
        String contraseña = txtFld4.getText();

        List<String> basesDeDatos = obtenerBasesDeDatos(ip, puerto, usuario, contraseña);

        if (!basesDeDatos.isEmpty()) {
            cambiarAVistaDatabase(basesDeDatos, ip, puerto, usuario, contraseña);
        }
    }

    private List<String> obtenerBasesDeDatos(String ip, String puerto, String usuario, String contraseña) {
        List<String> basesDeDatos = new ArrayList<>();
        String url = String.format(URL_TEMPLATE, ip, puerto);

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement ps = conexion.prepareStatement("SHOW DATABASES");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                basesDeDatos.add(rs.getString(1));
            }

            System.out.println("Conexión exitosa. Bases de datos encontradas.");
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }

        return basesDeDatos;
    }

    private void cambiarAVistaDatabase(List<String> basesDeDatos, String ip, String puerto, String usuario, String contraseña) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/SeleccionBD.fxml"));
            Parent root = loader.load();

            SeleccionBDController controller = loader.getController();
            controller.inicializarBasesDeDatos(basesDeDatos, ip, puerto, usuario, contraseña);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("SeleccionDB");
            stage.setResizable(false);
            stage.show();

            Stage currentStage = (Stage) btnIniciarSesion.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
