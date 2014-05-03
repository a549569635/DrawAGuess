package core;

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
				}
				
				else if(data.getPro()==15){ 
					this.socket=null;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
