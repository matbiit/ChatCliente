package model;

import java.util.UUID;

public class ResponseProtocol {
	
	private String cmd;
	private String id;
	private String msgNr;
	private String dst;
	private String data;
	
	public ResponseProtocol(){
		this.msgNr = UUID.randomUUID().toString();
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
	public String getMsgNr() {
		return msgNr;
	}
	public void setMsgNr(String msgNr) {
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
