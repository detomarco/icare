package thread;

import java.util.Map;
import java.util.Scanner;

import classes.CTCAE;
import classes.ESAS;
import classes.Questionario;
import util.Messaggio;
import util.Out;

public class Paziente extends Thread{
	private Questionario esas;
	private Questionario ctcae;
	private Messaggio messaggio;
	public Paziente(Messaggio messaggio) {
		super("Paziente");
		this.esas = new ESAS();
		this.ctcae = new CTCAE();
		this.messaggio = messaggio;
	}
	
	public void run() {
		
		Scanner in = new Scanner(System.in);
		Out.println("Ciao Walter, è arrivato il momento di compilare i nuovi questionari.");
		Out.println("Premi invio per iniziare");
		in.nextLine();
		
		/* QUESTIONARIO ESAS */
			Out.div();
			Out.println("Questionario ESAS: indica la gravità del sintomo con dei numeri che vanno da 0 a 10");
			
			// Per ogni domanda del questionario ESAS
			for(Map.Entry<String,Integer> element:esas.getMap().entrySet()){
				String label = element.getKey();
				int value; ;
				
				do{
					try{
						// Mostra domanda
						Out.print(label + ": ");
						// Recupera valore dalla console
						value = Integer.parseInt(in.nextLine());
					}catch(NumberFormatException ex){
						// Se non è stato inserita una cifra, imposta il valore a -1 cosi da ripetere la domanda corrente
						value = -1;
					}
				// Se il valore inserito non è valido
				} while (value < 0 || value > 10);
				
				// Inserisci il valore nella map
				esas.putValue(label, value);
	        }
			
			Out.println("Il questionario ESAS è concluso: passiamo ora a quello CTCAE");
			
		/* QUESTIONARIO CTCAE */
			Out.div();
			Out.println("Questionario CTCAE: indica la gravità del sintomo con dei numeri che vanno da 0 a 4\n");
			
			// Per ogni domanda del questionario CTCAE
			for(Map.Entry<String,Integer> element:ctcae.getMap().entrySet()){
				String label = element.getKey();
				int value; ;
				
				do{
					try{
						// Mostra domanda
						Out.print(label + ": ");
						// Recupera valore dalla console
						value = Integer.parseInt(in.nextLine());
					}catch(NumberFormatException ex){
						// Se non è stato inserita una cifra, imposta il valore a -1 cosi da ripetere la domanda corrente
						value = -1;
					}
				// Se il valore inserito non è valido
				} while (value < 0 || value > 4);
				
				// Inserisci il valore nella map
				ctcae.putValue(label, value);
	        }
			
			Out.println("Il questionario CTCAE è concluso: la compilazione dei questionari si è conclusa.");
			Out.div();
		
			in.close();
			
			// Simulazione di invio dei questionari
				Out.wait("Invio dei questionari in corso");
				
			
				Out.println("Questionario inviato, ti invieremo una notifica appena verrano valutati");
			
			Out.div();
			
			// Invio dei questionari
			this.inviaQuestionari(esas, ctcae);
		
	}
	
	public void inviaQuestionari(Questionario esas, Questionario ctcae){
		this.messaggio.put(Questionario.toJSON(esas, ctcae));
	}
	

}
