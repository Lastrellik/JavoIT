package com.Javoit;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class MouseMove {
	public Point beginningMousePosition;
	public Point finalMousePosition;
	private int speed;
	private double slope;
	private Robot robot;

	public static void main(String[] args) {
		MouseMove[] moves = new MouseMove[15];

		moves[0] = new MouseMove(950, 600);
		moves[1] = new MouseMove(1920, 0);
		moves[2] = new MouseMove(950, 600);
		moves[3] = new MouseMove(1920, 1080);
		moves[4] = new MouseMove(950, 600);
		moves[5] = new MouseMove(0, 1080);
		moves[6] = new MouseMove(950, 600);
		moves[7] = new MouseMove(0, 600);
		moves[8] = new MouseMove(950, 600);
		moves[9] = new MouseMove(1920, 600);
		moves[10] = new MouseMove(950, 600);
		moves[11] = new MouseMove(950, 1080);
		moves[12] = new MouseMove(950, 600);
		moves[13] = new MouseMove(950, 0);
		moves[14] = new MouseMove(950, 600);

		for (int i = 0; i < moves.length; i++) {
			moves[i].moveMouse();
		}
		
	}

	MouseMove(int x, int y) {
		finalMousePosition = new Point(x, y);
		this.speed = 10;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	MouseMove(int x, int y, int speed) {
		this(x, y);
		this.speed = speed;
	}

	void moveMouse() {
		this.beginningMousePosition = MouseInfo.getPointerInfo().getLocation();
		this.slope = (double) (finalMousePosition.y - beginningMousePosition.y)
				/ (double) (finalMousePosition.x - beginningMousePosition.x);
		int width;
		int xIterationWidth;
		int yIterationHeight;
		int currentX = beginningMousePosition.x;
		int currentY = beginningMousePosition.y;
		boolean xBiggerThanY;
		
		if (finalMousePosition.x - beginningMousePosition.x > 0) {
			xIterationWidth = 10;
		} else {
			xIterationWidth = -10;
		}
		
		if (finalMousePosition.y - beginningMousePosition.y > 0){
			yIterationHeight = 10;
		} else {
			yIterationHeight = -10;
		}		

		if (Math.abs(finalMousePosition.x - beginningMousePosition.x) > Math
				.abs(finalMousePosition.y - beginningMousePosition.y)) {
			width = Math.abs(finalMousePosition.x - beginningMousePosition.x) / 10;
			xBiggerThanY = true;
		} else {
			width = Math.abs(finalMousePosition.y - beginningMousePosition.y) / 10;
			xBiggerThanY = false;
		}
		
		if (speed != 0) {
			for (int i = 1; i < width; i++) {
				if (xBiggerThanY) {
					robot.mouseMove(currentX, y(currentX));
					currentX += xIterationWidth;
					//System.out.println(currentX + " " + y(currentX));
				} else {
					if(finalMousePosition.x != beginningMousePosition.x){
						robot.mouseMove(x(currentY), currentY);
						currentY += yIterationHeight;
						//System.out.println(currentX + " " + y(currentX));
					} else {
						robot.mouseMove(beginningMousePosition.x, currentY);
						currentY += yIterationHeight;
					}
				}
				try {
					Thread.sleep(this.speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		robot.mouseMove(finalMousePosition.x, finalMousePosition.y);
	}

	private int y(int x) {
		return (int) Math.round((this.slope * x) - (this.slope * finalMousePosition.x) + finalMousePosition.y);
	}

	private int x(int y) {
		return (int) Math.round((y - finalMousePosition.y + (this.slope * finalMousePosition.x)) / this.slope);
	}

	Point getFinalMousePosition() {
		return finalMousePosition;
	}

	void setFinalMousePosition(Point finalMousePosition) {
		this.finalMousePosition = finalMousePosition;
	}

	int getSpeed() {
		return speed;
	}

	void setSpeed(int speed) {
		this.speed = speed;
	}

}
