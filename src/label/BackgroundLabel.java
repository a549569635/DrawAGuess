package label;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackgroundLabel extends JLabel {
	
	public BackgroundLabel(URL url,int Length,int Width) {
		ImageIcon BGP = new ImageIcon(url);
		this.setIcon(BGP);
		this.setBounds(0, 0, Length, Width);
	}
}
