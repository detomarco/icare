package thread;

import util.Out;

public class IDB extends Thread{
	
	String questionario;
	public IDB(String questionario) {
		super("IDB");
		this.questionario = questionario;
	}

	public void run() {
		Out.println("L'interfaccia al database si è avviata");
		salvaRisultati(questionario);


	}

	public void salvaRisultati(String questionario){
		
		Out.println("Elaborazione ed inserimento del seguente risultato: ");
		Out.println(questionario);
	}
}
