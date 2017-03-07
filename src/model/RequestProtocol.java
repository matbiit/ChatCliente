package model;

import java.util.Set;

public class RequestProtocol {
	
	private String id;
	private String msgNr;
	private String dst;
	private DataProtocol data;
	private Set<String> userLoggedIn;
	
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
	public DataProtocol getData() {
		return data;
	}
	public void setData(DataProtocol data) {
		this.data = data;
	}
	public Set<String> getUserLoggedIn() {
		return userLoggedIn;
	}
	public void setUserLoggedIn(Set<String> userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}
	
}
