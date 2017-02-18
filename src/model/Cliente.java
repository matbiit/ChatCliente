package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
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
					System.out.println("Erro ao ler mensagens do cliente");
					e.printStackTrace();
				}
	    	}
		 }).start();
		
		 Scanner teclado = new Scanner(System.in);
	     PrintStream saida = new PrintStream(cliente.getOutputStream());
	     while (teclado.hasNextLine()) {
	       saida.println(teclado.nextLine());
	     }
	     
	     saida.close();
	     teclado.close();
		 cliente.close();    
	} catch (NumberFormatException | IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
   }
}
