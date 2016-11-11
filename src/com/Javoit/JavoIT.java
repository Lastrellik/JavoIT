package com.Javoit;

public class JavoIT {

	public static void main(String[] args) {
		JavoIT javoit = new JavoIT();
		javoit.sleep(5);
	}
	
	public JavoIT(){
		
	}
	
	/**
	 * Pauses execution for the specified milliseconds.
	 */
	public void sleep(long milliseconds){
		Sleep sleep = new Sleep(milliseconds);
		sleep.pause();
	}

}
