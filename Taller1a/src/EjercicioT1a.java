public class EjercicioT1a extends Thread{
	
	private int paridad;
	private int sleep;
	
	public EjercicioT1a(int pParidad, int pSleep) {
		paridad = pParidad;
		sleep = pSleep;
	}
	
	public void run() {
		for (int i = 1; i < 21; i++) {
			if(i % 2 == paridad) {
				System.out.println(i);
			}
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		EjercicioT1a t1 = new EjercicioT1a(0, 100); //Pares
		EjercicioT1a t2 = new EjercicioT1a(1, 100); //Impares
		
		t1.start();
		t2.start();
	
	}
}
