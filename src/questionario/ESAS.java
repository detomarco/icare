/**
 * 
 */
package questionario;

import java.util.HashMap;

/**
 * @author Marco
 *
 */
public class ESAS extends Questionario{
	
	private static final String[] quests = {"Dolore: 0 senza dolore / 10 dolore forte",
											"Attività fisica: 0 molto attivo fisicamente / 10 non attivo fisicamente",
											"Nausea: 0 senza nausea / 10 molta nausea",
											"Depressione: 0 senza depressione / 10 molta depressione",
											"Ansia: 0 senza ansia / 10 molta ansia",
											"Appetito: 0 molto appetito / 10 molto appetito",
											"Benessere: 0 ottimo benessere / 10 scarso benessere",
											"Respiratione: 0 nessuna fatica di respiro / 10 molta fatica di respiro",
											"Sonnolenza: 0 senza sonnolenza / 10 molta sonnolenza"
											};

	public ESAS() {
		super(7);
		// Inizializzazione della map
		data = new HashMap<>();
		for(String label: this.quests ){
			this.data.put(label, new Integer(-1));
		}
	}

}
