/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Adiacenze;
import it.polito.tdp.crimes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCategoria"
    private ComboBox<String> boxCategoria; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="boxArco"
    private ComboBox<?> boxArco; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPercorso(ActionEvent event) {

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
String reato=    	this.boxCategoria.getValue();
if(reato==null)
{
	this.txtResult.appendText("Selezioanre un reato dalla tendina ");
return;	
}
int mese;
try {
	mese=this.boxMese.getValue();
}catch(NumberFormatException e) {
	txtResult.appendText("Selezionare un mese dalla tendina ");
	return;
}this.model.creaGrafo(reato,mese);

txtResult.appendText("Grafo creato!\n");
txtResult.appendText("# Vertici : " + this.model.nVertici() + "\n");
txtResult.appendText("# Archi : " + this.model.nArchi() + "\n");

double pesoMedio=this.model.pesoMedio();


this.txtResult.appendText("peso medio: "+pesoMedio+"\n");
for(Adiacenze a:this.model.peso()) {
	this.txtResult.appendText(a.toString()+"\n");
}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCategoria != null : "fx:id=\"boxCategoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxArco != null : "fx:id=\"boxArco\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxCategoria.getItems().addAll(model.listAllReati());
   this.boxMese.getItems().addAll(model.listAllMesi());
    }
}
