package thread;

import java.util.Map;

import classes.Questionario;
import util.Out;

public class Analizzatore extends Thread{
	Questionario quest;
	private String result;
	Questionario esas, ctcae;
	
	public Analizzatore(Questionario esas, Questionario ctcae){
		super("Analizzatore");
		this.esas = esas;
		this.ctcae = ctcae;
		this.result = "";
	}
	
	public Analizzatore(Questionario quest){
		super("Analizzatore");
		this.quest = quest;
		valutaQuestionario();
		this.result = "";
	}
	
	public String getResult(){
		return this.result;
	}
	
	public void run(){
		Out.println("L'analizzatore  si è avviato");
		Analizzatore analizzatore_esas = new Analizzatore(esas);
		Analizzatore analizzatore_ctcae = new Analizzatore(ctcae);
		this.result = analizzatore_esas.getResult() + analizzatore_ctcae.getResult();
	}
	
	public void valutaQuestionario(){
		Out.println("Valutazione del questionario " + quest.getClass().getSimpleName() + " in corso");
        String attr;
        Integer value;
        for(Map.Entry<String,Integer> element:quest.getMap().entrySet()){
            attr = element.getKey();
            value = (int)element.getValue();
            if(value >= quest.getValoreAllarmante()){
            	String messaggio = "Il questionario " + quest.getClass().getSimpleName() + " è allarmante. ";
            	Out.println(messaggio);
            	this.result =  messaggio;
            	return;
            }
        }
        
	}

}
