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
		Out.div();
		Out.wait("Analisi dei risultati in corso");
		Out.println("I risultati dicono che: " + json.get("descrizione").toString());
		Out.wait("Consultazione approfondita dei questionari");
		Out.println("Presa dei dovuti provvedimenti");
		Out.div();
	}
	

}
