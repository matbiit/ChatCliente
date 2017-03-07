package model;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Parser {
	
	private Gson jsonParser;
	
	public Parser(){
		this.jsonParser = new Gson();
	}
	
	public RequestProtocol parseToRequest(String rawMessage) {
		return this.jsonParser.fromJson(rawMessage, RequestProtocol.class);
	}
	
	public String parseResponseToJson(ResponseProtocol response) {
		return this.jsonParser.toJson(response);
	}
}
