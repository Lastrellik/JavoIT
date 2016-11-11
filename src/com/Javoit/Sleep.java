package com.Javoit;

public class Sleep {
	long milliseconds;
	
	public Sleep(long milliseconds){
		this.milliseconds = milliseconds;
	}
	
	public void pause(){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
