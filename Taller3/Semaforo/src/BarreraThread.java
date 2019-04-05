
public class BarreraThread extends Thread{
	
	private static Semaforo s1 = new Semaforo(0);
	private static Semaforo s2 = new Semaforo(0);
	private char aOrB; 
	private Barrera b;
	
	public BarreraThread(char c, Barrera barrera) {
		aOrB = c;
		b = barrera;
		b.setSemaforo(s1, s2);
	}
	
	
	public void run(){
		if(aOrB == 'A') {
			for (int i = 0; i < 1; i++) {	
				b.a();
				
			}
		} else if (aOrB == 'B'){
			for (int i = 0; i < 1; i++) {
				b.b();
								
			}
		} else if (aOrB == 'C'){
			for (int i = 0; i < 1; i++) {
				b.c();
								
			}
		}
	}

}
