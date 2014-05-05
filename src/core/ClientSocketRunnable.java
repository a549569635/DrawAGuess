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
//System.out.println("CSR����\n");
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO �Զ����ɵķ������

		while (isStart) {

			if (socket == null) {
				JOptionPane.showMessageDialog(null, "������ѹرգ��˳�����");
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
						Driver.hallframe.msgArea.append("\n[��]��["+msg.to+"]˵��"+msg.body);
					} else if(msg.to.equals(Driver.SELF.getID())){
						Driver.hallframe.msgArea.append("\n["+msg.from+"]��[��]˵��"+msg.body);
						Object obj = msg.from;
						Driver.hallframe.addAim(obj);
					} else{
						Driver.hallframe.msgArea.append("\n["+msg.from+"]˵��"+msg.body);
					}
					Driver.hallframe.msgArea.setCaretPosition(Driver.hallframe.msgArea.getText().length());
				} else if(data.getPro() == 8){
					
				} else if(data.getPro() == 9){
					
				} else if(data.getPro() == 10){
					
				}
				
				else if(data.getPro()==15){ 
					this.socket=null;
					JOptionPane.showMessageDialog(null, "��Ǹ���������ѹرգ������˳�����");
					System.exit(0);
				} else if(data.getPro() == 22){
					JOptionPane.showMessageDialog(null, "��Ǹ�����뷿��ʧ��");
				} else if(data.getPro() == 23){
					JOptionPane.showMessageDialog(null, "��Ǹ����������ʧ��");
				} else if(data.getPro() == 24){
					JOptionPane.showMessageDialog(null, "��Ǹ����Ϣ����ʧ��");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "��Ǹ��������һЩ���⣬�����˳�����");
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
