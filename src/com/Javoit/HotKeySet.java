package com.Javoit;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * Create a hotkey that executes code from the specified class. Does 
 * not support arguments.
 */
class HotKeySet implements NativeKeyListener {

	private int[] hotKeyIDs;
	private boolean[] isPressed;
	private String methodName;
	private KeyTextToUniqueID keyTextToID; //To create unique IDs for keystrokes
	private Object callingObject;
	private String hotKeys;
	
	/**
	 * 
	 * @param hotKeys String of hotkeys
	 * @param methodName The name of the method to be executed
	 * @param callingObject The object where the method lies
	 */
	HotKeySet(String hotKeys, String methodName, Object callingObject) {
		if(hotKeys == null){
			throw new NullPointerException();
		}
		this.hotKeys = hotKeys;
		this.callingObject = callingObject;
		keyTextToID = new KeyTextToUniqueID();
		parseHotkeyString(hotKeys);
		this.methodName = methodName;
	}

	private void parseHotkeyString(String hotKeys) {
		Scanner stringScanner = new Scanner(hotKeys);
		stringScanner.useDelimiter("");
		String currentCharInString;
		LinkedList<Integer> hotKeyIDList = new LinkedList<>();
		
		while(stringScanner.hasNext()){
			currentCharInString = stringScanner.next();
			//To take into account special keys
			if(currentCharInString.matches("\\{")){
				String charInsideSpecialKey = stringScanner.next();
				StringBuilder specialKey = new StringBuilder("{");
				while(!charInsideSpecialKey.matches("\\}")){
					specialKey.append(charInsideSpecialKey);
					charInsideSpecialKey = stringScanner.next();
				}
				specialKey.append("}");	
				//Uppercase to match what's in the hash table
				hotKeyIDList.add(keyTextToID.getUniqueID(specialKey.toString().toUpperCase()));
			} else {
				hotKeyIDList.add(keyTextToID.getUniqueID(currentCharInString));
			}
		}

		
		hotKeyIDs = new int[hotKeyIDList.size()];
		isPressed = new boolean[hotKeyIDList.size()];
		
		for(int i = 0; i < hotKeyIDs.length; i++){
			hotKeyIDs[i] = hotKeyIDList.get(i);
		}
		stringScanner.close();
	}

	
	
	void startListening() {
		
		try {
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.OFF);
			if(!GlobalScreen.isNativeHookRegistered()){
				GlobalScreen.registerNativeHook();
			} 
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.removeNativeKeyListener(this);
		GlobalScreen.addNativeKeyListener(this);
	}

	// ***************************VERY IMPORTANT**************************
	// The hash table created uses returned text from
	// NativeKeyEvent.getKeyText(e.getKeyCode());
	// This is what we have to use when getting the unique ID associated with
	// the key entry.
	// *********************************************************************
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
	
		for (int i = 0; i < hotKeyIDs.length; i++) {
			if (hotKeyIDs[i] == keyTextToID.getUniqueID(keyText)) {
				isPressed[i] = true;
			}
		}

		if (isAllTrue(isPressed)) {
			if(methodName != null){
				runMethod();				
			}
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
		
		for (int i = 0; i < hotKeyIDs.length; i++) {
			if (hotKeyIDs[i] == keyTextToID.getUniqueID(keyText)) {
				isPressed[i] = false;
			}
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
	}

	private void runMethod() {
		Method method;
		try {
			method = callingObject.getClass().getMethod(methodName);
			//Cannot eliminate this warning. HotKeySet doesn't support arguments.
			method.invoke(callingObject, null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private boolean isAllTrue(boolean[] array) {
		for (boolean b : array)
			if (!b)
				return false;
		return true;
	}
	
	String getHotKeys(){
		return this.hotKeys;
	}

	String getMethodName() {
		return methodName;
	}

	void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	Object getCallingObject() {
		return callingObject;
	}

	void setCallingObject(Object callingObject) {
		this.callingObject = callingObject;
	}

	void setHotKeys(String hotKeys) {
		this.hotKeys = hotKeys;
	}
	
	

}
