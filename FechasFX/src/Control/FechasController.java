/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Control;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author abell
 */
public class FechasController implements Initializable {

    @FXML
    private DatePicker fechaRetira;
    @FXML
    private DatePicker fechaEntrega;
    @FXML
    private TextField fldFechaRetira;
    @FXML
    private TextField fldFechaEntrega;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doRetira(ActionEvent event) {
        LocalDate inicio=this.fechaRetira.getValue();
        if(inicio!=null)
        {
            this.fldFechaRetira.setText(inicio.toString());
        }
    }

    @FXML
    private void doChequear(ActionEvent event) {
        this.doValidar(event);
    }
    
    private void doValidar(ActionEvent event) {
        String mesg;
        
        LocalDate inicio=this.fechaRetira.getValue();
        LocalDate fin=this.fechaEntrega.getValue();
        
        if(inicio!=null && fin!=null && inicio.isAfter(fin))
        {
            mesg="¡La fecha de entrega no puede ser antes de la fecha de retiro!";
            this.showMessages(mesg,1);
            this.fechaEntrega.setValue(null);
            this.fechaEntrega.requestFocus();
        }
        else
        {
            if(fin!=null)
            {
                this.fldFechaEntrega.setText(fin.toString());
            }
        }
    }
    
    private boolean showMessages(String mesg, int caso)
    {
        Alert msg;
        boolean ok=false;
        
        if(caso==1) //Error
        {
            msg=new Alert(Alert.AlertType.ERROR);
            msg.setHeaderText(null);
            msg.setTitle("ERROR");
            msg.setContentText(mesg);
            msg.showAndWait();
        }
        if(caso==2) //Notificación
        {
            msg=new Alert(Alert.AlertType.INFORMATION);
            msg.setHeaderText(null);
            msg.setTitle("LISTO");
            msg.setContentText(mesg);
            msg.showAndWait();
        }
        if(caso==3) //Confirmación
        {
            msg=new Alert(Alert.AlertType.CONFIRMATION);
            msg.setTitle("Petición eliminación");
            msg.setHeaderText(null);
            msg.initStyle(StageStyle.UTILITY);
            msg.setContentText(mesg);
            Optional<ButtonType> result=msg.showAndWait();
            if(result.get()==ButtonType.OK)
            {
                ok=true;
            }
        }
        return ok;
    }

    @FXML
    private void doSalir(ActionEvent event) {
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
        System.exit(0);
    }
    
}
