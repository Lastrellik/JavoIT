package com.Javoit;

public class JavoIT {
	private MouseClick mouseClick;

	public static void main(String[] args) {
		JavoIT javoit = new JavoIT();
		javoit.HotKeySet("0", "derp", javoit);
	}
	
	public JavoIT(){
		
	}
	
	public void derp(){
		MouseClick("LEFT", 1000, 1000);
	}
	
	/**
	 * Pauses execution for the specified milliseconds.
	 */
	public void sleep(long milliseconds){
		Sleep sleep = new Sleep(milliseconds);
		sleep.pause();
	}
	
	public void HotKeySet(String hotKeys, String methodName, Object callingObject){
		@SuppressWarnings("unused")
		HotKeySet hks = new HotKeySet(hotKeys, methodName, callingObject);
	}
	
	public void MouseClick(String button, int x, int y){
		if(mouseClick == null) mouseClick = new MouseClick();
		mouseClick.click(button, x, y, 10);
	}
	
	
}
