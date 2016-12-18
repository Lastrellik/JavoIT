package com.Javoit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

class MouseClickDrag {

	private int x1, x2, y1, y2, speed;
	private String button;
	private MouseMove pos1, pos2;
	private Robot robot;
	
	public static void main(String[] args){
		MouseClickDrag mcd = new MouseClickDrag("Left", 600, 10, 600, 500, 20);
		mcd.clickDrag();
	}

	MouseClickDrag(String button, int x1, int y1, int x2, int y2) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.button = button;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.speed = 10;
	}

	MouseClickDrag(String button,  int x1, int y1, int x2, int y2, int speed) {
		this(button, x1, y1, x2, y2);
		this.speed = speed;
	}
	
	void clickDrag(){
		pos1 = new MouseMove(x1, y1, speed);
		pos2 = new MouseMove(x2, y2, speed);
		
		pos1.moveMouse();
		
		if (button.toUpperCase().matches("LEFT") || button == "") {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			pos2.moveMouse();
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} else if (button.toUpperCase().matches("MIDDLE")) {
			robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
			pos2.moveMouse();
			robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);

		} else if (button.toUpperCase().matches("RIGHT")) {
			robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			pos2.moveMouse();
			robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		}

		else
			throw new IllegalArgumentException("Unrecognized mouse input event");
		
	}

	int getX1() {
		return x1;
	}

	void setX1(int x1) {
		this.x1 = x1;
	}

	int getX2() {
		return x2;
	}

	void setX2(int x2) {
		this.x2 = x2;
	}

	int getY1() {
		return y1;
	}

	void setY1(int y1) {
		this.y1 = y1;
	}

	int getY2() {
		return y2;
	}

	void setY2(int y2) {
		this.y2 = y2;
	}

	int getSpeed() {
		return speed;
	}

	void setSpeed(int speed) {
		this.speed = speed;
	}

	String getButton() {
		return button;
	}

	void setButton(String button) {
		this.button = button;
	}

	Robot getRobot() {
		return robot;
	}

	void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	
	
}
