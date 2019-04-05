
public class RendezVousThread extends Thread{
	
	private static Semaforo s1 = new Semaforo(0);
	private static Semaforo s2 = new Semaforo(0);
	private char aOrB; 
	private RendezVous r;
	
	public RendezVousThread(char c, RendezVous pRendezVous) {
		aOrB = c;
		r = pRendezVous;
		r.setSemaforo(s1, s2);
	}
	
	public void run(){
		if(aOrB == 'A') {
			for (int i = 0; i < 1; i++) {	
				r.a1();
				r.a2();
				
			}
		} else if (aOrB == 'B'){
			for (int i = 0; i < 1; i++) {
				r.b2();
				r.b1();
								
			}
		}
	}

}
