package model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
   
   
   public void iniciarNoIPePorta(String ip, String porta) {
    Socket cliente;
	try {
		cliente = new Socket(ip, Integer.parseInt(porta));
		System.out.println("Conectado ao servidor");
		 
		new Thread(new Runnable() {
					
			@Override
			public void run() {
				Scanner s;
				try {
					s = new Scanner(cliente.getInputStream());
					while (s.hasNextLine()) {
						System.out.println(s.nextLine());
		    		}
		    		s.close();
		    	} catch (IOException e) {
					System.out.println("Erro ao ler mensagens do servidor");
					e.printStackTrace();
				}
	    	}
		 }).start();
		
//		 Scanner clienteInput = new Scanner(System.in);
//	     PrintStream psCliente = new PrintStream(cliente.getOutputStream());
//	     while (clienteInput.hasNextLine()) {
//	    	 psCliente.println(clienteInput.nextLine());
//	     }
//	     
//	     psCliente.close();
//	     clienteInput.close();
//		 cliente.close();    
	} catch (NumberFormatException | IOException e1) {
		e1.printStackTrace();
	}
   }
}
