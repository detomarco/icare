package thread;

import classes.CTCAE;
import classes.ESAS;
import classes.Questionario;
import util.Messaggio;
import util.Out;

public class ILP extends Thread{
	private Questionario esas;
	private Questionario ctcae;
	private StringBuilder questionario;
	private Messaggio messaggio;
	
	public ILP() {
		super("ILP");
		esas = new ESAS();
		ctcae = new CTCAE();
		this.questionario = new StringBuilder ();
	}
	
	public void run() {
		Out.println("L'interfaccia lato paziente si è avviata");
		messaggio = new Messaggio();
		
		Paziente sender = new Paziente(messaggio);
		sender.start();
		
		try {
			// Attendi il completamento della compilazione dei questionari
			sender.join();
		} catch (InterruptedException e) {}
		
		
	}

	public String riceviQuestionario(){
		return messaggio.get().toString();
	}
	
	public void inviaNotifica(String descrizione){
		// TO DO
	}
}
