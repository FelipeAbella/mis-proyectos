/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * FXML Controller class
 *
 * @author abell
 */
public class IngresoController implements Initializable {

    @FXML
    private AnchorPane ingreso;
    
    @FXML
    private Button btnIniciar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciar(ActionEvent event) {
        try {
        // Cargar la nueva interfaz
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Inicio.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Crear una nueva ventana (stage)
        Stage stage = new Stage();
        stage.setOnCloseRequest(even -> { even.consume(); });
        stage.setResizable(false);
        stage.setTitle("INICIO");
        stage.setScene(scene);
        stage.show();

        // Cerrar la ventana actual (la que tiene el botón que disparó este evento)
        Stage myStage = (Stage) this.btnIniciar.getScene().getWindow();
        myStage.close();
    } catch (IOException ex) {
        Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
