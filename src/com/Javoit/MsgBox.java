package com.Javoit;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

class MsgBox {
	
	private String text = "";
	private String title = "";
	private JButton[] buttons = {new JButton("OK")};
	private TreeMap<Integer, Object> map;
	private Icon icon = null;
	private int defaultButton = 0;
	private int modal = 0;//TODO
	private boolean alwaysOnTop = false;
	private int componentAlignment = SwingConstants.LEFT;
	private int timeout;
	private GraphicsDevice activeScreen;
	private MsgBoxFrame msgBoxFrame;
	private boolean addHelp;
	private JavoIT javoit = new JavoIT();
	
	public static void main(String[] args){
		MsgBox msgBox = new MsgBox((MsgBoxConstants.MB_DEFBUTTON3 + MsgBoxConstants.MB_YESNOCANCEL + MsgBoxConstants.MB_ICONWARNING + MsgBoxConstants.MB_HELP), "Test Title", "The Quick Brown Fox Jumps all over "
				+ "the lazy shitty stupid bullshit dog and I am just typing a lot of stuff "
				+ "the lazy shitty stupid bullshit dog and I am just typing a lot of stuff "
				+ "hit.", 10);
		msgBox.showMsgBox();
		
		/*System.out.println("CANCEL_OPTION: " + JOptionPane.CANCEL_OPTION);
		System.out.println("CLOSED_OPTION: " + JOptionPane.CLOSED_OPTION);
		System.out.println("DEFAULT_OPTION: " + JOptionPane.DEFAULT_OPTION);
		System.out.println("OK_CANCEL_OPTION: " + JOptionPane.OK_CANCEL_OPTION);
		System.out.println("NO_OPTION: " + JOptionPane.NO_OPTION);
		System.out.println("OK_OPTION: " + JOptionPane.OK_OPTION);
		System.out.println("YES_NO_CANCEL_OPTION " + JOptionPane.YES_NO_CANCEL_OPTION);
		System.out.println("YES_NO_OPTION " + JOptionPane.YES_NO_OPTION);
		System.out.println("YES_OPTION" + JOptionPane.YES_OPTION);
		System.out.println();
		System.out.println("ERROR_MESSAGE: " + JOptionPane.ERROR_MESSAGE);
		System.out.println("INFORMATION_MESSAGE: " + JOptionPane.INFORMATION_MESSAGE);
		System.out.println("PLAIN_MESSAGE: " + JOptionPane.PLAIN_MESSAGE);
		System.out.println("QUESTION_MESSAGE: " + JOptionPane.QUESTION_MESSAGE);
		System.out.println("WARNING_MESSAGE: " + JOptionPane.WARNING_MESSAGE);
		
		CANCEL_OPTION: 2
		CLOSED_OPTION: -1
		DEFAULT_OPTION: -1
		OK_CANCEL_OPTION: 2
		NO_OPTION: 1
		OK_OPTION: 0
		YES_NO_CANCEL_OPTION 1
		YES_NO_OPTION 0
		YES_OPTION 0

		ERROR_MESSAGE: 0
		INFORMATION_MESSAGE: 1
		PLAIN_MESSAGE: -1
		QUESTION_MESSAGE: 3
		WARNING_MESSAGE: 2
		*/

	}
	
	MsgBox(int flag, String title, String text){
		this(flag, title, text, -1);
		
	}
	
	MsgBox(int flag, String title, String text, int timeout){
		this.title = title;
		this.text = text;
		this.timeout = timeout;
		buildTable();
		parseFlag(flag);
		msgBoxFrame = new MsgBoxFrame();
	}
	
	private void buildTable(){
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");
		JButton abort = new JButton("Abort");
		JButton retry = new JButton("Retry");
		JButton ignore = new JButton("Ignore");
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		JButton tryAgain = new JButton("Try Again");
		JButton continueButton = new JButton("Continue");
		JButton help = new JButton("Help");
		
		map = new TreeMap<>();
		//buttons
		map.put(0, new JButton[] {ok, help});
		map.put(1, new JButton[] {ok, cancel, help});
		map.put(2, new JButton[] {abort, retry, ignore, help});
		map.put(3, new JButton[] {yes, no, cancel, help});
		map.put(4, new JButton[] {yes, no, help});
		map.put(5, new JButton[] {retry, cancel, help});
		map.put(6, new JButton[] {cancel, tryAgain, continueButton, help});
		//icons
		map.put(16, new ImageIcon(this.getClass().getResource("Icons/stop.png")));
		map.put(32, new ImageIcon(this.getClass().getResource("Icons/question.png")));
		map.put(48, new ImageIcon(this.getClass().getResource("Icons/exclamation.png")));
		map.put(64, new ImageIcon(this.getClass().getResource("Icons/info.png")));
		map.put(16384, true);
		
		//default button
		map.put(256, 1);
		map.put(512, 2);
		map.put(768, 3);
		
		//modal
		map.put(4096, 1);
		map.put(8192, 2);
		
		//attripbutes
		map.put(65536, true);
		map.put(131072, 7); //TODO after creating JFrame
		map.put(262144, true);
		map.put(524288, Component.RIGHT_ALIGNMENT);	
	}
	
	private void parseFlag(int flag){
		if(flag >= 524288){
			componentAlignment = SwingConstants.RIGHT;
			parseFlag(flag - 524288);
		} else if (flag >= 262144){
			alwaysOnTop = true;
			parseFlag(flag - 262144);
		} else if (flag >= 131072){
			//TODO after creating JFrame
		} else if (flag >= 65536){
			alwaysOnTop = true;
			parseFlag(flag - 65536);
		}  else if (flag >= 16384){
			addHelp = true;
			parseFlag(flag - 16384);
		} else if (flag >= 4096){
			modal = (int) map.get(map.floorKey(flag));
			parseFlag(flag - map.floorKey(flag));
		} else if (flag >= 256){
			defaultButton = (int) map.get(map.floorKey(flag));
			parseFlag(flag - map.floorKey(flag));
		} else if (flag >= 16){
			System.out.println(map.floorKey(flag));
			icon = (Icon) map.get(map.floorKey(flag));
			parseFlag(flag - map.floorKey(flag));
		} else {
			buttons = (JButton[]) map.get(map.floorKey(flag));
			return;
		}
	}
	
	void showMsgBox(){
		msgBoxFrame.setVisible(true);		
		msgBoxFrame.getRootPane().setDefaultButton(buttons[defaultButton]);
		buttons[defaultButton].requestFocus();
		if(timeout > 0){
			javoit.sleep(timeout * 1000);
			msgBoxFrame.setVisible(false);
			msgBoxFrame.dispose();
		}
	}
	
	public class MsgBoxFrame extends JFrame {


		private static final long serialVersionUID = 4903069352269655465L;
		private JPanel contentPane;
		
		private final String html1 = "<html><body style='width: ";
        private final String html2 = "px'>";
		
		public MsgBoxFrame() {
			int width;
			int height = 150;

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] gd = ge.getScreenDevices();
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			setTitle(title);			
			setAlwaysOnTop(alwaysOnTop);
			
			

			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			contentPane.add(buttonPanel, BorderLayout.SOUTH);
			
			for(int i = 0; i < buttons.length; i++){ 
				if(i == buttons.length - 1 && !addHelp){
					break;
				}
				buttonPanel.add(buttons[i]);
			}
			
			JLabel iconLabel = new JLabel("");
			contentPane.add(iconLabel, BorderLayout.WEST);
			iconLabel.setIcon(icon);
			iconLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
			iconLabel.setVerticalAlignment(SwingConstants.TOP);
			
			JLabel textLabel = new JLabel("New label");
			contentPane.add(textLabel, BorderLayout.CENTER);
			textLabel.setText(html1 + "100%" + html2 + text);
			textLabel.setHorizontalAlignment(componentAlignment);
			
			width = (buttonPanel.getComponentCount() - 1) * 100;
			
			if(icon != null) width += 100;
			if(text.length() > 50) width += 200;
			if(text.length() > 210) height += ((text.length() / 80) * 21);
			
			for(int i = 0; i < title.toCharArray().length; i++) width += 5;
			setBounds(0, 0, width, height);
			setLocationRelativeTo(null);
		}

	}
	
}
