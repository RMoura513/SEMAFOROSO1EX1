package view;

import java.util.concurrent.Semaphore;

import controller.transacao;

public class Main {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for (int id = 1; id < 22; id++) {
			transacao to = new transacao(id, semaforo);
			to.start();
		}
		
	}
}