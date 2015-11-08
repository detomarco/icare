/*
 * 	Per la parte implementativa, viene richiesto al team GIMFA di gestire solo la parte relativa ai questionari: 
 * 	dalla loro compilazione, alla loro analisi con eventuale notifica al paziente o alert al medico 
 * 
 */



package thread;

import java.util.Iterator;

import org.json.JSONObject;

import classes.Alert;
import classes.CTCAE;
import classes.ESAS;
import classes.Questionario;
import util.Out;

public class Core extends Thread{
	
	public Core() {
		super("Core");
		start();
	}
	
	public static void main(String[] args) {
		new Core();
	}
	
	public void run() {
		new Analizzatore(new ESAS());
		Out.println("Il core si è avviato");
		this.gestioneQuestionario();
	}
	
	public void gestioneQuestionario(){
		// Avvio dell'interfaccia lato medico
			ILM ilm = new ILM();
			ilm.start();
		// Avvio dell'interfaccia lato paziente
			ILP ilp = new ILP();
			ilp.start();
		try {
			// Aspetta che l'ILP termini la sua esecuzione
			ilp.join();
		} catch (InterruptedException e) { }
		
		// Ricezione dei questionari in formato json
		String questionario = ilp.riceviQuestionario();
		
		// Gestione della stringa json per l'istanziazzione dei due oggetti questionari
			JSONObject json  = new JSONObject(questionario);
			Questionario esas = new ESAS();
			Questionario ctcae = new CTCAE();
			
			// Creazione dell'oggetto esas
				JSONObject esas_json  = json.getJSONObject("esas").getJSONObject("fields");
				Iterator<String> iter = esas_json.keys();
				while (iter.hasNext()) {
				    String key = iter.next();
				    int value = Integer.parseInt(esas_json.get(key).toString());
			        esas.putValue(key, value);		    
				}
				esas.setId(json.getJSONObject("esas").get("id").toString());
				
			// Creazione dell'oggetto ctcae
				JSONObject ctcae_json  = json.getJSONObject("ctcae").getJSONObject("fields");
				Iterator<String> iter2 = ctcae_json.keys();
				while (iter2.hasNext()) {
				    String key = iter2.next();
				    int value = Integer.parseInt(ctcae_json.get(key).toString());
				    ctcae.putValue(key, value);		    
				}
				ctcae.setId(json.getJSONObject("ctcae").get("id").toString());
			
		// Avvio del thread per analizzare il questionario ESAS
			Analizzatore analizzatore_esas = new Analizzatore(esas);
			analizzatore_esas.start();
		// Avvio del thread per analizzare il questionario CTCAE
			Analizzatore analizzatore_ctcae = new Analizzatore(ctcae);
			analizzatore_ctcae.start();

		try {
			// Attendere la fine delle analisi
				analizzatore_esas.join();
				analizzatore_ctcae.join();
		} catch (InterruptedException e) { }
	
		
		// Recupero del risultato delle analisi, con relativa descrizione in caso di criticità
			String result_esas = analizzatore_esas.getResult();
			String result_ctcae = analizzatore_ctcae.getResult();
		
		// Generazione della descrizione dei risultati
		String descrizione = result_esas + result_ctcae;
		
		// Se è presente una descrizione, allora è stata rilevata una criticità
		if(!descrizione.equals("")){
			// Allarmare il medico
				Out.println("Situazione del paziente allarmante.");
				Out.wait("Invio dell'alert all'interfaccia lato medico in corso");
				// Creazione dell'oggetto alert
				Alert alert = new Alert(questionario, descrizione);
				ilm.inviaAlert(alert);
		}else{
			
			// Notificare il paziente
				Out.wait("Situazione del paziente rassicurante, notificarlo al paziente");
				descrizione = "Complimenti, i questionari hanno ottenuto esito positivo.";
				ilp.inviaNotifica(descrizione);
		}
		
		
		
			
	}
	

}
