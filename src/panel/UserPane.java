package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

import core.Driver;
import warpper.User;

public class UserPane extends JPanel {
	private JLabel hpLabel,nickLabel,idLabel,levelLabel,expLabel,bgLabel;
	private JProgressBar expBar;
	public JLabel infoLabel;

	public UserPane(User user,int style) {
		super();
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.setOpaque(false);
		this.setLayout(null);
		if(style == 1){
			this.setPreferredSize(new Dimension(180,320));
		}else if(style == 2){
			this.setPreferredSize(new Dimension(130,190));
		}
		
		if(user == null){
			bgLabel = new JLabel(new ImageIcon(Driver.class.getResource("/image/emptyBGP.png")));
			bgLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
			add(bgLabel);
		}else{
			hpLabel = new JLabel();
			hpLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			try {
				hpLabel.setIcon(new ImageIcon(user.getHPPath()));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			add(hpLabel);
			
			nickLabel = new JLabel(user.getNickname());
			nickLabel.setFont(new Font("宋体", Font.BOLD, 20));
			nickLabel.setForeground(new Color(153, 0, 0));
			add(nickLabel);
			
			idLabel = new JLabel("ID: "+user.getID());
			add(idLabel);
			
			levelLabel = new JLabel("level: "+user.getLevel());
			levelLabel.setFont(new Font("宋体", Font.PLAIN, 16));
			levelLabel.setForeground(new Color(255, 0, 0));
			add(levelLabel);
			
			expLabel = new JLabel("Exp: "+user.getExp());
			expLabel.setFont(new Font("宋体", Font.PLAIN, 16));
			expLabel.setForeground(new Color(204, 0, 153));
			add(expLabel);
			
			expBar = new JProgressBar();
			expBar.setForeground(new Color(0, 204, 204));
			expBar.setValue(user.getExp());
			add(expBar);

			infoLabel = new JLabel();
			infoLabel.setFont(new Font("幼圆", Font.BOLD, 20));
			infoLabel.setForeground(Color.GREEN);
			add(infoLabel);
			
			if(style == 1){
				hpLabel.setBounds(48, 20, 84, 84);//(48, 20);
				nickLabel.setBounds(20, 120, 140, 30);//.setLocation(20, 120);
				idLabel.setBounds(20, 160, 140, 20);//.setLocation(20, 160);
				levelLabel.setBounds(20, 190, 80, 25);//.setLocation(20, 190);
				expLabel.setBounds(20, 225, 80, 25);//.setLocation(20, 225);
				expBar.setBounds(10, 250, 160, 12);//.setLocation(10, 250);
				infoLabel.setBounds(40, 275, 100, 30);//.setLocation(40, 280);
				if(user.getReadyed()){
					infoLabel.setText("准备");
				}else{
					infoLabel.setForeground(Color.DARK_GRAY);
					infoLabel.setText("未准备");
				}
			} else if(style == 2){
				hpLabel.setBounds(23, 5, 84, 84);//.setLocation(23, 5);
				nickLabel.setBounds(10, 95, 140, 25);//.setLocation(10, 95);
				nickLabel.setFont(new Font("宋体", Font.BOLD, 16));
				idLabel.setBounds(10, 125, 140, 20);//.setLocation(10, 120);
				levelLabel.setBounds(10, 150, 80, 20);//.setLocation(10, 140);
				levelLabel.setFont(new Font("宋体", Font.PLAIN, 14));
				expLabel.setVisible(false);
				expBar.setVisible(false);
				infoLabel.setBounds(90, 150, 30, 30);//.setLocation(85, 140);
			}
		}
	}
}
