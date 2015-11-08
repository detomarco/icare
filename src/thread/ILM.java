package thread;

import org.json.JSONObject;

import classes.Alert;
import util.Messaggio;
import util.Out;

public class ILM extends Thread{
	private Messaggio messaggio;
	private Alert alert;
	public ILM(Alert alert) {
		super("ILM");
		this.messaggio = new Messaggio();
		this.alert = alert;
	}

	public void run() {
		Out.println("L'interfaccia lato medico si è avviata");
		Out.wait("Invio dell'alert all'interfaccia lato medico in corso");
		// Creazione dell'oggetto alert
		inviaAlert();
	}

	public void inviaAlert(){
		// Simulazione della consultazione dei questionari con i relativi risultati da parte del medico in caso di alert
		//	(in realtà questo thread viene eseguito nell'app mobile/desktop del medico)
		Medico medico = new Medico(messaggio);
		medico.start();
		
		JSONObject json = new JSONObject(alert.getQuestionario());
		json.put("descrizione", alert.getDescrizione());
		
		messaggio.put(json.toString());
	}
}
