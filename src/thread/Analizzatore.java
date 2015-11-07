package thread;

import java.util.Map;

import questionario.Questionario;
import util.Out;

public class Analizzatore extends Thread{
	Questionario quest;
	private boolean result;
	
	public Analizzatore(Questionario quest){
		super("Analizzatore");
		this.quest = quest;
		result = true;
	}
	
	public boolean getResult(){
		return this.result;
	}
	
	public void run(){
		Out.println("L'analizzatore per il questionario " + quest.getClass().getSimpleName() + " si è avviato");
		this.result = valutaQuestionario();
	}
	
	public boolean valutaQuestionario(){
		Out.println("Valutazione del questionario " + quest.getClass().getSimpleName() + " in corso");
		
        String attr;
        Integer value;
        boolean result = true;
        for(Map.Entry<String,Integer> element:quest.getMap().entrySet()){
            attr = element.getKey();
            value = (int)element.getValue();
            if(value >= quest.getValoreAllarmante()){
            	Out.println("Questionario " + quest.getClass().getSimpleName() + " allarmanete\n");
            	result = false;
            	break;
            }
        }
		return result;
	}
}
