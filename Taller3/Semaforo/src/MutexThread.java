
public class MutexThread extends Thread{

	private char aOrB; 
	private static int compartida = 0;
	private int a;
	private int b;
	private static Mutex m;
	private static Semaforo s = new Semaforo(0);


	public MutexThread(char c) {
		aOrB = c;
	}

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


	public static void main(String[] args) {
		MutexThread a = new MutexThread('A');
		MutexThread b = new MutexThread('B');
		
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
