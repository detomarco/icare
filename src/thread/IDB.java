package thread;

import org.json.JSONObject;

import util.Out;

public class IDB extends Thread{
	
	private String questionario;
	private String descrizione;
	
	public IDB(String questionario, String descrizione) {
		super("IDB");
		this.questionario = questionario;
		this.descrizione = descrizione;
	}

	public void run() {
		Out.println("L'interfaccia al database si è avviata");
		salvaRisultati(questionario, descrizione);

	}

	public void salvaRisultati(String questionario, String descrizione){
		
		JSONObject json = new JSONObject(questionario);
		// Inserimento della descrizione nella stringa da elaborare ed inserire nel db
		json.put("descrizione", descrizione);
		
		Out.println("Elaborazione ed inserimento del seguente risultato: " + json.toString());
	}
}
