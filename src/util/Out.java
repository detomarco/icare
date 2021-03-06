package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Out {
	
	private static SimpleDateFormat date = new SimpleDateFormat("H:m:s");
	
	public static void print(String o){
		String.format("%30s", ""); 
		System.out.print(date.format(new Date()) + " - " + Thread.currentThread().getName() + ": " + o.toString());
	}
	
	public static void println(String o){
		Out.print(o.toString() + "\n");
	}
	
	public static void wait(Object o){
		Out.println(o.toString() + "..........");
		try { 
			Thread.sleep(new Random().nextInt(10000)); 
		} catch (InterruptedException e) { }
	}
	
	public static void div(){
		System.out.println("\n-------------------------------------------------------------------------------------\n");
	}
	
}
