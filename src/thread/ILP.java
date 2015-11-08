package thread;

import util.Messaggio;
import util.Out;

public class ILP extends Thread{
	private Messaggio messaggio;
	private Messaggio notifica;
	
	public ILP() {
		super("ILP");
		messaggio = new Messaggio();
		notifica = new Messaggio();
	}
	
	public void run() {
		Out.println("L'interfaccia lato paziente si è avviata");
		// Simulazione della compilazione di un questionario 
		// 	(in realtà questo thread viene svolto nell'app del paziente)
		Paziente paziente = new Paziente(messaggio, notifica);
		paziente.start();
		
	}

	public String riceviQuestionario(){
		return messaggio.get().toString();
	}
	
	public void inviaNotifica(String descrizione){
		notifica.put(descrizione);
	}
}
