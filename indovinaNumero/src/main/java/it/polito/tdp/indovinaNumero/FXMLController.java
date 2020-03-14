package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	private final int NMAX=100; // scelto da me 
	private final int TMAX= 8;  // ragionevolmente puntando sempre alla meta' degli intervalli
	
	private int segreto; //variabile 
	
	private int tentativiFatti; 
	private boolean inGioco= false; //sono ancora vivo o no
	
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
    	//LOGICA DEL GIOCO
    	
    	//definisco un numero segreto a caso
    	this.segreto= (int)(Math.random()*NMAX) +1;
    	
    	this.tentativiFatti=0; 
    	this.inGioco=true; 
    	
    	//GESTIONE INTERFACCIA
    	//abilitazione dell hbox
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear(); //pulire i msg vecchi 
    	// aggiorno tentativi rimasti
    	txtRimasti.setText(Integer.toString(TMAX));
    	

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
    	
    	//aggiorno il numero di tentativi effettuati; 
    	this.tentativiFatti++; 
    	
    	//verifico
    	if (tentativo==this.segreto) {
    		// indovinato
    		txtRisultato.appendText("Hai vinto!!! Hai utilizzato : "+this.tentativiFatti+"tentativi.");
    		layoutTentativo.setDisable(true);
    		this.inGioco=false; 
    		return; 
    	}
    	
    	// controllo di non aver esaurito i tentativi
    	if (this.tentativiFatti==TMAX) {
    		//non indovinato e perso
    		txtRisultato.appendText("Hai perso!!! Il numero segreto era : "+this.segreto);
    		layoutTentativo.setDisable(true);
    		this.inGioco=false; 
    		return; 
    	}
    	
    	// non ho perso e non ho indovinato
    	if (tentativo<this.segreto) {
    		txtRisultato.appendText("Tentativo troppo basso\n");
    		
    	}
    	else {
    		txtRisultato.appendText("Tentativo troppo alto \n");
    	}
    	
    	txtRimasti.setText(Integer.toString(TMAX-tentativiFatti));
    	

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
}
