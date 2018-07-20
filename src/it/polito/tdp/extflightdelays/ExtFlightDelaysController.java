/**
 * Sample Skeleton for 'ExtFlightDelays.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExtFlightDelaysController {

	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="voliMinimo"
    private TextField voliMinimo; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBoxAeroportoPartenza"
    private ComboBox<Airport> cmbBoxAeroportoPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="btnAeroportiConnessi"
    private Button btnAeroportiConnessi; // Value injected by FXMLLoader

    @FXML // fx:id="numeroOreTxtInput"
    private TextField numeroOreTxtInput; // Value injected by FXMLLoader

    @FXML // fx:id="btnOttimizza"
    private Button btnOttimizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	try {
    		
    		int numVoliMin = Integer.parseInt(this.voliMinimo.getText());
    		
    		model.creaGrafo(numVoliMin);
    		
    		List<Airport> airports = new ArrayList<Airport>(model.getGrafo().vertexSet());
    		
    		this.txtResult.setText(String.format("Grafo creato: %d vertici - %d archi\n",
    				model.getGrafo().vertexSet().size(), model.getGrafo().edgeSet().size()));
    		
    		this.cmbBoxAeroportoPartenza.getItems().clear();
    		this.cmbBoxAeroportoPartenza.getItems().addAll(airports);
    		
    	} catch (RuntimeException e) {
    		this.txtResult.setText("ERRORE - input non valido!\n");
    	}
    	
    }

    @FXML
    void doCalcolaAeroportiConnessi(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	Airport airport = this.cmbBoxAeroportoPartenza.getValue();
    	
    	if(airport == null) {
    		txtResult.setText("ERRORE - nessun aeroporto selezionato!\n");
    		return;
    	}
    	
    	List<Airport> adiacenti = model.stampaRisultato(airport);
    	this.txtResult.appendText("Aeroporti adiacenti di \"" + airport.getAirportName() + "\":\n");
    	for(Airport a : adiacenti) {
    		this.txtResult.appendText(("- " + a.getAirportName() + "\n"));
    	}
    	
    }

    @FXML
    void doCercaItinerario(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert voliMinimo != null : "fx:id=\"voliMinimo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxAeroportoPartenza != null : "fx:id=\"cmbBoxAeroportoPartenza\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnAeroportiConnessi != null : "fx:id=\"btnAeroportiConnessi\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert numeroOreTxtInput != null : "fx:id=\"numeroOreTxtInput\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnOttimizza != null : "fx:id=\"btnOttimizza\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }
    
    public void setModel(Model model) {
  		this.model = model;
  	}
    
    public Model getModel() {
    	return this.model;
    }
}
