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
	
	public void inicializarChat(String user, String ip, String porta){
		this.cliente = new Cliente();
		cliente.iniciarNoIPePorta(user, ip, porta);
	}
	
	public void deslogar(String id){
		cliente.deslogar(id);
	}
	
	public void enviarMensagem(String message, String userTo, String userFrom){
		cliente.enviarMensagemPara(message, userTo, userFrom);
	}
	
	
	public static Core getInstance(){
		return instance;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
