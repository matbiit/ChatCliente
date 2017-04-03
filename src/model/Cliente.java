package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.Timer;

public class Cliente {
   
	private Socket servidor; 
	private int msgNr;
	private Timer timer;
    public static boolean notFirstTime = false;	

   public Cliente(String id){
	   timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 ResponseHandler response = new ResponseHandler();
				
				 enviaMensagemAoServidor(response.receberMensagens(id));
				 notFirstTime = true;
				 
			}
		});
   }
   public void iniciarNoIPePorta(String user, String ip, String porta) {
	try {
		servidor = new Socket(ip, Integer.parseInt(porta));
		System.out.println("Conectado ao servidor");

		ResponseHandler response = new ResponseHandler();
		
		this.enviaMensagemAoServidor(response.doLogin(user));
		 
		RequestHandler tc = new RequestHandler(servidor);
		new Thread(tc).start();
		
		 timer.start();
	} catch (NumberFormatException | IOException e1) {
		e1.printStackTrace();
	}
   }
   
   public void enviarMensagemPara(String message, String userTo, String userFrom){
	   ResponseHandler response = new ResponseHandler();
		
	   this.enviaMensagemAoServidor(response.enviarMensagem(msgNr, message, userTo, userFrom));
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

public int getmMsgNr() {
	return msgNr;
}

public void setMsgNr(int msgNr) {
	this.msgNr = ++msgNr;
}
}
