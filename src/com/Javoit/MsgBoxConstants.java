package com.Javoit;

public abstract class MsgBoxConstants {
	/**
	 * OK button
	 */
	public static final int MB_OK = 0;
	/**
	 * OK and Cancel
	 */
	public static final int MB_OKCANCEL = 1;
	/**
	 * Abort, Retry and Ignore
	 */
	public static final int MB_ABORTRETRYIGNORE = 2;
	/**
	 * Yes, No, and Cancel
	 */
	public static final int MB_YESNOCANCEL = 3;
	/**
	 * Yes and No
	 */
	public static final int MB_YESNO = 4;
	/**
	 * Retry and Cancel
	 */
	public static final int MB_RETRYCANCEL = 5;
	/**
	 * Cancel, Try Again, Continue
	 */
	public static final int MB_CANCELTRYCONTINUE = 6;
	/**
	 * Adds a Help button to themessage box. When the user clicks the Help button 
	 * or presses F1, the system sends a WM_HELP message to the owner.
	 */
	public static final int MB_HELP = 16384;
	/**
	 * Stop-sign icon
	 */
	public static final int MB_ICONERROR = 16;
	/**
	 * Question-mark icon
	 */
	public static final int MB_ICONQUESTION = 32;
	/**
	 * Exclamation-point icon
	 */
	public static final int MB_ICONWARNING = 48;
	/**
	 * Information-sign icon consisting of an 'i' in a circle
	 */
	public static final int MB_ICONINFORMATION = 64;
	/**
	 * First button is default
	 */
	public static final int MB_DEFBUTTON1 = 0;
	/**
	 * Second button is default button
	 */
	public static final int MB_DEFBUTTON2 = 256;
	/**
	 * Third button is default button
	 */
	public static final int MB_DEFBUTTON3 = 512;
	/**
	 * Fourth button is default buton
	 */
	public static final int MB_DEFBUTTON4 = 768;
	/**
	 * Application 0x0
	 */
	public static final int MB_APPLMODAL = 0;
	/**
	 * System modal (dialog has an icon)
	 */
	public static final int MB_SYSTEMMODAL = 4096;
	/**
	 * Task modal
	 */
	public static final int MB_TASKMODAL = 8192;
	/**
	 * MsgBox() shows on the desktop of the interactive window station
	 */
	public static final int MB_DEFAULT_DESKTOP_ONLY = 131072;
	/**
	 * Title and text are right-justified
	 */
	public static final int MB_RIGHT = 524288;
	/**
	 * Displays message and caption text using right-to-left reading order on Hebrew and Arabic Systems
	 */
	public static final int MB_RTLREADING = 1048576;
	/**
	 * The message box becomes the foreground window.
	 */
	public static final int MB_SETFOREGROUND = 65536;
	/**
	 * MsgBox() has top-most attribute set
	 */
	public static final int MB_TOPMOST = 262144;
	/**
	 * The function displays a message box on the current active desktop, even if there is no user logged 
	 * 	on to the computer.
	 */
	public static final int MB_SERVICE_NOTIFICATON = 2097152;
}
