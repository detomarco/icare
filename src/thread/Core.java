/*
 * 	Per la parte implementativa, viene richiesto al team GIMFA di gestire solo la parte relativa ai questionari: 
 * 	dalla loro compilazione, alla loro analisi con eventuale notifica al paziente o alert al medico 
 * 
 */



package thread;

import java.util.Iterator;

import org.json.JSONObject;

import questionario.CTCAE;
import questionario.ESAS;
import questionario.Questionario;
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
		
		ILP ilp = new ILP();
		ilp.start();
		try {
			// Aspetta che l'ILP termini la sua esecuzione
			ilp.join();
		} catch (InterruptedException e) { }
		
		// Ricezione del questionario in formato json
		String questionario = ilp.riceviQuestionario();
		
		// Gestione della stringa json per la ricreazione degli oggetti riferiti ai due questionari
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
		
		// Se uno dei due questionari è allarmante
		if(!analizzatore_esas.getResult() || !analizzatore_ctcae.getResult()){
			// Allarmare il medico
			Out.println("Situazione del paziente allarmanete, avvisare il medico");
		}else{
			// Notificare il paziente
			Out.println("Situazione del paziente rassicurante, notificarlo al paziente");
		}
			
	}
	

}
