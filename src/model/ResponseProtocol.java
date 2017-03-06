package model;

import java.util.Set;
import java.util.UUID;

public class ResponseProtocol {
	
	private String id;
	private String msgNr;
	private String[] data;
	private Set<String> userLoggedIn;
	
	public ResponseProtocol(){
		this.msgNr = UUID.randomUUID().toString();
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
	public String[] getData() {
		return data;
	}
	public void setData(String... data) {
		this.data = data;
	}

	public Set<String> getUserLoggedIn() {
		return userLoggedIn;
	}

	public void setUsersLoggedIn(Set<String> set) {
		this.userLoggedIn = set;
	}
}
