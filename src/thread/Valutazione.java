package thread;

import java.util.Map;

import classes.Questionario;
import util.Out;

public class Valutazione extends Thread{
	private Questionario quest;
	private String descrizione;
	private boolean result;
	
	public Valutazione(Questionario quest){
		super("Valutazione");
		this.quest = quest;
		descrizione = "";
		result = true;
	}

	public void run(){
		Out.println("Il modulo per la valutazione del questionario " + quest.getClass().getSimpleName() + " si � avviato");
		this.descrizione = valutaQuestionario();
	}
	
	public String valutaQuestionario(){
		Out.wait("Valutazione del questionario " + quest.getClass().getSimpleName() + " in corso");
        Integer value;
        String messaggio;
        for(Map.Entry<String,Integer> element:quest.getMap().entrySet()){
            value = (int)element.getValue();
            if(value >= quest.getValoreAllarmante()){
            	messaggio = "Il questionario " + quest.getClass().getSimpleName() + " � allarmante. ";
            	Out.println(messaggio);
            	result = false;
            	return messaggio;
            }
        }
        messaggio = "Il questionario " + quest.getClass().getSimpleName() + " � rassicurante. ";
        Out.println(messaggio);
		return messaggio;
	}
	
	public String getDescrizione(){
		return this.descrizione;
	}
	
	public boolean getResult(){
		return this.result;
	}

}
