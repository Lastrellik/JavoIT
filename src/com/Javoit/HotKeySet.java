package com.Javoit;

import java.util.Arrays;
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
	
	/**
	 * 
	 * @param hotKeys String of hotkeys
	 * @param methodName The name of the method to be executed
	 * @param callingObject The object where the method lies
	 */
	HotKeySet(String hotKeys, String methodName, Object callingObject) {
		if(hotKeys == null || methodName == null || callingObject == null){
			throw new NullPointerException();
		}
		this.callingObject = callingObject;
		keyTextToID = new KeyTextToUniqueID();
		this.hotKeyIDs = new int[hotKeys.length()];
		for (int i = 0; i < hotKeys.length(); i++) {
			hotKeyIDs[i] = keyTextToID.getUniqueID(Character.toString(hotKeys.charAt(i)));
		}
		this.isPressed = new boolean[hotKeys.length()];
		this.methodName = methodName;
		Arrays.fill(isPressed, false);
		startListening();
	}

	private void startListening() {

		try {
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.OFF);
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(this);
	}

	// ***************************VERY
	// IMPORTANT**************************************************
	// The hash table created uses returned text from
	// NativeKeyEvent.getKeyText(e.getKeyCode());
	// This is what we have to use when getting the unique ID associated with
	// the key entry.
	// *******************************************************************************************
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
		for (int i = 0; i < hotKeyIDs.length; i++) {
			if (hotKeyIDs[i] == keyTextToID.getUniqueID(keyText)) {
				isPressed[i] = true;
			}
		}

		if (isAllTrue(isPressed)) {
			runMethod();
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

}
