package thread;

import java.util.Map;

import classes.Questionario;
import util.Out;

public class Valutazione extends Thread{
	private Questionario quest;
	private String risultato;
	
	private Valutazione(Questionario quest){
		super("Valutazione");
		this.quest = quest;
		this.risultato = "";
	}

	public void run(){
		Out.println("Il thread per la valutazione del questionario " + quest.getClass().getSimpleName() + " si è avviato");
		Out.wait("Valutazione del questionario " + quest.getClass().getSimpleName() + " in corso");
        Integer value;
        for(Map.Entry<String,Integer> element:quest.getMap().entrySet()){
            value = (int)element.getValue();
            if(value >= quest.getValoreAllarmante()){
            	this.risultato = "Il questionario " + quest.getClass().getSimpleName() + " è allarmante. ";
            	Out.println(risultato);
            	return;
            }
        }
        this.risultato = "Il questionario " + quest.getClass().getSimpleName() + " è rassicurante. ";
        Out.println(risultato);
        
	}
	
	public static String valutaQuestionario(Questionario esas, Questionario ctcae){
		
		// Avvio del thread per analizzare il questionario ESAS
			Valutazione valutazione_esas = new Valutazione(esas);
			valutazione_esas.start();
		// Avvio del thread per analizzare il questionario CTCAE
			Valutazione valutazione_ctcae = new Valutazione(ctcae);
			valutazione_ctcae.start();
			try {
				// Attendere la fine delle analisi
					valutazione_esas.join();
					valutazione_ctcae.join();
			} catch (InterruptedException e) { }
			
		Out.println("Analisi dei questionari terminata");
		// Restituzione dei risultati
		return valutazione_esas.getRisultato() + valutazione_ctcae.getRisultato();
		
	}
	
	public String getRisultato(){
		return this.risultato;
	}

}
