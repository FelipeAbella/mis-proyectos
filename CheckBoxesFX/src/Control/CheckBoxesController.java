/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abell
 */
public class CheckBoxesController implements Initializable {

    @FXML
    private Button btnCalificar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnSalir;
    @FXML
    private CheckBox chbCoci;
    @FXML
    private CheckBox chbVideo;
    @FXML
    private CheckBox chbMus;
    @FXML
    private CheckBox chbPelis;
    @FXML
    private CheckBox chbLec;
    @FXML
    private CheckBox chbDep;
    @FXML
    private CheckBox chbAceptar;
    @FXML
    private CheckBox chbRechazar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doCalificar(ActionEvent event) {
        int conta=0;
        
        if(this.chbCoci.isSelected())
        {
            conta++;
        }
        
        if(this.chbVideo.isSelected())
        {
            conta++;
        }
        
        if(this.chbMus.isSelected())
        {
            conta++;
        }
        
        if(this.chbPelis.isSelected())
        {
            conta++;
        }
        
        if(this.chbLec.isSelected())
        {
            conta++;
        }
        
        if(this.chbDep.isSelected())
        {
            conta++;
        }
        
        if (conta<3)
        {
            this.chbRechazar.setSelected(true);
        }
        else
            this.chbAceptar.setSelected(true);
    }

    @FXML
    private void doLimpiar(ActionEvent event) {
        this.chbCoci.setSelected(false);
        this.chbVideo.setSelected(false);
        this.chbMus.setSelected(false);
        this.chbPelis.setSelected(false);
        this.chbLec.setSelected(false);
        this.chbDep.setSelected(false);
        this.chbAceptar.setSelected(false);
        this.chbRechazar.setSelected(false);
    }

    @FXML
    private void doSalir(ActionEvent event) {
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
        System.exit(0);
    }
    
}
