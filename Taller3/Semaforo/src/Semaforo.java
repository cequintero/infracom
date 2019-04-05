import java.util.Queue;

public class Semaforo {
	
	int contador;
	
	public Semaforo(int pContador) {
		contador = pContador;
		
	}
	
	public synchronized void p() {
		contador--;
		while (contador < 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public synchronized void v() {
			contador++;
			notify();
	}

}
