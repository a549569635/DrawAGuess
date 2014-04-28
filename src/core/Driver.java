package core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import frame.LinkFrame;

public class Driver {
	private static LinkFrame Link;
	public static Socket soc;
	public static ClientSocketRunnable client;
	//public static BufferedOutputStream out;
	//public static BufferedInputStream in;
	public static ObjectOutputStream out;
	public static ObjectInputStream in;
	private static File drawAG = new File("D:\\DrawAndGuess");
	private static File resource = new File("D:\\DrawAndGuess\\resource");
	private static File user = new File("D:\\DrawAndGuess\\user");
	private static File temp = new File("D:\\DrawAndGuess\\resource\\temp");
	private static File newuser;
	
	public static void main(String[] args){
		Link = new LinkFrame();
		Link.setVisible(true);
	}
}

	

