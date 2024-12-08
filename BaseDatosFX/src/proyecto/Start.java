/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author abell
 */
public class Start extends Application{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage ventana) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/Inicio.fxml"));  //Crea la ventana con el código XML
        Scene scene = new Scene(root);
        ventana.setScene(scene);
        
        ventana.setTitle("INICIO");
        ventana.setResizable(false);  //No permite que se redimensione el tamaño de la ventana
        // ventana.setOnCloseRequest(event -> {event.consume();});  //Deshabilita la "X" para cerrar la ventana
        
        ventana.show();  //Muestra la ventana
    }
    
}
