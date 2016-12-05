package com.Javoit;

public class JavoIT {
	private MouseClick mouseClick;
	private HotKeySet hotKeySet;

	public static void main(String[] args) {
		JavoIT javoit = new JavoIT();
		//javoit.HotKeySet("!a", "derp", javoit);
		javoit.sleep(1000);
		javoit.MouseClick("LeFT", 500, 500);
		javoit.sleep(1000);
		javoit.MouseClick("left", 900, 500);
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
		if(hotKeySet == null){
			hotKeySet = new HotKeySet(hotKeys, methodName, callingObject);
		} else if (hotKeySet.getHotKeys() == hotKeys){
			hotKeySet.setMethodName(methodName);
			hotKeySet.setCallingObject(callingObject);
		}
		hotKeySet.startListening();
	}
	
	public void MouseClick(String button, int x, int y){
		if(mouseClick == null) mouseClick = new MouseClick();
		mouseClick.click(button, x, y);
	}
	
	public void MouseClick(String button, int x, int y, int clicks){
		if(mouseClick == null) mouseClick = new MouseClick();
		mouseClick.click(button, x, y, clicks);
	}
	
	
}
