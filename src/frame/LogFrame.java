package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.Driver;

public class LogFrame extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JPasswordField PasswordField;
	private JButton SubButton;
	private JButton RegisterButton;
	
	private String ID;
	private String Password;
	private URL BGMurl;
	private AudioClip BGMclip;

	public LogFrame(String oriID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("登陆");
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/Logo.png"));
		ID = oriID;
		
		try {
			BGMurl = new File("music/LogBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO 自动生成的 catch 块
		}
		BGMclip = Applet.newAudioClip(BGMurl);
		BGMclip.loop();
		
		final JLabel IDLabel = new JLabel("账号：");
		contentPane.add(IDLabel);
		IDLabel.setBounds(100, 50, 50, 30);
		
		final JLabel PasswordLabel = new JLabel("密码：");
		contentPane.add(PasswordLabel);
		PasswordLabel.setBounds(100, 110, 50, 30);
		
		IDField = new JTextField(ID,1);
		contentPane.add(IDField);
		IDField.setBounds(150, 50, 200, 30);
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
		PasswordField.setBounds(150, 110, 200, 30);
		
		SubButton = new JButton("Start！");
		contentPane.add(SubButton);
		SubButton.setBounds(80, 190, 120, 40);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Password = String.valueOf(PasswordField.getPassword());
				BGMclip.stop();
				Driver.Logcomplete(ID,Password);
			}		
		});
		
		RegisterButton = new JButton("新人入伙");
		contentPane.add(RegisterButton);
		RegisterButton.setBounds(250, 190, 120, 40);
		RegisterButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				BGMclip.stop();
				Driver.Logcomplete(ID);
			}		
		});
	}
	
}
