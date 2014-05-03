package frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import label.BackgroundLabel;

import javax.swing.JCheckBox;

import warpper.Protocol;
import warpper.User;
import core.ClientSocketRunnable;
import core.Driver;

import java.awt.Color;

public class LogFrame extends JFrame {
	private ObjectInputStream in = Driver.in;
	private ObjectOutputStream out = Driver.out;
	private BufferedReader fbr;
	private BufferedWriter fbw;
	public static ClientSocketRunnable client;

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
	private AudioClip BGMclip;
	private Boolean Linked = true;
	private Boolean Loged = false;
	private Protocol data;
	
	private LogRunnable lr;
	private Thread thr;

	private static File resource = new File("D:\\DrawAndGuess\\resource");
	private static File autolog = new File("D:\\DrawAndGuess\\resource\\AutoLog.txt");
	private static File user = new File("D:\\DrawAndGuess\\user");

	public LogFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u4F60\u753B\u6211\u731C-\u767B\u9646");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Driver.class.getResource("/image/Logo.png")));
		getRootPane().setDefaultButton(SubButton);
		
		BGMclip = Applet.newAudioClip(Driver.class.getResource("/music/LogBGM.wav"));
		BGMclip.loop();

		LogBGL = new BackgroundLabel(Driver.class.getResource("/image/LogBGP.jpg"),400,300);
		LogingBGL = new BackgroundLabel(Driver.class.getResource("/image/LogingBGP.jpg"),400,300);
		this.getRootPane().add(LogBGL,new Integer(Integer.MIN_VALUE));
		this.getRootPane().add(LogingBGL,new Integer(Integer.MIN_VALUE));
		LogBGL.setVisible(true);
		LogingBGL.setVisible(false);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(400,300));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		
		IDField = new JTextField("",1);
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

		remenberCheck = new JCheckBox("\u4FDD\u5B58\u5BC6\u7801");
		remenberCheck.setForeground(Color.WHITE);
		remenberCheck.setBounds(80, 200, 100, 25);
		remenberCheck.setOpaque(false);
		contentPane.add(remenberCheck);
		
		autologCheck = new JCheckBox("\u81EA\u52A8\u767B\u9646");
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
				Log();
			}		
		});
		
		RegisterButton = new JButton("\u65B0\u4EBA\u62A5\u5230");
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
				if(Loged){
					Launch();
				} else if(Linked){
					Cancel();
				} else{
					System.exit(0);
				}
			}		
		});
		
		if(!resource.exists()){
			try {
				resource.mkdirs();
				autolog.createNewFile();
				//fbw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(autolog)));
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else if(!autolog.exists()){
			try {
				autolog.createNewFile();
				//fbw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(autolog)));
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		} else{
			try {
				fbr = new BufferedReader(new InputStreamReader(new FileInputStream(autolog)));
				String str = fbr.readLine();
				String[] s = str.split("-");
				
				if(s[0].equals("1")){
					ID = s[1];
					IDField.setText(ID);
					Password = s[2];
					Log();
				} else if(s[0].equals("0")){
					ID = s[1];
					IDField.setText(ID);
					Password = s[2];
					PasswordField.setText(Password);
					remenberCheck.setSelected(true);
				}
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} finally{
				try {
					fbr.close();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		}
		pack();
		setLocationRelativeTo(null);
	}

	private void Log(){
		//setVisible(false);
		//dispose();
		//HallFrame hall = new HallFrame();
		//hall.setVisible(true);
		//Loged = true;
		if(remenberCheck.isSelected()){
			if(autologCheck.isSelected()){
				SaveAL("1");
			} else{
				SaveAL("0");
			}
		}
		
		PasswordField.setText("");
		remenberCheck.setSelected(false);
		autologCheck.setSelected(false);
		
		LogBGL.setVisible(false);
		LogingBGL.setVisible(true);
		contentPane.setVisible(false);
		LogingPane.setVisible(true);
		setContentPane(LogingPane);
		
		lr = new LogRunnable();
		thr = new Thread(lr);
		//thr.sleep(100);
		thr.start();
	}
	
	private void Sign(){
		setVisible(false);
		dispose();
		Driver.signframe = new SignFrame();
		Driver.signframe.setVisible(true);
	}
	
	private void Cancel(){
		Loged = false;
		LogingButton.setText("取消");
		LogingLabel.setText("正在登陆，请稍等...");
		LogingBGL.setVisible(false);
		LogBGL.setVisible(true);
		LogingPane.setVisible(false);
		setContentPane(contentPane);
		contentPane.setVisible(true);
		data = null;
		thr = null;
		lr = null;
	}
	
	private void Launch() {
		// TODO 自动生成的方法存根
		setVisible(false);
		thr = null;
		lr = null;
		dispose();
		Driver.hallframe = new HallFrame();
		Driver.hallframe.setVisible(true);
		
		try {
			Driver.client = new ClientSocketRunnable(Driver.socket);
			new Thread(Driver.client).start();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	private void SaveAL(String s){
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\DrawAndGuess\\resource\\AutoLog.txt")));
			writer.write(s+"-"+ID+"-"+Password);
			writer.flush();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	private class LogRunnable implements Runnable{
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				Driver.self = new User(ID,Password);
				out.writeObject((Object)new Protocol(1,Driver.self));
				//out.flush();
				//setVisible(false);
				//dispose();
System.out.println("789");
				if (Driver.socket == null) {
					LogingLabel.setText("未连接服务端！请点击“确定”退出");
					LogingButton.setText("确定");
					Linked = false;
					//JOptionPane.showMessageDialog(null, "服务端已关闭，请手动关闭");
					//break;
				} else {
					try {
						data = (Protocol) in.readObject();
						System.out.println("登陆反馈已接收"+data.getPro());
						if (data == null) {
						} else if (data.getPro() == 1) {
							//Launch();
							//JOptionPane.showMessageDialog(null,"登陆成功");
							Driver.self = (User)data.getObj();
							LogingLabel.setText("登陆成功！请点击“确定”进入游戏大厅");
							LogingButton.setText("确定");
							Loged = true;
						} else if (data.getPro() == 20) {
							LogingLabel.setText("用户名密码错误！请点击“确定”重新输入");
							LogingButton.setText("确定");
						} else if (data.getPro()==25) {
							LogingLabel.setText("用户已登陆！请点击“确定”返回");
							LogingButton.setText("确定");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
