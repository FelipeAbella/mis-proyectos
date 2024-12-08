/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Control;

import Modelo.Operaciones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abell
 */
public class OperacionesController implements Initializable {

    @FXML
    private TextField fieldDato1;
    @FXML
    private TextField fieldResultado;
    @FXML
    private TextField fieldDato2;
    @FXML
    private Button btnSuma;
    @FXML
    private Button btnDiv;
    @FXML
    private Button btnMult;
    @FXML
    private Button btnResta;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnSalir;
    
    private Operaciones ope;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.ope=null;
    }    

    @FXML
    private void doSuma(ActionEvent event) {
        float a,b,c;
        
        try
        {
            a=Float.parseFloat(this.fieldDato1.getText());
            b=Float.parseFloat(this.fieldDato2.getText());
            this.ope=new Operaciones(a,b);
            
            c=this.ope.getSuma();
            this.fieldResultado.setText(String.valueOf(c));
        }
        catch(NumberFormatException ex)
        {
            String mesg="Un dato ingresado tiene un formato no válido.";
            this.showMessages(mesg,1);
        }
    }

    @FXML
    private void doDiv(ActionEvent event) {
        float a,b,c;
        
        try
        {
            a=Float.parseFloat(this.fieldDato1.getText());
            b=Float.parseFloat(this.fieldDato2.getText());
            this.ope=new Operaciones(a,b);
            
            c=this.ope.getDiv();
            this.fieldResultado.setText(String.valueOf(c));
        }
        catch(NumberFormatException ex)
        {
            String mesg="Un dato ingresado tiene un formato no válido.";
            this.showMessages(mesg,1);
        }
    }

    @FXML
    private void doMult(ActionEvent event) {
        float a,b,c;
        
        try
        {
            a=Float.parseFloat(this.fieldDato1.getText());
            b=Float.parseFloat(this.fieldDato2.getText());
            this.ope=new Operaciones(a,b);
            
            c=this.ope.getMult();
            this.fieldResultado.setText(String.valueOf(c));
        }
        catch(NumberFormatException ex)
        {
            String mesg="Un dato ingresado tiene un formato no válido.";
            this.showMessages(mesg,1);
        }
    }

    @FXML
    private void doResta(ActionEvent event) {
        float a,b,c;
        
        try
        {
            a=Float.parseFloat(this.fieldDato1.getText());
            b=Float.parseFloat(this.fieldDato2.getText());
            this.ope=new Operaciones(a,b);
            
            c=this.ope.getResta();
            this.fieldResultado.setText(String.valueOf(c));
        }
        catch(NumberFormatException ex)
        {
            String mesg="Un dato ingresado tiene un formato no válido.";
            this.showMessages(mesg,1);
        }
    }

    @FXML
    private void doLimpiar(ActionEvent event) {
        this.fieldDato1.setText("");
        this.fieldDato2.setText("");
        this.fieldResultado.setText("");
        this.fieldDato1.requestFocus();
    }

    @FXML
    private void doSalir(ActionEvent event) {
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
        System.exit(0);
    }    
    
    private void showMessages(String mesg, int caso)
    {
        Alert msg;
        if(caso==1)//Tipo de error
        {
            msg=new Alert(Alert.AlertType.ERROR);
            msg.setHeaderText(null);
            msg.setTitle("ERROR");
            msg.setContentText(mesg);
            msg.showAndWait();
        }
    }
}