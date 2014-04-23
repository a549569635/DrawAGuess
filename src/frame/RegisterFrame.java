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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.Driver;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField NicknameField;
	private JTextField IDField;
	private JPasswordField PasswordField;
	private JPasswordField RePasswordField;
	private JButton SubButton;
	private JButton ResetButton;
	
	private String ID;
	private String Password;
	private URL BGMurl;
	private AudioClip BGMclip;

	public RegisterFrame(String oriID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ע��");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(450,300));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/Logo.png"));
		ID = oriID;
		
		try {
			BGMurl = new File("music/RegisterBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO �Զ����ɵ� catch ��
		}
		BGMclip = Applet.newAudioClip(BGMurl);
		BGMclip.loop();
		
		final JLabel NicknameLabel = new JLabel("�ǳƣ�");
		contentPane.add(NicknameLabel);
		NicknameLabel.setBounds(80, 30, 70, 30);
		
		final JLabel IDLabel = new JLabel("�˺ţ�");
		contentPane.add(IDLabel);
		IDLabel.setBounds(80, 70, 70, 30);
		
		final JLabel PasswordLabel = new JLabel("���룺");
		contentPane.add(PasswordLabel);
		PasswordLabel.setBounds(80, 110, 70, 30);

		final JLabel RePasswordLabel = new JLabel("����һ�Σ�");
		RePasswordLabel.setBounds(80, 150, 70, 30);
		contentPane.add(RePasswordLabel);
		
		NicknameField = new JTextField("ȡ��������o(>_<)o ~~",1);
		contentPane.add(NicknameField);
		NicknameField.setBounds(150, 30, 200, 30);
		NicknameField.addFocusListener(new FocusListener(){  
            public void focusLost(FocusEvent e) {    
            	if(NicknameField.getText().equals("")){
            		NicknameField.setText("ȡ��������o(>_<)o ~~");
				}
            }
            @Override  
            public void focusGained(FocusEvent arg0) {
            	if(NicknameField.getText().equals("ȡ��������o(>_<)o ~~")){
            		NicknameField.setText("");
				}
            }
		});
		
		IDField = new JTextField(ID,1);
		contentPane.add(IDField);
		IDField.setBounds(150, 70, 200, 30);
		IDField.addFocusListener(new FocusListener(){  
            public void focusLost(FocusEvent e) {    
            	if(IDField.getText().equals("")){
					IDField.setText("����˺���o(>_<)o ~~");
				}
            }
            @Override  
            public void focusGained(FocusEvent arg0) {
            	if(IDField.getText().equals("����˺���o(>_<)o ~~")){
					IDField.setText("");
				}
            }
		});
		
		PasswordField = new JPasswordField("",1);
		contentPane.add(PasswordField);
		PasswordField.setBounds(150, 110, 200, 30);
		
		RePasswordField = new JPasswordField("",1);
		contentPane.add(RePasswordField);
		RePasswordField.setBounds(150, 150, 200, 30);
		
		SubButton = new JButton("ȷ��ע��");
		contentPane.add(SubButton);
		SubButton.setBounds(80, 210, 120, 40);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				ID = IDField.getText();
				Password = String.valueOf(PasswordField.getPassword());
				BGMclip.stop();
				Driver.RegLog(ID,Password);
			}
		});
		
		ResetButton = new JButton("��д");
		contentPane.add(ResetButton);
		ResetButton.setBounds(250, 210, 120, 40);
		
		ResetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				NicknameField.setText("ȡ��������o(>_<)o ~~");
				IDField.setText("����˺���o(>_<)o ~~");
				PasswordField.setText("");
				RePasswordField.setText("");
			}		
		});
		
		pack();
	}
	
}
