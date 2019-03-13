package Controller;
import java.io.IOException;

import View.DrawingManager;
/** 
 * The class that controls the user interface
 */

public class Main {
	
	/**
	 * Main method
	 * Gets the input from the standard input, and processes the the commands and parameters
	 * 
	 * @param 	args		
	 * @throws	IOException
	 */
	static public void main(String[] args){
		
		DrawingManager g = new DrawingManager();
		
		if(g != null) {
			
			System.out.println("App is running.");
			
		}
		
	}
	
}