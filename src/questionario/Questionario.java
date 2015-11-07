package questionario;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

import org.json.JSONObject;

import util.Out;

public class Questionario {
	private String id;
	protected Date date;
	protected final int valoreAllarmante;
	protected Map<String, Integer> data;

	public int getValoreAllarmante() {
		return valoreAllarmante;
	}

	private boolean empty = true; 
	
	public Questionario(int valoreAllarmante) {
		this.id = this.generateId();
		this.valoreAllarmante = valoreAllarmante;
		this.date = new Date();
	}
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String generateId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public Map<String, Integer> getMap() {
		return data;
	}

	public void putValue(String label, int value){
		this.data.put(label, new Integer(value));
	}

	public static String toJSON(Questionario esas, Questionario ctcae){
		JSONObject json = new JSONObject();
		JSONObject esas_json = new JSONObject();
		JSONObject ctcae_json = new JSONObject();

		esas_json.put("id", esas.getId());
		esas_json.put("fields", esas.getMap());
		json.put("esas", esas_json);

		ctcae_json.put("id", ctcae.getId());
		ctcae_json.put("fields", ctcae.getMap());
		json.put("ctcae", ctcae_json);

		return json.toString();
	}
	
	public String toJSON(){
		JSONObject json = new JSONObject();
		JSONObject fields = new JSONObject();
		json.put("id", this.getId());
		for(Map.Entry<String,Integer> element: this.data.entrySet()){
			fields.put(element.getKey(), element.getValue());
		}
		json.put("fields", fields);

		return json.toString();
	}
	
}
