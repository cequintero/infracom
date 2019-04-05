
public class EjThreads01a extends Thread{
	
	private int n;
	
	public EjThreads01a(int n) {
		System.out.println("Extendiendo la clase Thread.");
		this.n = n;
	}
	
	public void run() {
		System.out.println("El valor inicial es: " + n);
	}
	
	public static void main(String[] args) {
		EjThreads01a t = new EjThreads01a(5);
		t.start();
	}

}
