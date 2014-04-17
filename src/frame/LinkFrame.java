package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

import panel.BackgroundPanel;
import core.Driver;
import java.awt.Toolkit;

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
		setTitle("Link");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/Logo.png"));
		
		System.out.print(this.getContentPane().getHeight());
		
		BackgroundPanel BGP = new BackgroundPanel("image/LinkBGP.jpg",300,200);
		this.getRootPane().add(BGP,new Integer(Integer.MIN_VALUE));
		
		try {
			BGMurl = new File("music/LinkBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO 自动生成的 catch 块
		}
		BGMclip = Applet.newAudioClip(BGMurl);
		BGMclip.play();
		
		TipLab = new JLabel("需要填一下服务器IP哟，么么哒~");
		contentPane.add(TipLab);
		TipLab.setBounds(40, 20, 260, 30);
		
		IPLab = new JLabel("IP:");
		contentPane.add(IPLab);
		IPLab.setBounds(30, 60, 30, 30);
		
		IPField = new JTextField("",1);
		contentPane.add(IPField);
		IPField.setBounds(60, 60, 200, 30);
		
		SubButton = new JButton("Start！");
		contentPane.add(SubButton);
		SubButton.setBounds(40, 110, 80, 30);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				BGMclip.stop();
				Driver.Linkcomplete();
			}		
		});
		
	}
}
