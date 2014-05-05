package panel;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import core.Driver;
import warpper.*;

public class ReadyPane extends JPanel implements Runnable{
	private Room room = null;
	private UserPane[] userPane = new UserPane[3];
	
	public ReadyPane() {
		super();
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		setOpaque(false);
		this.setVisible(false);
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			if(Driver.SELF_ROOM != null && !Driver.SELF_ROOM.equals(room)){
				room = Driver.SELF_ROOM;
				int i = 0;
System.out.println(room.getID());
				for(User user : room.user){
					if(!user.getID().equals(Driver.SELF.getID())){
						userPane[i] = new UserPane(user,1);
						userPane[i].setLocation(i*190+10, 30);
						add(userPane[i]);
						i++;
					}
				}
				for(;i<3;i++){
					userPane[i] = new UserPane(null,1);
					userPane[i].setLocation(i*190+10, 30);
					add(userPane[i]);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
