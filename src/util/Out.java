package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Out {
	
	private static SimpleDateFormat date = new SimpleDateFormat("H:m:s");
	
	public static void print(Object o){
		System.out.print(date.format(new Date()) + " - " + Thread.currentThread().getName() + ": " + o.toString());
	}
	
	public static void println(Object o){
		Out.print(o.toString() + "\n");
	}
	
	public static void wait(Object o){
		Out.print(o.toString());
		for(int i=0; i <= 10; i++){
			try { Thread.sleep(300); } catch (InterruptedException e) { }
			if(i != 10){ System.out.print(".");
			}else{ System.out.println(".");}
		}
	}
	
}
