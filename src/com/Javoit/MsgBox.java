package com.Javoit;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
	private ModalityType modal = Dialog.ModalityType.MODELESS;
	private boolean alwaysOnTop = false;
	private int componentAlignment = SwingConstants.LEFT;
	private int timeout;
	private Window activeWindow = null;
	private MsgBoxFrame msgBoxFrame;
	private boolean addHelp;
	private JavoIT javoit = new JavoIT();
	
	public static void main(String[] args){
		MsgBox msgBox = new MsgBox(0, "Title" , "Knowing that millions of "
				+ "people around the world would be watching in person and "
				+ "on television and expecting great things from him — at "
				+ "least one more gold medal for America, if not another "
				+ "world record — during this, his fourth and surely his "
				+ "last appearance in the World Olympics, and realizing "
				+ "that his legs could no longer carry him down the runway "
				+ "with the same blazing speed and confidence in making a "
				+ "huge, eye-popping leap that they were capable of a few "
				+ "years ago when he set world records in the 100-meter dash "
				+ "and in the 400-meter relay and won a silver medal in the "
				+ "long jump, the renowned sprinter and track-and-field "
				+ "personality Carl Lewis, who had known pressure from fans "
				+ "and media before but never, even as a professional runner, "
				+ "this kind of pressure, made only a few appearances in races "
				+ "during the few months before the Summer Olympics in Atlanta, "
				+ "Georgia, partly because he was afraid of raising expectations "
				+ "even higher and he did not want to be distracted by "
				+ "interviews and adoring fans who would follow him into stores"
				+ " and restaurants demanding autographs and photo-opportunities,"
				+ " but mostly because he wanted to conserve his energies and "
				+ "concentrate, like a martial arts expert, on the job at hand:"
				+ " winning his favorite competition, the long jump, and "
				+ "bringing home another Gold Medal for the United States, "
				+ "the most fitting conclusion to his brilliant career in "
				+ "track and field.");
		msgBox.showMsgBox();
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
		map.put(4096, Dialog.ModalityType.APPLICATION_MODAL);
		map.put(8192, Dialog.ModalityType.TOOLKIT_MODAL);
		
		//attributes
		map.put(65536, true);
		//map.put(131072, 7); 
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
		} else if (flag >= 65536){
			alwaysOnTop = true;
			parseFlag(flag - 65536);
		}  else if (flag >= 16384){
			addHelp = true;
			parseFlag(flag - 16384);
		} else if (flag >= 4096){
			modal = (ModalityType) map.get(map.floorKey(flag));
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
	
	public class MsgBoxFrame extends JDialog {

		private static final long serialVersionUID = 4903069352269655465L;
		private JPanel contentPane;
		
		private final String html1 = "<html><body style='width: ";
        private final String html2 = "px'>";
		
		public MsgBoxFrame() {
			int width = 250;
			int height = 150;
			
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			setTitle(title);			
			setAlwaysOnTop(alwaysOnTop);
			setModalityType(modal);

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
			
			
			if(icon != null) width += 100;
			if(text.length() > 50) width += 200;
			if(text.length() > 210) height += ((text.length() / 106) * 20);
			System.out.println(textLabel.getText().length());
			for(int i = 0; i < title.toCharArray().length; i++) width += 5;
			setBounds(0, 0, width, height);
			setLocationRelativeTo(activeWindow);
		}

	}
	
}
