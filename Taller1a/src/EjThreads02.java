public class EjThreads02 implements Runnable {
	
	public void run() {
		System.out.println("Implementando la interfaz Runnable.");
	}
	
	public static void main(String[] args) {
		Thread t = new Thread(new EjThreads02());
		t.start();
	}
}