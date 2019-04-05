public class EjThreads02d implements Runnable {
	private String name;
	public EjThreads02d(String name) {
		System.out.println("Implementando la interfaz Runnable.");
		this.name = name;
	}
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(name + " " + "valor: " + i);
				Thread.sleep(50);
			}
		}
		catch (Exception e) {}
		System.out.println("Saliendo: " + name);
	}
	public static void main(String[] args) {
		Thread t0 = new Thread(new EjThreads02d("Thread"+0));
		Thread t1 = new Thread(new EjThreads02d("Thread"+1));
		Thread t2 = new Thread(new EjThreads02d("Thread"+2));
		t0.start();
		t1.start();
		t2.start();
	}
}