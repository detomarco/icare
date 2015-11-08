
 /** SPECIFICHE DELLA PARTE IMPLEMENTATIVA
  	Per la parte implementativa, viene richiesto al team GIMFA di gestire solo la parte relativa ai questionari: 
  	dalla loro compilazione, alla loro analisi con eventuale notifica al paziente o alert al medico e storage nel db
 */


package thread;

import org.json.JSONArray;
import org.json.JSONObject;

import classes.Alert;
import classes.CTCAE;
import classes.ESAS;
import classes.Questionario;
import util.Out;

public class Core extends Thread{
	
	public Core() {
		super("Core");
	}
	
	public static void main(String[] args) {
		new Core().start();
	
	}
	
	public void run() {
		Out.println("Il core si è avviato");
		this.gestioneQuestionario();
	}
	
	public void gestioneQuestionario(){
		
		// Avvio dell'interfaccia lato paziente
			ILP ilp = new ILP();
			ilp.start();

		// Attendi la ricezione del questionario
		String questionario = ilp.riceviQuestionario();
		
		// Elaborazione della stringa json ricevuta per l'istanziazione dei due oggetti questionari
			JSONObject json  = new JSONObject(questionario);
			Questionario esas = new ESAS();
			Questionario ctcae = new CTCAE();
			
			// Creazione dell'oggetto esas
				JSONArray esas_json  = json.getJSONObject("esas").getJSONArray("fields");
				int[] esas_fields = new int[esas_json.length()];
				for (int i = 0, size = esas_json.length(); i < size; i++){
					esas_fields[i] = esas_json.getInt(i);
				}
				esas.putArray(esas_fields);
				esas.setId(json.getJSONObject("esas").get("id").toString());
				
			// Creazione dell'oggetto ctcae
				JSONArray ctcae_json  = json.getJSONObject("ctcae").getJSONArray("fields");
				int[] ctcae_fields = new int[ctcae_json.length()];
				for (int i = 0, size = ctcae_json.length(); i < size; i++){
					ctcae_fields[i] = ctcae_json.getInt(i);
				}
				ctcae.putArray(ctcae_fields);
				ctcae.setId(json.getJSONObject("esas").get("id").toString());
			
		String descrizione = Valutazione.valutaQuestionario(esas, ctcae);
		Out.println("Analisi dei questionari terminata");
		Out.div();

		// Avvio dell'interfaccia al database
			IDB idb = new IDB(questionario, descrizione);
			idb.start();
			
		// Se è presente una descrizione, allora è stata rilevata una criticità
		if(descrizione.indexOf("allarmante") >= 0){
			// Allarmare il medico
				Out.println("Condizione del paziente allarmante.");
				Alert alert = new Alert(questionario, descrizione);
			// Avvio dell'interfaccia lato medico
				ILM ilm = new ILM(alert);
				ilm.start();
			
		}else{
			
			// Notificare il paziente
				Out.println("Condizione del paziente rassicurante");
				Out.wait("Invio della notifica al paziente");
				ilp.inviaNotifica(descrizione);
		}
		
	}
	
}
