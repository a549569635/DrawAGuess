package label;

import javax.swing.JLabel;

import warpper.User;

public class UserLabel extends JLabel {
	private JLabel readyLabel;
	private JLabel hpLabel;
	private JLabel nickLabel;
	private JLabel levelLabel;
	

	public UserLabel(User us) {
		// TODO �Զ����ɵĹ��캯�����
		setLayout(null);
		this.setOpaque(false);
		
		readyLabel = new JLabel();
		readyLabel.setOpaque(false);
		readyLabel.setBounds(20, 20, 80, 80);
		add(readyLabel);
		
	}

}
