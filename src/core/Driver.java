package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.net.Socket;

import frame.LinkFrame;

public class Driver {
	private static LinkFrame Link;
	public static Socket soc;
	public static BufferedWriter bwer;
	public static BufferedReader brer;
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

	

