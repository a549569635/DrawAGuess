package core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import frame.*;
import warpper.*;

public class ClientSocketRunnable implements Runnable {
	private static Socket socket;// �����׽���
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
				// ������Ϣ
				Protocol data = (Protocol) objIn.readObject();
				if (data == null) {
					continue;
				}
				// ͨ��Э���ж��յ�����ʲô����
				//if (data.getPro() == 1) {
                   // HallFrame Hall = new HallFrame();
                    //Hall.setVisible(true);
				//}
				// if (data.getPro() == 3) {// Э��3.��ȡ�����б���Ϣ
					// ������յ������û��б�
					//ServerCore.ONLINE_USER = (List<User>) data.getObj();
				//} else if (data.getPro() == 4) { // Э��4.��ȡ��Ϸ������Ϣ
					// ��Ϸ������Ϣ��ʾ����
					//List<Room> list = (List<Room>) data.getObj();
					//ServerCore.CLIENT_ONLINE_Room = Room.copyList(list);
					//if (ServerCore.ROOM != null) { // ����з�����Ϣ������ȡ���µ�
						//for (Room room : ServerCore.CLIENT_ONLINE_Room) {
							//if (room.getRoomNo() == ServerCore.ROOM.getRoomNo()) {
								//ServerCore.ROOM = room;
							//}
						//}
					//}

				//} else if (data.getPro() == 5) {
					// ���ͻ����յ�����Ϣ����� ��δ����Ϣ�б���
					//QQMsg msg = (QQMsg) data.getObj();

					//ServerCore.MSG_LIST_CLIENT.add(msg);

				//}else if(data.getPro()==10){  //Э��10.������Ϸ��սʵ���
					//VSGameData VSdata=(VSGameData) data.getObj();  
					//ServerCore.VS_DATA=(VSGameData) VSdata.clone();//���Ƹ��������е�ʵ���													
				//}else if(data.getPro()==8){      //������Ϸ��սʵ��� GAMEDATA
					//GameData gamedata=(GameData) data.getObj(); //��ȡ���ݰ�					
					//ServerCore.CLIENT_GAME_DATA=(GameData)gamedata.clone();//����Ϸ���ݰ����Ʒ����ͻ���
					//System.out.println("����"+gamedata.getLife()+"����"+gamedata.getScore());
				//} 
				else if(data.getPro()==15){  //Э��15.�رշ����� 
					this.socket=null;
				}
				//else if (data.getPro() == 20) {
					//JOptionPane.showMessageDialog(null, "�û���������������µ�½");
					//LogFrame logFrame = new LogFrame();
					//logFrame.setVisible(true);
				//} else if (data.getPro()==25) {
				    //JOptionPane.showMessageDialog(null, "�û��Ѵ��ڣ�������˺ŵ�½");
					//LogFrame logFrame = new LogFrame();
					//logFrame.setVisible(true);
				//}
			    //else if(data.getPro()==18){
					//ServerCore.DELETEUSER.add(data.getObj().toString());
					//JOptionPane.showMessageDialog(null, "���Ѿ����������߳�");
					//socket.close();
					//isStart=false;
				//}else if(data.getPro()==19){
					//ServerCore.FORBINUSER.add(data.getObj().toString());
					//JOptionPane.showMessageDialog(null, "���Ѿ�������");
				//}
			    

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}

	}

	/**
	 * @param data���ݰ�Э��
	 */
	public void SendMsg(Protocol data) {
		try {
			objOut.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
