package model;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import view.Login;
import view.Tela;

class RequestHandler implements Runnable {

	private Socket servidor;
	private Parser parser;
	
	public RequestHandler(Socket servidor) {
		this.servidor = servidor;
	this.parser = new Parser();
	}

	public void run() {
		try(Scanner s = new Scanner(this.servidor.getInputStream())) {
			while (s.hasNextLine()) {
				String requestLine = s.nextLine();
				System.out.println(requestLine);
				RequestProtocol request = parser.parseToRequest(requestLine);
				this.checkRequest(request);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkRequest(RequestProtocol request) {
		if(request.getUserLoggedIn() != null)
			if(request.getUserLoggedIn().size() > 1){
				for (String user : request.getUserLoggedIn()) {
					if(!user.equalsIgnoreCase(Login.getInstance().getUser()))
						Tela.getInstance().addUser(user);
				}
			}
		if(request.getData() != null){
			Tela.getInstance().receiveMessage(request.getData().getData());
		}
			
	}
}