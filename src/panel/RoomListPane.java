package panel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import core.Driver;
import warpper.Room;

public class RoomListPane extends JScrollPane implements Runnable{
	private HashMap<Integer,Room> ONLINE_ROOM;
	private JPanel listPane;
	private HashMap<Integer,RoomPane> roomPane = new HashMap<Integer,RoomPane>();
	private HashMap<RoomPane,Integer> inroomPane = new HashMap<RoomPane,Integer>();
	private int selectedPane = -1;
	public Room selectedRoom = null;
	private MouseAdapter MA;
	public Boolean run = true;
	
	public RoomListPane(){
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//setLayout(null);
		setOpaque(false);
		getViewport().setOpaque(false);
		
		listPane = new JPanel();
		listPane.setOpaque(false);
		listPane.setLayout(null);
		setViewportView(listPane);
		
		MA = new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				//for(RoomPane roomP : roomPane.values()){
				//RoomPane roomP = roomPane.get(inroomPane.get(e.getSource()));
					//if(e.getSource().equals(roomP)){
				RoomPane roomP = (RoomPane) e.getSource();
				if(roomP.isEnabled()){
					roomP.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					if(selectedPane != -1){
						roomPane.get(selectedPane).setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					}
					selectedPane = inroomPane.get(roomP);
					selectedRoom = roomP.room;
				}		
					//}
				//}
				Driver.hallframe.joinButton.setEnabled(selectedRoom != null);
			}
		};
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(run){
			if(Driver.ONLINE_ROOM.size() > 0 && !Driver.ONLINE_ROOM.equals(this.ONLINE_ROOM)){
				this.ONLINE_ROOM = Driver.ONLINE_ROOM;
//System.out.println("kaishigenxinfangjianliebaio");
				Room pastSelect = selectedRoom;
				selectedRoom = null;
				roomPane.clear();
				listPane.setPreferredSize(new Dimension(580,((int)((ONLINE_ROOM.size()+3)/2))*190));
				int i = 0;
				for(Room room : ONLINE_ROOM.values()){
//System.out.println("开始绘制房间面板");
					RoomPane roomP = new RoomPane(room);
					roomP.setBounds(290*(i%2)+5, ((int)(i/2))*190+5, 280, 180);
					roomPane.put(i,roomP);
					inroomPane.put(roomP, i);
					if(pastSelect != null && room == pastSelect && roomP.isEnabled()){
						roomP.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						selectedPane = i;
						selectedRoom = roomP.room;
					}
					i++;
					listPane.add(roomP);
					roomP.addMouseListener(MA);
//System.out.println("房间面板绘制一个");
				}
				super.repaint();
			}

			Driver.hallframe.joinButton.setEnabled(selectedRoom != null);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
