package util;

import classes.Questionario;

public class Messaggio {
	private String content;
	private boolean empty = true;
	
	public Messaggio(){
		this.content = "";
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public synchronized String get() {

		while (empty) { 
			try {
				wait();
			} catch (InterruptedException  e) { }
		}
		
		empty = true; 
		notifyAll(); 
		return this.content;
	} 

	public synchronized void put(Questionario esas, Questionario ctcae) {

		while (!empty) { 
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		
		this.content = Questionario.toJSON(esas, ctcae);
		empty = false; 
		notifyAll(); 
		
	} 
}
