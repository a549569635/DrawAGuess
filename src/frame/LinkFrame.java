package frame;

import java.applet.Applet;
import java.applet.AudioClip;
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

	private JPanel contentPane;
	private JLabel TipLab;
	private JLabel IPLab;
	private JTextField IPField;
	private JButton SubButton;
	private JButton ResetButton;
	
	private URL BGMurl;
	private AudioClip BGMclip;
	
	public LinkFrame() {
		setTitle("你画我猜-Link");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(350,200);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(300,200));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/Logo.png"));
		
		BackgroundLabel BGL = new BackgroundLabel("src/image/LinkBGP.jpg",300,200);
		this.getRootPane().add(BGL,new Integer(Integer.MIN_VALUE));
		
		try {
			BGMurl = new File("src/music/LinkBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO 自动生成的 catch 块
		}
		BGMclip = Applet.newAudioClip(BGMurl);
		
		IPField = new JTextField("",1);
		contentPane.add(IPField);
		IPField.setBounds(70, 85, 200, 30);
		
		SubButton = new JButton("Start！");
		contentPane.add(SubButton);
		SubButton.setBounds(40, 150, 80, 30);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				BGMclip.play();
				Log();
			}		
		});
		
		pack();
	}
	
	private void Log(){
		setVisible(false);
		dispose();
		LogFrame Log = new LogFrame("");
		Log.setVisible(true);
	}
}
