package util;

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

	public synchronized void put(String content) {

		while (!empty) { 
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		
		this.content = content;
		empty = false; 
		notifyAll(); 
		
	} 
}
