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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class SignFrame extends JFrame {
	private ObjectInputStream in = Driver.in;
	private ObjectOutputStream out = Driver.out;

	private BackgroundLabel SignBGL,SigningBGL;
	private JPanel contentPane,SigningPane;
	private JLabel SigningLabel;
	private JTextField NickField,IDField,EmailField;
	private JPasswordField PWField,RePWField;
	private JButton SubButton,ResetButton,SigningButton;
	private JLabel NickCheckL,IDCheckL,PWCheckL,RePWCheckL,EmailCheckL;
	private JTextArea NickTipT,IDTipT,PWTipT,RePWTipT,EmailTipT;
	private JCheckBox AMCheckBox;
	private JLabel Agreement;

	private URL BGMurl;
	private AudioClip BGMclip;
	private ImageIcon passTip = new ImageIcon(Driver.class.getResource("/image/passTip.png"));
	private ImageIcon failTip = new ImageIcon(Driver.class.getResource("/image/failTip.png"));
	
	private String Nickname,ID,Password,Email;
	private Boolean Signed = false,Nickpassed = false,IDpassed = false,PWpassed = false,RePWpassed = false,Emailpassed = true,AMpassed = false;
	
	private SignRunnable sr;
	private Thread thr;
	private Protocol data;

	public SignFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�㻭�Ҳ�-ע��");
		setResizable(false);
		getRootPane().setDefaultButton(SubButton);
		
		BGMurl = Driver.class.getResource("/music/SignBGM.wav");
		BGMclip = Applet.newAudioClip(BGMurl);
		BGMclip.loop();
		
		SignBGL = new BackgroundLabel(Driver.class.getResource("/image/SignBGP.jpg"),450,600);
		SigningBGL = new BackgroundLabel(Driver.class.getResource("/image/SigningBGP.jpg"),450,600);
		this.getRootPane().add(SignBGL,new Integer(Integer.MIN_VALUE));
		this.getRootPane().add(SigningBGL,new Integer(Integer.MIN_VALUE));
		SignBGL.setVisible(true);
		SigningBGL.setVisible(false);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(450,600));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Driver.class.getResource("/image/Logo.png")));
		
		NickCheckL = new JLabel();
		NickCheckL.setBounds(380, 60, 20, 20);
		contentPane.add(NickCheckL);
		
		NickTipT = new JTextArea("\u6635\u79F0\u957F\u5EA6\u8BF7\u4FDD\u6301\u5728\u4E8C\u5230\u5341\u516D\u4E2A\u5B57\u7B26");
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
		
		IDTipT = new JTextArea("\u8D26\u53F7\u5E94\u7531\u516D\u5230\u5341\u516B\u4F4D\u5B57\u6BCD\u548C\u6570\u5B57\u7EC4\u6210");
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
		
		PWTipT = new JTextArea("\u5BC6\u7801\u5E94\u7531\u516D\u5230\u5341\u516B\u4F4D\u5B57\u6BCD\u3001\u6570\u5B57\u548C\u7B26\u53F7\u7EC4\u6210\uFF08\u5B57\u6BCD\u533A\u5206\u5927\u5C0F\u5199\uFF09");
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
		
		RePWTipT = new JTextArea("\u8BF7\u91CD\u590D\u8F93\u5165\u4E00\u904D\u5BC6\u7801\u4EE5\u786E\u8BA4");
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
		
		EmailTipT = new JTextArea("\u8BF7\u586B\u5199\u4E00\u4E2A\u5E38\u7528\u90AE\u7BB1\u7528\u4E8E\u627E\u56DE\u5BC6\u7801\uFF08\u9009\u586B\uFF09");
		EmailTipT.setBounds(120, 400, 250, 40);
		//EmailTipT.setForeground(Color.WHITE);
		EmailTipT.setFont(new Font(null,Font.PLAIN,12));
		EmailTipT.setEditable(false);
		EmailTipT.setLineWrap(true);
		EmailTipT.setWrapStyleWord(true);
		EmailTipT.setOpaque(false);
		contentPane.add(EmailTipT);
		
		AMCheckBox = new JCheckBox("\u6211\u5DF2\u8BA4\u771F\u9605\u8BFB\u5E76\u540C\u610F");
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
					Desktop.getDesktop().open(new File(Driver.class.getResource("/text/Agreement.txt").toURI()));
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					JOptionPane.showMessageDialog(null,"��Ǹ���û�Э���ʧ����");
				}
			}
		});
		
		NickField = new JTextField("\u53D6\u4E2A\u540D\u5B57\u5457o(>_<)o ~~",1);
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
				//} else if(){
					
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
		
		SubButton = new JButton("\u786E\u8BA4\u6CE8\u518C");
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
				Sign();
			}
		});
		
		ResetButton = new JButton("\u8FD4\u56DE\u767B\u9646");
		contentPane.add(ResetButton);
		ResetButton.setBounds(250, 520, 120, 40);
		
		ResetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				log();
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
		
		sr = new SignRunnable();
		thr = new Thread(sr);
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
		SigningLabel.setText("����ע�ᣬ���Ժ�...");
		SigningButton.setText("ȡ��");
		SigningBGL.setVisible(false);
		SignBGL.setVisible(true);
		SigningPane.setVisible(false);
		setContentPane(contentPane);
		contentPane.setVisible(true);
		data = null;
		thr = null;
		sr = null;
	}
	
	private void log(){
		setVisible(false);
		BGMclip.stop();
		dispose();
		Driver.logframe = new LogFrame();
		Driver.logframe.setVisible(true);
	}
	
	private class SignRunnable implements Runnable{
		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			try {
				out.writeObject((Object)new Protocol(2,new User(ID,Password,Nickname,Email)));
				out.flush();
	//System.out.println("ע�������ѷ���\n");
				data = (Protocol) in.readObject();
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

	private void reset() {
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
}
