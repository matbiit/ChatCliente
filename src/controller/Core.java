package controller;

import model.Cliente;
import view.Login;
import view.Tela;

public class Core {

	private static Core instance = new Core();
	private Cliente cliente;
	
	public static void main(String[] args) {

		Login.getInstance();
//		new Tela();
		
	}
	
	public void inicializarChat(String ip, String porta){
		this.cliente = new Cliente();
		cliente.iniciarNoIPePorta(ip, porta);
	}
	
	
	public static Core getInstance(){
		return instance;
	}
}
