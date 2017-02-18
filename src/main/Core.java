package main;

import model.Cliente;

public class Core {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		cliente.iniciarNoIPePorta("127.0.0.1", "777");
		
	}
}
