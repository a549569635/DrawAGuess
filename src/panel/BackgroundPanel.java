package panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	
	private Image BGP;

	public BackgroundPanel(String ImgPath,int Length,int Width) {
		this.setLayout(null);
		this.setBounds(0, 0, Length, Width);
		
		BGP = Toolkit.getDefaultToolkit().getImage(ImgPath);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(BGP,0,0,null);
	}

}
