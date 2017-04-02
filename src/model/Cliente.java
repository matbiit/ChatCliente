package model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
   
	private Socket servidor; 
	private int ack;
	
   public void iniciarNoIPePorta(String user, String ip, String porta) {
	try {
		servidor = new Socket(ip, Integer.parseInt(porta));
		System.out.println("Conectado ao servidor");

		ResponseHandler response = new ResponseHandler();
		
		this.enviaMensagemAoServidor(response.doLogin(user));
		 
		RequestHandler tc = new RequestHandler(servidor);
		new Thread(tc).start();
		 
	} catch (NumberFormatException | IOException e1) {
		e1.printStackTrace();
	}
   }
   
   public void enviarMensagemPara(String message, String userTo, String userFrom){
	   ResponseHandler response = new ResponseHandler();
		
	   this.enviaMensagemAoServidor(response.enviarMensagem(ack, message, userTo, userFrom));
   }
   
   public void deslogar(String id){
	   ResponseHandler response = new ResponseHandler();
		
	   this.enviaMensagemAoServidor(response.doLogout(id));
   }
   
   public void enviaMensagemAoServidor(String msg){
		try {
			PrintStream ps = new PrintStream(servidor.getOutputStream());
			System.out.println(msg);
			ps.println(msg);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

public int getAck() {
	return ack;
}

public void setAck(int ack) {
	this.ack = ++ack;
}
}
