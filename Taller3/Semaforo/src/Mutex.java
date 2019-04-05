import java.util.Queue;

public class Mutex {
	
	int contador;
	
	public void Mutex() {
		contador = 1;
		
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
