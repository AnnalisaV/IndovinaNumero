package it.polito.tdp.indovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private final int NMAX=100; // scelto da me 
	private final int TMAX= 8;  // ragionevolmente puntando sempre alla meta' degli intervalli
    private int segreto; //variabile 
	private int tentativiFatti; 
	private boolean inGioco;  //sono ancora vivo o no
	
	private Set<Integer> tentativi; // traccia dei tentativi fatti
	
	public Model() {
		// nel costruttore inserisco tutte le operazioni da effettuare quando
		//parte l'app/il gioco
		
		this.inGioco=false; 
		this.tentativiFatti=0; 
		
		}
	
	// nuova partita
	public void nuovaPartita() {
		
       //LOGICA DEL GIOCO
    	
    	//definisco un numero segreto a caso
    	this.segreto= (int)(Math.random()*NMAX) +1;
    	
    	this.tentativiFatti=0; 
    	this.inGioco=true; 
    	
    	this.tentativi= new HashSet<Integer>(); 
	}
	
	//decido che il valore di ritorno sia significativo per fare capire se
	// l'utente ha indovinato il numero segreto(0), ha tentato con un valore troppo
	//basso (-1) o troppo alto (+1)
	public int tentativo(int tentativo) {
		
		//controllo se la partita e' in corso
		if(!inGioco) {
			// return 5; (valore a caso) ma e' poco efficiente
			throw new IllegalStateException("La partita e' gia' terminata"); // gia' predefinita
		}
		
		//controllo l'input 
		// ricevo gia' un int quindi qst controllo lo lascio al Controller
		// ma sono nell'intervallo corretto?
		if (!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e "+NMAX+"\n"); 
		}
		
		//il tentativo e' valido 
		//-> lo si ''prova''
		
		//aggiorno il numero di tentativi effettuati; 
    	this.tentativiFatti++; 
    	
    	// ad ogni tentativo, lo memorizzo
    	this.tentativi.add(tentativo); 
    	
    	if (this.tentativiFatti==TMAX) {
    		//ho finito i tentativi quindi ho terminato la partita
    		this.inGioco=false; 
    	}
    	
    	//ora, 
    	//ho indovinato
    	if (tentativo==this.segreto) {
    		this.inGioco=false; 
    		return 0; 
    	}
    	
    	//non ho indovinato
    	if(tentativo<this.segreto) {
    		return -1; 
    	}
    	else return 1; 
    	
    	
	}
	
	// delega migliore
	private boolean tentativoValido(int tentativo) {
		
		if (tentativo<1 || tentativo>NMAX)
			return false; 
		else {
			// e' gia' sttao utilizzato?
			if (this.tentativi.contains(tentativo)) {
				return false; 
			}
			else return true; 
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
