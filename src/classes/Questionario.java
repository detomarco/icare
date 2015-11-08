package classes;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import util.Out;

public class Questionario {
	private String id;
	protected Date date;
	protected final int valoreAllarmante;
	protected Map<String, Integer> data;

	

	public Questionario(int valoreAllarmante) {
		this.id = this.generateId();
		this.valoreAllarmante = valoreAllarmante;
		this.date = new Date();
	}
	public String getId() {
		return this.id;
	}
	public int getValoreAllarmante() {
		return valoreAllarmante;
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

	public void put(String label, int value){
		this.data.put(label, new Integer(value));
	}
	
	public void putArray(int[] value){
		int i = 0;
		for(Map.Entry<String,Integer> element: this.data.entrySet()){
			this.put(element.getKey(), value[i]);
			i++;
		}
	}

	public static String toJSON(Questionario esas, Questionario ctcae){
		JSONObject json = new JSONObject();
		JSONObject esas_json = new JSONObject();
		JSONObject ctcae_json = new JSONObject();

		json.put("esas", esas.toJSON());

		json.put("ctcae", ctcae.toJSON());

		return json.toString();
	}
	
	public JSONObject toJSON(){
		JSONObject json = new JSONObject();
		JSONArray fields = new JSONArray();
		json.put("id", this.getId());
		for(Map.Entry<String,Integer> element: this.data.entrySet()){
			fields.put(element.getValue());
		}
		json.put("fields", fields);
		

		return json;
	}
	
}
