package warpper;

import java.io.Serializable;

public class Protocol implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6228201989769940782L;
	private int pro;
	private Object obj;
	
	public Protocol() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public Protocol(int pro,Object obj) {
		super();
		this.pro = pro;
		this.obj = obj;
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public int getPro() {
		return pro;
	}
	public void setPro(int pro) {
		this.pro = pro;
	}
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
