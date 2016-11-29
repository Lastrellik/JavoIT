package com.Javoit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

class MouseClick {
	Robot robot;

	MouseClick() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	void click(String button) {
		if (button.toUpperCase() == "LEFT" || button == "") {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.delay(10);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} else if (button.toUpperCase() == "MIDDLE") {
			robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
			robot.delay(10);
			robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
			
		} else if (button.toUpperCase() == "RIGHT") {
			robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			robot.delay(10);
			robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		}

		else
			throw new IllegalArgumentException("Unrecognized mouse input event");
	}

	void click(String button, int x, int y) {

		robot.mouseMove(x, y);
		click(button);
	}

	void click(String button, int x, int y, int clicks) {

		robot.mouseMove(x, y);
		for (int i = 0; i < clicks; i++) {
			click(button);
		}
	}
}
