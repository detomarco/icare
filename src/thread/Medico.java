package thread;

import org.json.JSONObject;

import util.Messaggio;
import util.Out;

public class Medico extends Thread{
	private Messaggio messaggio;
	
	public Medico(Messaggio messaggio) {
		super("Medico");
		this.messaggio = messaggio;
	}
	
	public void run() {
		
		analizzaQuestionari();
	}
	
	public void analizzaQuestionari(){
		String questionario = this.messaggio.get();
		JSONObject json = new JSONObject(questionario);
		Out.wait("Analisi dei risultati in corso...");
		Out.print("I risultati dicono che: " + json.get("descrizione").toString());
	}
	

}
