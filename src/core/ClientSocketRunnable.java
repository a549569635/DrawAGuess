package core;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import frame.*;
import warpper.*;

public class ClientSocketRunnable implements Runnable {
	private static Socket socket;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private Thread thread;
	private String str;
	public static volatile boolean isStart=true;
	public static Socket getSocket() {
		return socket;
	}

	public static void setSocket(Socket socket) {
		ClientSocketRunnable.socket = socket;
	}

	public ClientSocketRunnable(Socket socket) {
		try {
			this.socket = socket;
			objOut = Driver.out;
			//objIn = new ObjectInputStream(socket.getInputStream());
			objIn = Driver.in;
//System.out.println("CSR启动\n");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根

		while (isStart) {

			if (socket == null) {
				JOptionPane.showMessageDialog(null, "服务端已关闭，退出程序");
			    System.exit(0);
			}
			try {
				Protocol data = (Protocol) objIn.readObject();
				if (data == null) {
					continue;
				}
				if(data.getPro() == 3){
					Driver.ONLINE_USER = (ArrayList<User>) data.getObj();
				} else if(data.getPro() == 4){
					Driver.ONLINE_ROOM = (HashMap<Integer, Room>) data.getObj();
System.out.println("update Driver.ONLION_ROOM");
					if(Driver.SELF_ROOM != null){
						Driver.SELF_ROOM = Driver.ONLINE_ROOM.get(Driver.SELF_ROOM.getID());
					}
				} else if(data.getPro() == 5){
					
				} else if(data.getPro() == 6){
					Room room = (Room) data.getObj();
					Driver.hallframe.joinReady(room);
				} else if(data.getPro() == 7){
					Message msg = (Message) data.getObj();
					if(msg.from.equals(Driver.SELF.getID())){
						Driver.hallframe.msgArea.append("\n[您]对["+msg.to+"]说："+msg.body);
					} else if(msg.to.equals(Driver.SELF.getID())){
						Driver.hallframe.msgArea.append("\n["+msg.from+"]对[您]说："+msg.body);
						Object obj = msg.from;
						Driver.hallframe.addAim(obj);
					} else{
						Driver.hallframe.msgArea.append("\n["+msg.from+"]说："+msg.body);
					}
					Driver.hallframe.msgArea.setCaretPosition(Driver.hallframe.msgArea.getText().length());
				} else if(data.getPro() == 8){
					
				} else if(data.getPro() == 9){
					
				} else if(data.getPro() == 10){
					
				}
				
				else if(data.getPro()==15){ 
					this.socket=null;
					JOptionPane.showMessageDialog(null, "抱歉，服务器已关闭，即将退出程序");
					System.exit(0);
				} else if(data.getPro() == 22){
					JOptionPane.showMessageDialog(null, "抱歉，进入房间失败");
				} else if(data.getPro() == 23){
					JOptionPane.showMessageDialog(null, "抱歉，创建房间失败");
				} else if(data.getPro() == 24){
					JOptionPane.showMessageDialog(null, "抱歉，消息发送失败");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "抱歉，出现了一些问题，即将退出程序");
				System.exit(0);
				break;
			}
		}

	}

	public void SendMsg(Protocol data) {
		try {
			objOut.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
