package com.Javoit;

import java.util.Arrays;

class PixelChecksum {
	private int left, top, right, bottom;
	private int[] pixelChecksumArray;
	private JavoIT javoit = new JavoIT();
	
	public static void main(String[] args){
		PixelChecksum pcs = new PixelChecksum(0,0,100,100);
		System.out.println(pcs.getChecksum());
	}
	
	PixelChecksum(int left, int top, int right, int bottom){
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		buildArray();
	}
	
	void buildArray(){
		int width = right - left;
		int height = bottom - top;
		pixelChecksumArray = new int[width * height];
		int x = left;
		int y = top;
		long start = System.currentTimeMillis();
		
		System.out.println("start");
		for(int i = 0; i < pixelChecksumArray.length; i++){
			if(x % 20 == 0)
			System.out.println(x + "  " + y);
			if(x > right){
				x = left;
				y++;
			}
			
			pixelChecksumArray[i] = javoit.PixelGetColor(x++, y);
		}
		System.out.println("done. time: " + (System.currentTimeMillis() - start));
	}
	
	int getChecksum(){
		return pixelChecksumArray.hashCode();
	}
}
