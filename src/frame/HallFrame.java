package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import core.Driver;
import warpper.*;
import label.*;
import javax.swing.ListSelectionModel;

public class HallFrame extends JFrame {
	private JPanel contentPane;
	private JPanel infoPane;
	private JScrollPane usersPane;
	private JPanel roomlistPane;
	private JPanel readyPane;
	private JPanel msgPane;
	private JProgressBar expBar;
	private JButton joinButton;
	private JButton creatButton;
	private JTable usersTable;
	private DefaultTableModel userModel;
	private JScrollPane msgscroPane;
	private JTextField textField;
	private JLabel hpLabel;
	private JLabel nickLabel;
	private JLabel idLabel;
	private JLabel levelLabel;
	private JLabel expLabel;
	private JTextArea msgArea;
	private JComboBox aimBox;
	private DefaultComboBoxModel modellist;
	private JButton sendButton;
	private User[] roomMates = new User[4];
	private UserLabel[] userLabel = new UserLabel[4];
	private ArrayList<RoomLabel> roomList = new ArrayList<RoomLabel>();
	private AudioClip BGMclip;
	private Object[] EXIT_TIP = {"确认退出","继续游戏"};
	
	public HallFrame() {
		setTitle("\u4F60\u753B\u6211\u731C-\u6E38\u620F\u5927\u5385");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800,600));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Driver.class.getResource("/image/Logo.png")));
		getRootPane().setDefaultButton(sendButton);
		final HallFrame h = this;
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int i = JOptionPane.showOptionDialog(h, "确定退出游戏么？", "提示",JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, EXIT_TIP, EXIT_TIP[0]);
				if (i == JOptionPane.YES_OPTION){
					dispose();
					System.exit(0);
				}
			}
		});
		
		BGMclip = Applet.newAudioClip(Driver.class.getResource("/music/HallBGM.wav"));
		BGMclip.loop();
		
		BackgroundLabel BGL = new BackgroundLabel(Driver.class.getResource("/image/HallBGP.jpg"),800,600);
		this.getRootPane().add(BGL,new Integer(Integer.MIN_VALUE));
		
		infoPane = new JPanel();
		infoPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u73A9\u5BB6\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 0, 0)));
		infoPane.setBounds(10, 10, 200, 180);
		infoPane.setOpaque(false);
		infoPane.setLayout(null);
		contentPane.add(infoPane);
		
		hpLabel = new JLabel("");
		hpLabel.setBounds(10, 20, 84, 84);
		hpLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		infoPane.add(hpLabel);
		
		nickLabel = new JLabel("nick name");
		nickLabel.setBounds(100, 20, 100, 25);
		infoPane.add(nickLabel);
		
		idLabel = new JLabel("id");
		idLabel.setBounds(100, 50, 100, 25);
		infoPane.add(idLabel);
		
		levelLabel = new JLabel("level");
		levelLabel.setBounds(100, 80, 100, 25);
		infoPane.add(levelLabel);
		
		expLabel = new JLabel("Exp:");
		expLabel.setBounds(10, 110, 180, 25);
		infoPane.add(expLabel);
		
		expBar = new JProgressBar();
		expBar.setForeground(new Color(0, 204, 204));
		expBar.setBounds(10, 140, 180, 14);
		infoPane.add(expBar);

		nickLabel.setText(Driver.self.getNickname());
		idLabel.setText(Driver.self.getID());
		levelLabel.setText("Level"+Driver.self.getLevel());
		expLabel.setText("Exp:"+Driver.self.getExp()+"/100");
		expBar.setValue(Driver.self.getExp()%100);
		
		userModel = new DefaultTableModel(new Object[][] {},new String[] {"\u7528\u6237\u6635\u79F0", "\u7B49\u7EA7"});
		usersTable = new JTable();
		usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersTable.setCellEditor(null);
		//usersTable.setEnabled(false);
		usersTable.setForeground(new Color(153, 0, 0));
		//usersTable
		usersTable.setModel(userModel);
		usersTable.setRowHeight(20);
		
		usersPane = new JScrollPane(usersTable);
		usersPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		usersPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		usersPane.setBounds(10, 190, 200, 400);
		usersPane.setOpaque(false);
		usersPane.getViewport().setOpaque(false);
		contentPane.add(usersPane);
		
		roomlistPane = new JPanel();
		roomlistPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		roomlistPane.setBounds(210, 10, 580, 400);
		roomlistPane.setOpaque(false);
		roomlistPane.setVisible(false);
		contentPane.add(roomlistPane);
		
		msgPane = new JPanel();
		msgPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u804A\u5929\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 0, 0)));
		msgPane.setBounds(210, 410, 450, 180);
		msgPane.setOpaque(false);
		msgPane.setLayout(null);
		contentPane.add(msgPane);
		
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		msgArea.setOpaque(false);
		
		msgscroPane = new JScrollPane(msgArea);
		msgscroPane.setBounds(10, 20, 430, 95);
		msgscroPane.setOpaque(false);
		msgscroPane.getViewport().setOpaque(false);
		msgPane.add(msgscroPane);
		
		modellist = new DefaultComboBoxModel(new String[] {"\u6240\u6709\u4EBA"});
		
		aimBox = new JComboBox();
		aimBox.setModel(modellist);
		aimBox.setBounds(10, 115, 100, 25);
		msgPane.add(aimBox);
		
		textField = new JTextField();
		textField.setBounds(10, 140, 380, 30);
		//textField.setOpaque(false);
		msgPane.add(textField);
		
		sendButton = new JButton(new ImageIcon(Driver.class.getResource("/image/send.png")));
		sendButton.setContentAreaFilled(false);
		sendButton.setBounds(400, 130, 40, 40);
		//sendButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		msgPane.add(sendButton);
		
		joinButton = new JButton("<html>\u52A0\u5165<br>\u623F\u95F4</html>");
		joinButton.setForeground(new Color(153, 0, 0));
		joinButton.setFont(new Font("幼圆", Font.BOLD, 35));
		joinButton.setContentAreaFilled(false);
		joinButton.setBounds(670, 420, 120, 100);
		//joinButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		joinButton.setEnabled(false);
		contentPane.add(joinButton);
		joinButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				joinRoom();
			}
		});
		
		creatButton = new JButton("\u521B\u5EFA\u623F\u95F4");
		creatButton.setForeground(new Color(153, 51, 0));
		creatButton.setFont(new Font("幼圆", Font.BOLD, 20));
		creatButton.setContentAreaFilled(false);
		contentPane.add(creatButton);
		creatButton.setBounds(670, 530, 120, 60);
		//creatButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		creatButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				creatRoom();
			}
		});
		
		pack();
		setLocationRelativeTo(null);
		
		new Thread(new HallRunnable()).start();
	}
	
	private void joinRoom(){
		readyPane = new JPanel();
		readyPane.setBounds(210, 10, 580, 400);
		readyPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		readyPane.setLayout(null);
		readyPane.setOpaque(false);
		contentPane.add(readyPane);
		//readyPane.setVisible(false);
		
		int i = 0;
		for(User us:roomMates){
			userLabel[i] = new UserLabel(us);
		}
		
	}
	
	private void creatRoom(){
		
	}
	
	private class HallRunnable implements Runnable{
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true){
				//if(Driver.socket!=null){
				//break;
				// }
				if(Driver.ONLINE_USER.size()>0){
					userModel.setRowCount(0);				
					//循环所有在线用户列表，添加到下拉框中 
					for(User user:Driver.ONLINE_USER){
						if(user.getID().equals(Driver.self.getID())){
							if(!user.equals(Driver.self)){
								Driver.self = user;
								nickLabel.setText(Driver.self.getNickname());
								idLabel.setText(Driver.self.getID());
								levelLabel.setText("Level"+Driver.self.getLevel());
								expLabel.setText("Exp:"+Driver.self.getExp()+"/100");
								expBar.setValue(Driver.self.getExp()%100);
							}
						} else{
							userModel.addRow(new Object[]{user.getNickname(),user.getLevel()});
						}
					}
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
