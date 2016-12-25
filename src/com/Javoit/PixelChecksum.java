package com.Javoit;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

class PixelChecksum {
	private int left, top, right, bottom;
	private Robot robot;
	private Rectangle screenRect;
	private BufferedImage checksumImage;
	
	public static void main(String[] args){
		long currentTime = System.currentTimeMillis();
		PixelChecksum pcs = new PixelChecksum(0,0,2560,1440);
		System.out.println(pcs.getChecksum() + "  " + (System.currentTimeMillis() - currentTime));
	}
	
	PixelChecksum(int left, int top, int right, int bottom){
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		//buildArray();
	}
	
	int getChecksum(){
		int width = this.right - this.left;
		int height = this.bottom - this.top;
		this.screenRect = new Rectangle(this.left, this.top, width, height);
		checksumImage = robot.createScreenCapture(screenRect);
		int[] pixels = ((DataBufferInt) checksumImage.getRaster().getDataBuffer()).getData();
		return Arrays.hashCode(pixels);
	}

	void setLeft(int left) {
		this.left = left;
	}

	void setTop(int top) {
		this.top = top;
	}

	void setRight(int right) {
		this.right = right;
	}

	void setBottom(int bottom) {
		this.bottom = bottom;
	}

}
