package frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import panel.BackgroundLabel;

public class HallFrame extends JFrame {

	private JPanel contentPane;
	
	private JButton SubButton;
	
	
	public HallFrame() {
		setTitle("你画我猜-游戏大厅");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(100,80);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800,600));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/Logo.png"));
		
		BackgroundLabel BGL = new BackgroundLabel("src/image/HallBGP.jpg",800,600);
		this.getRootPane().add(BGL,new Integer(Integer.MIN_VALUE));
		
		SubButton = new JButton("确认注册");
		contentPane.add(SubButton);
		SubButton.setBounds(80, 210, 120, 40);
		SubButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		pack();
	}

}
