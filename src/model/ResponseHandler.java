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

	public String enviarMensagem(String message, String userTo, String userFrom) {
		ResponseProtocol response = new ResponseProtocol();
		response.setCmd("enviar");
		response.setId(userFrom);
		if(userTo.equals("servidor"))
			response.setDst("0");
		else
			response.setDst(userTo);
		response.setData(message);
		return this.parser.parseResponseToJson(response);
	}

}
