package controller;

import java.util.concurrent.Semaphore;

public class transacao extends Thread {
	private int id;
	private int execucoes;
	private Semaphore semaforo;
	private int tempoP;
	private int tempoT;

	public transacao(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		switch (id % 3) {
		case 0:
			this.execucoes = 3;
			this.tempoP = (int) ((Math.random() * 1001) + 1000);
			this.tempoT = 1500;
			break;
		case 1:
			this.execucoes = 2;
			this.tempoP = (int) ((Math.random() * 801) + 200);
			this.tempoT = 1000;
			break;
		case 2:
			this.execucoes = 3;
			this.tempoP = (int) ((Math.random() * 1001) + 500);
			this.tempoT = 1500;
			break;
		}
		
		for (int c = 0; c < execucoes; c++) {
			calc();
			
			try {
				semaforo.acquire();
				transacaoBD();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			
		}
	}

	private void calc() {
		System.out.println("Thread #" + id + " calculando...");
		
		try {
			sleep(tempoP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void transacaoBD() {
		System.out.println("Thread #" + id + " Transação BD...");
		
		try {
			sleep(tempoT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}