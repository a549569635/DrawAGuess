package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.*;

import core.ClientSocketRunnable;
import core.Driver;

import java.awt.Toolkit;

import label.BackgroundLabel;

public class LinkFrame extends JFrame {

	private BackgroundLabel LinkBGL;
	private BackgroundLabel LinkingBGL;
	private JPanel contentPane;
	private JPanel LinkingPane;
	private JTextArea LinkingT;
	private JTextField IPField;
	private JButton SubButton;
	private JButton LinkingButton;
	
	private URL BGMurl;
	private AudioClip BGMclip;
	private Boolean Linked = false;
	private String ip;
	
	public LinkFrame() {
		setTitle("�㻭�Ҳ�-Link");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		try {
			BGMurl = new File("src/music/LinkBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO �Զ����ɵ� catch ��
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
		
		SubButton = new JButton("Link Start��");
		contentPane.add(SubButton);
		SubButton.setBounds(90, 145, 120, 30);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				BGMclip.play();
				Link();
			}		
		});
		
		LinkingPane = new JPanel();
		LinkingPane.setOpaque(false);
		LinkingPane.setLayout(null);
		LinkingPane.setPreferredSize(new Dimension(400,300));
		LinkingPane.setVisible(false);
		
		LinkingT = new JTextArea("�������ӣ����Ժ�...");
		LinkingT.setForeground(Color.WHITE);
		LinkingT.setBounds(40, 82, 220, 30);
		LinkingT.setEditable(false);
		LinkingT.setLineWrap(true);
		LinkingT.setWrapStyleWord(true);
		LinkingT.setOpaque(false);
		LinkingPane.add(LinkingT);
		
		LinkingButton = new JButton("ȡ��");
		LinkingButton.setBounds(100, 130, 100, 30);
		LinkingPane.add(LinkingButton);
		LinkingButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				if(Linked){
					Log();
				} else {
					Cancel();
				}
			}		
		});
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void Link(){
		LinkingButton.setText("ȡ��");
		LinkingT.setText("�������ӣ����Ժ�...");
		
		LinkBGL.setVisible(false);
		LinkingBGL.setVisible(true);
		
		contentPane.setVisible(false);
		LinkingPane.setVisible(true);
		setContentPane(LinkingPane);
		
		try {
			Driver.soc = new Socket(ip,8888);
			//Driver.soc.setSoTimeout(10000);
			while(!Linked){
				Driver.in = new ObjectInputStream(Driver.soc.getInputStream());
				Driver.out = new ObjectOutputStream(Driver.soc.getOutputStream());
				
				Linked = true;
				LinkingButton.setText("ȷ��");
				LinkingT.setText("���ӳɹ���������ȷ���������½����");
			}
		} catch(SocketTimeoutException e){
			LinkingButton.setText("ȷ��");
			LinkingT.setText("���ӳ�ʱ��������ȷ��������");
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			LinkingButton.setText("ȷ��");
			LinkingT.setText("�Ҳ�����������������ȷ��������");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			LinkingButton.setText("ȷ��");
			LinkingT.setText("�����쳣��������ȷ��������");
			e.printStackTrace();
		}
	}
	
	private void Cancel(){
		Driver.soc = null;
		Driver.in = null;
		Driver.out = null;
		
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
