public class EjThreads01 extends Thread {
	
	public void run() {
		System.out.println("Extendiendo la clase Thread.");
	}
	
	public static void main(String[] args) {
		EjThreads01 t = new EjThreads01();
		t.start();
	}
}