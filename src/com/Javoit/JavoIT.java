package com.Javoit;

import java.awt.Point;

public class JavoIT {
	private MouseClick mouseClick;
	private HotKeySet hotKeySet;
	private MouseMove mouseMove;
	private static JavoIT javoit = new JavoIT();
	private MouseClickDrag mouseClickDrag;
	private MsgBox msgBox;
	private PixelGetColor pixelGetColor;
	private MouseGetPos mouseGetPos;
	private PixelChecksum pixelChecksum;
	private Run run;
	private RunWait runWait;

	public static void main(String[] args) {
		//javoit.HotKeySet("a", "derp", javoit);
		javoit.RunWait("C:\\Share\\Desktop\\Mouse Cursor Position Tooltip.exe");
		System.out.println("Done");
	}

	public JavoIT() {

	}

	public void derp() {
		long start = System.currentTimeMillis();
		System.out.println(javoit.PixelChecksum(0, 0, 10, 10) + " " + (System.currentTimeMillis() - start));
	}

	/**
	 * Pauses execution for the specified milliseconds.
	 */
	public void sleep(long milliseconds) {
		Sleep sleep = new Sleep(milliseconds);
		sleep.pause();
	}

	public void HotKeySet(String hotKeys, String methodName, Object callingObject) {
		if (hotKeySet == null) {
			hotKeySet = new HotKeySet(hotKeys, methodName, callingObject);
		} else if (hotKeySet.getHotKeys() == hotKeys) {
			hotKeySet.setMethodName(methodName);
			hotKeySet.setCallingObject(callingObject);
		}
		hotKeySet.startListening();
	}

	public void MouseClick() {
		MouseClick("left", javoit.MouseGetPos(0), javoit.MouseGetPos(1), 10, 1);
	}

	public void MouseClick(String button) {
		MouseClick(button, javoit.MouseGetPos(0), javoit.MouseGetPos(1), 10, 1);
	}

	public void MouseClick(String button, int x, int y) {
		MouseClick(button, x, y, 10, 1);
	}

	public void MouseClick(String button, int x, int y, int speed) {
		MouseClick(button, x, y, speed, 1);
	}

	public void MouseClick(String button, int x, int y, int speed, int clicks) {
		if (mouseClick == null)
			mouseClick = new MouseClick(button, x, y, speed, clicks);
		else {
			mouseClick.setButton(button);
			mouseClick.setX(x);
			mouseClick.setY(y);
			mouseClick.setSpeed(speed);
			mouseClick.setClicks(clicks);
		}
		mouseClick.click();
	}

	public void MouseMove(int x, int y) {
		MouseMove(x, y, 10);
	}

	public void MouseMove(int x, int y, int speed) {
		if (mouseMove == null) {
			mouseMove = new MouseMove(x, y, speed);
		} else {
			mouseMove.setFinalMousePosition(new Point(x, y));
			if (mouseMove.getSpeed() != speed)
				mouseMove.setSpeed(speed);
			mouseMove.moveMouse();
		}
	}

	public void MouseClickDrag(String button, int x1, int y1, int x2, int y2) {
		MouseClickDrag(button, x1, y1, x2, y2, 10);
	}

	public void MouseClickDrag(String button, int x1, int y1, int x2, int y2, int speed) {
		if(mouseClickDrag == null)
			mouseClickDrag = new MouseClickDrag(button, x1, y1, x2, y2, speed);
		else {
			mouseClickDrag.setButton(button);
			mouseClickDrag.setX1(x1);
			mouseClickDrag.setY1(y1);
			mouseClickDrag.setX2(x2);
			mouseClickDrag.setY2(y2);
			mouseClickDrag.setSpeed(speed);
		}
		mouseClickDrag.clickDrag();
	}
	
	public int MsgBox(int flag, String title, String text){
		return MsgBox(flag, title, text, -1);
	}
	
	public <N extends Number> int MsgBox(int flag, String title, N text){
		return MsgBox(flag, title, text, -1);
	}
	
	public <N extends Number> int MsgBox(int flag, String title, N text, int timeout){
		return MsgBox(flag, title, text.toString(), timeout);
	}
	
	public int MsgBox(int flag, String title, String text, int timeout){
		if(msgBox == null){
			msgBox = new MsgBox(flag, title, text, timeout);
		} else {
			msgBox.resetData();
			msgBox.setFlag(flag);
			msgBox.setTitle(title);
			msgBox.setText(text);
			msgBox.setTimeout(timeout);
			msgBox.parseFlag();
		}
		msgBox.showMsgBox();
		return msgBox.getButtonReturnValue();
	}
	
	public int PixelGetColor(int x, int y){
		if(pixelGetColor == null){
			pixelGetColor = new PixelGetColor(x, y);
		} else {
			pixelGetColor.setX(x);
			pixelGetColor.setY(y);
		}
		return pixelGetColor.getHashCode();
	}
	
	public int MouseGetPos(int dimension){
		if(mouseGetPos == null){
			mouseGetPos = new MouseGetPos(dimension);
		} else {
			mouseGetPos.setMousePoint();
			mouseGetPos.setDimension(dimension);
		}
		return mouseGetPos.getPosition();
	}
	
	public int PixelChecksum(int left, int top, int right, int bottom){
		if(pixelChecksum == null){
			pixelChecksum = new PixelChecksum(left, top, right, bottom);
		} else {
			pixelChecksum.setLeft(left);
			pixelChecksum.setRight(right);
			pixelChecksum.setBottom(bottom);
			pixelChecksum.setTop(top);
		}
		return pixelChecksum.getChecksum();
	}
	
	public void Run(String filePath){
		if(run == null){
			run = new Run(filePath);
		} else {
			run.setFileToRun(filePath);
		}
		run.executeProgram();
	}
	
	public void RunWait(String program){
		if(runWait == null){
			runWait = new RunWait(program);
		} else {
			runWait.setProgram(program);
		}
		runWait.runProgram();
	}
}
