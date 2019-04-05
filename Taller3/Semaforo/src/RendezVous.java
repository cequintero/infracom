
public class RendezVous {
	private Semaforo s1;
	private Semaforo s2;

	private static int compartida = 0;

	public void a1() {

		compartida+=100;
		s1.v();
	}

	public void b1() {
		compartida+=10;
	}

	public void a2() {
		s2.p();
		compartida+=1000;

	}

	public void b2() {
		s1.p();
		compartida+=200;
		s2.v();
	}


	public void setSemaforo(Semaforo pS1, Semaforo pS2 ) {
		s1 = pS1;
		s2 = pS2;
	}


	public static void main(String[] args) {

		RendezVous ren = new RendezVous();

		RendezVousThread a = new RendezVousThread('A', ren);
		RendezVousThread b = new RendezVousThread('B', ren);

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
