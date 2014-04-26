package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.*;

import core.Driver;

import java.awt.Toolkit;

import label.BackgroundLabel;

public class LinkFrame extends JFrame {

	private BackgroundLabel LinkBGL;
	private BackgroundLabel LinkingBGL;
	private JPanel contentPane;
	private JPanel LinkingPane;
	private JLabel LinkingLabel;
	private JTextField IPField;
	private JButton SubButton;
	private JButton LinkingButton;
	
	private URL BGMurl;
	private AudioClip BGMclip;
	private Boolean Linked = false;
	private String ip;
	
	public LinkFrame() {
		setTitle("你画我猜-Link");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		try {
			BGMurl = new File("src/music/LinkBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO 自动生成的 catch 块
		}
		BGMclip = Applet.newAudioClip(BGMurl);
		
		LinkBGL = new BackgroundLabel("src/image/LinkBGP.jpg",300,200);
		LinkingBGL = new BackgroundLabel("src/image/LinkingBGP.gif",300,200);
		this.getRootPane().add(LinkBGL,new Integer(Integer.MIN_VALUE));
		this.getRootPane().add(LinkingBGL,new Integer(Integer.MIN_VALUE));
		LinkBGL.setVisible(true);
		LinkingBGL.setVisible(false);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(300,200));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/Logo.png"));
		
		IPField = new JTextField("",1);
		contentPane.add(IPField);
		IPField.setBounds(70, 85, 200, 30);
		
		SubButton = new JButton("Link Start！");
		contentPane.add(SubButton);
		SubButton.setBounds(90, 145, 120, 30);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				LinkBGL.setVisible(false);
				LinkingBGL.setVisible(true);
				
				contentPane.setVisible(false);
				LinkingPane.setVisible(true);
				setContentPane(LinkingPane);
				
				BGMclip.play();
				Link();
			}		
		});
		
		LinkingPane = new JPanel();
		LinkingPane.setOpaque(false);
		LinkingPane.setLayout(null);
		LinkingPane.setPreferredSize(new Dimension(400,300));
		LinkingPane.setVisible(false);
		
		LinkingLabel = new JLabel("正在连接，请稍候...");
		LinkingLabel.setForeground(Color.WHITE);
		LinkingLabel.setBounds(75, 78, 300, 30);
		LinkingPane.add(LinkingLabel);
		
		LinkingButton = new JButton("取消");
		LinkingButton.setBounds(100, 130, 100, 30);
		LinkingPane.add(LinkingButton);
		LinkingButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(Linked){
					Log();
				} else {
					cancel();
				}
			}		
		});
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void Link(){
		
		try {
			Driver.soc = new Socket(ip,8888);
			while(true){
				Driver.brer = new BufferedReader(new InputStreamReader(Driver.soc.getInputStream()));
				Driver.bwer = new BufferedWriter(new OutputStreamWriter(Driver.soc.getOutputStream()));
				
				Linked = true;
				LinkingButton.setText("确定");
				LinkingLabel.setText("连接成功！请点击“确定”进入登陆界面");
			}
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	private void cancel(){
		Driver.soc = null;
		Driver.brer = null;
		Driver.bwer = null;
		
		LinkingBGL.setVisible(false);
		LinkBGL.setVisible(true);
		
		LinkingPane.setVisible(false);
		setContentPane(contentPane);
		contentPane.setVisible(true);
	}
	
	private void Log(){
		setVisible(false);
		dispose();
		LogFrame Log = new LogFrame();
		Log.setVisible(true);	
	}
}
