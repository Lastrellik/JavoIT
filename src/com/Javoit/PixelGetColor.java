package com.Javoit;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

class PixelGetColor {
	int x, y;
	private Robot robot;
	private Color color;
	
	public PixelGetColor(int x, int y) {
		this.x = x;
		this.y = y;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.color = robot.getPixelColor(x, y);
	}
	
	Integer getHashCode(){
		this.color = robot.getPixelColor(this.x, this.y);
		return color.hashCode();
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}	
	
}
