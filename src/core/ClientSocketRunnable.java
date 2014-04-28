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
	private static Socket socket;// 服务套接字
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
				// 接收消息
				Protocol data = (Protocol) objIn.readObject();
				if (data == null) {
					continue;
				}
				// 通过协议判断收到的是什么数据
				//if (data.getPro() == 1) {
                   // HallFrame Hall = new HallFrame();
                    //Hall.setVisible(true);
				//}
				// if (data.getPro() == 3) {// 协议3.获取在线列表信息
					// 如果是收到在线用户列表
					//ServerCore.ONLINE_USER = (List<User>) data.getObj();
				//} else if (data.getPro() == 4) { // 协议4.获取游戏大厅信息
					// 游戏大厅信息显示出来
					//List<Room> list = (List<Room>) data.getObj();
					//ServerCore.CLIENT_ONLINE_Room = Room.copyList(list);
					//if (ServerCore.ROOM != null) { // 如果有房间信息遍历获取最新的
						//for (Room room : ServerCore.CLIENT_ONLINE_Room) {
							//if (room.getRoomNo() == ServerCore.ROOM.getRoomNo()) {
								//ServerCore.ROOM = room;
							//}
						//}
					//}

				//} else if (data.getPro() == 5) {
					// 将客户端收到的消息，存放 到未读消息列表中
					//QQMsg msg = (QQMsg) data.getObj();

					//ServerCore.MSG_LIST_CLIENT.add(msg);

				//}else if(data.getPro()==10){  //协议10.接收游戏对战实体包
					//VSGameData VSdata=(VSGameData) data.getObj();  
					//ServerCore.VS_DATA=(VSGameData) VSdata.clone();//复制给核心类中的实体包													
				//}else if(data.getPro()==8){      //接收游戏对战实体包 GAMEDATA
					//GameData gamedata=(GameData) data.getObj(); //获取数据包					
					//ServerCore.CLIENT_GAME_DATA=(GameData)gamedata.clone();//把游戏数据包复制发给客户端
					//System.out.println("生命"+gamedata.getLife()+"分数"+gamedata.getScore());
				//} 
				else if(data.getPro()==15){  //协议15.关闭服务器 
					this.socket=null;
				}
				//else if (data.getPro() == 20) {
					//JOptionPane.showMessageDialog(null, "用户名密码错误！请重新登陆");
					//LogFrame logFrame = new LogFrame();
					//logFrame.setVisible(true);
				//} else if (data.getPro()==25) {
				    //JOptionPane.showMessageDialog(null, "用户已存在！请更换账号登陆");
					//LogFrame logFrame = new LogFrame();
					//logFrame.setVisible(true);
				//}
			    //else if(data.getPro()==18){
					//ServerCore.DELETEUSER.add(data.getObj().toString());
					//JOptionPane.showMessageDialog(null, "你已经被服务器踢出");
					//socket.close();
					//isStart=false;
				//}else if(data.getPro()==19){
					//ServerCore.FORBINUSER.add(data.getObj().toString());
					//JOptionPane.showMessageDialog(null, "你已经被禁言");
				//}
			    

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}

	}

	/**
	 * @param data数据包协议
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
