package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

import java.awt.Toolkit;

import label.BackgroundLabel;

public class LinkFrame extends JFrame {

	private BackgroundLabel LinkBGL;
	private BackgroundLabel LinkingBGL;
	private JPanel contentPane;
	private JPanel LinkingPane;
	private JLabel LinkingLabel;
	private JLabel TipLab;
	private JLabel IPLab;
	private JTextField IPField;
	private JButton SubButton;
	private JButton ResetButton;
	private JButton LogingButton;
	
	private URL BGMurl;
	private AudioClip BGMclip;
	private Boolean Linking = false;
	
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
		
		SubButton = new JButton("Start！");
		contentPane.add(SubButton);
		SubButton.setBounds(100, 145, 100, 30);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
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
		
		LogingButton = new JButton("取消");
		LogingButton.setBounds(100, 130, 100, 30);
		LinkingPane.add(LogingButton);
		LogingButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(Linking){
					cancel();
				} else {
					LinkingPane.setVisible(false);
					setContentPane(contentPane);
					contentPane.setVisible(true);
				}
			}		
		});
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void Link(){
		//setVisible(false);
		//dispose();
		LogFrame Log = new LogFrame("");
		Log.setVisible(true);
		
		Linking = true;
		
		LinkBGL.setVisible(false);
		LinkingBGL.setVisible(true);
		
		contentPane.setVisible(false);
		LinkingPane.setVisible(true);
		setContentPane(LinkingPane);
	}
	
	private void cancel(){
		LinkingBGL.setVisible(false);
		LinkBGL.setVisible(true);
		
		LinkingPane.setVisible(false);
		setContentPane(contentPane);
		contentPane.setVisible(true);
	}
}
