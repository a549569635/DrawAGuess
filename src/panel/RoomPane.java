package panel;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Driver;
import warpper.Room;

import java.awt.Font;
import java.awt.Color;

import javax.swing.border.BevelBorder;

public class RoomPane extends JPanel {
	private JLabel hpLabel,roomIDLabel,roomNameLabel,userNumLabel,roomStateLabel;
	private ImageIcon accessTip,playingTip,fullTip;
	
	public Room room;
	
	public RoomPane(Room room){
		this.room = room;
		//try {
			//accessTip = new ImageIcon(Driver.class.getResource("/image/roomATip.png"));
			//playingTip = new ImageIcon(Driver.class.getResource("/image/roomATip.png"));
			//fullTip = new ImageIcon(Driver.class.getResource("/image/roomATip.png"));
		//} catch (Exception e) {
			// TODO 自动生成的 catch 块
			//e.printStackTrace();
		//}
		
		//this.setPreferredSize(new Dimension(280,180));
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(null);
		setOpaque(false);
		if(room.getState() != Room.STATE_ACCESS){
			setEnabled(false);
		}
		
		try {
			hpLabel = new JLabel(new ImageIcon(room.user.get(0).getHPPath()));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
//System.out.println("1111111");
		hpLabel.setBounds(30, 40, 84, 84);
		hpLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		hpLabel.setOpaque(false);
		add(hpLabel);
		
		roomIDLabel = new JLabel("ID: "+room.getID());
		roomIDLabel.setFont(new Font("幼圆", Font.PLAIN, 14));
		roomIDLabel.setBounds(140, 20, 130, 20);
		roomIDLabel.setOpaque(false);
		add(roomIDLabel);
		
		roomNameLabel = new JLabel(room.getName());
		roomNameLabel.setForeground(new Color(153, 0, 0));
		roomNameLabel.setFont(new Font("幼圆", Font.BOLD, 22));
		roomNameLabel.setBounds(140, 50, 130, 30);
		roomNameLabel.setOpaque(false);
		add(roomNameLabel);
		
		userNumLabel = new JLabel(room.getUserNum()+"/4");
		userNumLabel.setFont(new Font("幼圆", Font.BOLD, 16));
		userNumLabel.setBounds(200, 90, 50, 20);
		userNumLabel.setOpaque(false);
		add(userNumLabel);
		
		roomStateLabel = new JLabel();
		roomStateLabel.setBounds(150, 130, 120, 30);
		roomStateLabel.setOpaque(false);
		add(roomStateLabel);
		
		if(room.getState() == Room.STATE_ACCESS){
			roomStateLabel.setFont(new Font("幼圆", Font.BOLD, 20));
			roomStateLabel.setText("\u53EF\u52A0\u5165");
			roomStateLabel.setForeground(new Color(0, 255, 51));
			//roomStateLabel.setIcon(sccessTip);
		} else if(room.getState() == Room.STATE_PLAYING){
			roomStateLabel.setFont(new Font("幼圆", Font.BOLD, 20));
			roomStateLabel.setText("\u6E38\u620F\u4E2D");
			roomStateLabel.setForeground(new Color(255, 0, 51));
			//roomStateLabel.setIcon(playingTip);
		} else if(room.getState() == Room.STATE_FULL){
			roomStateLabel.setFont(new Font("幼圆", Font.BOLD, 20));
			roomStateLabel.setText("\u623F\u95F4\u5DF2\u6EE1");
			roomStateLabel.setForeground(new Color(0, 51, 255));
			//roomStateLabel.setIcon(fullTip);
		}
		
	}

}
