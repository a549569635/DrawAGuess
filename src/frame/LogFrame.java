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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import label.BackgroundLabel;
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
		setTitle("�㻭�Ҳ�-��½");
		setResizable(false);
		setLocation(300,200);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400,300));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/Logo.png"));
		ID = oriID;

		BackgroundLabel BGL = new BackgroundLabel("src/image/LogBGP.jpg",400,300);
		this.getRootPane().add(BGL,new Integer(Integer.MIN_VALUE));
		
		try {
			BGMurl = new File("src/music/LogBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO �Զ����ɵ� catch ��
		}
		BGMclip = Applet.newAudioClip(BGMurl);
		BGMclip.loop();
		
		IDField = new JTextField(ID,1);
		contentPane.add(IDField);
		IDField.setBounds(75, 135, 300, 25);
		IDField.addFocusListener(new FocusListener(){  
            public void focusLost(FocusEvent e) {    
            	if(IDField.getText().equals("")){
					IDField.setText("�ף����������˺�o(>_<)o ~~");
				}
            	if(!(IDField.getText().equals("�ף����������˺�o(>_<)o ~~") || IDField.getText().equals(""))){
            		ID = IDField.getText();
				}
            }
            @Override  
            public void focusGained(FocusEvent arg0) {
            	if(IDField.getText().equals("�ף����������˺�o(>_<)o ~~")){
					IDField.setText("");
				}
            }
		});
		
		PasswordField = new JPasswordField("",1);
		contentPane.add(PasswordField);
		PasswordField.setBounds(75, 170, 300, 25);
		
		SubButton = new JButton("Start��");
		contentPane.add(SubButton);
		SubButton.setBounds(50, 240, 120, 30);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Password = String.valueOf(PasswordField.getPassword());
				BGMclip.stop();
				Hall(ID,Password);
			}		
		});
		
		RegisterButton = new JButton("�������");
		contentPane.add(RegisterButton);
		RegisterButton.setBounds(200, 240, 120, 30);
		RegisterButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				BGMclip.stop();
				Sign();
			}		
		});
		
		pack();
	}
	
	private void Sign(){
		setVisible(false);
		dispose();
		SignFrame Sign = new SignFrame(ID);
		Sign.setVisible(true);
	}
	
	private void Hall(String ID, String Password){
		setVisible(false);
		dispose();
		HallFrame hall = new HallFrame();
		hall.setVisible(true);
	}
}
