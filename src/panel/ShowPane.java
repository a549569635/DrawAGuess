package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import core.Driver;

public class ShowPane extends JPanel implements Runnable{
	private JToolBar toolBar;
	private JPanel showTable;
	private BufferedImage image = new BufferedImage(500, 320, BufferedImage.TYPE_INT_RGB);

	private ByteArrayInputStream bais;
	private DataInputStream dis;
	private byte[] data;
	
	public ShowPane(){
		setSize(new Dimension(550,320));
		setOpaque(false);
		//this.setEnabled(false);
		setLayout(null);
		this.setVisible(false);

		toolBar = new JToolBar(SwingConstants.VERTICAL);
		toolBar.setBounds(0, 0, 50, 320);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toolBar.setFloatable(false);
		toolBar.setOpaque(false);
		toolBar.setLayout(new FlowLayout());
		add(toolBar);
		
		showTable = new JPanel();
		showTable.setBounds(50, 0, 500, 320);
		showTable.setBackground(Color.WHITE);
		add(showTable);
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			try{
				dis = new DataInputStream(Driver.socket.getInputStream());
				dis.read(data);
				bais = new ByteArrayInputStream(data);
				image = ImageIO.read(bais);
				showTable.getGraphics().drawImage(image, 0, 0, 500, 320, null);
				dis.close();
				bais.close();
			}catch(Exception e){
				
			}
		}
	}
}
