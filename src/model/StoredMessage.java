package model;

import java.sql.Timestamp;

public class StoredMessage {

	private String uid;
	private String to;
	private String from;
	private String msg;
	private Timestamp time;

	public StoredMessage(String uid, String to, String from, String msg) {
		
	}

	public StoredMessage(RequestProtocol request) {
		this.uid = request.getMsgNr();
		this.to = request.getDst();
		this.from = request.getId();
		this.msg = request.getData();
		this.time = new Timestamp(System.currentTimeMillis());
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
