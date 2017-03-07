package model;

import java.util.Set;

public class ResponseHandler {
	
	private Parser parser;
	
	public ResponseHandler(){
		this.parser = new Parser();
	}

	public String doLogin(String id) {
		ResponseProtocol response = new ResponseProtocol();
		response.setCmd("login");
		response.setId(id);
		return this.parser.parseResponseToJson(response);
	}

}
