package com.Javoit;

import java.util.Hashtable;

/*
 * Used to get a unique and platform independent ID for every keystroke. 
 * It also takes into account what NativeKeyEvent.getKeyText(e.getKeyCode()) 
 * returns
 */
class KeyTextToUniqueID {
	private Hashtable<String, Integer> numTable;

	public static void main(String[] args) {
		KeyTextToUniqueID kttuid = new KeyTextToUniqueID();
		System.out.println(kttuid.getUniqueID("Page Up"));
	}

	KeyTextToUniqueID() {
		numTable = new Hashtable<>();
		buildTable();
	}

	/**
	 * 
	 * @param keyText The string to be ID'd
	 * @return The Unique ID for the keyText
	 */
	int getUniqueID(String keyText) {
		return (numTable.get(keyText));
	}

	private void buildTable() {
		numTable.put("Escape", 0);
		numTable.put("{ESC}", 0);
		numTable.put("F1", 1);
		numTable.put("{F1}", 1);
		numTable.put("F2", 2);
		numTable.put("{F2}", 2);
		numTable.put("F3", 3);
		numTable.put("{F3}", 3);
		numTable.put("F4", 4);
		numTable.put("{F4}", 4);
		numTable.put("F5", 5);
		numTable.put("{F5}", 5);
		numTable.put("F6", 6);
		numTable.put("{F6}", 6);
		numTable.put("F7", 7);
		numTable.put("{F7}", 7);
		numTable.put("F8", 8);
		numTable.put("{F8}", 8);
		numTable.put("F9", 9);
		numTable.put("{F9}", 9);
		numTable.put("F10", 10);
		numTable.put("{F10}", 10);
		numTable.put("F11", 11);
		numTable.put("{F11}", 11);
		numTable.put("F12", 12);
		numTable.put("Print Screen", 13);
		numTable.put("{PRINTSCREEN}", 13);
		numTable.put("Scroll Lock", 14);
		numTable.put("{SCROLLLOCK}", 14);
		numTable.put("Pause", 15);
		numTable.put("{PAUSE}", 15);
		numTable.put("`", 16);
		numTable.put("Back Quote", 16);
		numTable.put("~", 16);
		numTable.put("1", 17); 
		numTable.put("!", 78); //same as alt
		numTable.put("2", 18);
		numTable.put("@", 18);
		numTable.put("3", 19);
		numTable.put("#", 77);//same as left meta
		numTable.put("4", 20);
		numTable.put("$", 20);
		numTable.put("5", 21);
		numTable.put("%", 21);
		numTable.put("6", 22);
		numTable.put("^", 76); //The same as ctrl
		numTable.put("7", 23);
		numTable.put("&", 23);
		numTable.put("8", 24);
		numTable.put("*", 24);
		numTable.put("9", 25);
		numTable.put("(", 25);
		numTable.put("0", 26);
		numTable.put(")", 26);
		numTable.put("-", 27);
		numTable.put("Minus", 27);
		numTable.put("_", 27);
		numTable.put("=", 28);
		numTable.put("Equals", 28);
		numTable.put("+", 63); //same as shift
		numTable.put("Backspace", 29);
		numTable.put("{BACKSPACE}", 29);
		numTable.put("{BS}", 29);
		numTable.put("Insert", 30);
		numTable.put("{INSERT}", 30);
		numTable.put("{INS}", 30);
		numTable.put("Home", 31);
		numTable.put("{HOME}", 31);
		numTable.put("Page Up", 32);
		numTable.put("{PGUP}", 32);
		numTable.put("Tab", 33);
		numTable.put("{TAB}", 33);
		numTable.put("q", 34);
		numTable.put("Q", 34);
		numTable.put("w", 35);
		numTable.put("W", 35);
		numTable.put("e", 36);
		numTable.put("E", 36);
		numTable.put("r", 37);
		numTable.put("R", 37);
		numTable.put("t", 38);
		numTable.put("T", 38);
		numTable.put("y", 39);
		numTable.put("Y", 39);
		numTable.put("u", 40);
		numTable.put("U", 40);
		numTable.put("i", 41);
		numTable.put("I", 41);
		numTable.put("o", 42);
		numTable.put("O", 42);
		numTable.put("p", 43);
		numTable.put("P", 43);
		numTable.put("[", 44);
		numTable.put("Open Bracket", 44);
		numTable.put("{", 44);
		numTable.put("]", 45);
		numTable.put("Close Bracket", 45);
		numTable.put("}", 45);
		numTable.put("\\", 46);
		numTable.put("Back Slash", 46);
		numTable.put("|", 46);
		numTable.put("Delete", 47);
		numTable.put("{DELETE}", 47);
		numTable.put("{DEL}", 47);
		numTable.put("End", 48);
		numTable.put("{END}", 48);
		numTable.put("Page Down", 49);
		numTable.put("{PGDN}", 49);
		numTable.put("Caps Lock", 50);
		numTable.put("{CAPSLOCK}", 50);
		numTable.put("a", 51);
		numTable.put("A", 51);
		numTable.put("s", 52);
		numTable.put("S", 52);
		numTable.put("d", 53);
		numTable.put("D", 53);
		numTable.put("f", 54);
		numTable.put("F", 54);
		numTable.put("g", 55);
		numTable.put("G", 55);
		numTable.put("h", 56);
		numTable.put("H", 56);
		numTable.put("j", 57);
		numTable.put("J", 57);
		numTable.put("k", 58);
		numTable.put("K", 58);
		numTable.put("l", 59);
		numTable.put("L", 59);
		numTable.put(";", 60);
		numTable.put("Semicolon", 60);
		numTable.put(":", 60);
		numTable.put("'", 61);
		numTable.put("Quote", 61);
		numTable.put("\"", 61);
		numTable.put("Enter", 62);
		numTable.put("Left Shift", 63);
		numTable.put("z", 64);
		numTable.put("Z", 64);
		numTable.put("x", 65);
		numTable.put("X", 65);
		numTable.put("c", 66);
		numTable.put("C", 66);
		numTable.put("v", 67);
		numTable.put("V", 67);
		numTable.put("b", 68);
		numTable.put("B", 68);
		numTable.put("n", 69);
		numTable.put("N", 69);
		numTable.put("m", 70);
		numTable.put("M", 70);
		numTable.put(",", 71);
		numTable.put("Comma", 71);
		numTable.put("<", 71);
		numTable.put(".", 72);
		numTable.put("Period", 72);
		numTable.put(">", 72);
		numTable.put("/", 73);
		numTable.put("Slash", 73);
		numTable.put("?", 73);
		numTable.put("Right Shift", 63); //same as left shift
		numTable.put("Up", 75);
		numTable.put("{UP}", 75);
		numTable.put("Left Control", 76);
		numTable.put("Left Meta", 77);
		numTable.put("Left Alt", 78);
		numTable.put("{ALT}", 78);
		numTable.put("Space", 79);
		numTable.put("{SPACE}", 79);
		numTable.put("Right Alt", 78); 
		numTable.put("Right Control", 76);//both controls on one key
		numTable.put("Left", 82);
		numTable.put("{LEFT}", 82);
		numTable.put("Down", 83);
		numTable.put("{DOWN}", 83);
		numTable.put("Right", 84);
		numTable.put("{RIGHT}", 84);
		numTable.put("Num Lock", 85);
		numTable.put("{NUMLOCK}", 85);
		numTable.put("NumPad Divide", 86);
		numTable.put("{NUMPADDIV}", 86);
		numTable.put("NumPad Multiply", 87);
		numTable.put("{NUMPADMULT}", 87);
		numTable.put("NumPad Subtract", 88);
		numTable.put("{NUMPADSUB}", 88);
		numTable.put("NumPad 7", 89);
		numTable.put("{NUMPAD7}", 89);
		numTable.put("NumPad 8", 90);
		numTable.put("{NUMPAD8}", 90);
		numTable.put("NumPad 9", 91);
		numTable.put("{NUMPAD9}", 91);
		numTable.put("NumPad Add", 92);
		numTable.put("{NUMPADADD}", 92);
		numTable.put("NumPad 4", 93);
		numTable.put("{NUMPAD4}", 93);
		numTable.put("NumPad 5", 94);
		numTable.put("{NUMPAD5}", 94);
		numTable.put("NumPad 6", 95);
		numTable.put("{NUMPAD6}", 95);
		numTable.put("NumPad 1", 96);
		numTable.put("{NUMPAD1}", 96);
		numTable.put("NumPad 2", 97);
		numTable.put("{NUMPAD2}", 97);
		numTable.put("NumPad 3", 98);
		numTable.put("{NUMPAD3}", 98);
		numTable.put("NumPad 0", 99);
		numTable.put("{NUMPAD0}", 99);
		numTable.put("NumPad Separator", 100);
		numTable.put("{NUMPADDOT}", 100);
		numTable.put("NumPad Enter", 62); //same as enter
		numTable.put("{NUMPADENTER}", 62);
	}

}
