package thread;

import java.util.Map;

import classes.Questionario;
import util.Out;

public class Analizzatore extends Thread{
	Questionario quest;
	private String result;
	
	public Analizzatore(Questionario quest){
		super("Analizzatore");
		this.quest = quest;
		result = "";
	}

	public void run(){
		Out.println("L'analizzatore per il questionario " + quest.getClass().getSimpleName() + " si � avviato");
		this.result = valutaQuestionario();
	}
	
	public String valutaQuestionario(){
		Out.println("Valutazione del questionario " + quest.getClass().getSimpleName() + " in corso");
        Integer value;
        for(Map.Entry<String,Integer> element:quest.getMap().entrySet()){
            value = (int)element.getValue();
            if(value >= quest.getValoreAllarmante()){
            	String messaggio = "Il questionario " + quest.getClass().getSimpleName() + " � allarmante. ";
            	Out.println(messaggio);
            	return messaggio;
            }
        }
        
		return "";
	}
	
	
	public String getResult(){
		return this.result;
	}

}
