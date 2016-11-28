package com.Javoit;

import java.awt.Canvas;

import javax.swing.JFrame;

public class JavoIT {
	//Used for key binding with HotKeySet
	Canvas canvas;
	JFrame frame;

	public static void main(String[] args) {
		JavoIT javoit = new JavoIT();
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
	
	public void HotKeySet(String hotKeys, String methodName, Object callingObject){
		@SuppressWarnings("unused")
		HotKeySet hks = new HotKeySet(hotKeys, methodName, callingObject);
	}
}
