public class EjercicioT1b implements Runnable{
	
	private int paridad;
	private int sleep;
	
	public EjercicioT1b(int pParidad, int pSleep) {
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
		Thread t1 = new Thread(new EjercicioT1b(0, 100)); //Pares
		Thread t2 = new Thread(new EjercicioT1b(1, 100)); //Impares		
		
		t1.start();
		t2.start();
	
	}
}
