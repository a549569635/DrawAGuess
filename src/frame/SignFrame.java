package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import label.BackgroundLabel;

import javax.swing.JCheckBox;

import warpper.Protocol;
import warpper.User;
import core.Driver;
import frame.LogFrame.LogRunnable;

public class SignFrame extends JFrame {

	private BackgroundLabel SignBGL;
	private BackgroundLabel SigningBGL;
	private JPanel contentPane;
	private JPanel SigningPane;
	private JLabel SigningLabel;
	private JTextField NickField;
	private JTextField IDField;
	private JPasswordField PWField;
	private JPasswordField RePWField;
	private JTextField EmailField;
	private JButton SubButton;
	private JButton ResetButton;
	private JButton SigningButton;
	private JLabel NickCheckL;
	private JLabel IDCheckL;
	private JLabel PWCheckL;
	private JLabel RePWCheckL;
	private JLabel EmailCheckL;
	private JTextArea NickTipT;
	private JTextArea IDTipT;
	private JTextArea PWTipT;
	private JTextArea RePWTipT;
	private JTextArea EmailTipT;
	private JCheckBox AMCheckBox;
	private JLabel Agreement;

	private URL BGMurl;
	private AudioClip BGMclip;
	private ImageIcon passTip = new ImageIcon("src/image/passTip.png");
	private ImageIcon failTip = new ImageIcon("src/image/failTip.png");
	
	private String Nickname;
	private String ID;
	private String Password;
	private String Email;
	private Boolean Signed = false;
	private Boolean Nickpassed = false;
	private Boolean IDpassed = false;
	private Boolean PWpassed = false;
	private Boolean RePWpassed = false;
	private Boolean Emailpassed = true;
	private Boolean AMpassed = false;

	public SignFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�㻭�Ҳ�-ע��");
		setResizable(false);
		
		try {
			BGMurl = new File("src/music/SignBGM.wav").toURI().toURL();
		} catch (MalformedURLException e1) {
			// TODO �Զ����ɵ� catch ��
		}
		BGMclip = Applet.newAudioClip(BGMurl);
		BGMclip.loop();
		
		SignBGL = new BackgroundLabel("src/image/SignBGP.jpg",450,600);
		SigningBGL = new BackgroundLabel("src/image/SigningBGP.jpg",450,600);
		this.getRootPane().add(SignBGL,new Integer(Integer.MIN_VALUE));
		this.getRootPane().add(SigningBGL,new Integer(Integer.MIN_VALUE));
		SignBGL.setVisible(true);
		SigningBGL.setVisible(false);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(450,600));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/Logo.png"));
		
		NickCheckL = new JLabel();
		NickCheckL.setBounds(380, 60, 20, 20);
		contentPane.add(NickCheckL);
		
		NickTipT = new JTextArea("�ǳƳ����뱣���ڶ���ʮ�����ַ�");
		NickTipT.setBounds(120, 80, 250, 40);
		NickTipT.setForeground(Color.WHITE);
		NickTipT.setFont(new Font(null,Font.PLAIN,12));
		NickTipT.setEditable(false);
		NickTipT.setLineWrap(true);
		NickTipT.setWrapStyleWord(true);
		NickTipT.setOpaque(false);
		contentPane.add(NickTipT);
		
		IDCheckL = new JLabel();
		contentPane.add(IDCheckL);
		IDCheckL.setBounds(380, 140, 20, 20);
		
		IDTipT = new JTextArea("�˺�Ӧ������ʮ��λ��ĸ���������");
		IDTipT.setBounds(120, 160, 250, 40);
		IDTipT.setForeground(Color.WHITE);
		IDTipT.setFont(new Font(null,Font.PLAIN,12));
		IDTipT.setEditable(false);
		IDTipT.setLineWrap(true);
		IDTipT.setWrapStyleWord(true);
		IDTipT.setOpaque(false);
		contentPane.add(IDTipT);
		
		PWCheckL = new JLabel();
		contentPane.add(PWCheckL);
		PWCheckL.setBounds(380, 220, 20, 20);
		
		PWTipT = new JTextArea("����Ӧ������ʮ��λ��ĸ�����ֺͷ�����ɣ���ĸ���ִ�Сд��");
		PWTipT.setBounds(120, 240, 250, 40);
		//PWTipT.setForeground(Color.WHITE);
		PWTipT.setFont(new Font(null,Font.PLAIN,12));
		PWTipT.setEditable(false);
		PWTipT.setLineWrap(true);
		PWTipT.setWrapStyleWord(true);
		PWTipT.setOpaque(false);
		contentPane.add(PWTipT);

		RePWCheckL = new JLabel();
		RePWCheckL.setBounds(380, 300, 20, 20);
		contentPane.add(RePWCheckL);
		
		RePWTipT = new JTextArea("���ظ�����һ��������ȷ��");
		RePWTipT.setBounds(120, 320, 250, 40);
		//RePWTipT.setForeground(Color.WHITE);
		RePWTipT.setFont(new Font(null,Font.PLAIN,12));
		RePWTipT.setEditable(false);
		RePWTipT.setLineWrap(true);
		RePWTipT.setWrapStyleWord(true);
		RePWTipT.setOpaque(false);
		contentPane.add(RePWTipT);
		
		EmailCheckL = new JLabel(passTip);
		EmailCheckL.setBounds(380, 380, 20, 20);
		contentPane.add(EmailCheckL);
		
		EmailTipT = new JTextArea("����дһ���������������һ����루ѡ�");
		EmailTipT.setBounds(120, 400, 250, 40);
		//EmailTipT.setForeground(Color.WHITE);
		EmailTipT.setFont(new Font(null,Font.PLAIN,12));
		EmailTipT.setEditable(false);
		EmailTipT.setLineWrap(true);
		EmailTipT.setWrapStyleWord(true);
		EmailTipT.setOpaque(false);
		contentPane.add(EmailTipT);
		
		AMCheckBox = new JCheckBox("���������Ķ���ͬ��");
		AMCheckBox.setBounds(120, 460, 150, 20);
		AMCheckBox.setOpaque(false);
		contentPane.add(AMCheckBox);
		AMCheckBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO �Զ����ɵķ������
				AMpassed = AMCheckBox.isSelected();
				checkpass();
			}
			
		});
		
		Agreement = new JLabel("�û�Э��");
		Agreement.setBounds(270, 460, 60, 20);
		contentPane.add(Agreement);
		Agreement.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO �Զ����ɵķ������
				try {
					Desktop.getDesktop().open(new File("src/text/Agreement.txt"));
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					JOptionPane.showMessageDialog(null,"��Ǹ���û�Э���ʧ����");
				}
			}
		});
		
		NickField = new JTextField("ȡ��������o(>_<)o ~~",1);
		contentPane.add(NickField);
		NickField.setBounds(120, 50, 250, 30);
		NickField.addFocusListener(new FocusListener(){  
            public void focusLost(FocusEvent e) {    
            	if(NickField.getText().equals("")){
            		NickCheckL.setIcon(failTip);
					NickTipT.setText("�ǳƲ���Ϊ�գ��ǳƳ����뱣���ڶ���ʮ�����ַ�");
            		NickField.setText("ȡ��������o(>_<)o ~~");
					Nickpassed = false;
				} else if (NickField.getText().equals("ȡ��������o(>_<)o ~~")){
					NickCheckL.setIcon(failTip);
					NickTipT.setText("�����Ƥ������ô��Ƥ���������");
					Nickpassed = false;
				} else if(NickField.getText().length() < 2){
					NickCheckL.setIcon(failTip);
					NickTipT.setText("�ǳ�̫���ˣ������뱣���ڶ���ʮ�����ַ�");
					Nickpassed = false;
				} else if(NickField.getText().length() > 16){
					NickCheckL.setIcon(failTip);
					NickTipT.setText("�ǳ�̫���ˣ������뱣���ڶ���ʮ�����ַ�");
					Nickpassed = false;
				} else{
					NickCheckL.setIcon(passTip);
					NickTipT.setText("��ϲ���ǳƿ��ã�");
					Nickpassed = true;
					Nickname = NickField.getText();
				}
            	checkpass();
            }
            @Override  
            public void focusGained(FocusEvent arg0) {
            	if(NickField.getText().equals("ȡ��������o(>_<)o ~~")){
            		NickCheckL.setIcon(null);
					NickTipT.setText("�ǳƳ����뱣���ڶ���ʮ�����ַ�");
            		NickField.setText("");
				}
            }
		});
		
		IDField = new JTextField(ID,1);
		contentPane.add(IDField);
		IDField.setBounds(120, 130, 250, 30);
		IDField.addFocusListener(new FocusListener(){  
            public void focusLost(FocusEvent e) {    
            	if(IDField.getText().equals("")){
            		IDCheckL.setIcon(failTip);
            		IDTipT.setText("�˺Ų���Ϊ�գ��˺�Ӧ������ʮ��λ��ĸ���������");
            		IDField.setText("����˺���o(>_<)o ~~");
            		IDpassed = false;
				} else if (IDField.getText().equals("����˺���o(>_<)o ~~")){
					IDCheckL.setIcon(failTip);
					IDTipT.setText("�����Ƥ������ô��Ƥ���������");
					IDpassed = false;
				} else if(IDField.getText().length() < 6){
					IDCheckL.setIcon(failTip);
					IDTipT.setText("�˺�̫���ˣ������뱣��������ʮ��λ");
					IDpassed = false;
				} else if(IDField.getText().length() > 18){
					IDCheckL.setIcon(failTip);
					IDTipT.setText("�˺�̫���ˣ������뱣��������ʮ��λ");
					IDpassed = false;
				} else{
					IDCheckL.setIcon(passTip);
					IDTipT.setText("��ϲ���˺ſ��ã�");
					IDpassed = true;
					ID = IDField.getText();
				}
            	checkpass();
            }
            @Override  
            public void focusGained(FocusEvent arg0) {
            	if(IDField.getText().equals("����˺���o(>_<)o ~~")){
            		IDCheckL.setIcon(null);
					IDTipT.setText("�˺�Ӧ������ʮ��λ��ĸ���������");
					IDField.setText("");
				}
            }
		});
		
		PWField = new JPasswordField("",1);
		contentPane.add(PWField);
		PWField.setBounds(120, 210, 250, 30);
		PWField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {}
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO �Զ����ɵķ������
				if(String.valueOf(PWField.getPassword()).equals("")){
					PWCheckL.setIcon(failTip);
					PWTipT.setText("���벻��Ϊ�գ�����Ӧ������ʮ��λ��ĸ�����ֺͷ������");
					PWpassed = false;
				} else if(PWField.getPassword().length < 6){
					PWCheckL.setIcon(failTip);
					PWTipT.setText("����̫���ˣ������뱣��������ʮ��λ");
					PWpassed = false;
				} else if(PWField.getPassword().length > 18){
					PWCheckL.setIcon(failTip);
					PWTipT.setText("����̫���ˣ������뱣��������ʮ��λ");
					PWpassed = false;
				} else{
					PWCheckL.setIcon(passTip);
					PWTipT.setText("��ϲ��������ã�");
					PWpassed = true;
					Password = String.valueOf(PWField.getPassword());
				}
            	checkpass();
			}
		});
		
		RePWField = new JPasswordField("",1);
		contentPane.add(RePWField);
		RePWField.setBounds(120, 290, 250, 30);
		RePWField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {}
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO �Զ����ɵķ������
				if(String.valueOf(RePWField.getPassword()).equals(String.valueOf(PWField.getPassword()))){
					RePWCheckL.setIcon(passTip);
					RePWTipT.setText("��ϲ��ȷ�ϳɹ���");
					RePWpassed = true;
				} else{
					RePWCheckL.setIcon(failTip);
					RePWTipT.setText("ȷ��ʧ�ܣ������������벻һ��");
					RePWpassed = false;
				}
            	checkpass();
			}
		});
		
		EmailField = new JTextField("");
		contentPane.add(EmailField);
		EmailField.setBounds(120, 370, 250, 30);
		
		SubButton = new JButton("ȷ��ע��");
		contentPane.add(SubButton);
		getRootPane().setDefaultButton(SubButton);
		SubButton.setBounds(75, 520, 120, 40);
		SubButton.setEnabled(false);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				ID = IDField.getText();
				Password = String.valueOf(PWField.getPassword());
				Nickname = NickField.getText();
				Email = EmailField.getText();
				BGMclip.stop();
				Sign();
			}
		});
		
		ResetButton = new JButton("����");
		contentPane.add(ResetButton);
		ResetButton.setBounds(250, 520, 120, 40);
		
		ResetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				NickField.setText("ȡ��������o(>_<)o ~~");
				IDField.setText("����˺���o(>_<)o ~~");
				PWField.setText("");
				RePWField.setText("");
				EmailField.setText("");
				NickTipT.setText("�ǳƳ����뱣���ڶ���ʮ�����ַ�");
        		IDTipT.setText("�˺�Ӧ������ʮ��λ��ĸ���������");
				PWTipT.setText("����Ӧ������ʮ��λ��ĸ�����ֺͷ�����ɣ���ĸ���ִ�Сд��");
				RePWTipT.setText("���ظ�����һ��������ȷ��");
				//EmailTipT.setText("����дһ���������������һ����루ѡ�");
				NickCheckL.setIcon(null);
				IDCheckL.setIcon(null);
				PWCheckL.setIcon(null);
				RePWCheckL.setIcon(null);
				//EmailCheckL.setIcon(null);
				AMCheckBox.setSelected(false);
			}		
		});
		
		SigningPane = new JPanel();
		SigningPane.setOpaque(false);
		SigningPane.setLayout(null);
		SigningPane.setPreferredSize(new Dimension(400,300));
		SigningPane.setVisible(false);
		
		SigningLabel = new JLabel("����ע�ᣬ���Ժ�...");
		//SigningLabel.setForeground(Color.WHITE);
		SigningLabel.setFont(new Font(null,Font.PLAIN,18));
		SigningLabel.setBounds(80, 220, 300, 30);
		SigningPane.add(SigningLabel);
		
		SigningButton = new JButton("ȡ��");
		SigningButton.setBounds(150, 400, 150, 50);
		SigningPane.add(SigningButton);
		SigningButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				if(Signed){
					log();
				} else {
					cancel();
				}
			}		
		});
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void checkpass(){
		if(Nickpassed && IDpassed && PWpassed && RePWpassed && Emailpassed && AMpassed){
			SubButton.setEnabled(true);
		} else{
			SubButton.setEnabled(false);
		}
	}
	
	private void Sign(){
		SignBGL.setVisible(false);
		SigningBGL.setVisible(true);		
		contentPane.setVisible(false);
		SigningPane.setVisible(true);
		setContentPane(SigningPane);
		
		LogRunnable lr = new LogRunnable();
		Thread thr = new Thread(lr);
		try {
			thr.sleep(100);
			thr.start();
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	private void cancel(){
		Signed = false;
		
		SigningBGL.setVisible(false);
		SignBGL.setVisible(true);
		
		SigningPane.setVisible(false);
		setContentPane(contentPane);
		contentPane.setVisible(true);
	}
	
	private void log(){
		setVisible(false);
		dispose();
		LogFrame log = new LogFrame();
		log.setVisible(true);
	}
	
	public class LogRunnable implements Runnable{
		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			try {
				Driver.out.writeObject((Object)new Protocol(2,new User(ID,Password,Nickname,Email)));
	//System.out.println("ע�������ѷ���\n");
				Protocol data = (Protocol) Driver.in.readObject();
	//System.out.println(data.getPro()+"ע�ᷴ���ѽ���\n");
				if(data.getPro() == 2){
					SigningLabel.setText("ע��ɹ���������ȷ������½");
					SigningButton.setText("ȷ��");
					Signed = true;
				}else if(data.getPro() == 21){
					SigningLabel.setText("ע��ʧ�ܣ�������ȷ��������");
					SigningButton.setText("ȷ��");
					//JOptionPane.showMessageDialog(null, "ʧ��");
				}
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
