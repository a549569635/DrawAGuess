package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import label.BackgroundLabel;
import javax.swing.JCheckBox;
import java.awt.Color;

public class LogFrame extends JFrame {

	private BackgroundLabel LogBGL;
	private BackgroundLabel LogingBGL;
	private JPanel contentPane;
	private JPanel LogingPane;
	private JLabel LogingLabel;
	private JTextField IDField;
	private JPasswordField PasswordField;
	private JCheckBox remenberCheck;
	private JCheckBox autologCheck;
	private JButton SubButton;
	private JButton RegisterButton;
	private JButton LogingButton;
	
	private String ID;
	private String Password;
	private URL BGMurl;
	private AudioClip BGMclip;
	private Boolean Loging = false;

	public LogFrame(String oriID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("你画我猜-登陆");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/Logo.png"));
		ID = oriID;
		
		try {
			BGMurl = new File("src/music/LogBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO 自动生成的 catch 块
		}
		BGMclip = Applet.newAudioClip(BGMurl);
		BGMclip.loop();

		LogBGL = new BackgroundLabel("src/image/LogBGP.jpg",400,300);
		LogingBGL = new BackgroundLabel("src/image/LogingBGP.jpg",400,300);
		this.getRootPane().add(LogBGL,new Integer(Integer.MIN_VALUE));
		this.getRootPane().add(LogingBGL,new Integer(Integer.MIN_VALUE));
		LogBGL.setVisible(true);
		LogingBGL.setVisible(false);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400,300));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		
		IDField = new JTextField(ID,1);
		contentPane.add(IDField);
		IDField.setBounds(75, 135, 300, 25);
		IDField.addFocusListener(new FocusListener(){  
            public void focusLost(FocusEvent e) {    
            	if(IDField.getText().equals("")){
					IDField.setText("亲，你忘了输账号o(>_<)o ~~");
				}
            	if(!(IDField.getText().equals("亲，你忘了输账号o(>_<)o ~~") || IDField.getText().equals(""))){
            		ID = IDField.getText();
				}
            }
            @Override  
            public void focusGained(FocusEvent arg0) {
            	if(IDField.getText().equals("亲，你忘了输账号o(>_<)o ~~")){
					IDField.setText("");
				}
            }
		});
		
		PasswordField = new JPasswordField("",1);
		contentPane.add(PasswordField);
		PasswordField.setBounds(75, 170, 300, 25);

		remenberCheck = new JCheckBox("保存密码");
		remenberCheck.setForeground(Color.WHITE);
		remenberCheck.setBounds(80, 200, 100, 25);
		remenberCheck.setOpaque(false);
		contentPane.add(remenberCheck);
		
		autologCheck = new JCheckBox("自动登录");
		autologCheck.setForeground(Color.WHITE);
		autologCheck.setBounds(260, 200, 100, 25);
		autologCheck.setOpaque(false);
		contentPane.add(autologCheck);
		
		SubButton = new JButton("Start！");
		contentPane.add(SubButton);
		SubButton.setBounds(50, 240, 120, 30);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Password = String.valueOf(PasswordField.getPassword());
				BGMclip.stop();
				Log(ID,Password);
			}		
		});
		
		RegisterButton = new JButton("新人入伙");
		contentPane.add(RegisterButton);
		RegisterButton.setBounds(230, 240, 120, 30);
		RegisterButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				BGMclip.stop();
				Sign();
			}		
		});
		
		LogingPane = new JPanel();
		LogingPane.setOpaque(false);
		LogingPane.setLayout(null);
		LogingPane.setPreferredSize(new Dimension(400,300));
		LogingPane.setVisible(false);
		
		LogingLabel = new JLabel("正在登陆，请稍等...");
		LogingLabel.setForeground(Color.WHITE);
		LogingLabel.setBounds(75, 135, 300, 25);
		LogingPane.add(LogingLabel);
		
		LogingButton = new JButton("取消");
		LogingButton.setBounds(150, 200, 100, 30);
		LogingPane.add(LogingButton);
		LogingButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(Loging){
					cancel();
				} else {
					LogingPane.setVisible(false);
					setContentPane(contentPane);
					contentPane.setVisible(true);
				}
			}		
		});
		
		pack();
		setLocationRelativeTo(null);
	}

	private void Log(String ID, String Password){
		//setVisible(false);
		//dispose();
		//HallFrame hall = new HallFrame();
		//hall.setVisible(true);
		LogBGL.setVisible(false);
		LogingBGL.setVisible(true);
		
		Loging = true;
		
		LogBGL.setVisible(false);
		LogingBGL.setVisible(true);
		
		contentPane.setVisible(false);
		LogingPane.setVisible(true);
		setContentPane(LogingPane);
	}
	
	private void Sign(){
		setVisible(false);
		dispose();
		SignFrame Sign = new SignFrame();
		Sign.setVisible(true);
	}
	
	private void cancel(){
		LogingBGL.setVisible(false);
		LogBGL.setVisible(true);
		
		Loging = false;
		
		LogingPane.setVisible(false);
		setContentPane(contentPane);
		contentPane.setVisible(true);
	}
}
