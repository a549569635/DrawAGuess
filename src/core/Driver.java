package core;

import frame.LinkFrame;
import frame.LogFrame;
import frame.RegisterFrame;

public class Driver {
	private static LogFrame Log;
	private static RegisterFrame Register;
	private static LinkFrame Link;
	
	public static void main(String[] args){
		Link = new LinkFrame();
		Link.setVisible(true);
	}
	
	public static void LinkLog(){
		Link.setVisible(false);
		Link = null;
		Log = new LogFrame("");
		Log.setVisible(true);
	}
	
	public static void LogHall(String ID, String Password){
		Log.setVisible(false);
		Log = null;
		
	}
	
	public static void LogReg(String ID){
		Log.setVisible(false);
		Log = null;
		Register = new RegisterFrame(ID);
		Register.setVisible(true);
	}
	
	public static void RegLog(String ID,String Password){
		Register.setVisible(false);
		Register = null;
		Log = new LogFrame(ID);
		Log.setVisible(true);
	}

}
