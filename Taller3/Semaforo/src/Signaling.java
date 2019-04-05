
public class Signaling {
	
	private char aOrB; 
	private static int compartida = 0;
	private Semaforo s;
	
	public void a() {

		compartida+=100;
		s.v();
	}

	public void b() {
		s.p();
		compartida+=10;
	}
	
	public void run(){
		if(aOrB == 'A') {
			for (int i = 0; i < 1; i++) {	
				b();
				esperaAleatoria();
			}
		} else if (aOrB == 'B'){
			for (int i = 0; i < 1; i++) {
				a();
				esperaAleatoria();
			}
		}
	}
	
	private void esperaAleatoria() {
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setSemaforo(Semaforo pS) {
		s = pS;
	}
	
	public static void main(String[] args) {
		
		Signaling sig = new Signaling();
		
		SignalingThread a = new SignalingThread('A', sig);
		SignalingThread b = new SignalingThread('B', sig);
		
		int orden = (int) (Math.random() * 100) % 2;
		if (orden == 0) {
			System.out.println("Inicia a");
			a.start();
			b.start();
		} else {
			System.out.println("Inicia b");
			b.start();
			a.start();
		}


	}

	
}
