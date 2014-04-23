package frame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import panel.BackgroundPane;

public class HallFrame extends JFrame {

	private JPanel contentPane;

	public HallFrame() {
		setTitle("Äã»­ÎÒ²Â-ÓÎÏ·´óÌü");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(100,80);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800,600));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/Logo.png"));
		
		BackgroundPane BGP = new BackgroundPane("src/image/HallBGP.jpg",800,600);
		this.getRootPane().add(BGP,new Integer(Integer.MIN_VALUE));
		
		pack();
	}

}
