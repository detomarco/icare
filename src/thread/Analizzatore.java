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
	
	public String getResult(){
		return this.result;
	}
	
	public void run(){
		Out.println("L'analizzatore per il questionario " + quest.getClass().getSimpleName() + " si è avviato");
		this.result = valutaQuestionario();
	}
	
	public String valutaQuestionario(){
		Out.println("Valutazione del questionario " + quest.getClass().getSimpleName() + " in corso");
        String attr;
        Integer value;
        boolean result = true;
        for(Map.Entry<String,Integer> element:quest.getMap().entrySet()){
            attr = element.getKey();
            value = (int)element.getValue();
            if(value >= quest.getValoreAllarmante()){
            	String messaggio = "Questionario " + quest.getClass().getSimpleName() + " allarmanete\n";
            	Out.println(messaggio);
            	return messaggio;
            }
        }
        
		return "";
	}

}
