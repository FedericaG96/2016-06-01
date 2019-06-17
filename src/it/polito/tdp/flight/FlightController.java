package it.polito.tdp.flight;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.flight.model.Airline;
import it.polito.tdp.flight.model.Airport;
import it.polito.tdp.flight.model.AirportDistance;
import it.polito.tdp.flight.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FlightController {
	
	Model model;
	List<Airport> serviti = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Airline> boxAirline;

    @FXML
    private ComboBox<Airport> boxAirport;

    @FXML
    private TextArea txtResult;

    @FXML
    void doServiti(ActionEvent event) {

    	Airline compagnia = boxAirline.getValue();
    	if(compagnia == null) {
    		txtResult.setText("Selezionare una compagnia");  
    		return;
    	}
    	else {
    		model.creaGrafo(compagnia);
    	}
    	
    	serviti = new ArrayList<>();
    	serviti = model.getServiti(compagnia);
    	for(Airport a : serviti) {
    		txtResult.appendText(a.toString()+"\n");
    	}
    	Collections.sort(serviti);
    	boxAirport.getItems().addAll(serviti);
    }
    
    @FXML
    void doRaggiungibili(ActionEvent event) {
    	Airport airpScelto = null;
    	
    	
    	 airpScelto = boxAirport.getValue();
    	if(airpScelto == null) {
    		txtResult.setText("Selezionare un aeroporto");   
    		return;
    	}
    	txtResult.clear();
    	List<AirportDistance> raggiungibili = model.getRaggiungibili(airpScelto);
    	for(AirportDistance a : raggiungibili) {
    		txtResult.appendText(a.getEnd().toString()+ " "+ a.getDistanza()+"\n");
    	}
    }

  

    @FXML
    void initialize() {
        assert boxAirline != null : "fx:id=\"boxAirline\" was not injected: check your FXML file 'Flight.fxml'.";
        assert boxAirport != null : "fx:id=\"boxAirport\" was not injected: check your FXML file 'Flight.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Flight.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
		boxAirline.getItems().addAll(model.getAllAirlines());

	}
}
