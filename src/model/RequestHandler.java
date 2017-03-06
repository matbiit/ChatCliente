package model;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

class RequestHandler implements Runnable {

	private Socket cliente;
	private Parser parser;
	
	public RequestHandler(Socket cliente) {
		this.cliente = cliente;
	this.parser = new Parser();
	}

	public void run() {
		try(Scanner s = new Scanner(this.cliente.getInputStream())) {
			while (s.hasNextLine()) {
				RequestProtocol request = parser.parseToRequest(s.nextLine());
				this.checkCommand(request);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkCommand(RequestProtocol request) {
		
		ResponseHandler response = new ResponseHandler();
		
//		switch (request.getCmd().toLowerCase()) {
//		case "login":
//			if(!this.servidor.clientes.containsKey(request.getId()))
//				this.servidor.clientes.put(request.getId(), this.cliente);
//			servidor.enviaMensagemAoCliente(cliente, 
//					response.doLogin(request.getId(), servidor.usersLoggedIn()));
//			break;
//		case "enviar":
//			if(request.getDst().equals(servidor.ID))
//				servidor.doKnocKnoc(cliente, request.getData());
//			else {
//				Socket dest = servidor.clientes.get(request.getDst());
//				if(dest != null){
//					servidor.enviaMensagemAoCliente(dest, request.getData());					
//				}
//				else {
//					StoredMessage message = new StoredMessage(request);
//					servidor.registerMessage(message);
//				}
//				servidor.enviaMensagemAoCliente(cliente, response.sendFeedback());
//			}
//			break;
//		case "receber":
//			ArrayList<StoredMessage> userMessages = (ArrayList<StoredMessage>) servidor.getUserMessages(request.getId());
//			servidor.enviaMensagemAoCliente(cliente, response.messageFeedback(userMessages));
//			break;
//		default:
//			break;
//		}		
	}
}