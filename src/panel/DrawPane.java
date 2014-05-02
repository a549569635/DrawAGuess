package panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import core.Driver;

public class DrawPane extends JPanel implements Runnable{
	private JToolBar toolBar;
	private JPanel drawTable;
	private ButtonGroup toolGroup,weightGroup,shapeGroup;
	private JButton penButton,eraserButton,shapeButton,weightButton,colorButton,clearButton;
	private JRadioButtonMenuItem shapeLine,shapeRect,shapeOval,shapefillRect,shapefillOval;
	private JRadioButtonMenuItem weightLarge,weightMedium,weightSmall;
	private JPopupMenu weightMenu,shapeMenu;
	private JLabel colorLabel;
	private Cursor penCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(Driver.class.getResource("/image/penCursor.png")).getImage(), new Point(1,29), "pen");
	private Cursor eraserCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(Driver.class.getResource("/image/eraserCursor.png")).getImage(), new Point(13,28), "eraser");
	//private JToolTip penTip,eraserTip,shapeTip,weightTip,colorchooserTip,clearTip,colorTip;
	
	private BufferedImage image = new BufferedImage(500, 320, BufferedImage.TYPE_INT_RGB);
	private Graphics2D g2d = image.createGraphics(),g2dTemp;
	private Point p1,p2;
	private Thread thr = new Thread();
	private Color color = Color.BLACK;
	private int weight = 3;
	private String flag = "画笔";
	ButtonModel weightBM,shapeBM;
	
	public DrawPane() {
		//setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//setBounds(10, 70, 550, 320);
		setSize(new Dimension(550,320));
		setOpaque(false);
		setLayout(null);
		
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, 500, 320);
		
		toolBar = new JToolBar(SwingConstants.VERTICAL);
		toolBar.setBounds(0, 0, 50, 320);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		toolBar.setFloatable(false);
		toolBar.setOpaque(false);
		toolBar.setLayout(new FlowLayout());
		add(toolBar);
		
		drawTable = new JPanel();
		//drawTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		drawTable.setBounds(50, 0, 500, 320);
		drawTable.setBackground(Color.WHITE);
		drawTable.setCursor(penCursor);
		//g2d = (Graphics2D) drawTable.getGraphics();
		add(drawTable);
		
		penButton = new JButton(null,new ImageIcon(Driver.class.getResource("/image/pen.png")));
		penButton.setSelectedIcon(new ImageIcon(Driver.class.getResource("/image/penSed.png")));
		penButton.setSelected(true);
		penButton.setPreferredSize(new Dimension(40, 40));
		penButton.setBorderPainted(false);
		penButton.setContentAreaFilled(false);
		toolBar.add(penButton);
		penButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				penButton.setSelected(true);
				eraserButton.setSelected(false);
				shapeButton.setSelected(false);
				flag = "画笔";
				drawTable.setCursor(penCursor);
			}
		});
		
		eraserButton = new JButton(null,new ImageIcon(Driver.class.getResource("/image/eraser.png")));
		eraserButton.setSelectedIcon(new ImageIcon(Driver.class.getResource("/image/eraserSed.png")));
		eraserButton.setPreferredSize(new Dimension(40, 40));
		eraserButton.setBorderPainted(false);
		eraserButton.setContentAreaFilled(false);
		toolBar.add(eraserButton);
		eraserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				penButton.setSelected(false);
				eraserButton.setSelected(true);
				shapeButton.setSelected(false);
				flag = "橡皮";
				drawTable.setCursor(eraserCursor);
			}
		});
		
		shapeMenu = new JPopupMenu();
		shapeGroup = new ButtonGroup();
		
		shapeLine = new JRadioButtonMenuItem("Line",new ImageIcon(Driver.class.getResource("/image/shapeLine.png")));
		shapeGroup.add(shapeLine);
		shapeMenu.add(shapeLine);

		shapeRect = new JRadioButtonMenuItem("Rect",new ImageIcon(Driver.class.getResource("/image/shapeRect.png")),true);
		shapeGroup.add(shapeRect);
		shapeMenu.add(shapeRect);

		shapeOval = new JRadioButtonMenuItem("Oval",new ImageIcon(Driver.class.getResource("/image/shapeOval.png")));
		shapeGroup.add(shapeOval);
		shapeMenu.add(shapeOval);

		shapefillRect = new JRadioButtonMenuItem("FillRect",new ImageIcon(Driver.class.getResource("/image/shapefillRect.png")));
		shapeGroup.add(shapefillRect);
		shapeMenu.add(shapefillRect);

		shapefillOval = new JRadioButtonMenuItem("FillOval",new ImageIcon(Driver.class.getResource("/image/shapefillOval.png")));
		shapeGroup.add(shapefillOval);
		shapeMenu.add(shapefillOval);

		shapeButton = new JButton(new ImageIcon(Driver.class.getResource("/image/shape.png")));
		shapeButton.setSelectedIcon(new ImageIcon(Driver.class.getResource("/image/shapeSed.png")));
		shapeButton.setPreferredSize(new Dimension(40, 40));
		shapeButton.setBorderPainted(false);
		shapeButton.setContentAreaFilled(false);
		toolBar.add(shapeButton);
		shapeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				shapeMenu.show(shapeButton, 40, 0);
				penButton.setSelected(false);
				eraserButton.setSelected(false);
				shapeButton.setSelected(true);
				flag = "形状";
				drawTable.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		
		//toolBar.addSeparator();
		
		weightMenu = new JPopupMenu();
		//toolBar.add(weightMenu);
		//weightMenu.setIcon(new ImageIcon("src/image/weight.png"));
		//weightMenu.setBounds(5, 5, 30, 30);
		
		weightGroup = new ButtonGroup();
		
		weightLarge = new JRadioButtonMenuItem("large",new ImageIcon(Driver.class.getResource("/image/weightLarge.png")));
		weightGroup.add(weightLarge);
		weightMenu.add(weightLarge);
		
		weightMedium = new JRadioButtonMenuItem("medium",new ImageIcon(Driver.class.getResource("/image/weightMedium.png")));
		weightGroup.add(weightMedium);
		weightMenu.add(weightMedium);
		
		weightSmall = new JRadioButtonMenuItem("small",new ImageIcon(Driver.class.getResource("/image/weightSmall.png")),true);
		weightGroup.add(weightSmall);
		weightSmall.setSelected(true);
		weightMenu.add(weightSmall);
		
		weightButton = new JButton(new ImageIcon(Driver.class.getResource("/image/weight.png")));
		weightButton.setPreferredSize(new Dimension(40, 40));
		weightButton.setContentAreaFilled(false);
		toolBar.add(weightButton);
		weightButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				weightMenu.show(weightButton, 40, 0);
			}
		});
		
		colorButton = new JButton(new ImageIcon(Driver.class.getResource("/image/color.png")));
		colorButton.setPreferredSize(new Dimension(40, 40));
		colorButton.setContentAreaFilled(false);
		toolBar.add(colorButton);
		colorButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				color = JColorChooser.showDialog(colorButton, "颜色选择", color);
				colorLabel.setBackground(color);
			}
		});
		
		clearButton = new JButton(new ImageIcon(Driver.class.getResource("/image/clear.png")));
		clearButton.setPreferredSize(new Dimension(40, 40));
		clearButton.setContentAreaFilled(false);
		toolBar.add(clearButton);
		clearButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				g2d.clearRect(0, 0, 500, 320);
				repaint();
			}
		});
		
		colorLabel = new JLabel();
		colorLabel.setPreferredSize(new Dimension(30, 30));
		colorLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		colorLabel.setOpaque(true);
		colorLabel.setBackground(color);
		toolBar.add(colorLabel);
		
		drawTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				p1=e.getPoint();
				g2d.fillOval(p1.x-weight/2, p1.y-weight/2, weight, weight);
				drawTable.getGraphics().drawImage(image, 0, 0, 500, 320, null);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				p1 = e.getPoint();
				weightBM = weightGroup.getSelection();
				if(weightBM.equals(weightLarge.getModel())){
					weight = 13;
				} else if(weightBM.equals(weightMedium.getModel())){
					weight = 8;
				} else if(weightBM.equals(weightSmall.getModel())){
					weight = 3;
				}
				if(flag.equals("形状")){
					shapeBM = shapeGroup.getSelection();
				}
				drawTable.getGraphics().drawImage(image, 0, 0, 500, 320, null);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
//System.out.println(color);
				if(flag.equals("形状")){
					p2 = e.getPoint();
					g2d.setColor(color);
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2d.setStroke(new BasicStroke(weight));
					
					if(shapeBM.equals(shapeLine.getModel())){
						g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
					} else if(shapeBM.equals(shapeRect.getModel())){
//System.out.println(p2);
						g2d.drawRect(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x-p1.x), Math.abs(p2.y-p1.y));
					} else if(shapeBM.equals(shapeOval.getModel())){
						g2d.drawOval(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x-p1.x), Math.abs(p2.y-p1.y));
					} else if(shapeBM.equals(shapefillRect.getModel())){
						g2d.fillRect(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x-p1.x), Math.abs(p2.y-p1.y));
					} else if(shapeBM.equals(shapefillOval.getModel())){
						g2d.fillOval(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x-p1.x), Math.abs(p2.y-p1.y));
					}
				}
				drawTable.getGraphics().drawImage(image, 0, 0, 500, 320, null);
			}
		});

		drawTable.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO 自动生成的方法存根
				p2 = e.getPoint();
				g2dTemp = (Graphics2D) drawTable.getGraphics();
				g2d.setColor(color);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setStroke(new BasicStroke(weight));
				
				if(flag.equals("画笔")){
					g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
					p1 = p2;
//System.out.println(p1.x+"\t"+p1.y+"\t"+p2.x+"\t"+p2.y);
				} else if(flag.equals("橡皮")){
					g2d.setColor(Color.WHITE);
					g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
					p1 = p2;
				} else if(flag.equals("形状")){
					g2dTemp.setColor(color);
					g2dTemp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2dTemp.setStroke(new BasicStroke(weight));
//System.out.println(shapeBM.toString());
					if(shapeBM.equals(shapeLine.getModel())){
						g2dTemp.drawLine(p1.x, p1.y, p2.x, p2.y);
						run();
					} else if(shapeBM.equals(shapeRect.getModel())){
						g2dTemp.drawRect(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x-p1.x), Math.abs(p2.y-p1.y));
						run();
					} else if(shapeBM.equals(shapeOval.getModel())){
						g2dTemp.drawOval(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x-p1.x), Math.abs(p2.y-p1.y));
						run();
					} else if(shapeBM.equals(shapefillRect.getModel())){
						g2dTemp.fillRect(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x-p1.x), Math.abs(p2.y-p1.y));
						run();
					} else if(shapeBM.equals(shapefillOval.getModel())){
						g2dTemp.fillOval(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x-p1.x), Math.abs(p2.y-p1.y));
						run();
					} 
				}
				g2dTemp.drawImage(image, 0, 0, 500, 320, null);
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO 自动生成的方法存根
				drawTable.getGraphics().drawImage(image, 0, 0, 500, 320, null);
			}
		});
		
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			thr.sleep(150);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
