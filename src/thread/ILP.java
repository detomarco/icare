package thread;

import util.Messaggio;
import util.Out;

public class ILP extends Thread{
	private Messaggio messaggio;
	
	public ILP() {
		super("ILP");
		messaggio = new Messaggio();
	}
	
	public void run() {
		Out.println("L'interfaccia lato paziente si è avviata");
		Paziente paziente = new Paziente(messaggio);
		paziente.start();
		
	}

	public String riceviQuestionario(){
		return messaggio.get().toString();
	}
	
	public void inviaNotifica(String descrizione){
		// TO DO
	}
}
