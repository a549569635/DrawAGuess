package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import javax.swing.ListSelectionModel;

import core.Driver;
import warpper.*;
import label.*;
import panel.*;

public class HallFrame extends JFrame {
	private JPanel contentPane;
	private JPanel infoPane,msgPane;
	private JScrollPane usersPane,msgscroPane;
	private RoomListPane roomscroPane;
	private ReadyPane readyPane;
	private JProgressBar expBar;
	public JButton firstButton,joinButton,secondButton;
	public JTable usersTable;
	public DefaultTableModel userModel;
	private JTextField textField;
	private JLabel hpLabel,nickLabel,idLabel,levelLabel,expLabel;
	public JTextArea msgArea;
	public JComboBox aimBox;
	public DefaultComboBoxModel modellist;
	private JButton sendButton;
	private AudioClip BGMclip;
	private Object[] EXIT_TIP = {"确认退出","继续游戏"};
	private JPopupMenu userMenu = new JPopupMenu();
	private JMenuItem menuChat,menuInfo;
	
	public Thread userThr,roomThr,readyThr;
	
	private Boolean joined = false;
	private Boolean readyed = false;
	
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
		infoPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u73A9\u5BB6\u4FE1\u606F", 
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 0, 0)));
		infoPane.setBounds(10, 10, 200, 180);
		infoPane.setOpaque(false);
		infoPane.setLayout(null);
		contentPane.add(infoPane);
		
		hpLabel = new JLabel("");
		hpLabel.setBounds(10, 20, 84, 84);
		hpLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		infoPane.add(hpLabel);
		
		nickLabel = new JLabel("nick name");
		nickLabel.setFont(new Font("宋体", Font.BOLD, 16));
		nickLabel.setForeground(new Color(153, 0, 0));
		nickLabel.setBounds(100, 20, 100, 25);
		infoPane.add(nickLabel);
		
		idLabel = new JLabel("id: ");
		idLabel.setBounds(100, 50, 100, 25);
		infoPane.add(idLabel);
		
		levelLabel = new JLabel("level: ");
		levelLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		levelLabel.setForeground(new Color(255, 0, 0));
		levelLabel.setBounds(100, 80, 100, 25);
		infoPane.add(levelLabel);
		
		expLabel = new JLabel("Exp: ");
		expLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		expLabel.setForeground(new Color(204, 0, 153));
		expLabel.setBounds(10, 110, 180, 25);
		infoPane.add(expLabel);
		
		expBar = new JProgressBar();
		expBar.setForeground(new Color(0, 204, 204));
		expBar.setBounds(10, 140, 180, 14);
		infoPane.add(expBar);

		try {
			hpLabel.setIcon(new ImageIcon(Driver.SELF.getHPPath()));
			nickLabel.setText(Driver.SELF.getNickname());
			idLabel.setText(Driver.SELF.getID());
			levelLabel.setText("Level: "+Driver.SELF.getLevel());
			expLabel.setText("Exp: "+Driver.SELF.getExp()+"/100");
			expBar.setValue(Driver.SELF.getExp());
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		userModel = new DefaultTableModel(new Object[][] {},new String[] {"ID", "\u6635\u79F0", "Level"})
		    {boolean[] columnEditables = new boolean[] {false, false, false};
		        public boolean isCellEditable(int row, int column) {
			        return columnEditables[column];
		        }
		    }
		;
		usersTable = new JTable(userModel);
		//usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//usersTable.setEnabled(false);
		usersTable.setForeground(new Color(153, 0, 0));
		usersTable.setOpaque(false);
		usersTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		usersTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		usersTable.getColumnModel().getColumn(2).setPreferredWidth(23);
		usersTable.setRowHeight(20);
		
		usersPane = new JScrollPane(usersTable);
		usersPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		usersPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		usersPane.setBounds(10, 190, 200, 400);
		usersPane.setOpaque(false);
		usersPane.getViewport().setOpaque(false);
		contentPane.add(usersPane);
		
		menuChat = new JMenuItem("私聊");
		menuChat.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				if(usersTable.getSelectedRowCount() <= 0){
					JOptionPane.showMessageDialog(Driver.hallframe, "请选中要聊天的对象！");
					return;
				} else{
					Object obj = userModel.getValueAt(usersTable.getSelectedRow(), 0);
					if(!addAim(obj)){
						JOptionPane.showMessageDialog(Driver.hallframe, "聊天对象已存在下拉框中！");
					}
				}
			}
		});
		
		menuInfo = new JMenuItem("查看玩家信息");
		menuInfo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				if(usersTable.getSelectedRowCount() <= 0){
					JOptionPane.showMessageDialog(Driver.hallframe, "请选中要查看的对象！");
					return;
				} else{
					for(User user : Driver.ONLINE_USER){
						if(user.getID().equals(userModel.getValueAt(usersTable.getSelectedRow(), 0))){
							JFrame infoFrame = new JFrame("玩家信息");
							infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							infoFrame.getContentPane().add(new UserPane(user,1));
							infoFrame.pack();
							infoFrame.setLocationRelativeTo(Driver.hallframe);
							infoFrame.setVisible(true);
						}
					}
				}
			}
		});
		
		userMenu = new JPopupMenu();
		userMenu.add(menuChat);
		userMenu.add(menuInfo);
		
		usersTable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(e.getButton() == MouseEvent.BUTTON3){
					userMenu.show(usersTable,e.getX(),e.getY());
				}
			}
		});
		
		//roomlistPane = new JPanel();
		//roomlistPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//roomlistPane.setBounds(210, 10, 580, 400);
		//roomlistPane.setLayout(null);
		//roomlistPane.setOpaque(false);
		//roomlistPane.setVisible(false);
		
		roomscroPane = new RoomListPane();
		roomscroPane.setBounds(210, 10, 580, 380);
		contentPane.add(roomscroPane);
		
		msgPane = new JPanel();
		msgPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u804A\u5929\u4FE1\u606F", 
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 0, 0)));
		msgPane.setBounds(210, 390, 450, 200);
		msgPane.setOpaque(false);
		msgPane.setLayout(null);
		contentPane.add(msgPane);
		
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		msgArea.setOpaque(false);
		msgArea.setForeground(new Color(153, 51, 0));
		
		msgscroPane = new JScrollPane(msgArea);
		msgscroPane.setBounds(10, 20, 430, 115);
		msgscroPane.setOpaque(false);
		msgscroPane.getViewport().setOpaque(false);
		msgPane.add(msgscroPane);
		
		modellist = new DefaultComboBoxModel(new String[] {"\u6240\u6709\u4EBA"});
		
		aimBox = new JComboBox();
		aimBox.setMaximumRowCount(3);
		aimBox.setModel(modellist);
		aimBox.setBounds(10, 135, 100, 25);
		msgPane.add(aimBox);
		
		textField = new JTextField();
		textField.setBounds(10, 160, 380, 30);
		//textField.setOpaque(false);
		msgPane.add(textField);
		
		sendButton = new JButton(new ImageIcon(Driver.class.getResource("/image/send.png")));
		sendButton.setContentAreaFilled(false);
		sendButton.setBounds(400, 150, 40, 40);
		//sendButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		msgPane.add(sendButton);
		sendButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				String to = (String) aimBox.getSelectedItem();
				Message msg = new Message(Driver.SELF.getID(),to,textField.getText());
				Driver.client.SendMsg(new Protocol(7,msg));
				textField.setText("");
			}
		});
		
		firstButton = new JButton("<html>\u5FEB\u901F<br>\u52A0\u5165<html>");
		firstButton.setFont(new Font("幼圆", Font.BOLD, 30));
		firstButton.setForeground(new Color(153, 0, 0));
		firstButton.setBounds(670, 400, 120, 90);
		firstButton.setContentAreaFilled(false);
		contentPane.add(firstButton);
		firstButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				if(!joined){
					Boolean b = true;
					if(Driver.ONLINE_ROOM.size() > 0){
						for(Room room : Driver.ONLINE_ROOM.values()){
							if(room.getState() == Room.STATE_ACCESS){
								joinRoom(room);
								b = false;
								break;
							}
						}
					}
					if(b){
						JOptionPane.showMessageDialog(Driver.hallframe, "没有合适加入的房间！");
					}
				} else if(!Driver.SELF.getReadyed()){
					Driver.SELF.setReadyed(true);
					Driver.client.SendMsg(new Protocol(3,Driver.SELF));
					firstButton.setText("<html>取消<br>准备</html>");
				} else{
					Driver.SELF.setReadyed(false);
					Driver.client.SendMsg(new Protocol(3,Driver.SELF));
					firstButton.setText("准备");
				}
			}
		});
		
		joinButton = new JButton("\u52A0\u5165\u623F\u95F4");
		joinButton.setForeground(new Color(153, 51, 0));
		joinButton.setFont(new Font("幼圆", Font.BOLD, 18));
		joinButton.setContentAreaFilled(false);
		joinButton.setBounds(670, 500, 120, 40);
		//joinButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		joinButton.setEnabled(false);
		contentPane.add(joinButton);
		joinButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				joinRoom(roomscroPane.selectedRoom);
			}
		});
		
		secondButton = new JButton("\u521B\u5EFA\u623F\u95F4");
		secondButton.setForeground(new Color(153, 0, 0));
		secondButton.setFont(new Font("幼圆", Font.BOLD, 20));
		secondButton.setContentAreaFilled(false);
		contentPane.add(secondButton);
		secondButton.setBounds(670, 550, 120, 40);
		//creatButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		secondButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(!joined){
					creatRoom();
				}else{
					quitReady();
				}
			}
		});

		pack();
		setLocationRelativeTo(null);
		
		userThr = new Thread(new UserRunnable());
		roomThr = new Thread(roomscroPane);
		userThr.start();
		roomThr.start();
	}
	
	public void joinRoom(Room room){
		Driver.client.SendMsg(new Protocol(5,room));
	}
	
	private void creatRoom(){
		String str = JOptionPane.showInputDialog("<html>请输入一个房间名<br>或直接使用默认值</html>", "娱乐天空");
//System.out.println(str);
		if(str != null){
			Room room = new Room(str);
			room.addUser(Driver.SELF);
			Driver.client.SendMsg(new Protocol(6,room));
		}
	}
	
	public void joinReady(Room room){
		Driver.SELF_ROOM = room;
		roomscroPane.run = false;
		roomThr = null;
		readyPane = new ReadyPane();
		readyPane.setBounds(210, 10, 580, 380);
		contentPane.add(readyPane);
		readyThr = new Thread(readyPane);
		readyThr.start();
		
		roomscroPane.setVisible(false);
		readyPane.setVisible(true);
		joined = true;
		firstButton.setText("准备");
		joinButton.setText("");
		joinButton.setVisible(false);
		secondButton.setText("退出房间");
	}
	
	private void quitReady(){
		Driver.SELF_ROOM = null;
		Driver.client.SendMsg(new Protocol(22,null));
		readyPane.setVisible(false);
		readyPane = null;
		roomscroPane.setVisible(true);
		joined = false;
		firstButton.setText("<html>\u5FEB\u901F<br>\u52A0\u5165<html>");
		joinButton.setText("\u52A0\u5165\u623F\u95F4");
		joinButton.setVisible(true);
		secondButton.setText("\u521B\u5EFA\u623F\u95F4");
		readyThr = null;
		roomscroPane.run = true;
		roomThr = new Thread(roomscroPane);
		roomThr.start();
	}
	
	public boolean addAim(Object obj){
		for(int i = 0;i < modellist.getSize();i++){
			if (modellist.getElementAt(i).equals(obj)){
				return false;
			}
		}
		modellist.addElement(obj);
		aimBox.setSelectedItem(obj);
		return true;
	}
	
	private class UserRunnable implements Runnable{
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true){
				//if(Driver.socket!=null){
				//break;
				// }
				if(Driver.ONLINE_USER.size() > 0){
					userModel.setRowCount(0);				
					for(User user:Driver.ONLINE_USER){
						if(user.getID().equals(Driver.SELF.getID())){
							if(!user.equals(Driver.SELF)){
								try {
									Driver.SELF = user;
									hpLabel.setIcon(new ImageIcon(Driver.SELF.getHPPath()));
									nickLabel.setText(Driver.SELF.getNickname());
									idLabel.setText(Driver.SELF.getID());
									levelLabel.setText("Level: "+Driver.SELF.getLevel());
									expLabel.setText("Exp: "+Driver.SELF.getExp()+"/100");
									expBar.setValue(Driver.SELF.getExp()%100);
								} catch (Exception e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
							}
						} else{
							userModel.addRow(new Object[]{user.getID(),user.getNickname(),user.getLevel()});
						}
					}
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	
	private class MsgRunnable implements Runnable{
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true){
				
			}
		}
	}
}
