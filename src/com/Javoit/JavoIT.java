package com.Javoit;

import java.awt.Point;

public class JavoIT {
	private MouseClick mouseClick;
	private HotKeySet hotKeySet;
	private MouseMove mouseMove;
	private static JavoIT javoit = new JavoIT();

	public static void main(String[] args) {
		JavoIT javoit = new JavoIT();
		javoit.HotKeySet("a", "derp", javoit);
	}

	public JavoIT() {

	}

	public void derp() {
		javoit.MouseClick("Left", 1000, 500, 10, 5);
	}

	/**
	 * Pauses execution for the specified milliseconds.
	 */
	public void sleep(long milliseconds) {
		Sleep sleep = new Sleep(milliseconds);
		sleep.pause();
	}

	public void HotKeySet(String hotKeys, String methodName, Object callingObject) {
		if (hotKeySet == null) {
			hotKeySet = new HotKeySet(hotKeys, methodName, callingObject);
		} else if (hotKeySet.getHotKeys() == hotKeys) {
			hotKeySet.setMethodName(methodName);
			hotKeySet.setCallingObject(callingObject);
		}
		hotKeySet.startListening();
	}

	public void MouseClick() {

	}

	public void MouseClick(String button) {

	}

	public void MouseClick(String button, int x, int y) {
		if (mouseClick == null)
			mouseClick = new MouseClick(button, x, y);
		else {
			mouseClick.setButton(button);
			mouseClick.setX(x);
			mouseClick.setY(y);
		}
		mouseClick.click();
	}

	public void MouseClick(String button, int x, int y, int speed) {
		if (mouseClick == null)
			mouseClick = new MouseClick(button, x, y, speed);
		else {
			mouseClick.setButton(button);
			mouseClick.setX(x);
			mouseClick.setY(y);
			mouseClick.setSpeed(speed);
		}
		mouseClick.click();
	}

	public void MouseClick(String button, int x, int y, int speed, int clicks) {
		if (mouseClick == null)
			mouseClick = new MouseClick(button, x, y, speed, clicks);
		else {
			mouseClick.setButton(button);
			mouseClick.setX(x);
			mouseClick.setY(y);
			mouseClick.setSpeed(speed);
			mouseClick.setClicks(clicks);
		}
		mouseClick.click();
	}

	public void MouseMove(int x, int y) {
		if (mouseMove == null) {
			mouseMove = new MouseMove(x, y);
			mouseMove.moveMouse();
		} else {
			mouseMove.setFinalMousePosition(new Point(x, y));
			mouseMove.moveMouse();
		}
	}

	public void MouseMove(int x, int y, int speed) {
		if (mouseMove == null) {
			mouseMove = new MouseMove(x, y, speed);
		} else {
			mouseMove.setFinalMousePosition(new Point(x, y));
			if (mouseMove.getSpeed() != speed)
				mouseMove.setSpeed(speed);
			mouseMove.moveMouse();
		}
	}

}
