package frame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HallFrame extends JFrame {

	private JPanel contentPane;

	public HallFrame() {
		setTitle("ÓÎÏ·´óÌü");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800,600));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/Logo.png"));
		
		pack();
	}

}
