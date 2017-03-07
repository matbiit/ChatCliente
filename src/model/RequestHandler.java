package model;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

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
				System.out.println(s.nextLine());
				RequestProtocol request = parser.parseToRequest(s.nextLine());
				this.checkRequest(request);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkRequest(RequestProtocol request) {
		
	}
}