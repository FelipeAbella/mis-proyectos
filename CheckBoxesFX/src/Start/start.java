/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Start;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author abell
 */
public class start extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage ventana) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/CheckBoxes.fxml"));  //Crea la ventana con el código XML
        Scene scene = new Scene(root);
        ventana.setScene(scene);
        
        ventana.setTitle("CALIFICADOR");
        ventana.setResizable(false);  //No permite que se redimensione el tamaño de la ventana
        ventana.setOnCloseRequest(event -> {event.consume();});  //Deshabilita la "X" para cerrar la ventana
        
        ventana.show();  //Muestra la ventana
    }
}