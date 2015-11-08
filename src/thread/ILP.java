package thread;

import util.Messaggio;
import util.Out;

public class ILP extends Thread{
	private Messaggio messaggio;
	
	public ILP() {
		super("ILP");
	}
	
	public void run() {
		Out.println("L'interfaccia lato paziente si è avviata");
		messaggio = new Messaggio();
		
		Paziente paziente = new Paziente(messaggio);
		paziente.start();
		
		try {
			// Attendi il completamento della compilazione dei questionari
			paziente.join();
		} catch (InterruptedException e) {}
		
		
	}

	public String riceviQuestionario(){
		return messaggio.get().toString();
	}
	
	public void inviaNotifica(String descrizione){
		// TO DO
	}
}
