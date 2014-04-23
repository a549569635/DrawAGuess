package panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundPane extends JPanel {
	
	private Image BGP;

	public BackgroundPane(String ImgPath,int Length,int Width) {
		this.setLayout(null);
		this.setBounds(0, 0, Length, Width);
		
		BGP = Toolkit.getDefaultToolkit().getImage(ImgPath);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(BGP,0,0,null);
	}

}
