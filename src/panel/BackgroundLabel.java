package panel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackgroundLabel extends JLabel {
	
	public BackgroundLabel(String ImgPath,int Length,int Width) {
		ImageIcon BGP = new ImageIcon(ImgPath);
		this.setIcon(BGP);
		this.setBounds(0, 0, Length, Width);
	}
}
