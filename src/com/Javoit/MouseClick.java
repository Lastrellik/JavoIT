package com.Javoit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

class MouseClick {
	private Robot robot;
	private MouseMove mouseMove;
	private String button;
	private int speed;
	private int clicks;
	private int x;
	private int y;
	private JavoIT javoit;

	MouseClick() {
		this.javoit = new JavoIT();
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	MouseClick(String button) {
		this();
		this.button = button;
		this.clicks = 0;
	}

	MouseClick(String button, int x, int y) {
		this(button);
		mouseMove = new MouseMove(x, y);
		this.clicks = 0;
	}

	MouseClick(String button, int x, int y, int clicks) {
		this(button, x, y);
		this.clicks = clicks;
	}

	MouseClick(String button, int x, int y, int clicks, int speed) {
		this(button, x, y, clicks);
		this.speed = speed;
		mouseMove.setSpeed(this.speed);
	}

	void click() {
		if (mouseMove != null)
			mouseMove.moveMouse();
		for (int i = 0; i < clicks; i++) {
			if (button.toUpperCase().matches("LEFT") || button == "") {
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.delay(10);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			} else if (button.toUpperCase().matches("MIDDLE")) {
				robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
				robot.delay(10);
				robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);

			} else if (button.toUpperCase().matches("RIGHT")) {
				robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
				robot.delay(10);
				robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			}

			else
				throw new IllegalArgumentException("Unrecognized mouse input event");
			
			javoit.sleep(10);
		}
	}

	String getButton() {
		return button;
	}

	void setButton(String button) {
		this.button = button;
	}

	int getSpeed() {
		return speed;
	}

	void setSpeed(int speed) {
		this.speed = speed;
	}

	int getClicks() {
		return clicks;
	}

	void setClicks(int clicks) {
		this.clicks = clicks;
	}

	int getX() {
		return x;
	}

	void setX(int x) {
		this.x = x;
	}

	int getY() {
		return y;
	}

	void setY(int y) {
		this.y = y;
	}	
	
	
}
