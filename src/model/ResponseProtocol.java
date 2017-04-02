package model;

import java.util.UUID;

public class ResponseProtocol {
	
	private String cmd;
	private String id;
	private int msgNr;
	private String dst;
	private String data;
	
	public ResponseProtocol(){
		this.msgNr++;
	}
	
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

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

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
