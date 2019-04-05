
public class SignalingThread extends Thread {
	
	private char aOrB; 
	private static Semaforo s = new Semaforo(0);
	Signaling signaling;
	
	public SignalingThread(char c, Signaling pSignaling) {
		aOrB = c;
		signaling = pSignaling;
		signaling.setSemaforo(s);
	}
	
	public void run(){
		if(aOrB == 'A') {
			for (int i = 0; i < 1; i++) {	
				signaling.b();
				
			}
		} else if (aOrB == 'B'){
			for (int i = 0; i < 1; i++) {
				signaling.a();
				
			}
		}
	}

}
