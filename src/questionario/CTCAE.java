/**
 * 
 */
package questionario;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco
 *
 */
public class CTCAE extends Questionario{
	
	private static final String[] quests = {"Diarrea: 0 assente / 4 più di sette evazuazioni",
											"Infiammazione alla bocca: 0 assente / 4 dolore grave",
											"Vomito: 0 assente / 4 almeno sei volte al giorno",
											"Affanno: 0 assente / 4 affanno a riposo"
											};

	public CTCAE() {
		super(3);
		// Inizializzazione della map
		this.data = new HashMap<>();
		for(String label: this.quests ){
			this.data.put(label, new Integer(-1));
		}
	}

}
