package com.Javoit;

import java.awt.MouseInfo;
import java.awt.Point;

class MouseGetPos {
	private Point mousePoint;
	private int dimension = -1;
	
	public static void main(String[] args){
		MouseGetPos mgp = new MouseGetPos(1);
		System.out.println(mgp.getPosition());
	}
	
	MouseGetPos(int dimension){
		if(dimension < 0 || dimension > 1){
			throw new IllegalArgumentException("dimension must be 1 or 0");
		}
		mousePoint = MouseInfo.getPointerInfo().getLocation();
		this.dimension = dimension;
	}
	
	int getPosition(){
		if(dimension == 0) return (int) mousePoint.getX();
		else return (int) mousePoint.getY();
	}
	
	void setMousePoint(){
		mousePoint = MouseInfo.getPointerInfo().getLocation();
	}
	
	void setDimension(int dimension){
		if(dimension > 1 || dimension < 0){
			throw new IllegalArgumentException("dimension must be 1 or 0");
		}
		this.dimension = dimension;
	}
}
