package frame;

import label.BackgroundLabel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import core.Driver;
import panel.DrawPane;
import panel.ShowPane;
import java.awt.Font;

public class GameFrame extends JFrame {
	private JPanel contentPane,cdPane,tipPane,infoPane,msgPane,roommatePane;
	private DrawPane drawPane;
	private ShowPane showPane;
	private JLabel cdLabel;
	private JLabel hpLabel,nickLabel,idLabel,levelLabel,expLabel;
	private JProgressBar expBar;
	private JLabel timeLabel,tipLabel,roundLabel;
	public JTextArea msgArea;
	private JScrollPane msgscroPane;
	private JTextField textField;
	private JButton sendButton;
	private Object[] EXIT_TIP = {"确认退出","继续游戏"};
	
	private Thread drawThr,showThr;
	
	public GameFrame() {
		setTitle("\u4F60\u753B\u6211\u731C-\u6E38\u620F");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800,600));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Driver.class.getResource("/image/Logo.png")));
		final GameFrame g = this;
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int i = JOptionPane.showOptionDialog(g, "<html>游戏仍在进行中！<br>确定退出游戏？<html>", "提示",JOptionPane.YES_NO_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, EXIT_TIP, EXIT_TIP[0]);
				if (i == JOptionPane.YES_OPTION){
					dispose();
					System.exit(0);
				}
			}
		});
		
		BackgroundLabel BGL = new BackgroundLabel(Driver.class.getResource("/image/GameBGP.jpg"),800,600);
		this.getRootPane().add(BGL,new Integer(Integer.MIN_VALUE));
		
		tipPane = new JPanel();
		tipPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tipPane.setBounds(10, 10, 780, 50);
		tipPane.setOpaque(false);
		tipPane.setLayout(null);
		contentPane.add(tipPane);
		
		timeLabel = new JLabel("60");
		timeLabel.setForeground(new Color(204, 0, 0));
		timeLabel.setBounds(10, 0, 60, 50);
		tipPane.add(timeLabel);
		timeLabel.setFont(new Font("宋体", Font.BOLD, 40));
		
		tipLabel = new JLabel("\u63D0\u793A\uFF1A3\u4E2A\u5B57");
		tipLabel.setFont(new Font("宋体", Font.BOLD, 18));
		tipLabel.setForeground(new Color(0, 153, 204));
		tipLabel.setBounds(280, 10, 140, 30);
		tipPane.add(tipLabel);
		
		roundLabel = new JLabel("\u7B2C2/3\u8F6E");
		roundLabel.setFont(new Font("宋体", Font.BOLD, 18));
		roundLabel.setBounds(660, 10, 90, 30);
		tipPane.add(roundLabel);
		
		cdPane = new JPanel();
		cdPane.setBounds(10, 70, 550, 320);
		cdPane.setLayout(null);
		contentPane.add(cdPane);
		
		cdLabel = new JLabel();
		cdLabel.setForeground(new Color(153, 0, 0));
		cdLabel.setFont(new Font("宋体", Font.BOLD, 99));
		cdLabel.setText("3");
		cdLabel.setBounds(240, 100, 100, 100);
		cdPane.add(cdLabel);
		
		drawPane = new DrawPane();
		drawPane.setLocation(10, 70);
		contentPane.add(drawPane);
		
		showPane = new ShowPane();
		showPane.setLocation(10, 70);
		contentPane.add(showPane);
		
		infoPane = new JPanel();
		infoPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u73A9\u5BB6\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 0, 0)));
		infoPane.setBounds(570, 70, 220, 180);
		infoPane.setOpaque(false);
		infoPane.setLayout(null);
		contentPane.add(infoPane);
		
		hpLabel = new JLabel("");
		hpLabel.setBounds(10, 20, 84, 84);
		hpLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		infoPane.add(hpLabel);
		
		nickLabel = new JLabel("New label");
		nickLabel.setBounds(100, 20, 100, 25);
		infoPane.add(nickLabel);
		
		idLabel = new JLabel("New label");
		idLabel.setBounds(100, 50, 100, 25);
		infoPane.add(idLabel);
		
		levelLabel = new JLabel("New label");
		levelLabel.setBounds(100, 80, 100, 25);
		infoPane.add(levelLabel);
		
		expLabel = new JLabel("Exp:");
		expLabel.setBounds(10, 110, 180, 25);
		infoPane.add(expLabel);
		
		expBar = new JProgressBar();
		expBar.setForeground(new Color(0, 204, 204));
		expBar.setBounds(10, 140, 200, 14);
		infoPane.add(expBar);
		
		msgPane = new JPanel();
		msgPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u804A\u5929\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 0, 0)));
		msgPane.setBounds(570, 260, 220, 330);
		msgPane.setOpaque(false);
		msgPane.setLayout(null);
		contentPane.add(msgPane);
		
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		msgArea.setOpaque(false);
		
		msgscroPane = new JScrollPane(msgArea);
		msgscroPane.setBounds(10, 20, 200, 260);
		msgscroPane.setOpaque(false);
		msgscroPane.getViewport().setOpaque(false);
		msgPane.add(msgscroPane);
		
		textField = new JTextField();
		textField.setBounds(10, 290, 160, 30);
		textField.setOpaque(false);
		msgPane.add(textField);
		textField.setColumns(10);
		
		sendButton = new JButton(new ImageIcon(Driver.class.getResource("/image/send.png")));
		sendButton.setBounds(175, 285, 35, 35);
		//sendButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sendButton.setContentAreaFilled(false);
		msgPane.add(sendButton);
		
		roommatePane = new JPanel();
		roommatePane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		roommatePane.setBounds(10, 400, 550, 190);
		roommatePane.setOpaque(false);
		contentPane.add(roommatePane);

		
		pack();
		setLocationRelativeTo(null);
	}
}
