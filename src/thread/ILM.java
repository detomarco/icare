package thread;

import org.json.JSONObject;

import classes.Alert;
import util.Messaggio;
import util.Out;

public class ILM extends Thread{
	
	Messaggio messaggio;
	public ILM() {
		this.messaggio = new Messaggio();
	}

	public void run() {

		

	}

	public void inviaAlert(Alert alert){
		Medico medico = new Medico(messaggio);
		medico.start();
		
		JSONObject json = new JSONObject(alert.getQuestionario());
		json.put("descrizione", alert.getDescrizione());
		
		messaggio.put(json.toString());
	}
}
