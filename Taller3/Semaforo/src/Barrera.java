
public class Barrera {
	private Semaforo s1;
	private Semaforo s2;
	
	private static int compartida = 0;
	
	public void a() {

		compartida+=100;
		s1.v();
	}

	public void b() {
		compartida+=10;
		s2.v();
	}

	public void c() {
		s1.p();
		s2.p();
		compartida+=1000;

	}
	
	public void setSemaforo(Semaforo pS1, Semaforo pS2 ) {
		s1 = pS1;
		s2 = pS2;
	}
	
	
	public static void main(String[] args) {

		RendezVous ren = new RendezVous();

		RendezVousThread a = new RendezVousThread('A', ren);
		RendezVousThread b = new RendezVousThread('B', ren);
		RendezVousThread c = new RendezVousThread('C', ren);

		int orden = (int) (Math.random() * 100) % 2;
		if (orden == 0) {
			System.out.println("Inicia a");
			a.start();
			b.start();
			c.start();
		} else {
			System.out.println("Inicia c");
			c.start();
			b.start();
			a.start();
		}


	}
}
