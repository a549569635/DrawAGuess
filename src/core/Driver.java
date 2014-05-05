package core;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import warpper.*;
import frame.*;

public class Driver {
	public static LinkFrame linkframe;
	public static LogFrame logframe;
	public static SignFrame signframe;
	public static HallFrame hallframe;
	public static GameFrame gameframe;
	
	public static Socket socket;
	public static ClientSocketRunnable client;
	public static ObjectOutputStream out;
	public static ObjectInputStream in;
	
	private static File drawAG = new File("D:\\DrawAndGuess");
	private static File resource = new File("D:\\DrawAndGuess\\resource");
	private static File user = new File("D:\\DrawAndGuess\\user");
	private static File temp = new File("D:\\DrawAndGuess\\resource\\temp");
	private static File newuser;
	
	public static volatile User SELF;
	
	public static volatile Room SELF_ROOM = null;
	
	public static volatile ArrayList<User> ONLINE_USER = new ArrayList<User>();

	public static volatile HashMap<String,User> CLIENT_SOCKET = new HashMap<String, User>();

	public static volatile HashMap<Integer,Room> ONLINE_ROOM = new HashMap<Integer,Room>();
	
	public static void main(String[] args){
		linkframe = new LinkFrame();
		linkframe.setVisible(true);
	}
}

	

