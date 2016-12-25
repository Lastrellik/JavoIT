package com.Javoit;

import java.io.IOException;

class RunWait {
	
	private String program;
	
	public static void main(String[] args){
		try {
			Process process = Runtime.getRuntime().exec("C:\\Share\\Desktop\\Mouse Cursor Position Tooltip.exe");
			process.waitFor();
			System.out.println("finished");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	RunWait(String program){
		this.program = program;
	}
	
	void runProgram(){
		try {
			Process process = Runtime.getRuntime().exec(this.program);
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void setProgram(String program){
		this.program = program;
	}
}
