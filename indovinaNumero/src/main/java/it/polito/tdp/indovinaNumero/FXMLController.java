package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	// riferimento al mio modello
	private Model model; 
	
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    void doNuova(ActionEvent event) {
    	
    	// gestione inizio di una nuova partita 
    	
    	//comunico al modello che stai iniziando una nuova partita
    	this.model.nuovaPartita();
    	
    	//GESTIONE INTERFACCIA
    	//abilitazione dell hbox
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear(); //pulire i msg vecchi 
    	// aggiorno tentativi rimasti
    	txtRimasti.setText(Integer.toString(this.model.getTMAX()));
    	

    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	//leggere l'input dell'utente 
    	String tent=txtTentativi.getText();
    	int tentativo; 
    	
    	// lo controllo 
    	try {
    	tentativo= Integer.parseInt(tent); 
    	}
    	catch(NumberFormatException nfe) {
    		txtRisultato.appendText("Devi inserire un numero!");
    		return; //fine 
    		}
    	
    	int risultato=-1; // valore di default 
    	// ho delle eccezioni da gestire 
    	try {
    	risultato= this.model.tentativo(tentativo); 
    	}
    	catch(IllegalStateException se) {
    		txtRisultato.appendText(se.getMessage());
    		return; 
    	}
    	catch(InvalidParameterException pe) {
    		txtRisultato.appendText(pe.getMessage());
    		return; 
    	}
    	
    	
    	//controllo il risultato sulla base del metodo tentativo()
    	if (risultato==0) {
    		//ho indovinato
    		txtRisultato.appendText("Hai vinto! Ed hai utilizzato "+this.model.getTentativiFatti()+" tentativi\n");
    	}
    	else if(risultato== -1) {
    		txtRisultato.appendText("tentativo troppo basso!\n");
    	}
    	else {
    		txtRisultato.appendText("tentativo troppo alto!\n");
    	}
    	
    	
    	//aggiorno i tentativi
    	txtRimasti.setText(Integer.toString(this.model.getTMAX() - this.model.getTentativiFatti()));
    	
    	
    	

    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    // per legare modello al controller
    public void setModel(Model model) {
    	this.model= model; 
    }
}
