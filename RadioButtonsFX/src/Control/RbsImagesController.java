/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Control;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abell
 */
public class RbsImagesController implements Initializable {

    @FXML
    private TextField fldId;
    @FXML
    private TextField fldNombre;
    @FXML
    private RadioButton rbtCatedra;
    @FXML
    private RadioButton rbtPlanta;
    @FXML
    private RadioButton rbtProfe;
    @FXML
    private ComboBox<String> cbxOperacion;
    @FXML
    private ImageView imgFoto;
    @FXML
    private Button btnBuscarFoto;
    @FXML
    private Button btnGuardar;
    
    private String nombreFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.nombreFoto="sinfoto.jpg";
        this.llenaCombos();
        String atributo="Nuevo";
        this. cbxOperacion.getSelectionModel().select(atributo);
        
        //Para que solo pueda seleccionar uno
        ToggleGroup tg= new ToggleGroup();
        this.rbtCatedra.setToggleGroup(tg);
        this.rbtPlanta.setToggleGroup(tg);
        this.rbtProfe.setToggleGroup(tg);
    }    

    @FXML
    private void doBuscarFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar imagen");
        fileChooser.setInitialDirectory(new File("./images/"));
        
        //Obtener la imagen seleccionada
        File imgFile=fileChooser.showOpenDialog(new Stage());
        this.nombreFoto=imgFile.getName();
        
        //Mostrar la imagen
        if(this.nombreFoto!=null)
        {
            Image image = new Image("file:" +imgFile.getAbsolutePath());
            this.imgFoto.setImage(image);
        }
        String atributo="Con foto";
        this.cbxOperacion.getSelectionModel().select(atributo);
    }

    @FXML
    private void doGuardar(ActionEvent event) {
        String id, nombre, foto;
        int cate=0;
        
        id=this.fldId.getText();
        nombre=this.fldNombre.getText();
        
        if(this.rbtCatedra.isSelected())
        {
            cate=1;
        }
        if(this.rbtPlanta.isSelected())
        {
            cate=3;
        }
        if(this.rbtProfe.isSelected())
        {
            cate=3;
        }
        
        System.out.println(id+","+nombre+"," +cate+","+this.nombreFoto);
        
        String atributo="Hecho";
        this.cbxOperacion.getSelectionModel().select(atributo);
    }
    
    private void llenaCombos()
    {
        this.cbxOperacion.getItems().add("Nuevo");
        this.cbxOperacion.getItems().add("Con foto");
        this.cbxOperacion.getItems().add("Hecho");
    }
}
