package core;

import frame.GameFrame;
import frame.HallFrame;

public class Test {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		GameFrame g = new GameFrame();
		g.setVisible(true);
		
		HallFrame h = new HallFrame();
		h.setVisible(true);
System.out.println(Driver.class.getResource("/text/Agreement.txt").toString());
System.out.println(Driver.class.getResource("/text/Agreement.txt").getFile());
System.out.println(Driver.class.getResource("/text/Agreement.txt").getPath());
	}

}
