package model;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import controller.Core;
//import view.Login;
//import view.PopUpMessage;
import view.Tela;

class RequestHandler implements Runnable {

	private Socket servidor;
	private Parser parser;
//	private int count = 0;
	
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
				Core.getInstance().getCliente().setMsgNr(request.getMsgNr());
				this.checkRequest(request);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkRequest(RequestProtocol request) {
//		if(request.getUserLoggedIn() != null)
//			if(request.getUserLoggedIn().size() > 1){
//				for (String user : request.getUserLoggedIn()) {
//					if(!Tela.getInstance().getUsers().contains(user))
//						if(!user.equalsIgnoreCase(Login.getInstance().getUser())){
//							Tela.getInstance().addUser(user);
//							if(count > 0){
//								new Thread(new Runnable() {
//									
//									@Override
//									public void run() {
//										System.out.println(user);
//										new PopUpMessage(user);
//									}
//								}).start();
//							}
//							
//						}
//				}
//			}
//		count++;
		if(Cliente.notFirstTime)
			if(request.getData() != null)
				if(request.getData().size() > 0)
					Tela.getInstance().receiveMessage(request.getData());
			
	}
}