package model;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestProtocol {
	
	private String id;
	private int msgNr;
	private String dst;
	private ArrayList<HashMap<String, String>> data;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMsgNr() {
		return msgNr;
	}
	public void setMsgNr(int msgNr) {
		this.msgNr = msgNr;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public ArrayList<HashMap<String, String>> getData() {
		return data;
	}
	public void setData(ArrayList<HashMap<String, String>> data) {
		this.data = data;
	}
	
}
