package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Out {
	
	private static SimpleDateFormat date = new SimpleDateFormat("H:m:s");
	
	public static void print(Object o){
		System.out.print(date.format(new Date()) + " - " + Thread.currentThread().getName() + ": " + o.toString());
	}
	
	public static void println(Object o){
		Out.print(o.toString() + "\n");
	}
	
	public static void wait(Object o){
		Out.println(o.toString() + "..........");
		try { Thread.sleep(new Random().nextInt(3000)); } catch (InterruptedException e) { }
	}
	
	public static void div(){
		System.out.println("\n-------------------------------------------------------------------------------------\n");
	}
	
}
