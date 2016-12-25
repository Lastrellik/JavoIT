package com.Javoit;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

class Run {
	private File fileToRun;
	
	public static void main(String[] args){
		Run run = new Run("C:\\Share\\Desktop\\Mouse Cursor Position Tooltip.exe");
		run.executeProgram();
	}
	
	Run(String program){
		this.fileToRun = new File(program);
	}
	
	void executeProgram(){
		try {
			Desktop.getDesktop().open(this.fileToRun);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setFileToRun(String file){
		this.fileToRun = new File(file);
	}
	
}
