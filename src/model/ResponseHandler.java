package model;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

public class ResponseHandler {
	
	private Parser parser;
	
	public ResponseHandler(){
		this.parser = new Parser();
	}

	public String doLogin(String id, Set<String> users) {
		// TODO verifica se há mensagens pelo id;
		ResponseProtocol response = new ResponseProtocol();
		response.setId("0");
		response.setUsersLoggedIn(users);
		return this.parser.parseResponseToJson(response);
	}

	public String sendFeedback() {
		ResponseProtocol response = new ResponseProtocol();
		response.setId("0");
		return this.parser.parseResponseToJson(response);
	}
	
	public String messageFeedback(ArrayList<StoredMessage> userMessages){
		ResponseProtocol response = new ResponseProtocol();
		response.setId("0");
		response.setData(this.parser.parseMessageToJson(userMessages));
		return this.parser.parseResponseToJson(response);
	}
	
}
